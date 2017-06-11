package com.edu.nju.asi.infoCarrier.traceBack;

import com.edu.nju.asi.utilities.enums.MarketSelectingType;

/**
 * Created by cuihua on 2017/6/11.
 *
 * 择时条件
 */
public class MarketSelectingCondition {

    /**
     * 择时指标
     */
    public MarketSelectingType type;

    /**
     * 选择的股指
     */
    public String baseStockName;

    /**
     * 条件一
     */
    public int criteria1;

    /**
     * 条件二
     */
    public int criteria2;

    /**
     * 条件三（没有则赋值为0）
     */
    public int criteria3;


    public MarketSelectingCondition() {
    }
}
