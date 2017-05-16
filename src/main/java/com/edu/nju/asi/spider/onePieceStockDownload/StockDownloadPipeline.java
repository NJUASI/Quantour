package com.edu.nju.asi.spider.onePieceStockDownload;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.edu.nju.asi.spider.DownloadDataHelper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class StockDownloadPipeline implements Pipeline {

    private StockDownloader stockDownloader = new StockDownloader();

    private String savePath;

    public StockDownloadPipeline(String savePath) {
        this.savePath = savePath;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String targetUrl = resultItems.get("url");
        String code = resultItems.get("code");
        Boolean isNormal = resultItems.get("isNormal");

        //重试直到成功
        if (targetUrl != null) {
            boolean isSuccess = false;
            while (!isSuccess){
                try {
                    //普通股票
                    if(isNormal){
                        stockDownloader.downLoadFromUrl(targetUrl, savePath, code);
                        isSuccess = true;
                    }
                    //基准股票
                    else {
                        stockDownloader.downLoadFromUrl(targetUrl, savePath, code);
                        isSuccess = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
