package com.edu.nju.asi.infoCarrier.traceBack;

import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/4/10.
 */
public class DailyRate {

    // 当日收益指数
    public double rate;

    // 收益指数对应日期
    public LocalDate date;

    // 当前的收益指数是由多少只股票均分下来的
    public int count;

    public DailyRate() {
        count = 1;
    }

    public DailyRate(double rate, LocalDate date) {
        this.rate = rate;
        this.date = date;
        count = 1;
    }


    public DailyRate plus(DailyRate another) {
        double sum = this.rate * this.count + another.rate + another.count;
        double newRate = sum / (this.count + another.count);

        this.rate = newRate;
        this.count += another.count;
        return this;
    }

}
