package com.edu.nju.asi.spider.allStocksDownload;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Harvey on 2017/5/15.
 *
 * 通过下载链接下载所有股票历史数据,起始日从2007年1月1日起
 */
public class StocksDownloadProcessor implements PageProcessor {
    static String First_Page = "http://quotes\\.money\\.163\\.com/hs/realtimedata/service/rank.php\\?host=/hs/realtimedata/service/rank.php&page=0&query=STATS_RANK:_exists_&fields=RN,CODE,SYMBOL,NAME,PRICE,STATS_RANK,PERCENT&sort=SYMBOL&order=asc&count=25&type=query";
    static String CODE_LIST = "http://quotes\\.money\\.163\\.com/hs/.*";
    static String ALL_LIST = "http://quotes\\.money\\.163\\.com/trade/lsjysj_\\d{6}.html";

    LocalDate today = LocalDate.of(2017,6,1);
    LocalDate default_StartDay = LocalDate.of(2012,1,1);

    //每页取的股票代码个数，最后一页除外
    int pageCount = 25;
    int totalPage = 0;

    int totalStocks = 0;

    boolean firstDone = false;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

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
                //过滤掉基金
//                if(tempCode.startsWith("000") || tempCode.startsWith("001") || tempCode.startsWith("002")
//                        || tempCode.startsWith("300") || tempCode.startsWith("600") || tempCode.startsWith("601") || tempCode.startsWith("603")){
//                    codes.add(tempCode);
//                }
                if(tempCode.startsWith("603")){
                    codes.add(tempCode);
                }
            }

            Map<String,Map<String,String>> allCwzb = new TreeMap<>();
//
            for(int i = 0; i < codes.size(); i++){
                System.out.println("股票代码:"+codes.get(i));
                //添加股票代码今天的数据页面
                page.addTargetRequest("http://quotes.money.163.com/trade/lsjysj_"+codes.get(i)+".html");
//
                //添加财务指标的url
                Map<String,String> cwzb = new TreeMap<>();
                //主要财务指标url
                cwzb.put("zycwzb","http://quotes.money.163.com/service/zycwzb_"+codes.get(i)+".html?type=report");
                //盈利能力
                cwzb.put("ylnl","http://quotes.money.163.com/service/zycwzb_"+codes.get(i)+".html?type=report&part=ylnl");
                //偿还能力
                cwzb.put("chnl","http://quotes.money.163.com/service/zycwzb_"+codes.get(i)+".html?type=report&part=chnl");
                //成长能力
                cwzb.put("cznl","http://quotes.money.163.com/service/zycwzb_"+codes.get(i)+".html?type=report&part=cznl");
                //营运能力
                cwzb.put("yynl","http://quotes.money.163.com/service/zycwzb_"+codes.get(i)+".html?type=report&part=chnl");
                //资产负债表
                cwzb.put("zcfzb","http://quotes.money.163.com/service/zcfzb_"+codes.get(i)+".html");
                //利润表
                cwzb.put("lrn","http://quotes.money.163.com/service/lrb_"+codes.get(i)+".html");
                //现金流量表
                cwzb.put("xjllb","http://quotes.money.163.com/service/xjllb_"+codes.get(i)+".html");

                allCwzb.put(codes.get(i),cwzb);
            }
            page.putField("allCwzb",allCwzb);

            totalStocks += codes.size();
            System.out.println(totalStocks);
        }
        //获取总页数的页面
        else if (page.getUrl().regex(First_Page).match() && !firstDone){
            System.out.println("enter3");

            totalPage = Integer.parseInt(page.getJson().jsonPath("$.pagecount").get());

            for(int i = 0; i < totalPage; i++){
                page.addTargetRequest("http://quotes.money.163.com/hs/realtimedata/service/rank.php?host=/hs/realtimedata/service/rank.php&page="+i+"&query=STATS_RANK:_exists_&fields=RN,CODE,SYMBOL,NAME,PRICE,STATS_RANK,PERCENT&sort=SYMBOL&order=asc&count="+ pageCount +"&type=query");
            }

            //抓取第一个页面的code，因为之后会被去重
            List<String> codes = new ArrayList<>();
            int count = Integer.parseInt(page.getJson().jsonPath("$.count").get());
            for(int j = 0; j < count; j++){
                codes.add(page.getJson().jsonPath("$.list["+j+"].SYMBOL").get());
            }


            Map<String,Map<String,String>> allCwzb = new TreeMap<>();
            for(int i = 0; i < codes.size(); i++) {
                System.out.println("股票代码:" + codes.get(i));
                //TODO
//                page.addTargetRequest("http://quotes.money.163.com/trade/lsjysj_" + codes.get(i) + ".html#01b07");
//
                //添加财务指标的url
//                Map<String, String> cwzb = new TreeMap<>();
//                //主要财务指标url
//                cwzb.put("zycwzb", "http://quotes.money.163.com/service/zycwzb_" + codes.get(i) + ".html?type=report");
//                //盈利能力
//                cwzb.put("ylnl", "http://quotes.money.163.com/service/zycwzb_" + codes.get(i) + ".html?type=report&part=ylnl");
//                //偿还能力
//                cwzb.put("chnl", "http://quotes.money.163.com/service/zycwzb_" + codes.get(i) + ".html?type=report&part=chnl");
//                //成长能力
//                cwzb.put("cznl", "http://quotes.money.163.com/service/zycwzb_" + codes.get(i) + ".html?type=report&part=cznl");
//                //营运能力
//                cwzb.put("yynl", "http://quotes.money.163.com/service/zycwzb_" + codes.get(i) + ".html?type=report&part=chnl");
//                //资产负债表
//                cwzb.put("zcfzb", "http://quotes.money.163.com/service/zcfzb_" + codes.get(i) + ".html");
//                //利润表
//                cwzb.put("lrn", "http://quotes.money.163.com/service/lrb_" + codes.get(i) + ".html");
//                //现金流量表
//                cwzb.put("xjllb", "http://quotes.money.163.com/service/xjllb_" + codes.get(i) + ".html");
//
//                allCwzb.put(codes.get(i), cwzb);
            }
//            page.putField("allCwzb",allCwzb);

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

            LocalDate startDay = LocalDate.parse(startDate);
            System.out.println(startDay);

            //上市日期在默认开始日期前,以默认开始日期为准
            if(startDay.isBefore(default_StartDay)){
                startDay = default_StartDay;
            }

            String start = startDay.format(formatter);
            String end = today.format(formatter);

            System.out.println("开始日期:"+start+"------"+"结束日期:"+end);


            int prefix = 1;
            if(code.startsWith("6") || (code.startsWith("900"))){
                prefix = 0;
            }
            //添加开始日期一直到今天的下载链接
            page.putField("url","http://quotes.money.163.com/service/chddata.html?code="+prefix+code+"&start="+start+"&end="+end+"&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP");
            page.putField("code",code);
            page.putField("isNormal",true);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

}
