package com.edu.nju.asi.model;


import com.edu.nju.asi.utilities.enums.Market;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
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

    // 市场名称
    @Basic
    private Market market;

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
    @Column(length = 200)
    private String volume;

    // 复权后的收盘指数
    @Basic
    private double adjClose;

    // 昨收
    @Basic
    private double preClose;

    // 昨日复权收盘指数
    @Basic
    private double preAdjClose;

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

    public double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(double adjClose) {
        this.adjClose = adjClose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public double getPreClose() {
        return preClose;
    }

    public void setPreClose(double preClose) {
        this.preClose = preClose;
    }

    public double getPreAdjClose() {
        return preAdjClose;
    }

    public void setPreAdjClose(double preAdjClose) {
        this.preAdjClose = preAdjClose;
    }
}
