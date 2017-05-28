package com.edu.nju.asi.infoCarrier;

import com.edu.nju.asi.utilities.LocalDateHelper;
import com.edu.nju.asi.utilities.tempHolder.StockComparisionCriteriaTempHolder;

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
    public LocalDate start;

    // 区间内结束时间
    public LocalDate end;

    public StockComparisionCriteria() {
    }

    public StockComparisionCriteria(String stockCode1, String stockCode2, LocalDate start, LocalDate end) {
        this.stockCode1 = stockCode1;
        this.stockCode2 = stockCode2;
        this.start = start;
        this.end = end;
    }

    public StockComparisionCriteria(StockComparisionCriteriaTempHolder holder) {
        this.stockCode1 = holder.stockCode1;
        this.stockCode2 = holder.stockCode2;
        this.start = LocalDateHelper.convertString(holder.startDate);
        this.end = LocalDateHelper.convertString(holder.endDate);
    }
}
