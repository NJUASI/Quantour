package vo;

import java.time.LocalDate;

/**
 * Created by harvey on 17-3-28.
 *
 * 保存用户在界面上选择的回测的条件
 */
public class TracebackChoiceVO {

    // 回测区间的起始时间
    LocalDate startDate;

    // 回测区间的结束时间
    LocalDate endDate;

    // 形成期／乖离率
    int formativePeriod;

    // 持有期
    int holdingPeriod;

    // 板块
    public Object[] block;

    // 基准对比股票代码
    public String baseStockCode;

    // 1／动量策略，2／均值回归
    public int strategyType ;

    // 持有股票数
    public int holdingNum;


}
