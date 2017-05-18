package com.edu.nju.asi.utilities.tempHolder;

import com.edu.nju.asi.infoCarrier.traceBack.ExcessAndWinRateDist;
import com.edu.nju.asi.utilities.NumberFormat;

/**
 * Created by cuihua on 2017/5/18.
 */
public class ExcessAndWinRateDistTempHolder {

    /**
     * 相对强弱周期
     */
    public int relativeCycle;

    /**
     * 超额收益
     */
    public String excessRate;

    /**
     * 策略胜率
     * TODO 绝对还是相对？
     */
    public String winRate;

    public ExcessAndWinRateDistTempHolder(ExcessAndWinRateDist dist) {
        this.relativeCycle = dist.relativeCycle;
        this.excessRate = NumberFormat.percentFormat(dist.excessRate, 2);
        this.winRate = NumberFormat.percentFormat(dist.winRate, 2);
    }

}
