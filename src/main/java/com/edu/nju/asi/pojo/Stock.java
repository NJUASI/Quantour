package com.edu.nju.asi.pojo;

import com.edu.nju.asi.utilities.enums.Market;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/5/7.
 *
 * 股票
 */
@Entity
@Table
public class Stock implements Serializable {

    // 股票代码
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String code;

    // 股票名称
    private String name;

    // 日期(月/日/年)
    private LocalDate date;

    // 市场名称
    private Market market;

    // 开盘指数
    private double open;

    // 最高指数
    private double high;

    // 最低指数
    private double low;

    // 收盘指数
    private double close;

    // 成交量
    private String volume;

    // 复权后的收盘指数
    private double adjClose;

    // 昨收
    private double preClose;

    // 昨日复权收盘指数
    private double preAdjClose;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
