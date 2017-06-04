package com.edu.nju.asi.infoCarrier.traceBack;

import com.edu.nju.asi.model.Stock;

import java.time.LocalDate;

/**
 * Created by cuihua on 2017/4/16.
 *
 * 暂时性保存均值回归策略所需的股票数据
 */
public class StrategyStock {

    /**
     * 名称
     */
    public String stockName;

    /**
     * 代号
     */
    public String stockCode;

    /**
     * 日期(月/日/年)
     */
    public LocalDate date;

    /**
     * 收盘指数
     */
    public double close;

    /**
     * 昨收
     */
    public double preClose;

    /**
     * 交易量
     */
    public double volume;

    /**
     * 流通市值
     */
    public double circulationMarketValue;

    public StrategyStock(Stock stock) {
        this.date = stock.getStockID().getDate();
        this.close = stock.getClose();
        this.preClose = stock.getPreClose();
        this.volume = new Double(stock.getVolume());
        this.circulationMarketValue = new Double(stock.getCirculationMarketValue());
    }

}
