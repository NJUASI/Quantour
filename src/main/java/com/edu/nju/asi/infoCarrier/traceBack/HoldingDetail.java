package com.edu.nju.asi.infoCarrier.traceBack;

import com.edu.nju.asi.utilities.NumberFormat;

import java.time.LocalDate;

/**
 * Created by harvey on 17-3-31.
 *
 * 仿照果仁网，历史持仓详情
 */
public class HoldingDetail {

    /**
     * 周期详情，即周期序号
     */
    public int periodSerial;

    /**
     * 开始日期
     */
    public String startDate;

    /**
     * 结束日期
     */
    public String endDate;

    /**
     * 股票持有只数
     */
    public int holdingNum;

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
     * 基准收益，相对上一周期
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

    public HoldingDetail(int periodSerial, LocalDate startDate, LocalDate endDate, double strategyReturn, double baseReturn, double excessReturn, double remainInvestment) {
        this.periodSerial = periodSerial;
        this.startDate = startDate.toString();
        this.endDate = endDate.toString();
        this.strategyReturn = Double.parseDouble(NumberFormat.decimaFormat(strategyReturn, 4));
        this.baseReturn = Double.parseDouble(NumberFormat.decimaFormat(baseReturn, 4));
        this.excessReturn = Double.parseDouble(NumberFormat.decimaFormat(excessReturn, 4));
        this.remainInvestment = Double.parseDouble(NumberFormat.decimaFormat(remainInvestment, 2));
    }
}
