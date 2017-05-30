package com.edu.nju.asi.infoCarrier.traceBack;

import com.edu.nju.asi.utilities.NumberFormat;

/**
 * Created by Harvey on 2017/4/15.
 *
 * 超额收益率与不同形成期/持有期的关系图以及策略胜率与不同形成期/持有期的关系图
 */
public class ExcessAndWinRateDist {

    /**
     * 相对强弱周期
     */
    public int relativeCycle;

    /**
     * 超额收益
     */
    public double excessRate;

    /**
     * 策略胜率
     * TODO 绝对还是相对？
     */
    public double winRate;

    public ExcessAndWinRateDist(int relativeCycle, double excessRate, double winRate) {
        this.relativeCycle = relativeCycle;
        this.excessRate = Double.parseDouble(NumberFormat.decimaFormat(excessRate, 4));
        this.winRate = Double.parseDouble(NumberFormat.decimaFormat(winRate, 4));
    }
}
