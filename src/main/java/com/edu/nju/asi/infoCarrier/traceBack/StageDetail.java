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
    public Double startPrice;

    /**
     * 结束价格（后复权）
     */
    public Double endPrice;

    /**
     * 涨跌幅
     */
    public Double changeRate;

    public StageDetail(String stockCode, String stockName, Double startPrice, Double endPrice) {
        this.stockCode = stockCode;
        this.stockName = stockName;

        if(endPrice != null && startPrice != null){
            this.startPrice = startPrice;
            this.endPrice = endPrice;
            this.changeRate = (endPrice - startPrice) / startPrice;
        }else {
            this.endPrice = null;
            this.changeRate = null;
        }
    }
}
