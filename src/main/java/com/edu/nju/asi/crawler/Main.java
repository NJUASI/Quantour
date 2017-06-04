package com.edu.nju.asi.crawler;


import com.edu.nju.asi.crawler.StoreDataHelper.StoreStockHelper;

/**
 * Created by Byron Dong on 2017/6/2.
 */
public class Main {

    public static void main(String[] args) {
        StoreStockHelper storeStockHelper = new StoreStockHelper();
        storeStockHelper.handle();
    }
}
