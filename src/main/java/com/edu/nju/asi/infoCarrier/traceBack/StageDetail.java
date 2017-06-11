package com.edu.nju.asi.infoCarrier.traceBack;

/**
 * Created by Harvey on 2017/6/10.
 */
public class StageDetail {

    /**
     * 股票代码
     */
    public String stockCode;

    /**
     * 股票名
     */
    public String stockName;

    /**
     * 开始价格（前复权）
     */
    public double startPrice;

    /**
     * 结束价格（后复权）
     */
    public double endPrice;

    /**
     * 涨跌幅
     */
    public double changeRate;


    public StageDetail() {
    }

    public StageDetail(String stockCode, String stockName, double startPrice, double endPrice) {
        this.stockCode = stockCode;
        this.stockName = stockName;
        this.startPrice = startPrice;
        this.endPrice = endPrice;

        this.changeRate = (endPrice - startPrice) / startPrice;
    }
}
