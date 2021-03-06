package com.edu.nju.asi.infoCarrier.traceBack;

import com.edu.nju.asi.utilities.enums.IndicatorType;
import com.edu.nju.asi.utilities.enums.ComparatorType;

import java.io.Serializable;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 筛选条件（仿果仁网）
 */
public class FilterCondition implements Comparable<FilterCondition> ,Serializable{

    /**
     * 指标
     */
    public IndicatorType indicatorType;

    /**
     * 比较符
     */
    public ComparatorType comparatorType;

    /**
     * 比较符的值
     */
    public int value;

    /**
     * 形成期(若没有形成期则为1)
     */
    public int formativePeriod;

    public FilterCondition() {
    }

    public FilterCondition(IndicatorType indicatorType, ComparatorType comparatorType, int value, int formativePeriod) {
        this.indicatorType = indicatorType;
        this.comparatorType = comparatorType;
        this.value = value;
        this.formativePeriod = formativePeriod;
    }

    @Override
    public int compareTo(FilterCondition o) {
        return o.value - this.value;
    }
}
