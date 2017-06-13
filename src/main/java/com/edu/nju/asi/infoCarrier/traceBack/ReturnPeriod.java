package com.edu.nju.asi.infoCarrier.traceBack;

import java.util.Map;

/**
 * Created by Harvey on 2017/4/15.
 *
 * 收益周期统计，保存绝对收益和相对收益指数，详见果仁网
 */
public class ReturnPeriod {

    /**
     * 正收益周期数
     */
    public int positivePeriodsNum;

    /**
     * 负收益周期数
     */
    public int negativePeriodNum;

    /**
     * 策略胜率
     */
    public double winRate;

    /**
     * 保存正收益率和对应的次数
     */
    public Map<Double, Integer> positiveNums;

    /**
     * 保存负收益率和对应的次数
     */
    public Map<Double, Integer> negativeNums;


    public int getPositivePeriodsNum() {
        return positivePeriodsNum;
    }

    public void setPositivePeriodsNum(int positivePeriodsNum) {
        this.positivePeriodsNum = positivePeriodsNum;
    }

    public int getNegativePeriodNum() {
        return negativePeriodNum;
    }

    public void setNegativePeriodNum(int negativePeriodNum) {
        this.negativePeriodNum = negativePeriodNum;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public Map<Double, Integer> getPositiveNums() {
        return positiveNums;
    }

    public void setPositiveNums(Map<Double, Integer> positiveNums) {
        this.positiveNums = positiveNums;
    }

    public Map<Double, Integer> getNegativeNums() {
        return negativeNums;
    }

    public void setNegativeNums(Map<Double, Integer> negativeNums) {
        this.negativeNums = negativeNums;
    }
}
