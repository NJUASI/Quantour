package com.edu.nju.asi.infoCarrier.traceBack;

import java.time.LocalDate;

/**
 * Created by Harvey on 2017/6/3.
 *
 * 调仓日期操作的详情
 */
public class TransferDayDetail {

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
     * 买入价格（不复权）
     */
    public double buyPrice;

    /**
     * 卖出价格（不复权）
     */
    public double sellPrice;

    /**
     * 涨跌幅（卖出-买入/买入）
     */
    public double changeRate;

//    /**
//     * 当前仓位
//     */
//    public double curPosition;
//
//    /**
//     * 当前仓位所占的价值
//     */
//    public double curPositionMoney;


    public TransferDayDetail() {
    }

    public TransferDayDetail(String stockName, String stockCode, LocalDate buyDate, LocalDate sellDate, double buyPrice, double sellPrice, double changeRate) {
        this.stockName = stockName;
        this.stockCode = stockCode;
        this.buyDate = buyDate;
        this.sellDate = sellDate;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.changeRate = changeRate;
    }

    /**
     * 买入时使用
     */
    public TransferDayDetail(String stockName, String stockCode, LocalDate buyDate, double buyPrice) {
        this.stockName = stockName;
        this.stockCode = stockCode;
        this.buyDate = buyDate;
        this.buyPrice = buyPrice;
//        this.curPosition = curPosition;
//        this.curPositionMoney = curPositionMoney;
    }

    /**
     * 卖出时使用
     */
    public TransferDayDetail(TransferDayDetail transferDayDetail, LocalDate sellDate, double sellPrice) {
        this.stockName = transferDayDetail.stockName;
        this.stockCode = transferDayDetail.stockCode;
        this.buyDate = transferDayDetail.buyDate;
        this.buyPrice = transferDayDetail.buyPrice;
//        this.curPosition = transferDayDetail.curPosition;
//        this.curPositionMoney = transferDayDetail.curPositionMoney;

        this.sellDate = sellDate;
        this.sellPrice = sellPrice;

        this.changeRate = (sellPrice - buyPrice) / buyPrice;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }

    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(double changeRate) {
        this.changeRate = changeRate;
    }
}
