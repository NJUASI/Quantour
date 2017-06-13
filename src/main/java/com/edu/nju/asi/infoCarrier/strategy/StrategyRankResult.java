package com.edu.nju.asi.infoCarrier.strategy;

/**
 * Created by cuihua on 2017/6/10.
 *
 * 策略回测的评分结果
 */
public class StrategyRankResult {

    /**
     * 收益分数
     */
    public double profit;

    /**
     * 抗风险分数
     */
    public double antiRisk;

    /**
     * 流动性分数
     */
    public double fluidity;

    /**
     * 稳定性分数
     */
    public double stability;

    /**
     * 实盘分数
     */
    public double reality;


    public StrategyRankResult() {
    }

    public StrategyRankResult(double profit, double antiRisk, double fluidity, double stability, double reality) {
        this.profit = profit;
        this.antiRisk = antiRisk;
        this.fluidity = fluidity;
        this.stability = stability;
        this.reality = reality;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getAntiRisk() {
        return antiRisk;
    }

    public void setAntiRisk(double antiRisk) {
        this.antiRisk = antiRisk;
    }

    public double getFluidity() {
        return fluidity;
    }

    public void setFluidity(double fluidity) {
        this.fluidity = fluidity;
    }

    public double getStability() {
        return stability;
    }

    public void setStability(double stability) {
        this.stability = stability;
    }

    public double getReality() {
        return reality;
    }

    public void setReality(double reality) {
        this.reality = reality;
    }
}
