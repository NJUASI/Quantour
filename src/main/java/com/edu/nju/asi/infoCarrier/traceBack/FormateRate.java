package com.edu.nju.asi.infoCarrier.traceBack;

/**
 * Created by harvey on 17-4-6.
 */
public class FormateRate implements Comparable<FormateRate>{

    // 股票代码
    public String stockCode;

    // 指标
    public Double indicatorVal;

    public FormateRate(String stockCode, Double indicatorVal) {
        this.stockCode = stockCode;
        this.indicatorVal = indicatorVal;
    }

    //默认按降序方式排序
    @Override
    public int compareTo(FormateRate o) {
        //两个中有一个为null
        if(this.indicatorVal == null && o.indicatorVal == null){
            return 0;
        }
        if(this.indicatorVal == null && o.indicatorVal != null){
            return 1;
        }
        if(this.indicatorVal != null && o.indicatorVal == null){
            return -1;
        }
        //两个都不为null
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
