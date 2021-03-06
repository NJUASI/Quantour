package com.edu.nju.asi.infoCarrier.traceBack;

import java.time.LocalDate;

/**
 * Created by harvey on 17-3-28.
 *
 * 回测累计收益率
 */
public class CumulativeReturn {

    // 当天的日期
    public LocalDate currentDate;

    // 当天的累计收益率
    public double cumulativeReturn;

    // 是否为最大回测点
    public boolean isTraceBack;

    public CumulativeReturn() {
    }

    public CumulativeReturn(LocalDate currentDate, double cumulativeReturn, boolean isTraceBack) {
        this.currentDate = currentDate;
        this.cumulativeReturn = cumulativeReturn;
        this.isTraceBack = isTraceBack;
    }
}
