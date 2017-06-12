package com.edu.nju.asi.spider.onePieceStockDownload;

import com.edu.nju.asi.task.crawler.spider.Code_Name;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harvey on 2017/5/15.
 *
 * 通过下载链接下载所有股票历史数据,起始日从2012年1月1日起
 */
public class NormalStockDownloadProcessor implements PageProcessor {
    static String First_Page = "http://quotes\\.money\\.163\\.com/hs/service/diyrank\\.php\\?host=http%3A%2F%2Fquotes\\.money\\.163\\.com%2Fhs%2Fservice%2Fdiyrank\\.php&page=0&query=STYPE%3AEQA&fields=NO%2CSYMBOL%2CNAME%2CPRICE%2CPERCENT%2CUPDOWN%2CFIVE_MINUTE%2COPEN%2CYESTCLOSE%2CHIGH%2CLOW%2CVOLUME%2CTURNOVER%2CHS%2CLB%2CWB%2CZF%2CPE%2CMCAP%2CTCAP%2CMFSUM%2CMFRATIO\\.MFRATIO2%2CMFRATIO\\.MFRATIO10%2CSNAME%2CCODE%2CANNOUNMT%2CUVSNEWS&sort=SYMBOL&order=asc&count=24&type=query";
    static String CODE_LIST = "http://quotes\\.money\\.163\\.com/hs/.*";
    static String ALL_LIST = "http://quotes\\.money\\.163\\.com/trade/lsjysj_\\d{6}.html";
    static String NAME_LIST = "http://quotes\\.money\\.163\\.com/stocksearch/json.do\\?count=1&word=\\d{6}";

    //TODO
    LocalDate startDay;
    LocalDate endDay;

    //每页取的股票代码个数，最后一页除外
    int pageCount = 24;
    int totalPage = 0;

    int totalStocks = 0;

    boolean firstDone = false;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");


    public NormalStockDownloadProcessor(LocalDate startDay, LocalDate endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
    }

    //部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me()
            .setCharset("utf-8")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.96 Safari/537.36")
            .setTimeOut(100000)
            .setRetryTimes(5)
            .setSleepTime(100);

    //

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {

        //获取所有股票代码的页面
        if (page.getUrl().regex(CODE_LIST).match() && firstDone){
            System.out.println("enter2");
            List<String> codes = new ArrayList<>();
            Json json = page.getJson();
            int count = pageCount;
            int curPage = Integer.parseInt(json.jsonPath("$.page").get());
            int total = Integer.parseInt(json.jsonPath("$.total").get());
            int totalPage = Integer.parseInt(json.jsonPath("$.pagecount").get());
            if(curPage == totalPage-1){
                count = total - (count * curPage);
            }

            for(int j = 0; j < count; j++){
                String tempCode = json.jsonPath("$.list["+j+"].SYMBOL").get();
                codes.add(tempCode);
            }

            for(int i = 0; i < codes.size(); i++){
                System.out.println("股票代码:"+codes.get(i));
                //TODO
                //添加股票代码今天的数据页面
                page.addTargetRequest("http://quotes.money.163.com/trade/lsjysj_"+codes.get(i)+".html");
                //添加通过代码搜索股票的界面
//                page.addTargetRequest("http://quotes.money.163.com/stocksearch/json.do?count=1&word="+codes.get(i));
            }

            totalStocks += codes.size();
            System.out.println(totalStocks);
        }
        //获取总页数的页面
        else if (page.getUrl().regex(First_Page).match() && !firstDone){
            System.out.println("enter3");

            totalPage = Integer.parseInt(page.getJson().jsonPath("$.pagecount").get());

            for(int i = 0; i < totalPage; i++){
                page.addTargetRequest("http://quotes.money.163.com/hs/service/diyrank.php?host=http%3A%2F%2Fquotes.money.163.com%2Fhs%2Fservice%2Fdiyrank.php&page="+i+"&query=STYPE%3AEQA&fields=NO%2CSYMBOL%2CNAME%2CPRICE%2CPERCENT%2CUPDOWN%2CFIVE_MINUTE%2COPEN%2CYESTCLOSE%2CHIGH%2CLOW%2CVOLUME%2CTURNOVER%2CHS%2CLB%2CWB%2CZF%2CPE%2CMCAP%2CTCAP%2CMFSUM%2CMFRATIO.MFRATIO2%2CMFRATIO.MFRATIO10%2CSNAME%2CCODE%2CANNOUNMT%2CUVSNEWS&sort=SYMBOL&order=asc&count="+pageCount+"&type=query");
            }

            //抓取第一个页面的code，因为之后会被去重
            List<String> codes = new ArrayList<>();
            int count = Integer.parseInt(page.getJson().jsonPath("$.count").get());
            for(int j = 0; j < count; j++){
                codes.add(page.getJson().jsonPath("$.list["+j+"].SYMBOL").get());
            }

            for(int i = 0; i < codes.size(); i++){
                System.out.println("股票代码:"+codes.get(i));
                //TODO
                page.addTargetRequest("http://quotes.money.163.com/trade/lsjysj_"+codes.get(i)+".html#01b07");
                //添加通过代码搜索股票的界面
//                page.addTargetRequest("http://quotes.money.163.com/stocksearch/json.do?count=1&word="+codes.get(i));
            }

            totalStocks += codes.size();
            System.out.println(totalStocks);

            firstDone = true;
        }
        //进入每只股票当天数据页面，获取上市日期，与默认数据下载开始日期做比较
        else if (page.getUrl().regex(ALL_LIST).match()) {
            System.out.println("enter1");
            //代码
            String code = page.getHtml().xpath("//div[@class=\"stock_info\"]//h1/span/a/text()").get();
            //上市日期
            String startDate = page.getHtml().regex("<input\\stype=\"radio\"\\sname=\"date_start_type\"\\svalue=\"(\\d{4}-\\d{2}-\\d{2})\">").get();

            //说明为今日发行的新股且没有数据,则略过
            if(startDate.split("-").length == 1){
                page.setSkip(true);
            }

            //TODO 在这里调整日期,自动化的时候，start、end都做成今天就行了
            String start = startDay.format(formatter);
            String end = endDay.format(formatter);

            System.out.println("开始日期:"+start+"------"+"结束日期:"+end);

            int prefix = 1;
            if(code.startsWith("6") || (code.startsWith("900"))){
                prefix = 0;
            }
            //TODO
            //添加当天数据的下载链接
            page.putField("url","http://quotes.money.163.com/service/chddata.html?code="+prefix+code+"&start="+start+"&end="+end+"&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP");
            page.putField("code",code);
            page.putField("isNormal", true);
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

            System.out.println("name"+name);

            page.putField("code_name", new Code_Name(code,name,spell,type));

        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
