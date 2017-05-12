package com.edu.nju.asi.utilities.comparator;

import com.edu.nju.asi.model.Stock;

import java.util.Comparator;

/**
 * Created by cuihua on 2017/5/11.
 */
public class StockDateComparator implements Comparator<Stock> {

    @Override
    public int compare(Stock o1, Stock o2) {
        if (o1.getStockID().getDate().isBefore(o2.getStockID().getDate())) return -1;
        if (o1.getStockID().getDate().isAfter(o2.getStockID().getDate())) return 1;
        return 0;
    }
}
