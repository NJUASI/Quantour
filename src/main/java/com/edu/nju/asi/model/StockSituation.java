package com.edu.nju.asi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/5/7.
 * <p>
 * 市场情况温度计
 */
@Entity
@Table(name = "stocksituation")
public class StockSituation implements Serializable {

    //日期
    @Id
    @GenericGenerator(name="myGenerator",strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    @Column(name = "date")
    private LocalDate date;

    // 当日总交易量
    @Basic
    private String volume;

    // 涨停股票数（使用复权收盘价进行计算）
    @Basic
    private int limitUpNum;

    // 跌停股票数（使用复权收盘价进行计算）
    @Basic
    private int limitDownNum;

    // 涨幅超过5%的股票数（使用复权收盘价进行计算）
    @Basic
    private int surgingNum;

    // 跌幅超过5%的股票数（使用复权收盘价进行计算）
    @Basic
    private int slumpingNum;

    // 开盘‐收盘小于‐5% * 上一个交易日收盘价的股票个数（使用收盘价进行计算，负涨）
    @Basic
    private int climbingNum;

    // 开盘‐收盘大于5% * 上一个交易日收盘价的股票个数（使用收盘价进行计算，正跌）
    @Basic
    private int slipingNum;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int getLimitUpNum() {
        return limitUpNum;
    }

    public void setLimitUpNum(int limitUpNum) {
        this.limitUpNum = limitUpNum;
    }

    public int getLimitDownNum() {
        return limitDownNum;
    }

    public void setLimitDownNum(int limitDownNum) {
        this.limitDownNum = limitDownNum;
    }

    public int getSurgingNum() {
        return surgingNum;
    }

    public void setSurgingNum(int surgingNum) {
        this.surgingNum = surgingNum;
    }

    public int getSlumpingNum() {
        return slumpingNum;
    }

    public void setSlumpingNum(int slumpingNum) {
        this.slumpingNum = slumpingNum;
    }

    public int getClimbingNum() {
        return climbingNum;
    }

    public void setClimbingNum(int climbingNum) {
        this.climbingNum = climbingNum;
    }

    public int getSlipingNum() {
        return slipingNum;
    }

    public void setSlipingNum(int slipingNum) {
        this.slipingNum = slipingNum;
    }
}
