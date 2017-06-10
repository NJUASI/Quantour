package com.edu.nju.asi.crawler;


import com.edu.nju.asi.crawler.StoreDataHelper.StoreAdjStockHelper;
import com.edu.nju.asi.crawler.StoreDataHelper.StoreBasicDataHelper;

/**
 * Created by Byron Dong on 2017/6/2.
 */
public class Main {

    public static void main(String[] args) {
        StoreBasicDataHelper storeBasicDataHelper = new StoreBasicDataHelper();
        storeBasicDataHelper.store();

    }

    public void test() {
        try {
            System.out.println(getClass().getClassLoader().getResource("python/CrawingToday.py").getPath());
            String path = getClass().getClassLoader().getResource("python/CrawingToday.py").getPath();
            Process proc = Runtime.getRuntime().exec("python " + path.substring(1));
            proc.waitFor();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
