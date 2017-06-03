package com.edu.nju.asi.infoCarrier;

import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.model.Strategy;

/**
 * Created by cuihua on 2017/6/2.
 */
public class StrategyDetail {

    /**
     * 策略的基本信息
     */
    public Strategy thisStrategy;


    /**
     * 回测条件
     */
    public TraceBackCriteria criteria;

    public StrategyDetail() {
    }

    public StrategyDetail(Strategy thisStrategy) {
        this.thisStrategy = thisStrategy;

        // TODO 根据thisStrategy的content解析出策略的条件


    }
}
