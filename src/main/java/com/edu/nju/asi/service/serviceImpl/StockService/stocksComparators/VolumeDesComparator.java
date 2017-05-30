package com.edu.nju.asi.service.serviceImpl.StockService.stocksComparators;

import com.edu.nju.asi.model.Stock;

import java.util.Comparator;

/**
 * Created by cuihua on 2017/5/29.
 */
public class VolumeDesComparator implements Comparator<Stock> {

    @Override
    public int compare(Stock o1, Stock o2) {
        Long tempResult = Long.parseLong(o1.getVolume()) - Long.parseLong(o2.getVolume());
        if (tempResult < 0) return 1;
        else if (tempResult > 0) return -1;
        else return 0;
    }
}
