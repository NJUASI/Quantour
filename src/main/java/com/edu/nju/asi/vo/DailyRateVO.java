package com.edu.nju.asi.vo;

import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/4/10.
 */
public class DailyRateVO {

    // 当日收益指数
    public double rate;

    // 收益指数对应日期
    public LocalDate date;

    public int count;

    public DailyRateVO() {
        count = 1;
    }

    public DailyRateVO(double rate, LocalDate date) {
        this.rate = rate;
        this.date = date;
        count = 1;
    }
}
