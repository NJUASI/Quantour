package com.edu.nju.asi.infoCarrier.traceBack;

/**
 * Created by harvey on 17-4-6.
 */
public class FormativePeriodRate implements Comparable<FormativePeriodRate>{

    // 股票代码
    public String stockCode;

    // 一段时间内的累计收益率（MS）／相应的偏离度（MR） / 交易量
    public double periodReturn;

    public FormativePeriodRate(String stockCode, double periodReturn) {
        this.stockCode = stockCode;
        this.periodReturn = periodReturn;
    }

    //默认按降序方式排序
    @Override
    public int compareTo(FormativePeriodRate o) {
        if(this.periodReturn > o.periodReturn){
            return -1;
        }
        else if(this.periodReturn == o.periodReturn){
            return 0;
        }
        else {
            return 1;
        }
    }
}
