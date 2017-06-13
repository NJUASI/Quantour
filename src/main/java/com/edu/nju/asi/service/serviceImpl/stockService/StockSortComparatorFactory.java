package com.edu.nju.asi.service.serviceImpl.stockService;

import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.service.serviceImpl.stockService.stocksComparators.*;
import com.edu.nju.asi.utilities.enums.StocksSortCriteria;

import java.util.Comparator;

/**
 * Created by cuihua on 2017/5/29.
 */
public class StockSortComparatorFactory {

    public Comparator<Stock> createSortComparator(StocksSortCriteria comparisionCriteria) {
        switch (comparisionCriteria) {
            /**
             * stockID.code需反射两次，只用一次，懒得写了
             */
            case CODE_ASC:
                return new CodeAscComparator();
            case CODE_DES:
                return new CodeDesComparator();

            /**
             * 根据String比较
             */
            case NAME_ASC:
                return new StocksStringAscComparator("name");
            case NAME_DES:
                return new StocksStringDescComparator("name");

            /**
             * 根据double比较
             */
            case OPEN_ASC:
                return new StocksDoubleAscComparator("open");
            case OPEN_DES:
                return new StocksDoubleDescComparator("open");
            case CLOSE_ASC:
                return new StocksDoubleAscComparator("close");
            case CLOSE_DES:
                return new StocksDoubleDescComparator("close");
            case LOW_ASC:
                return new StocksDoubleAscComparator("low");
            case LOW_DES:
                return new StocksDoubleDescComparator("low");
            case HIGH_ASC:
                return new StocksDoubleAscComparator("high");
            case HIGH_DES:
                return new StocksDoubleDescComparator("high");
            case PRE_CLOSE_ASC:
                return new StocksDoubleAscComparator("preClose");
            case PRE_CLOSE_DES:
                return new StocksDoubleDescComparator("preClose");
            case TRANSACTION_AMOUNT_ASC:
                return new StocksDoubleAscComparator("transactionAmount");
            case TRANSACTION_AMOUNT_DES:
                return new StocksDoubleDescComparator("transactionAmount");
            case VOLUME_ASC:
                return new StocksDoubleAscComparator("volume");
            case VOLUME_DES:
                return new StocksDoubleDescComparator("volume");
        }
        return null;
    }
}
