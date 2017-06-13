package com.edu.nju.asi.task.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
//            proc.waitFor();
            printInfo(proc);
        }  catch (IOException e) {
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
//            proc.waitFor();
            printInfo(proc);
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
        try {
            String path = getClass().getClassLoader().getResource("python/_crawing_area.py").getPath();
            System.out.println(path);
            Process proc = Runtime.getRuntime().exec("python " + path.substring(1));
//            proc.waitFor();
            printInfo(proc);
        }  catch (IOException e) {
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
        try {
            String path = getClass().getClassLoader().getResource("python/_crawing_industry.py").getPath();
            System.out.println(path);
            Process proc = Runtime.getRuntime().exec("python " + path.substring(1));
//            proc.waitFor();
            printInfo(proc);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向控制台输出进程的信息
     *
     * @author ByronDong
     * @updateTime 2017/6/2
     */
    private void printInfo(Process process){
        try {
            System.out.println("--------------------------正常信息输出-------------------------------------");
            BufferedReader infoReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line1 = null;

            while((line1=infoReader.readLine())!=null){
                System.out.println(line1);
            }
            infoReader.close();
            System.out.println("--------------------------正常信息输出结束-------------------------------------");
            System.out.println("--------------------------错误信息输出-------------------------------------");
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = null;
            while((line=errorReader.readLine())!=null){
                System.out.println(line);
            }
            errorReader.close();
            System.out.println("--------------------------错误信息输出结束-------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
