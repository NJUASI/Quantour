package com.edu.nju.asi.spider;

import com.edu.nju.asi.spider.allStocksDownload.StocksDownloadProcessor;
import com.edu.nju.asi.spider.onePieceStockDownload.Code_NamePipeline;
import com.edu.nju.asi.spider.onePieceStockDownload.NormalStockDownloadProcessor;
import com.edu.nju.asi.spider.onePieceStockDownload.StockDownloadPipeline;
import com.edu.nju.asi.spider.onePieceStockDownload.StockDownloader;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.utils.HttpConstant;

import java.time.LocalDate;


/**
 * Created by Harvey on 2017/5/16.
 */
public class SpiderController {


    static String allNormalStocksPath = "D:/Quant/stocks";
    static String allBaseStocksPath = "D:/Quant/baseStocks";
    static String todayNormalStocksPath = "D:/Quant/todayNormalStock";
    static String todayBaseStocksPath = "D:/Quant/todayBaseStock";


    public static void main(String[] args) {
        DownloadDataHelper downloadDataHelper = new DownloadDataHelper();
        StockDownloader stockDownloader = new StockDownloader();

//        先下载全部的普通股票数据(包括主要财务指标中所有表、资产负债表、利润表、现金流量表)
//        downloadAllNormalStocks(allNormalStocksPath);
        //保存全部的普通数据
//        downloadDataHelper.normalStockStore(allNormalStocksPath);
//        下载全部的基准股票数据
//        stockDownloader.downLoadBaseStock(allBaseStocksPath, LocalDate.of(2012,1,1), LocalDate.now().minusDays(1));
        //保存全部的基准股票数据
//        downloadDataHelper.baseStockStore(allBaseStocksPath);
        //下载当天的普通股票数据
        downloadTodayNormalStocks(todayNormalStocksPath);
        //保存当天的普通股票数据
//        downloadDataHelper.normalStockStore(todayNormalStocksPath);
        //下载当天的基准股票数据
//        stockDownloader.downLoadBaseStock(todayBaseStocksPath, LocalDate.of(2017, 6, 3), LocalDate.now());
        //保存当天的基准股票数据
//        downloadDataHelper.baseStockStore(todayBaseStocksPath);

    }

    public static void downloadTodayNormalStocks(String todayNormalStocksPath){
        Request request = new Request();
        request.setUrl("http://quotes.money.163.com/hs/service/diyrank.php?host=http%3A%2F%2Fquotes.money.163.com%2Fhs%2Fservice%2Fdiyrank.php&page=0&query=STYPE%3AEQA&fields=NO%2CSYMBOL%2CNAME%2CPRICE%2CPERCENT%2CUPDOWN%2CFIVE_MINUTE%2COPEN%2CYESTCLOSE%2CHIGH%2CLOW%2CVOLUME%2CTURNOVER%2CHS%2CLB%2CWB%2CZF%2CPE%2CMCAP%2CTCAP%2CMFSUM%2CMFRATIO.MFRATIO2%2CMFRATIO.MFRATIO10%2CSNAME%2CCODE%2CANNOUNMT%2CUVSNEWS&sort=SYMBOL&order=asc&count=24&type=query");
//        request.setUrl("http://quotes.money.163.com/trade/lsjysj_000002.html");
//        request.setUrl("http://quotes.money.163.com/hs/realtimedata/service/rank.php?host=/hs/realtimedata/service/rank.php&page=142&query=STATS_RANK:_exists_&fields=RN,CODE,SYMBOL,NAME,PRICE,STATS_RANK,PERCENT&sort=SYMBOL&order=asc&count=25&type=query");
        request.setMethod(HttpConstant.Method.GET);

        Spider.create(new NormalStockDownloadProcessor())
                .addRequest(request)
                //可以有多个pipeline
                .addPipeline(new ConsolePipeline())
                //下载每天的数据
                //TODO
//                .addPipeline(new StockDownloadPipeline(todayNormalStocksPath))
                //更新代码和名称
                .addPipeline(new Code_NamePipeline())
                //开启1个线程抓取
                .thread(1)
                //启动爬虫
                .run();
    }


    public static void downloadAllNormalStocks(String downloadPath){
        Request request = new Request();
        request.setUrl("http://quotes.money.163.com/hs/service/diyrank.php?host=http%3A%2F%2Fquotes.money.163.com%2Fhs%2Fservice%2Fdiyrank.php&page=0&query=STYPE%3AEQA&fields=NO%2CSYMBOL%2CNAME%2CPRICE%2CPERCENT%2CUPDOWN%2CFIVE_MINUTE%2COPEN%2CYESTCLOSE%2CHIGH%2CLOW%2CVOLUME%2CTURNOVER%2CHS%2CLB%2CWB%2CZF%2CPE%2CMCAP%2CTCAP%2CMFSUM%2CMFRATIO.MFRATIO2%2CMFRATIO.MFRATIO10%2CSNAME%2CCODE%2CANNOUNMT%2CUVSNEWS&sort=SYMBOL&order=asc&count=24&type=query");
//        request.setUrl("http://quotes.money.163.com/trade/lsjysj_000001.html");
//        request.setUrl("http://quotes.money.163.com/hs/realtimedata/service/rank.php?host=/hs/realtimedata/service/rank.php&page=1&query=STATS_RANK:_exists_&fields=RN,CODE,SYMBOL,NAME,PRICE,STATS_RANK,PERCENT&sort=SYMBOL&order=asc&count=25&type=query");
        request.setMethod(HttpConstant.Method.GET);

        Spider.create(new StocksDownloadProcessor())
                .addRequest(request)
                //可以有多个pipeline
                .addPipeline(new ConsolePipeline())
                .addPipeline(new StockDownloadPipeline(downloadPath))
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }

}
