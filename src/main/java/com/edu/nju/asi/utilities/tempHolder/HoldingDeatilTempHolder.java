package com.edu.nju.asi.utilities.tempHolder;

import com.edu.nju.asi.infoCarrier.traceBack.HoldingDetail;

/**
 * Created by cuihua on 2017/5/18.
 */
public class HoldingDeatilTempHolder {
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


    public HoldingDeatilTempHolder(HoldingDetail hd) {
        this.periodSerial = hd.periodSerial;
        this.startDate = hd.startDate.toString();
        this.endDate = hd.endDate.toString();
        this.holdingNum = hd.holdingNum;
        this.buyNum = hd.buyNum;
        this.sellNum = hd.sellNum;
        this.strategyReturn = hd.strategyReturn;
        this.baseReturn = hd.baseReturn;
        this.excessReturn = hd.excessReturn;
        this.remainInvestment = hd.remainInvestment;
    }
}
