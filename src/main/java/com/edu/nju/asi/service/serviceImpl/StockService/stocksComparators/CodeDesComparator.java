package com.edu.nju.asi.service.serviceImpl.StockService.stocksComparators;

import com.edu.nju.asi.model.Stock;

import java.util.Comparator;

/**
 * Created by cuihua on 2017/5/29.
 */
public class CodeDesComparator implements Comparator<Stock> {

    @Override
    public int compare(Stock o1, Stock o2) {
        int tempResult = o1.getStockID().getCode().compareTo(o2.getStockID().getCode());
        if (tempResult < 0) return 1;
        else if (tempResult > 0) return -1;
        else return 0;
    }
}
