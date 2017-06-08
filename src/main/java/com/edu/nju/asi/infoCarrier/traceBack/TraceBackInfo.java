package com.edu.nju.asi.infoCarrier.traceBack;

import java.util.List;

/**
 * Created by harvey on 17-4-9.
 */
public class TraceBackInfo {

    /**
     * 基准累计收益率
     */
    public List<CumulativeReturn> baseCumulativeReturn;

    /**
     * 策略累计收益率
     */
    public List<CumulativeReturn> strategyCumulativeReturn;

    /**
     * 调仓日期操作的详情
     */
    public List<TransferDayDetail> transferDayDetails;

    /**
     * 历史持仓详情
     */
    public List<HoldingDetail> holdingDetails;

    /**
     * 数值型数据
     */
    public TraceBackNumVal traceBackNumVal;

    /**
     * 相对指数收益
     */
    public ReturnPeriod relativeReturnPeriod;

    /**
     * 绝对指数收益
     */
    public ReturnPeriod absoluteReturnPeriod;

    /**
     * 保存最大回撤率的数据
     */
    public MaxTraceBack maxTraceBack;

}
