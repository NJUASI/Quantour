package com.edu.nju.asi.infoCarrier.traceBack;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by harvey on 17-3-28.
 *
 * 保存用户在界面上选择的回测的条件
 */
public class TraceBackCriteria implements Comparable<TraceBackCriteria>{

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
     * 是否使用自选股池
     */
    public boolean isCustomized;

    /**
     * 所有的筛选条件（仿果仁网）
     */
    public List<FilterCondition> filterConditions;

    /**
     * 所有的排名条件（仿果仁网）
     */
    public List<RankCondition> rankConditions;



    /*
    市场择时调仓
     */
    /**
     * 所有的市场择时条件（仿果仁网）
     */
    public List<MarketSelectingCondition> marketSelectingConditions;

    /**
     * （变为熊市时的）调仓比例
     */
    public double adjustPositionPercent;

    /**
     * 由熊变牛需要满足的条件数目
     */
    public int bearToBull_num;

    /**
     * 由牛变熊需要满足的条件数目
     */
    public int bullToBear_num;



    public TraceBackCriteria(){
    }

    public TraceBackCriteria(LocalDate startDate, LocalDate endDate, int holdingPeriod, StockPoolCriteria stockPoolCriteria, int maxHoldingNum, String baseStockName, boolean isCustomized, List<FilterCondition> filterConditions, List<RankCondition> rankConditions, List<MarketSelectingCondition> marketSelectingConditions, double adjustPositionPercent, int bearToBull_num, int bullToBear_num) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.holdingPeriod = holdingPeriod;
        this.stockPoolCriteria = stockPoolCriteria;
        this.maxHoldingNum = maxHoldingNum;
        this.baseStockName = baseStockName;
        this.isCustomized = isCustomized;
        this.filterConditions = filterConditions;
        this.rankConditions = rankConditions;
        this.marketSelectingConditions = marketSelectingConditions;
        this.adjustPositionPercent = adjustPositionPercent;
        this.bearToBull_num = bearToBull_num;
        this.bullToBear_num = bullToBear_num;
    }

    public TraceBackCriteria(TraceBackCriteria criteria) {
        this.startDate = criteria.startDate;
        this.endDate = criteria.endDate;
        this.holdingPeriod = criteria.holdingPeriod;
        this.maxHoldingNum = criteria.maxHoldingNum;
        this.stockPoolCriteria = criteria.stockPoolCriteria;
        this.baseStockName = criteria.baseStockName;
        this.filterConditions = criteria.filterConditions;
        this.rankConditions = criteria.rankConditions;
    }

    public TraceBackCriteria(TraceBackCriteria originTraceBackCriteria, List<FilterCondition> filterConditions, List<RankCondition> rankConditions) {
        this.startDate = originTraceBackCriteria.startDate;
        this.endDate = originTraceBackCriteria.endDate;
        this.holdingPeriod = originTraceBackCriteria.holdingPeriod;
        this.stockPoolCriteria = originTraceBackCriteria.stockPoolCriteria;
        this.maxHoldingNum = originTraceBackCriteria.maxHoldingNum;
        this.baseStockName = originTraceBackCriteria.baseStockName;
        this.isCustomized = originTraceBackCriteria.isCustomized;
        this.filterConditions = filterConditions;
        this.rankConditions = rankConditions;
        this.marketSelectingConditions = originTraceBackCriteria.marketSelectingConditions;
        this.adjustPositionPercent = originTraceBackCriteria.adjustPositionPercent;
        this.bearToBull_num = originTraceBackCriteria.bearToBull_num;
        this.bullToBear_num = originTraceBackCriteria.bullToBear_num;


    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getHoldingPeriod() {
        return holdingPeriod;
    }

    public StockPoolCriteria getStockPoolCriteria() {
        return stockPoolCriteria;
    }

    public int getMaxHoldingNum() {
        return maxHoldingNum;
    }

    public String getBaseStockName() {
        return baseStockName;
    }

    public List<FilterCondition> getFilterConditions() {
        return filterConditions;
    }

    public List<RankCondition> getRankConditions() {
        return rankConditions;
    }

    public List<MarketSelectingCondition> getMarketSelectingConditions() {
        return marketSelectingConditions;
    }

    public double getAdjustPositionPercent() {
        return adjustPositionPercent;
    }

    public int getBearToBull_num() {
        return bearToBull_num;
    }

    public int getBullToBear_num() {
        return bullToBear_num;
    }


    @Override
    public int compareTo(TraceBackCriteria o) {
        boolean isEqual = true;
        for(int i = 0; i < filterConditions.size(); i++){
            if(filterConditions.get(i).compareTo(o.filterConditions.get(i)) != 0){
                isEqual = false;
                break;
            }
        }
        if(!isEqual){
            return 1;
        }
        else {
            for(int i = 0; i < rankConditions.size(); i++){
                if(rankConditions.get(i).compareTo(o.rankConditions.get(i)) != 0){
                    isEqual = false;
                    break;
                }
            }
        }
        if(isEqual){
            return 0;
        }
        else {
            return -1;
        }
    }
}
