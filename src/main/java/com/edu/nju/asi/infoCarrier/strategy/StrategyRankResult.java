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
    public double reailty;

    public StrategyRankResult(double profit, double antiRisk, double fluidity, double stability, double reailty) {
        this.profit = profit;
        this.antiRisk = antiRisk;
        this.fluidity = fluidity;
        this.stability = stability;
        this.reailty = reailty;
    }
}
