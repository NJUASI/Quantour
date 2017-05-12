package com.edu.nju.asi.po;

/**
 * Created by cuihua on 2017/3/4.
 * <p>
 * 市场情况温度计
 */
public class StockSituationPO {

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

    public StockSituationPO() {
        volume = "0";
        limitUpNum = 0;
        limitDownNum = 0;
        surgingNum = 0;
        slumpingNum = 0;
        climbingNum = 0;
        slumpingNum = 0;
    }

    public StockSituationPO(String volume, int limitUpNum, int limitDownNum, int surgingNum, int slumpingNum, int climbingNum, int slipingNum) {
        this.volume = volume;
        this.limitUpNum = limitUpNum;
        this.limitDownNum = limitDownNum;
        this.surgingNum = surgingNum;
        this.slumpingNum = slumpingNum;
        this.climbingNum = climbingNum;
        this.slipingNum = slipingNum;
    }

    public StockSituationPO(String[] info){
        this.volume = info[0];
        this.limitUpNum = Integer.parseInt(info[1]);
        this.limitDownNum = Integer.parseInt(info[2]);
        this.surgingNum = Integer.parseInt(info[3]);
        this.slumpingNum = Integer.parseInt(info[4]);
        this.climbingNum = Integer.parseInt(info[5]);
        this.slipingNum = Integer.parseInt(info[6]);
    }

    public StockSituationPO plus(StockSituationPO another) {
        long newVolume = Long.parseLong(this.getVolume()) + Long.parseLong(another.getVolume());
        int newLimitUpNum = this.limitUpNum+another.limitUpNum;
        int newLimitDownNum = this.limitDownNum+another.limitDownNum;
        int newSurgingNum = this.surgingNum+another.surgingNum;
        int newSlumpingNum = this.slumpingNum+another.slumpingNum;
        int newClimbingNum = this.climbingNum+another.climbingNum;
        int newSlipingNum = this.slipingNum+another.slipingNum;
        return new StockSituationPO(String.valueOf(newVolume), newLimitUpNum, newLimitDownNum, newSurgingNum, newSlumpingNum, newClimbingNum, newSlipingNum);
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
