package com.edu.nju.asi.infoCarrier.traceBack;

import com.edu.nju.asi.utilities.enums.IndicatorType;
import com.edu.nju.asi.utilities.enums.RankType;

/**
 * Created by Harvey on 2017/6/7.
 *
 * 表示排名条件
 */
public class RankCondition implements Comparable<RankCondition>{

    /**
     * 指标
     */
    public IndicatorType indicatorType;

    /**
     * 次序
     */
    public RankType rankType;

    /**
     * 权重
     */
    public int weight;

    /**
     * 形成期(若没有形成期则为1)
     */
    public int formativePeriod;

    public RankCondition() {
    }

    public RankCondition(IndicatorType indicatorType, RankType rankType, int weight, int formativePeriod) {
        this.indicatorType = indicatorType;
        this.rankType = rankType;
        this.weight = weight;
        this.formativePeriod = formativePeriod;
    }

    @Override
    public int compareTo(RankCondition o) {
        return o.weight - this.weight;
    }
}
