package com.edu.nju.asi.infoCarrier.traceBack;

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
    public StockPoolCriteria stockPoolVO;

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
    public TraceBackCriteria(LocalDate startDate, LocalDate endDate, int formativePeriod, int holdingPeriod, StockPoolCriteria stockPoolVO, String baseStockName, boolean isCustomized, FormateAndPickCriteria formateAndPickCriteria) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.formativePeriod = formativePeriod;
        this.holdingPeriod = holdingPeriod;
        this.stockPoolVO = stockPoolVO;
//        this.holdingNum = holdingNum;
        this.baseStockName = baseStockName;
        this.isCustomized = isCustomized;
        this.formateAndPickCriteria = formateAndPickCriteria;
    }


    public TraceBackCriteria(TraceBackCriteria vo) {
        this.startDate = vo.startDate;
        this.endDate = vo.endDate;
        this.formativePeriod = vo.formativePeriod;
        this.holdingPeriod = vo.holdingPeriod;
        this.stockPoolVO = vo.stockPoolVO;
//        this.holdingNum = holdingNum;
        this.baseStockName = vo.baseStockName;
        this.isCustomized = vo.isCustomized;
        this.formateAndPickCriteria = vo.formateAndPickCriteria;
    }
}