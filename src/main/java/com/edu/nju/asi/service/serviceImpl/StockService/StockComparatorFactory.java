package com.edu.nju.asi.service.serviceImpl.StockService;

import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.service.serviceImpl.StockService.stocksComparators.*;
import com.edu.nju.asi.utilities.enums.StocksComparisionCriteria;

import java.util.Comparator;

/**
 * Created by cuihua on 2017/5/29.
 */
public class StockComparatorFactory {

    public Comparator<Stock> createSortComparator(StocksComparisionCriteria comparisionCriteria) {
        switch (comparisionCriteria) {
            case CODE_ASC:
                return new CodeAscComparator();
            case CODE_DES:
                return new CodeDesComparator();
            case NAME_ASC:
                return new NameAscComparator();
            case NAME_DES:
                return new NameDesComparator();
            case OPEN_ASC:
                return new OpenAscComparator();
            case OPEN_DES:
                return new OpenDesComparator();
            case CLOSE_ASC:
                return new CloseAscComparator();
            case CLOSE_DES:
                return new CloseDesComparator();
            case LOW_ASC:
                return new LowAscComparator();
            case LOW_DES:
                return new LowDesComparator();
            case HIGH_ASC:
                return new HighAscComparator();
            case HIGH_DES:
                return new HighDesComparator();
            case PRE_CLOSE_ASC:
                return new PreCloseAscComparator();
            case PRE_CLOSE_DES:
                return new PreCloseDesComparator();
            case VOLUME_ASC:
                return new VolumeAscComaparator();
            case VOLUME_DES:
                return new VolumeDesComaparator();
            case TRANSACTION_AMOUNT_ASC:
                return new TransactionAmountAscComaparator();
            case TRANSACTION_AMOUNT_DES:
                return new TransactionAmountDesComaparator();
        }
        return null;
    }
}
