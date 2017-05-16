package com.edu.nju.asi.spider;

import com.edu.nju.asi.spider.Model.BaseStock;
import com.edu.nju.asi.spider.Model.NormalStock;
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

public class StockPageProcessor implements PageProcessor {

    static String First_Page = "http://quotes\\.money\\.163\\.com/hs/realtimedata/service/rank.php\\?host=/hs/realtimedata/service/rank.php&page=0&query=STATS_RANK:_exists_&fields=RN,CODE,SYMBOL,NAME,PRICE,STATS_RANK,PERCENT&sort=SYMBOL&order=asc&count=25&type=query";
    static String CODE_LIST = "http://quotes\\.money\\.163\\.com/hs/.*";
    static String ALL_LIST = "http://quotes\\.money\\.163\\.com/trade/lsjysj_\\d{6}.html#01b07";
    static String DETAIL = "http://quotes\\.money\\.163\\.com/trade/lsjysj_\\d{6}.html\\?year=\\d{4}&season=\\d";

    boolean firstDone = false;

    //每页取的股票代码个数，最后一页除外
    int count = 25;

    int totalStocks = 0;

    //默认开始年份
    int default_startYear = 2007;
    //默认开始季度
    int default_startSeason = 1;

    //结束年份
    int endYear = 2017;
    //结束季度
    int endSeason = 2;

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

        //单只股票单季度的页面
        if (page.getUrl().regex(DETAIL).match()) {
            //代码
            String code = page.getHtml().xpath("//div[@class=\"stock_info\"]//h1/span/a/text()").get();
            //日期
            List<String> dates = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr/td[1]/text()").all();
            //开盘价
            List<String> open = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr/td[2]/text()").all();
            //收盘价
            List<String> close = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr/td[3]/text()").all();
            //最高价
            List<String> high = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr/td[4]/text()").all();
            //最低价
            List<String> low = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr/td[5]/text()").all();
            //涨跌额
            List<String> fluctuation = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr/td[6]/text()").all();
            //涨跌幅
            List<String> changRate = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr/td[7]/text()").all();
            //交易量
            List<String> volume = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr/td[8]/text()").all();
            //交易额
            List<String> amount = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr/td[9]/text()").all();
            //振幅
            List<String> swingRate = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr/td[10]/text()").all();
            //换手率
            List<String> turnOverRate = page.getHtml().xpath("//div[@class=\"inner_box\"]//tbody/tr/td[11]/text()").all();

            System.out.println(high.size()+"------------------------");

            //保存
            List<NormalStock> normalStocks = new ArrayList<>();
            for (int i = 0; i < dates.size(); i++) {
                normalStocks.add(new NormalStock(code,dates.get(i),open.get(i),close.get(i),high.get(i),low.get(i),fluctuation.get(i),
                        changRate.get(i),volume.get(i),amount.get(i),swingRate.get(i),turnOverRate.get(i)));
            }

            page.putField("normalStocks", normalStocks);
        }
        //获取单只股票上市日期的页面
        else if (page.getUrl().regex(ALL_LIST).match()) {
            //代码
            String code = page.getHtml().xpath("//div[@class=\"stock_info\"]//h1/span/a/text()").get();
            //上市日期
            String startDate = page.getHtml().regex("<input\\stype=\"radio\"\\sname=\"date_start_type\"\\svalue=\"(\\d{4}-\\d{2}-\\d{2})\">").get();

            //说明为今日发行的新股
            if(startDate.split("-").length == 1){
                page.setSkip(true);
            }

            System.out.println(startDate);

            int startYear = new Integer(startDate.split("-")[0]);
            int startMonth = new Integer(startDate.split("-")[1]);
            int startSeason = 1;

            //判断季度
            switch (startMonth){
                case 1:
                case 2:
                case 3:
                    startSeason = 1;
                    break;
                case 4:
                case 5:
                case 6:
                    startSeason = 2;
                    break;
                case 7:
                case 8:
                case 9:
                    startSeason = 3;
                    break;
                case 10:
                case 11:
                case 12:
                    startSeason = 4;
                    break;
            }

            boolean firstSeason = true;
            for(int i = startYear; i <= endYear; i++){
                for(int j = 1; j <= 4; j++){
                    if(i == startYear && firstSeason){
                        firstSeason = false;
                        j = startSeason;
                    }
                    else if(i == endYear && j == endSeason){
                        j = endSeason;
                        page.addTargetRequest("http://quotes.money.163.com/trade/lsjysj_"+code+".html?year=+"+i+"&season="+j);
                        break;
                    }
                    page.addTargetRequest("http://quotes.money.163.com/trade/lsjysj_"+code+".html?year=+"+i+"&season="+j);
                }
            }
        }
        //获取所有股票代码的页面
        else if (page.getUrl().regex(CODE_LIST).match() && firstDone){
            List<String> codes = new ArrayList<>();

            Json json = page.getJson();
            int count = Integer.parseInt(json.jsonPath("$.count").get());
            for(int j = 0; j < count; j++){
                String tempCode = json.jsonPath("$.list["+j+"].SYMBOL").get();
                //过滤掉基金
                if(!tempCode.startsWith("150") && !tempCode.startsWith("159") && !tempCode.startsWith("16") && !tempCode.startsWith("50")){
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
            System.out.println(page.getJson());

            Json json = page.getJson();

            int total = Integer.parseInt(json.jsonPath("$.total").get());
            int pages = Integer.parseInt(json.jsonPath("$.pageCount").get());
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
            int count = Integer.parseInt(json.jsonPath("$.count").get());
            for(int j = 0; j < count; j++){
                String tempCode = json.jsonPath("$.list["+j+"].SYMBOL").get();
                //过滤掉基金
                if(!tempCode.startsWith("150")){
                    codes.add(tempCode);
                }
            }

            for(int i = 0; i < codes.size(); i++){
                System.out.println("股票代码:"+codes.get(i));
                page.addTargetRequest("http://quotes.money.163.com/trade/lsjysj_"+codes.get(i)+".html");
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
        request.setMethod(HttpConstant.Method.GET);

        Spider.create(new StockPageProcessor())
                //从“http://quotes.money.163.com/old/#query=todayRank"开始抓
                .addRequest(request)
                //可以有多个pipeline
                .addPipeline(new ConsolePipeline())
                //开启1个线程抓取
                .thread(1)
                //启动爬虫
                .run();
    }
}