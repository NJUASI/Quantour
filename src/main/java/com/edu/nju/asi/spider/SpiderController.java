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

    static String allNormalStocksPath = "D:/Quantour/stocks";
    static String allBaseStocksPath = "D:/Quantour/baseStocks";
    static String todayNormalStocksPath = "D:/Quantour/todayNormalStock";
    static String todayBaseStocksPath = "D:/Quantour/todayBaseStock";


    public static void main(String[] args) {
        DownloadDataHelper downloadDataHelper = new DownloadDataHelper();
        StockDownloader stockDownloader = new StockDownloader();

        //先下载全部的普通股票数据
//        downloadAllNormalStocks(allNormalStocksPath);
        //再保存全部的普通数据
        downloadDataHelper.normalStockStore(allNormalStocksPath);
        //下载全部的基准股票数据
//        stockDownloader.downLoadBaseStock(allBaseStocksPath, LocalDate.of(2007,1,1), LocalDate.now());
        //保存全部的基准股票数据
        downloadDataHelper.baseStockStore(allBaseStocksPath);
        //下载当天的普通股票数据
//        downloadTodayNormalStocks(todayNormalStocksPath);
        //保存当天的普通股票数据
//        downloadDataHelper.normalStockStore(todayNormalStocksPath);
        //下载当天的基准股票数据
//        stockDownloader.downLoadBaseStock(todayBaseStocksPath, LocalDate.now(), LocalDate.now());
        //保存当天的基准股票数据
//        downloadDataHelper.baseStockStore(todayBaseStocksPath);

    }

    public static void downloadTodayNormalStocks(String todayNormalStocksPath){
        Request request = new Request();
        request.setUrl("http://quotes.money.163.com/hs/realtimedata/service/rank.php?host=/hs/realtimedata/service/rank.php&page=0&query=STATS_RANK:_exists_&fields=RN,CODE,SYMBOL,NAME,PRICE,STATS_RANK,PERCENT&sort=SYMBOL&order=asc&count=25&type=query");
//        request.setUrl("http://quotes.money.163.com/trade/lsjysj_000001.html");
//        request.setUrl("http://quotes.money.163.com/hs/realtimedata/service/rank.php?host=/hs/realtimedata/service/rank.php&page=142&query=STATS_RANK:_exists_&fields=RN,CODE,SYMBOL,NAME,PRICE,STATS_RANK,PERCENT&sort=SYMBOL&order=asc&count=25&type=query");
        request.setMethod(HttpConstant.Method.GET);

        Spider.create(new NormalStockDownloadProcessor())
                .addRequest(request)
                //可以有多个pipeline
                .addPipeline(new ConsolePipeline())
                //下载每天的数据
                .addPipeline(new StockDownloadPipeline(todayNormalStocksPath))
                //更新代码和名称
                .addPipeline(new Code_NamePipeline())
                //开启1个线程抓取
                .thread(1)
                //启动爬虫
                .run();
    }


    public static void downloadAllNormalStocks(String downloadPath){
        Request request = new Request();
        request.setUrl("http://quotes.money.163.com/hs/realtimedata/service/rank.php?host=/hs/realtimedata/service/rank.php&page=0&query=STATS_RANK:_exists_&fields=RN,CODE,SYMBOL,NAME,PRICE,STATS_RANK,PERCENT&sort=SYMBOL&order=asc&count=25&type=query");
//        request.setUrl("http://quotes.money.163.com/trade/lsjysj_000001.html");
//        request.setUrl("http://quotes.money.163.com/hs/realtimedata/service/rank.php?host=/hs/realtimedata/service/rank.php&page=142&query=STATS_RANK:_exists_&fields=RN,CODE,SYMBOL,NAME,PRICE,STATS_RANK,PERCENT&sort=SYMBOL&order=asc&count=25&type=query");
        request.setMethod(HttpConstant.Method.GET);

        Spider.create(new StocksDownloadProcessor())
                .addRequest(request)
                //可以有多个pipeline
                .addPipeline(new ConsolePipeline())
                .addPipeline(new StockDownloadPipeline(downloadPath))
                //开启1个线程抓取
                .thread(1)
                //启动爬虫
                .run();
    }

}
