package vo;

import utilities.enums.TracebackStrategy;

import java.time.LocalDate;

/**
 * Created by harvey on 17-3-28.
 *
 * 保存用户在界面上选择的回测的条件
 */
public class TracebackCriteriaVO {

    /**
     * 回测区间的起始时间
     */
    public LocalDate startDate;

    /**
     * 回测区间的结束时间
     */
    public LocalDate endDate;

    /**
     * 形成期／乖离率
     */
    public int formativePeriod;

    /**
     * 持有期
     */
    public int holdingPeriod;

    /**
     * 板块
     */
    public StockPoolCriteriaVO stockPoolVO;

    /**
     * 1／动量策略，2／均值回归
     */
    public TracebackStrategy strategyType ;

    /**
     * 持有股票数
     */
    public int holdingNum;

    /**
     * 基准股票的名称，如沪深300
     */
    public String baseStockName;
}
