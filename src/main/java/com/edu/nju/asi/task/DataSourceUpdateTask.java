package com.edu.nju.asi.task;

import com.edu.nju.asi.task.crawler.CrawlerController;
import com.edu.nju.asi.task.fileHelper.FileHelper;
import com.edu.nju.asi.task.fileHelper.TaskInfoHelper;
import com.edu.nju.asi.task.storer.StoreController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/6/3.
 *
 * 根据数据源更新数据库
 */
@Component
public class DataSourceUpdateTask {

    //爬取数据对象
    private CrawlerController crawler;

    //存储数据对象
    private StoreController storer;

    //文件对象
    private FileHelper fileHelper;

    //taskInfo文件的对象
    private TaskInfoHelper taskInfoHelper;

    //根目录
    private String root;

    //开始日期
    private LocalDate start;

    //结束日期
    private LocalDate end;

    // TODO 每晚23点整更新数据源
    @Scheduled(cron = "0 0 23 * * ?")
    public void update() {
        this.handle();
    }

    /**
     * 自动化更新数据
     *
     * @author ByronDong
     * @updateTime 2017/6/12
     */
    private void handle(){
        init();
        fileHelper.create();
        crawler.handleStock();
        storer.storeStock();
        storer.storeStockSearch();
        crawler.handleAdjStock();
        storer.storeAdjData();
        fileHelper.delete();
        taskInfoHelper.clearInfo();
    }

    /**
     * 初始化成员信息
     *
     * @author ByronDong
     * @updateTime 2017/6/12
     */
    private void init(){
        root = System.getProperty("user.dir")+ File.separator+"attachment";
        start = LocalDate.now();
        end = LocalDate.now();
        fileHelper = new FileHelper(root);
        storer = new StoreController(root,start,end);
        crawler = new CrawlerController(root,start,end);
        taskInfoHelper = new TaskInfoHelper();
    }

}
