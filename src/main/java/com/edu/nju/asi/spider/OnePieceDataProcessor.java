package com.edu.nju.asi.spider;

import com.edu.nju.asi.spider.Model.BaseStock;
import com.edu.nju.asi.spider.Model.Code_Name;
import com.edu.nju.asi.spider.Model.NormalStock;
import com.edu.nju.asi.spider.onePieceStockDownload.Code_NamePipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harvey on 2017/5/15.
 * 
 * 每天更新沪深板块所有股票的数据
 */
public class OnePieceDataProcessor implements PageProcessor {
    static String First_Page = "http://quotes\\.money\\.163\\.com/hs/realtimedata/service/rank.php\\?host=/hs/realtimedata/service/rank.php&page=0&query=STATS_RANK:_exists_&fields=RN,CODE,SYMBOL,NAME,PRICE,STATS_RANK,PERCENT&sort=SYMBOL&order=asc&count=25&type=query";
    static String CODE_LIST = "http://quotes\\.money\\.163\\.com/hs/.*";
    static String ALL_LIST = "http://quotes\\.money\\.163\\.com/trade/lsjysj_\\d{6}.html";
    static String NAME_LIST = "http://quotes\\.money\\.163\\.com/stocksearch/json.do\\?count=1&word=\\d{6}";

    //每页取的股票代码个数，最后一页除外
    int count = 25;

    int totalStocks = 0;

    boolean firstDone = false;

    //部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me()
            .setCharset("utf-8")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.96 Safari/537.36")
            .setTimeOut(600000)
            .setRetryTimes(5)
            .setSleepTime(100);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        //获取单只股票最新日期的数据
        if (page.getUrl().regex(ALL_LIST).match()) {
            System.out.println("enter1");
            //代码
            String code = page.getHtml().xpath("//div[@class=\"stock_info\"]//h1/span/a/text()").get();
            //日期
            String date = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr[1]/td[1]/text()").get();
            //开盘价
            String open = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr[1]/td[2]/text()").get();
            //收盘价
            String close = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr[1]/td[3]/text()").get();
            //最高价
            String high = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr[1]/td[4]/text()").get();
            //最低价
            String low = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr[1]/td[5]/text()").get();
            //涨跌额
            String fluctuation = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr[1]/td[6]/text()").get();
            //涨跌幅
            String changRate = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr[1]/td[7]/text()").get();
            //交易量
            String volume = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr[1]/td[8]/text()").get();
            //交易额
            String amount = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr[1]/td[9]/text()").get();
            //振幅
            String swingRate = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr/td[10]/text()").get();
            //换手率
            String turnOverRate = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr/td[11]/text()").get();

            System.out.println("date:"+date);

            List<NormalStock> normalStocks = new ArrayList<>();
            normalStocks.add(new NormalStock(code,date,open,close,high,low,fluctuation,
                    changRate,volume,amount,swingRate,turnOverRate));

            page.putField("normalStocks", normalStocks);

            //添加通过代码搜索股票的界面
            page.addTargetRequest("http://quotes.money.163.com/stocksearch/json.do?count=1&word="+code);
        }
        //获取对应代码的正确名称、简称、所属市场类型
        else if (page.getUrl().regex(NAME_LIST).match()){
            System.out.println("enter4");

            //代码
            String code = page.getHtml().regex("\"symbol\":\"(\\d+)").get();
            //名称
            String name = page.getHtml().regex("\"name\":\"(.*)\"").get();
            //简称
            String spell = page.getHtml().regex("\"spell\":\"(\\w+)").get();
            //市场类型
            String type = page.getHtml().regex("\"type\":\"(\\w+)").get();

            //TODO unicode 转 中文

            System.out.println("name"+name);

            page.putField("code_name", new Code_Name(code,name,spell,type));

        }
        //获取所有股票代码的页面
        else if (page.getUrl().regex(CODE_LIST).match() && firstDone){
            System.out.println("enter2");
            List<String> codes = new ArrayList<>();
            Json json = page.getJson();
            int count = Integer.parseInt(json.jsonPath("$.count").get());
            for(int j = 0; j < count; j++){
                String tempCode = json.jsonPath("$.list["+j+"].SYMBOL").get();
                //过滤掉基金
                if(!tempCode.startsWith("150") && !tempCode.startsWith("159") && !tempCode.startsWith("16") && !(tempCode.startsWith("50"))){
                    codes.add(tempCode);
                }
            }

            for(int i = 0; i < codes.size(); i++){
                System.out.println("股票代码:"+codes.get(i));
                page.addTargetRequest("http://quotes.money.163.com/trade/lsjysj_"+codes.get(i)+".html");
            }

            totalStocks += codes.size();
            System.out.println(totalStocks);
        }
        //获取总页数的页面
        else if (page.getUrl().regex(First_Page).match() && !firstDone){
            System.out.println("enter3");
            int total = Integer.parseInt(page.getJson().jsonPath("$.total").get());
            int pages = Integer.parseInt(page.getJson().jsonPath("$.pagecount").get());
            //最后一页显示的股票数
            int lastPageCount = total - (count*(pages-1));

            int pagecount = count;

            for(int i = 0; i < pages; i++){
                if(i == pages-1){
                    pagecount = lastPageCount;
                }
                page.addTargetRequest("http://quotes.money.163.com/hs/realtimedata/service/rank.php?host=/hs/realtimedata/service/rank.php&page="+i+"&query=STATS_RANK:_exists_&fields=RN,CODE,SYMBOL,NAME,PRICE,STATS_RANK,PERCENT&sort=SYMBOL&order=asc&count="+pagecount+"&type=query");
            }

            //抓取第一个页面的code，因为之后会被去重
            List<String> codes = new ArrayList<>();
            int count = Integer.parseInt(page.getJson().jsonPath("$.count").get());
            for(int j = 0; j < count; j++){
                codes.add(page.getJson().jsonPath("$.list["+j+"].SYMBOL").get());
            }

            for(int i = 0; i < codes.size(); i++){
                System.out.println("股票代码:"+codes.get(i));
                page.addTargetRequest("http://quotes.money.163.com/trade/lsjysj_"+codes.get(i)+".html#01b07");
            }

            totalStocks += codes.size();
            System.out.println(totalStocks);

            firstDone = true;
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Request request = new Request();
        request.setUrl("http://quotes.money.163.com/hs/realtimedata/service/rank.php?host=/hs/realtimedata/service/rank.php&page=0&query=STATS_RANK:_exists_&fields=RN,CODE,SYMBOL,NAME,PRICE,STATS_RANK,PERCENT&sort=SYMBOL&order=asc&count=25&type=query");
//        request.setUrl("http://quotes.money.163.com/stocksearch/json.do?count=1&word=000021");
        request.setMethod(HttpConstant.Method.GET);

        Spider.create(new OnePieceDataProcessor())
                //从“http://quotes.money.163.com/old/#query=todayRank"开始抓
                .addRequest(request)
                //可以有多个pipeline
                .addPipeline(new ConsolePipeline())
                .addPipeline(new Code_NamePipeline())
                //开启1个线程抓取
                .thread(1)
                //启动爬虫
                .run();
    }
}
