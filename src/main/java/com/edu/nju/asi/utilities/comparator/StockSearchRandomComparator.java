package com.edu.nju.asi.utilities.comparator;

import com.edu.nju.asi.model.StockSearch;

import java.util.Comparator;

/**
 * Created by cuihua on 2017/6/14.
 *
 * 将原有的StockSearch List打乱
 */
public class StockSearchRandomComparator implements Comparator<StockSearch> {

    @Override
    public int compare(StockSearch o1, StockSearch o2) {
        return (int)(Math.random() * 10 - 5);
    }
}
