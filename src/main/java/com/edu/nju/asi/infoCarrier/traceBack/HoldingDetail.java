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
    public LocalDate startDate;

    /**
     * 结束日期
     */
    public LocalDate endDate;

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


    public HoldingDetail() {
    }

    public HoldingDetail(int periodSerial, LocalDate startDate, LocalDate endDate, double strategyReturn, double baseReturn, double excessReturn, double remainInvestment) {
        this.periodSerial = periodSerial;
        this.startDate = startDate;
        this.endDate = endDate;
        this.strategyReturn = Double.parseDouble(NumberFormat.decimaFormat(strategyReturn, 4));
        this.baseReturn = Double.parseDouble(NumberFormat.decimaFormat(baseReturn, 4));
        this.excessReturn = Double.parseDouble(NumberFormat.decimaFormat(excessReturn, 4));
        this.remainInvestment = Double.parseDouble(NumberFormat.decimaFormat(remainInvestment, 2));
    }


    public int getPeriodSerial() {
        return periodSerial;
    }

    public void setPeriodSerial(int periodSerial) {
        this.periodSerial = periodSerial;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getHoldingNum() {
        return holdingNum;
    }

    public void setHoldingNum(int holdingNum) {
        this.holdingNum = holdingNum;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public int getSellNum() {
        return sellNum;
    }

    public void setSellNum(int sellNum) {
        this.sellNum = sellNum;
    }

    public double getStrategyReturn() {
        return strategyReturn;
    }

    public void setStrategyReturn(double strategyReturn) {
        this.strategyReturn = strategyReturn;
    }

    public double getBaseReturn() {
        return baseReturn;
    }

    public void setBaseReturn(double baseReturn) {
        this.baseReturn = baseReturn;
    }

    public double getExcessReturn() {
        return excessReturn;
    }

    public void setExcessReturn(double excessReturn) {
        this.excessReturn = excessReturn;
    }

    public double getRemainInvestment() {
        return remainInvestment;
    }

    public void setRemainInvestment(double remainInvestment) {
        this.remainInvestment = remainInvestment;
    }
}
