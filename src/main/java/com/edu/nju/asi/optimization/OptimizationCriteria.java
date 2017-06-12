package com.edu.nju.asi.optimization;

import com.edu.nju.asi.infoCarrier.traceBack.FilterCondition;
import com.edu.nju.asi.infoCarrier.traceBack.MarketSelectingCondition;
import com.edu.nju.asi.infoCarrier.traceBack.RankCondition;
import com.edu.nju.asi.infoCarrier.traceBack.StockPoolCriteria;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Harvey on 2017/6/13.
 *
 * 优化时选择的条件，默认只有筛选条件、排名条件的形成天数以及持有期可以更改
 */
public class OptimizationCriteria {

    /**
     * 回测区间的起始时间
     */
    public LocalDate startDate;

    /**
     * 回测区间的结束时间
     */
    public LocalDate endDate;

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

}
