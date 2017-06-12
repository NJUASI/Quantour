package com.edu.nju.asi.task.crawler;

import java.io.IOException;

/**
 * Created by Byron Dong on 2017/6/2.
 */
public class CrawingAdjStock {

    /**
     * 爬取复权数据
     *
     * @author ByronDong
     * @updateTime 2017/6/2
     */
    public void handleAdj(){
        this.crawHistoryFront();
        this.crawHistoryAfter();
    }

    /**
     * 爬取区域，行业信息
     *
     * @author ByronDong
     * @updateTime 2017/6/2
     */
    public void handleAreaAndIndustry(){
        this.crawArea();
        this.crawIndustry();
    }

    /**
     * 爬取前复权数据（指定的时间段）
     *
     * @author ByronDong
     * @updateTime 2017/6/2
     */
    public void crawHistoryFront(){
        try {
            String path = getClass().getClassLoader().getResource("python/_crawing_history_front.py").getPath();
            Process proc = Runtime.getRuntime().exec("python " + path.substring(1));
            proc.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 爬取后复权数据（指定的时间段）
     *
     * @author ByronDong
     * @updateTime 2017/6/2
     */
    public void crawHistoryAfter(){
        try {
            String path = getClass().getClassLoader().getResource("python/_crawing_history_after.py").getPath();
            Process proc = Runtime.getRuntime().exec("python " + path.substring(1));
            proc.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 爬取区域信息
     *
     * @author ByronDong
     * @updateTime 2017/6/2
     */
    public void crawArea(){
        System.out.println("----------------------------enterArea-------------------------------------");
        try {
            String path = getClass().getClassLoader().getResource("python/_crawing_area.py").getPath();
            System.out.println(path);
            Process proc = Runtime.getRuntime().exec("python " + path.substring(1));
            proc.waitFor();
            System.out.println("----------------------------endArea-------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 爬取行业信息
     *
     * @author ByronDong
     * @updateTime 2017/6/2
     */
    public void crawIndustry(){
        System.out.println("----------------------------enterIndustry-------------------------------------");
        try {
            String path = getClass().getClassLoader().getResource("python/_crawing_industry.py").getPath();
            System.out.println(path);
            Process proc = Runtime.getRuntime().exec("python " + path.substring(1));
            proc.waitFor();
            System.out.println("----------------------------endArea-------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
