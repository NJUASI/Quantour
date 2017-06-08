package com.edu.nju.asi.infoCarrier.traceBack;

import java.util.List;

/**
 * Created by Harvey on 2017/6/7.
 *
 * 保存策略的累计收益率和调仓日期的操作详情
 */
public class StrategyCumulativeAndTransferDetail {

    /**
     * 策略累计收益率
     */
    public List<CumulativeReturn> strategyCumulativeReturn;

    /**
     * 调仓日期的操作详情
     */
    public List<TransferDayDetail> transferDayDetails;

}
