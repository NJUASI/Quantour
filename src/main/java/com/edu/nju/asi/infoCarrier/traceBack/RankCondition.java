package com.edu.nju.asi.infoCarrier.traceBack;

import com.edu.nju.asi.utilities.enums.IndicatorType;
import com.edu.nju.asi.utilities.enums.RankType;

/**
 * Created by Harvey on 2017/6/7.
 *
 * 表示排名条件
 */
public class RankCondition {

    /**
     * 指标
     */
    IndicatorType indicatorType;

    /**
     * 次序
     */
    RankType rankType;

    /**
     * 权重
     */
    int weight;

    public RankCondition(IndicatorType indicatorType, RankType rankType, int weight) {
        this.indicatorType = indicatorType;
        this.rankType = rankType;
        this.weight = weight;
    }
}
