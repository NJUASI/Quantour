package com.edu.nju.asi.infoCarrier.traceBack;

/**
 * Created by harvey on 17-4-6.
 */
public class FilterConditionRate implements Comparable<FilterConditionRate>{

    // 股票代码
    public String stockCode;

    // 指标
    public double indicator;

    // 得分
    public double score;

    public FilterConditionRate(String stockCode, double indicator, double score) {
        this.stockCode = stockCode;
        this.indicator = indicator;
        this.score = score;
    }

    //默认按降序方式排序
    @Override
    public int compareTo(FilterConditionRate o) {
        if(this.indicator > o.indicator){
            return -1;
        }
        else if(this.indicator == o.indicator){
            return 0;
        }
        else {
            return 1;
        }
    }
}
