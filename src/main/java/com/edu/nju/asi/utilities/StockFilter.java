package com.edu.nju.asi.utilities;

import com.edu.nju.asi.model.Stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harvey on 2017/3/9.
 */
public class StockFilter {

    public StockFilter() {
    }

    //过滤器，过滤出停盘的数据
    public static List<Stock> filter(List<Stock> preList) {
        List<Stock> handledList = new ArrayList<>();
        for (Stock stock : preList) {
            if (isSuspension(stock)){
                handledList.add(stock);
            }
        }
        return handledList;
    }

    //判断是否当天是否停盘
    public static boolean isSuspension(Stock stock){
        if (new Long(stock.getVolume()) != 0){
            return true;
        }
        return false;
    }
}
