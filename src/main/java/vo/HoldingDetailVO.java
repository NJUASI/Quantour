package vo;

import java.time.LocalDate;

/**
 * Created by harvey on 17-3-31.
 *
 * 仿照果仁网，历史持仓详情
 */
public class HoldingDetailVO {

    /**
     * 周期详情，即周期序号
     */
    public int periodSerial;

    /**
     * 开始日期
     */
    public LocalDate startDate;

    /**
     * 结束日期
     */
    public LocalDate endDate;

    /**
     * 股票持有只数
     */
    public int holdNum;

    /**
     * 买入只数
     */
    public int buyNum;

    /**
     * 卖出只数
     */
    public int sellNum;

    /**
     * 策略收益,相对于上一周期
     */
    public double strategyReturn;

    /**
     * 本期收益，相对上一周期
     */
    public double baseReturn;

    /**
     * 超额收益，相对上一周期
     */
    public double excessReturn;

    /**
     * 模拟投资
     */
    public double remainInvestment;

}
