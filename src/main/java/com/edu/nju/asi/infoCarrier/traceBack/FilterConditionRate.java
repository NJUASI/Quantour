package com.edu.nju.asi.infoCarrier.traceBack;

/**
 * Created by harvey on 17-4-6.
 */
public class FilterConditionRate implements Comparable<FilterConditionRate>{

    // 股票代码
    public String stockCode;

    // 指标
    public Double indicatorVal;

    // 得分
    public double score;

    public FilterConditionRate(String stockCode, Double indicatorVal, double score) {
        this.stockCode = stockCode;
        this.indicatorVal = indicatorVal;
        this.score = score;
    }

    //默认按降序方式排序
    @Override
    public int compareTo(FilterConditionRate o) {
        if(this.indicatorVal > o.indicatorVal){
            return -1;
        }
        else if(this.indicatorVal.doubleValue() == o.indicatorVal.doubleValue()){
            return 0;
        }
        else {
            return 1;
        }
    }
}
