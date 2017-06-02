package com.edu.nju.asi.infoCarrier.traceBack;

import com.edu.nju.asi.utilities.enums.IndicatorType;
import com.edu.nju.asi.utilities.enums.ComparotorType;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 筛选条件（仿果仁网）
 */
public class FilterCondition {

    /**
     * 指标
     */
    public IndicatorType indicatorType;

    /**
     * 比较符
     */
    public ComparotorType comparotorType;

    /**
     * 比较符的值
     */
    public int value;

    /**
     * 排名的权重
     */
    public double weight;

    /**
     * 形成期(若没有形成期则为0)
     */
    public int formativePeriod;

    public FilterCondition() {
    }

    public FilterCondition(IndicatorType indicatorType, ComparotorType comparotorType, int value, double weight, int formativePeriod) {
        this.indicatorType = indicatorType;
        this.comparotorType = comparotorType;
        this.value = value;
        this.weight = weight;
        this.formativePeriod = formativePeriod;
    }
}
