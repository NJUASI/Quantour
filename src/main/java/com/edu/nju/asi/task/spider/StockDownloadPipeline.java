package com.edu.nju.asi.task.spider;
import java.io.*;
import java.util.Map;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class StockDownloadPipeline implements Pipeline {

    private StockDownloader stockDownloader = new StockDownloader();

    private String savePath;
    //保存财务指标需要
    private static String cwzbPath = "D:/Quant/cwzb";

    public StockDownloadPipeline(String savePath) {
        this.savePath = savePath;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        //下载历史数据的url
        String targetUrl = resultItems.get("url");
        String code = resultItems.get("code");
        Boolean isNormal = resultItems.get("isNormal");

        //下载财务指标的url映射
        Map<String,Map<String,String>> allCwzb = (Map<String,Map<String,String>>) resultItems.get("allCwzb");

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
        else if(allCwzb!= null){
            for(Map.Entry<String,Map<String,String>> entry : allCwzb.entrySet()){
                Map<String,String> eachCwzb = entry.getValue();
                for(Map.Entry<String,String> entry1 : eachCwzb.entrySet()) {
                    boolean isSuccess = false;
                    while (!isSuccess){
                        try {
                            stockDownloader.downLoadFromUrl(entry1.getValue(), cwzbPath+File.separator+entry1.getKey(), entry.getKey());
                            isSuccess = true;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
