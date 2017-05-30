package com.edu.nju.asi.utilities.tempHolder;

import com.edu.nju.asi.infoCarrier.traceBack.FormateAndPickCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.StockPoolCriteria;

/**
 * Created by cuihua on 2017/5/14.
 */
public class TraceBackCriteriaTempHolder {
    /**
     * 回测区间的起始时间
     */
    public String startDate;

    /**
     * 回测区间的结束时间
     */
    public String endDate;

    /**
     * 形成期（MS）／多少天均值（MR）
     */
    public int formativePeriod;

    /**
     * 持有期
     */
    public int holdingPeriod;

    /**
     * 板块
     */
    public StockPoolCriteria stockPoolCriteria;

//    /**
//     * 持有股票数
//     */
//    public int holdingNum;

    /**
     * 基准股票的名称，如沪深300
     */
    public String baseStockName;

//    /**
//     * 指出是否是自选股
//     */
//    public boolean isCustomized;

    /**
     * 形成期的形成和挑选类型以及rank
     */
    public FormateAndPickCriteria formateAndPickCriteria;

    public TraceBackCriteriaTempHolder() {
    }

}
