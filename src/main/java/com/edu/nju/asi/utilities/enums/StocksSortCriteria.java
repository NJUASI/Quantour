package com.edu.nju.asi.utilities.enums;

import com.edu.nju.asi.utilities.exceptions.NoMatchEnumException;

/**
 * Created by cuihua on 2017/5/29.
 * 股票市场的排序条件
 */
public enum StocksSortCriteria {

    // ASC升序，DES降序
    CODE_ASC(0),
    CODE_DES(1),
    NAME_ASC(2),
    NAME_DES(3),
    OPEN_ASC(4),
    OPEN_DES(5),
    CLOSE_ASC(6),
    CLOSE_DES(7),
    HIGH_ASC(8),
    HIGH_DES(9),
    LOW_ASC(10),
    LOW_DES(11),
    PRE_CLOSE_ASC(12),
    PRE_CLOSE_DES(13),
    VOLUME_ASC(14),
    VOLUME_DES(15),
    TRANSACTION_AMOUNT_ASC(16),
    TRANSACTION_AMOUNT_DES(17);

    int repre;

    private StocksSortCriteria(int repre) {
        this.repre = repre;
    }

    public int getRepre() {
        return repre;
    }

    public static StocksSortCriteria getEnum(int a) throws NoMatchEnumException {
        for (StocksSortCriteria criteria : StocksSortCriteria.values()) {
            if (criteria.repre == a) {
                return criteria;
            }
        }
        throw new NoMatchEnumException();
    }
}
