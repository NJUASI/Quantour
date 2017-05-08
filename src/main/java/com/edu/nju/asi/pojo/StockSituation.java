package com.edu.nju.asi.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/5/7.
 * <p>
 * 市场情况温度计
 */
@Entity
@Table
public class StockSituation implements Serializable {

    //日期
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private LocalDate date;

    // 当日总交易量
    private String volume;

    // 涨停股票数（使用复权收盘价进行计算）
    private int limitUpNum;

    // 跌停股票数（使用复权收盘价进行计算）
    private int limitDownNum;

    // 涨幅超过5%的股票数（使用复权收盘价进行计算）
    private int surgingNum;

    // 跌幅超过5%的股票数（使用复权收盘价进行计算）
    private int slumpingNum;

    // 开盘‐收盘小于‐5% * 上一个交易日收盘价的股票个数（使用收盘价进行计算，负涨）
    private int climbingNum;

    // 开盘‐收盘大于5% * 上一个交易日收盘价的股票个数（使用收盘价进行计算，正跌）
    private int slipingNum;

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
