package com.edu.nju.asi.infoCarrier;

import com.edu.nju.asi.utilities.LocalDateHelper;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/12.
 *
 * 股票比较时的选择条件
 */
public class StockComparisionCriteria {

    // 股票A
    public String stockCode1;

    // 股票B
    public String stockCode2;

    // 区间内开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate start;

    // 区间内结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate end;

    public StockComparisionCriteria() {
    }

    public StockComparisionCriteria(String stockCode1, String stockCode2, LocalDate start, LocalDate end) {
        this.stockCode1 = stockCode1;
        this.stockCode2 = stockCode2;
        this.start = start;
        this.end = end;
    }
}
