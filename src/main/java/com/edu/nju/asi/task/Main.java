package com.edu.nju.asi.task;


import com.edu.nju.asi.task.fileHelper.FileHelper;
import com.edu.nju.asi.task.fileHelper.TaskInfoHelper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Byron Dong on 2017/6/2.
 */
public class Main {

    public static void main(String[] args) {
//        FileHelper fileHelper = new FileHelper("F:\\test\\test1");
//        fileHelper.create();
//        System.out.println(System.getProperty("user.dir"));
        DataSourceUpdateTask dataSourceUpdateTask = new DataSourceUpdateTask();
        dataSourceUpdateTask.update();
//        Main main = new Main();
//        main.test();
    }

    public void test() {
        try {
            String path =getClass().getClassLoader().getResource("python/CrawingToday.py").getPath();
            String type = "python";
            String[] cmd = {type,path.substring(1)};
            Process process = Runtime.getRuntime().exec(cmd);
            System.out.println("--------------------------正常信息输出-------------------------------------");
            BufferedReader infoReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line1 = null;
            while((line1=infoReader.readLine())!=null){
                System.out.println(line1);
            }
            infoReader.close();

            System.out.println("--------------------------错误信息输出-------------------------------------");
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = null;
            while((line=errorReader.readLine())!=null){
                System.out.println(line);
            }
            errorReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
