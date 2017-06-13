package com.edu.nju.asi.spider.Model;

import java.time.LocalDate;

/**
 * Created by Harvey on 2017/5/15.
 */
public class NormalStock {

    public NormalStock() {

    }

    public NormalStock(String code, String date, String open, String close, String high, String low, String fluctuation, String changeRate, String volume, String amount, String swingRate, String turnOverRate) {
        this.code = code;
        this.date = LocalDate.parse(date);
        this.open = Double.parseDouble(open);
        this.close = Double.parseDouble(close);
        this.high = Double.parseDouble(high);
        this.low = Double.parseDouble(low);
        this.fluctuation = Double.parseDouble(fluctuation);
        this.changeRate = Double.parseDouble(changeRate);
        this.turnOverRate = Double.parseDouble(turnOverRate);

        String str = "";
        String[] arr_volume = volume.split(",");
        for(int i = 0; i < arr_volume.length; i++){
            str += arr_volume[i];
        }
        this.volume = Double.parseDouble(str);

        str = "";
        String[] arr_amount = amount.split(",");
        for(int i = 0; i < arr_amount.length; i++){
            str += arr_amount[i];
        }
        this.amount = Double.parseDouble(str);
    }

    /**
     * 代码
     */
    String code;

    /**
     * 名称
     */
    String name;

    /**
     * 日期
     */
    LocalDate date;

    /**
     * 开盘价
     */
    double open;

    /**
     * 收盘价
     */
    double close;

    /**
     * 最高价
     */
    double high;

    /**
     * 最低价
     */
    double low;

    /**
     * 昨日收盘价
     */
    double preClose;

    /**
     * 涨跌额
     */
    Double fluctuation;

    /**
     * 涨跌幅
     */
    Double changeRate;

    /**
     * 成交量(股)
     */
    double volume;

    /**
     * 成交额(元)
     */
    double amount;

    /**
     * 换手率
     */
    double turnOverRate;

    /**
     * 总市值
     */
    double marketCap;

    /**
     * 流通市值
     */
    double marketEquity;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code.substring(1);
    }

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

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
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

    public double getPreClose() {
        return preClose;
    }

    public void setPreClose(double preClose) {
        this.preClose = preClose;
    }

    public double getFluctuation() {
        return fluctuation;
    }

    public void setFluctuation(double fluctuation) {
        this.fluctuation = fluctuation;
    }

    public double getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(double changeRate) {
        this.changeRate = changeRate;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTurnOverRate() {
        return turnOverRate;
    }

    public void setTurnOverRate(double turnOverRate) {
        this.turnOverRate = turnOverRate;
    }

    public Double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public Double getMarketEquity() {
        return marketEquity;
    }

    public void setMarketEquity(double marketEquity) {
        this.marketEquity = marketEquity;
    }

    public void setDate(String date) {

        this.date = LocalDate.parse(date);
    }

    public void setOpen(String open) {
        this.open = Double.parseDouble(open);
    }

    public void setClose(String close) {
        this.close = Double.parseDouble(close);
    }

    public void setHigh(String high) {
        this.high = Double.parseDouble(high);
    }

    public void setLow(String low) {
        this.low = Double.parseDouble(low);
    }

    public void setPreClose(String preClose) {
        this.preClose = Double.parseDouble(preClose);
    }

    public void setFluctuation(String fluctuation) {
        this.fluctuation = Double.parseDouble(fluctuation);
    }

    public void setChangeRate(String changeRate) {
        this.changeRate = Double.parseDouble(changeRate);
    }

    public void setVolume(String volume) {
        this.volume = Double.parseDouble(volume);
    }

    public void setAmount(String amount) {
        this.amount = Double.parseDouble(amount);
    }

    public void setTurnOverRate(String turnOverRate) {
        this.turnOverRate = Double.parseDouble(turnOverRate);
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = Double.parseDouble(marketCap);
    }

    public void setMarketEquity(String marketEquity) {
        this.marketEquity = Double.parseDouble(marketEquity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
