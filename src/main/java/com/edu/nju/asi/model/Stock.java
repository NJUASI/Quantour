package com.edu.nju.asi.model;


import com.edu.nju.asi.spider.Model.NormalStock;
import com.edu.nju.asi.utilities.enums.Market;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Byron Dong on 2017/5/7.
 *
 * 股票
 */
@Entity
@Table(name = "stock")
public class Stock implements Serializable {

    // 股票代码
    @Id
    @GenericGenerator(name="myGenerator",strategy = "assigned")
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

    public Stock() {
    }

    public Stock(String name,double open, double high, double low, double close,
                 String volume, String transactionAmount, double preClose, double increaseMargin,
                 double fluctuation, double turnoverRate, String totalValue, String circulationMarketValue) {
        this.name = name;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.transactionAmount = transactionAmount;
        this.preClose = preClose;
        this.increaseMargin = increaseMargin;
        this.fluctuation = fluctuation;
        this.turnoverRate = turnoverRate;
        this.totalValue = totalValue;
        this.circulationMarketValue = circulationMarketValue;
    }

    public Stock(NormalStock normalStock) {
        this.stockID = new StockID(normalStock.getCode(),normalStock.getDate());
        this.name = normalStock.getName();
        //这里默认给深圳
        this.open = normalStock.getOpen();
        this.high = normalStock.getHigh();
        this.low = normalStock.getLow();
        this.close = normalStock.getClose();
        this.volume = new BigDecimal(normalStock.getVolume()).toString();
        this.transactionAmount = new BigDecimal(normalStock.getAmount()).toString();
        this.preClose = normalStock.getPreClose();
        this.increaseMargin = normalStock.getChangeRate();
        this.fluctuation = normalStock.getFluctuation();
        this.turnoverRate = normalStock.getTurnOverRate();
        this.totalValue = new BigDecimal(normalStock.getMarketCap()).toString();
        this.circulationMarketValue = new BigDecimal(normalStock.getMarketEquity()).toString();
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
}
