package com.edu.nju.asi.model;

import com.edu.nju.asi.spider.Model.BaseStockEve;
import com.edu.nju.asi.utilities.enums.Market;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Byron Dong on 2017/5/15.
 */
@Entity
@Table(name = "basestock")
public class BaseStock implements Serializable{

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

    public BaseStock() {
    }

    public BaseStock(String name,double open, double high, double low, double close,
                     String volume, String transactionAmount, double preClose, double increaseMargin, double fluctuation) {
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
    }

    public BaseStock(BaseStockEve baseStockEve) {
        this.stockID = new StockID(baseStockEve.getCode(),baseStockEve.getDate());
        this.name = baseStockEve.getName();
        //这里默认给SZ
        this.open = baseStockEve.getOpen();
        this.high = baseStockEve.getHigh();
        this.low = baseStockEve.getLow();
        this.close = baseStockEve.getClose();
        this.volume = baseStockEve.getVolume().toString();
        this.transactionAmount = baseStockEve.getAmount().toString();
        this.preClose = baseStockEve.getPreClose();
        this.increaseMargin = baseStockEve.getChangeRate();
        this.fluctuation = baseStockEve.getFluctuation();
    }

    public StockID getStockID() {
        return stockID;
    }

    public void setStockID(StockID stockID) {
        this.stockID = stockID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
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
}
