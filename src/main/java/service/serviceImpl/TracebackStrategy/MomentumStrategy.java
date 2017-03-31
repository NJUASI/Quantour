package service.serviceImpl.TracebackStrategy;

import vo.CumulativeReturnVO;
import vo.StockVO;
import vo.TracebackCriteriaVO;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by harvey on 17-3-31.
 */
public class MomentumStrategy {

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
     * 当前持有的股票
     */
    List<StockVO> curHoldingStocks;

    /**
     * 策略收益率
     */
    List<CumulativeReturnVO> strategyReturn;

    public void setTracebackCriteria(TracebackCriteriaVO vo){

    }
}
