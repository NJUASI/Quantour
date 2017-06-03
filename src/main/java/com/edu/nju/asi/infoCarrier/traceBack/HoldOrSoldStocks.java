package com.edu.nju.asi.infoCarrier.traceBack;

import java.time.LocalDate;

/**
 * Created by Harvey on 2017/6/3.
 *
 * 保存最近卖出的股票
 */
public class HoldOrSoldStocks {

    /**
     *  股票名
     */
    public String stockName;

    /**
     * 股票代码
     */
    public String stockCode;

    /**
     * 买入日期
     */
    public LocalDate buyDate;

    /**
     * 卖出日期
     */
    public LocalDate sellDate;

    /**
     * 买入价格（前复权）
     */
    public double buyPrice;

    /**
     * 卖出价格(前复权)
     */
    public double sellPrice;

    /**
     * 涨跌幅（卖出-买入/买入）
     */
    public double changeRate;

    public HoldOrSoldStocks(String stockName, String stockCode, LocalDate buyDate, LocalDate sellDate, double buyPrice, double sellPrice, double changeRate) {
        this.stockName = stockName;
        this.stockCode = stockCode;
        this.buyDate = buyDate;
        this.sellDate = sellDate;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.changeRate = changeRate;
    }

    public HoldOrSoldStocks(String stockName, String stockCode, LocalDate buyDate, double buyPrice) {
        this.stockName = stockName;
        this.stockCode = stockCode;
        this.buyDate = buyDate;
        this.buyPrice = buyPrice;
    }

    public HoldOrSoldStocks(HoldOrSoldStocks holdOrSoldStocks, LocalDate sellDate, double sellPrice) {
        this.stockName = holdOrSoldStocks.stockName;
        this.stockCode = holdOrSoldStocks.stockCode;
        this.buyDate = holdOrSoldStocks.buyDate;
        this.buyPrice = holdOrSoldStocks.buyPrice;
        this.sellDate = sellDate;
        this.sellPrice = sellPrice;

        this.changeRate = sellPrice - buyPrice / buyPrice;
    }
}
