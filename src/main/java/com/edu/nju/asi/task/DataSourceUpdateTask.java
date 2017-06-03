package com.edu.nju.asi.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by cuihua on 2017/6/3.
 *
 * 根据数据源更新数据库
 */
@Component
public class DataSourceUpdateTask {


    // TODO 每晚23点整更新数据源
    @Scheduled(cron = "0 0 23 * * ?")
    public void update() {

    }

}
