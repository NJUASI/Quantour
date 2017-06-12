package com.edu.nju.asi.task;


import com.edu.nju.asi.task.fileHelper.FileHelper;
import com.edu.nju.asi.task.fileHelper.TaskInfoHelper;

/**
 * Created by Byron Dong on 2017/6/2.
 */
public class Main {

    public static void main(String[] args) {
//        FileHelper fileHelper = new FileHelper("F:\\test\\test1");
//        fileHelper.create();
//        System.out.println(System.getProperty("user.dir"));
//        DataSourceUpdateTask dataSourceUpdateTask = new DataSourceUpdateTask();
//        dataSourceUpdateTask.update();
        Main main = new Main();
        main.test();
    }

    public void test() {
        try {
            System.out.println(getClass().getClassLoader().getResource("python/CrawingToday.py").getPath());
            String path =getClass().getClassLoader().getResource("python/CrawingToday.py").getPath();
            Process proc = Runtime.getRuntime().exec("python "+path.substring(1));
            proc.waitFor();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
