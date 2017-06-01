package com.edu.nju.asi.model;


import com.edu.nju.asi.spider.Model.NormalStock;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/5/7.
 * <p>
 * 股票
 */
@Entity
@Table(name = "stock")
public class Stock implements Serializable {

    // 股票代码
    @Id
    @GenericGenerator(name = "myGenerator", strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    private StockID stockID;

    // 股票名称
    @Basic
    @Column(length = 100)
    private String name;

    // 开盘指数
    @Basic
    private double open;

    // 最高指数
    @Basic
    private double high;

    // 最低指数
    @Basic
    private double low;

    // 收盘指数
    @Basic
    private double close;

    // 成交量
    @Basic
    @Column(length = 100)
    private String volume;

    //成交金额
    @Basic
    @Column(length = 100)
    private String transactionAmount;

    // 昨收
    @Basic
    private double preClose;

    //涨跌幅
    @Basic
    private double increaseMargin;

    //涨跌额
    @Basic
    private double fluctuation;

    //换手率
    @Basic
    private double turnoverRate;

    //总市值
    @Basic
    @Column(length = 100)
    private String totalValue;

    //流通市值
    @Basic
    @Column(length = 100)
    private String circulationMarketValue;

//    //复权数据
//
//    //前复权开盘指数
//    @Basic
//    private double frontAdjOpen;
//
//    //前复权收盘指数
//    @Basic
//    private double frontAdjClose;
//
//    //前复权当日最高指数
//    @Basic
//    private double frontAdjHigh;
//
//    //前复权当日最低指数
//    @Basic
//    private double frontAdjLow;
//
//    //昨日前复权收盘指数
//    @Basic
//    private double preFrontAdjClose;
//
//    //后复权开盘指数
//    @Basic
//    private double afterAdjOpen;

//    //后复权收盘指数
//    @Basic
//    private double afterAdjClose;
//
//    //后复权当日最高指数
//    @Basic
//    private double afterAdjHigh;
//
//    //后复权当日最低指数
//    @Basic
//    private double afterAdjLow;
//
//    //昨日后复权收盘指数
//    @Basic
//    private double preAfterAdjClose;

    public Stock() {
    }

    // 个人界面当天股票没有信息只显示有此自选股
    public Stock(String code, LocalDate date, String name) {
        this.stockID = new StockID(code, date);
        this.name = name;
        this.open = -1;
        this.high = -1;
        this.low = -1;
        this.close = -1;
        this.volume = null;
        this.transactionAmount = null;
        this.preClose = -1;
        this.increaseMargin = -1;
        this.fluctuation = -1;
        this.turnoverRate = -1;
        this.totalValue = null;
        this.circulationMarketValue = null;
    }

//    public Stock(String name, double open, double high, double low, double close, String volume, String transactionAmount,
//                 double preClose, double increaseMargin, double fluctuation, double turnoverRate, String totalValue,
//                 String circulationMarketValue, double frontAdjOpen, double frontAdjClose, double frontAdjHigh,
//                 double frontAdjLow, double preFrontAdjClose, double afterAdjOpen, double afterAdjClose,
//                 double afterAdjHigh, double afterAdjLow, double preAfterAdjClose) {
//        this.name = name;
//        this.open = open;
//        this.high = high;
//        this.low = low;
//        this.close = close;
//        this.volume = volume;
//        this.transactionAmount = transactionAmount;
//        this.preClose = preClose;
//        this.increaseMargin = increaseMargin;
//        this.fluctuation = fluctuation;
//        this.turnoverRate = turnoverRate;
//        this.totalValue = totalValue;
//        this.circulationMarketValue = circulationMarketValue;
//        this.frontAdjOpen = frontAdjOpen;
//        this.frontAdjClose = frontAdjClose;
//        this.frontAdjHigh = frontAdjHigh;
//        this.frontAdjLow = frontAdjLow;
//        this.preFrontAdjClose = preFrontAdjClose;
//        this.afterAdjOpen = afterAdjOpen;
//        this.afterAdjClose = afterAdjClose;
//        this.afterAdjHigh = afterAdjHigh;
//        this.afterAdjLow = afterAdjLow;
//        this.preAfterAdjClose = preAfterAdjClose;
//    }

    public Stock(NormalStock normalStock) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//格式化设置

        this.stockID = new StockID(normalStock.getCode(), normalStock.getDate());
        this.name = normalStock.getName();
        //这里默认给深圳
        this.open = normalStock.getOpen();
        this.high = normalStock.getHigh();
        this.low = normalStock.getLow();
        this.close = normalStock.getClose();
        this.volume = new BigDecimal(normalStock.getVolume()).toString();
        this.transactionAmount = decimalFormat.format(normalStock.getAmount());
        this.preClose = normalStock.getPreClose();
        this.increaseMargin = normalStock.getChangeRate();
        this.fluctuation = normalStock.getFluctuation();
        this.turnoverRate = normalStock.getTurnOverRate();
        this.totalValue = decimalFormat.format(normalStock.getMarketCap());
        this.circulationMarketValue = decimalFormat.format(normalStock.getMarketEquity());
    }

    public StockID getStockID() {
        return stockID;
    }

    public void setStockID(StockID stockID) {
        this.stockID = stockID;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreClose() {
        return preClose;
    }

    public void setPreClose(double preClose) {
        this.preClose = preClose;
    }

    public double getIncreaseMargin() {
        return increaseMargin;
    }

    public void setIncreaseMargin(double increaseMargin) {
        this.increaseMargin = increaseMargin;
    }

    public double getFluctuation() {
        return fluctuation;
    }

    public void setFluctuation(double fluctuation) {
        this.fluctuation = fluctuation;
    }

    public double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public String getCirculationMarketValue() {
        return circulationMarketValue;
    }

    public void setCirculationMarketValue(String circulationMarketValue) {
        this.circulationMarketValue = circulationMarketValue;
    }

//    public double getFrontAdjOpen() {
//        return frontAdjOpen;
//    }
//
//    public void setFrontAdjOpen(double frontAdjOpen) {
//        this.frontAdjOpen = frontAdjOpen;
//    }
//
//    public double getFrontAdjClose() {
//        return frontAdjClose;
//    }
//
//    public void setFrontAdjClose(double frontAdjClose) {
//        this.frontAdjClose = frontAdjClose;
//    }
//
//    public double getFrontAdjHigh() {
//        return frontAdjHigh;
//    }
//
//    public void setFrontAdjHigh(double frontAdjHigh) {
//        this.frontAdjHigh = frontAdjHigh;
//    }
//
//    public double getFrontAdjLow() {
//        return frontAdjLow;
//    }
//
//    public void setFrontAdjLow(double frontAdjLow) {
//        this.frontAdjLow = frontAdjLow;
//    }
//
//    public double getPreFrontAdjClose() {
//        return preFrontAdjClose;
//    }
//
//    public void setPreFrontAdjClose(double preFrontAdjClose) {
//        this.preFrontAdjClose = preFrontAdjClose;
//    }
//
//    public double getAfterAdjOpen() {
//        return afterAdjOpen;
//    }
//
//    public void setAfterAdjOpen(double afterAdjOpen) {
//        this.afterAdjOpen = afterAdjOpen;
//    }
//
//    public double getAfterAdjClose() {
//        return afterAdjClose;
//    }
//
//    public void setAfterAdjClose(double afterAdjClose) {
//        this.afterAdjClose = afterAdjClose;
//    }
//
//    public double getAfterAdjHigh() {
//        return afterAdjHigh;
//    }
//
//    public void setAfterAdjHigh(double afterAdjHigh) {
//        this.afterAdjHigh = afterAdjHigh;
//    }
//
//    public double getAfterAdjLow() {
//        return afterAdjLow;
//    }
//
//    public void setAfterAdjLow(double afterAdjLow) {
//        this.afterAdjLow = afterAdjLow;
//    }
//
//    public double getPreAfterAdjClose() {
//        return preAfterAdjClose;
//    }
//
//    public void setPreAfterAdjClose(double preAfterAdjClose) {
//        this.preAfterAdjClose = preAfterAdjClose;
//    }
}
