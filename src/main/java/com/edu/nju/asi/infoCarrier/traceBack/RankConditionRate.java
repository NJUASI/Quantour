package com.edu.nju.asi.infoCarrier.traceBack;

/**
 * Created by Harvey on 2017/6/7.
 */
public class RankConditionRate {

    // 股票代码
    public String stockCode;

    // 得分
    public double score;

    public RankConditionRate(String stockCode, double score) {
        this.stockCode = stockCode;
        this.score = score;
    }
}
