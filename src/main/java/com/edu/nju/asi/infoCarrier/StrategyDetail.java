package com.edu.nju.asi.infoCarrier;

import com.edu.nju.asi.infoCarrier.traceBack.FilterCondition;
import com.edu.nju.asi.model.Strategy;

import java.util.List;

/**
 * Created by cuihua on 2017/6/2.
 */
public class StrategyDetail {

    /**
     * 策略的基本信息
     */
    public Strategy thisStrategy;

    /**
     * 所有的筛选条件
     */
    public List<FilterCondition> filterConditions;

    /**
     * 持有期
     */
    public int holdingPeriod;

    /**
     * 最大持有股票数
     */
    public int maxHoldingNum;

    /**
     * 当前用户对此策略是否有操作权限
     */
    boolean canUpdate;

    public StrategyDetail() {
    }

    public StrategyDetail(Strategy thisStrategy) {
        this.thisStrategy = thisStrategy;

        // TODO 根据thisStrategy的content解析出策略的条件



    }
}
