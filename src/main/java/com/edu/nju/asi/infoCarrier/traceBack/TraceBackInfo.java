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
     * 给定形成期的超额收益率和策略胜率的分布信息
     */
    public List<ExcessAndWinRateDist> certainFormates;

    /**
     * 给定持有期的超额收益率和策略胜率的分布信息
     */
    public List<ExcessAndWinRateDist> certainHoldings;

    /**
     * 保存最大回撤率的数据
     */
    public MaxTraceBack maxTraceBack;

}
