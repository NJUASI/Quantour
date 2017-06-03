package com.edu.nju.asi.infoCarrier.traceBack;

import java.time.LocalDate;
import java.util.List;

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
     * 持有期
     */
    public int holdingPeriod;

    /**
     * 板块
     */
    public StockPoolCriteria stockPoolCriteria;

    /**
     * 最大持有股票数
     */
    public int maxHoldingNum;

    /**
     * 基准股票的名称，如沪深300
     */
    public String baseStockName;

    /**
     * 所有的筛选条件（仿果仁网）
     */
    public List<FilterCondition> filterConditions;

    public TraceBackCriteria(){

    }
    public TraceBackCriteria(LocalDate startDate, LocalDate endDate, int holdingPeriod, int maxHoldingNum, StockPoolCriteria stockPoolCriteria, String baseStockName, List<FilterCondition> filterConditions) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.holdingPeriod = holdingPeriod;
        this.maxHoldingNum = maxHoldingNum;
        this.stockPoolCriteria = stockPoolCriteria;
        this.baseStockName = baseStockName;
        this.filterConditions = filterConditions;
    }


    public TraceBackCriteria(TraceBackCriteria criteria) {
        this.startDate = criteria.startDate;
        this.endDate = criteria.endDate;
        this.holdingPeriod = criteria.holdingPeriod;
        this.maxHoldingNum = criteria.maxHoldingNum;
        this.stockPoolCriteria = criteria.stockPoolCriteria;
        this.baseStockName = criteria.baseStockName;
        this.filterConditions = criteria.filterConditions;
    }
}
