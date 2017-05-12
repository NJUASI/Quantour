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

    public int count;

    public DailyRate() {
        count = 1;
    }

    public DailyRate(double rate, LocalDate date) {
        this.rate = rate;
        this.date = date;
        count = 1;
    }
}
