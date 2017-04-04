package service.serviceImpl.TracebackService.TracebackStrategy;

import service.serviceImpl.TracebackService.AllTracebackStrategy;
import vo.CumulativeReturnVO;
import vo.TracebackCriteriaVO;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by harvey on 17-3-31.
 */
public class MomentumStrategy implements AllTracebackStrategy {

    /**
     * 时间区间的第一天
     */
    LocalDate startDate;

    /**
     * 时间区间的第二天
     */
    LocalDate endDate;

    /**
     * 形成期
     */
    int formativePeriod;

    /**
     * 持有期
     */
    int holdingPeriod;

    /**
     * 目标股票池,仅保存stock的code
     */
    List<String> stockPool;

    /**
     * 当前持有的股票，仅保存股票的代码
     */
    List<String> curHoldingStocks;

    /**
     * 策略收益率
     */
    List<CumulativeReturnVO> strategyReturn;

}
