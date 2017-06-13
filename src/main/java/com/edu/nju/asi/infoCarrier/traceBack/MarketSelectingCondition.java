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
     * 择时判断周期
     */
    public int cycle;

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

    public MarketSelectingCondition(MarketSelectingType type, String baseStockName, int cycle, int criteria1, int criteria2, int criteria3) {
        this.type = type;
        this.baseStockName = baseStockName;
        this.cycle = cycle;
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
        this.criteria3 = criteria3;
    }
}
