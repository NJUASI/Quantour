package com.edu.nju.asi.crawler;


import com.edu.nju.asi.crawler.StoreDataHelper.StoreAreaAndIndustryHelper;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;

/**
 * Created by Byron Dong on 2017/6/2.
 */
public class Main {

    public static void main(String[] args) {
       Main main =  new Main();
       main.test();
    }

    public void test(){
        try {
            System.out.println(getClass().getClassLoader().getResource("python/CrawingToday.py").getPath());
            String path = getClass().getClassLoader().getResource("python/CrawingToday.py").getPath();
            Process proc = Runtime.getRuntime().exec("python "+path.substring(1));
            proc.waitFor();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
