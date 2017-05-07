package com.edu.nju.asi.vo;

import java.util.List;

/**
 * Created by cuihua on 2017/4/9.
 */
public class TraceBackStrategyVO {

    // 策略累计收益率
    public List<CumulativeReturnVO> strategyCumulativeReturn;

    // 历史持仓详情
    public List<HoldingDetailVO> holdingDetailVOS;

    public TraceBackStrategyVO(List<CumulativeReturnVO> strategyCumulativeReturn, List<HoldingDetailVO> holdingDetailVOS) {
        this.strategyCumulativeReturn = strategyCumulativeReturn;
        this.holdingDetailVOS = holdingDetailVOS;
    }
}
