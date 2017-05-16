package com.edu.nju.asi.spider.onePieceStockDownload;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.edu.nju.asi.spider.DownloadDataHelper;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class StockDownloadPipeline implements Pipeline {

    private DownloadDataHelper dataHelper = new DownloadDataHelper();
    private StockDownloader stockDownloader = new StockDownloader();

    @Override
    public void process(ResultItems resultItems, Task task) {
        String targetUrl = resultItems.get("url");
        String code = resultItems.get("code");
        boolean isNormal = resultItems.get("isNormal");

        //重试直到成功
        if (targetUrl != null) {
            boolean isSuccess = false;
            while (!isSuccess){
                try {
                    //普通股票
                    if(isNormal){
                        stockDownloader.downLoadFromUrl(targetUrl, "G:/Quantour/todayNormalStock", code);
                        isSuccess = true;
                        //转储到数据库
                        dataHelper.normalStockStore("G:"+File.separator+"Quantour"+File.separator+"todayNormalStock"+File.separator+code+".csv");
                    }
                    //基准股票
                    else {
                        stockDownloader.downLoadFromUrl(targetUrl, "G:/Quantour/todayBaseStock", code);
                        isSuccess = true;
                        //转储到数据库
                        dataHelper.baseStockStore("G:"+File.separator+"Quantour"+File.separator+"todayBaseStock"+File.separator+code+".csv");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
