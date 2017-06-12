package com.edu.nju.asi.task.crawler;

import com.edu.nju.asi.task.crawler.spider.SpiderController;
import com.edu.nju.asi.task.fileHelper.TaskInfoHelper;

import java.io.File;
import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/6/12.
 */
public class CrawlerController {

    //taskInfo文件的控制对象
    private TaskInfoHelper taskInfoHelper;

    //爬取stock和baseStock的爬虫
    private SpiderController spiderController;

    //爬取stock复权数据的爬虫
    private CrawingAdjStock crawlerOfAdj;

    //初始化成员变量
    public CrawlerController(String root, LocalDate start,LocalDate end) {

        if(!root.endsWith(File.separator)){
            root = root+File.separator;
        }

        taskInfoHelper = new TaskInfoHelper();
        taskInfoHelper.writerInfo("root",root);
        spiderController = new SpiderController(root,root+"stocks",root+"baseStocks",start,end);
        crawlerOfAdj = new CrawingAdjStock();
    }

    /**
     * 爬取股票和基准的数据
     *
     * @author ByronDong
     * @updateTime 2017/6/12
     */
    public void handleStock(){
        spiderController.run();
    }

    /**
     * 爬取股票复权的数据
     *
     * @author ByronDong
     * @updateTime 2017/6/12
     */
    public void handleAdjStock(){
        crawlerOfAdj.handle();
    }
}
