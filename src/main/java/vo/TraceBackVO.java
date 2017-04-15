package vo;

import java.util.List;

/**
 * Created by harvey on 17-4-9.
 */
public class TraceBackVO {

    /**
     * 基准累计收益率
     */

    public List<CumulativeReturnVO> baseCumulativeReturn;

    /**
     * 策略累计收益率
     */
    public List<CumulativeReturnVO> strategyCumulativeReturn;

    /**
     * 历史持仓详情
     */
    public List<HoldingDetailVO> holdingDetailVOS;

    /**
     * 数值型数据
     */
    public TraceBackNumValVO traceBackNumValVO;

    /**
     * 相对指数收益
     */
    public ReturnPeriodVO relativeReturnPeriodVO;

    /**
     * 绝对指数收益
     */
    public ReturnPeriodVO absoluteReturnPeriodVO;

    /**
     * 给定形成期的超额收益率和策略胜率的分布信息
     */
    public List<ExcessAndWinRateDistVO> certainFormates;

    /**
     * 给定持有期的超额收益率和策略胜率的分部信息
     */
    public List<ExcessAndWinRateDistVO>  certainHoldings;

}
