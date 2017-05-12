package com.edu.nju.asi.vo;

import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/25.
 *
 * K线、均线总体时的起讫时间
 */
public class FirstLastDayVO {

    // 起始时间
    public LocalDate first;

    // 结束时间
    public LocalDate last;

    public FirstLastDayVO(LocalDate first, LocalDate last) {
        this.first = first;
        this.last = last;
    }
}
