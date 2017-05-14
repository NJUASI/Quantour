package com.edu.nju.asi.infoCarrier.traceBack;

import com.edu.nju.asi.utilities.LocalDateHelper;
import com.edu.nju.asi.utilities.tempHolder.TraceBackCriteriaTempHolder;

import java.time.LocalDate;

/**
 * Created by harvey on 17-3-28.
 *
 * 保存用户在界面上选择的回测的条件
 */
public class TraceBackCriteria {

    /**
     * 回测区间的起始时间
     */
    public LocalDate startDate;

    /**
     * 回测区间的结束时间
     */
    public LocalDate endDate;

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

    /**
     * 指出是否是自选股
     */
    public boolean isCustomized;

    /**
     * 形成期的形成和挑选类型以及rank
     */
    public FormateAndPickCriteria formateAndPickCriteria;

    public TraceBackCriteria(){

    }
    public TraceBackCriteria(LocalDate startDate, LocalDate endDate, int formativePeriod, int holdingPeriod, StockPoolCriteria stockPoolCriteria, String baseStockName, boolean isCustomized, FormateAndPickCriteria formateAndPickCriteria) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.formativePeriod = formativePeriod;
        this.holdingPeriod = holdingPeriod;
        this.stockPoolCriteria = stockPoolCriteria;
//        this.holdingNum = holdingNum;
        this.baseStockName = baseStockName;
        this.isCustomized = isCustomized;
        this.formateAndPickCriteria = formateAndPickCriteria;
    }


    public TraceBackCriteria(TraceBackCriteria criteria) {
        this.startDate = criteria.startDate;
        this.endDate = criteria.endDate;
        this.formativePeriod = criteria.formativePeriod;
        this.holdingPeriod = criteria.holdingPeriod;
        this.stockPoolCriteria = criteria.stockPoolCriteria;
//        this.holdingNum = holdingNum;
        this.baseStockName = criteria.baseStockName;
        this.isCustomized = criteria.isCustomized;
        this.formateAndPickCriteria = criteria.formateAndPickCriteria;
    }

    public TraceBackCriteria(TraceBackCriteriaTempHolder tempHolder) {
        this.startDate = LocalDateHelper.convertString(tempHolder.startDate);
        this.endDate = LocalDateHelper.convertString(tempHolder.endDate);
        this.formativePeriod = tempHolder.formativePeriod;
        this.holdingPeriod = tempHolder.holdingPeriod;
        this.stockPoolCriteria = tempHolder.stockPoolCriteria;
        this.baseStockName = tempHolder.baseStockName;
        this.isCustomized = tempHolder.isCustomized;
        this.formateAndPickCriteria = tempHolder.formateAndPickCriteria;
    }
}
