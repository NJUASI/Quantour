package com.edu.nju.asi.task.crawler.spider;

import com.edu.nju.asi.spider.onePieceStockDownload.NormalStockDownloadProcessor;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.utils.HttpConstant;

import java.time.LocalDate;


/**
 * Created by Harvey on 2017/5/16.
 */
public class SpiderController {

    //根路径
    String rootPath;
    //保存每天股票的stocks的路径
    String stocksPath;
    //保存每天股指的路径
    String baseStockPath;

    StockDownloader stockDownloader = new StockDownloader();

    //爬取的开始日期
    LocalDate start;

    //爬取的结束日期
    LocalDate end;

    public SpiderController(String rootPath, String stocksPath, String baseStockPath, LocalDate start, LocalDate end) {
        this.rootPath = rootPath;
        this.stocksPath = stocksPath;
        this.baseStockPath = baseStockPath;
        this.start = start;
        this.end = end;
    }

    public void run() {

        //下载当天的普通股票数据
        downloadTodayNormalStocks(stocksPath);
        //下载当天的基准股票数据
        stockDownloader.downLoadBaseStock(baseStockPath, start,end);

    }

    private void downloadTodayNormalStocks(String stocksPath){
        Request request = new Request();
        request.setUrl("http://quotes.money.163.com/hs/service/diyrank.php?host=http%3A%2F%2Fquotes.money.163.com%2Fhs%2Fservice%2Fdiyrank.php&page=0&query=STYPE%3AEQA&fields=NO%2CSYMBOL%2CNAME%2CPRICE%2CPERCENT%2CUPDOWN%2CFIVE_MINUTE%2COPEN%2CYESTCLOSE%2CHIGH%2CLOW%2CVOLUME%2CTURNOVER%2CHS%2CLB%2CWB%2CZF%2CPE%2CMCAP%2CTCAP%2CMFSUM%2CMFRATIO.MFRATIO2%2CMFRATIO.MFRATIO10%2CSNAME%2CCODE%2CANNOUNMT%2CUVSNEWS&sort=SYMBOL&order=asc&count=24&type=query");
        request.setMethod(HttpConstant.Method.GET);

        Spider.create(new NormalStockDownloadProcessor(start,end))
                .addRequest(request)
                //可以有多个pipeline
                .addPipeline(new ConsolePipeline())
                //下载每天的数据
                //TODO
                .addPipeline(new StockDownloadPipeline(stocksPath))
                //更新代码和名称
                .addPipeline(new Code_NamePipeline(rootPath))
                //开启1个线程抓取
                .thread(1)
                //启动爬虫
                .run();
    }

}
