package com.edu.nju.asi.infoCarrier.traceBack;

import com.edu.nju.asi.model.Stock;

import java.time.LocalDate;

/**
 * Created by cuihua on 2017/4/16.
 *
 * 暂时性保存均值回归策略所需的股票数据
 */
public class StrategyStock {

    // 日期(月/日/年)
    public LocalDate date;

    // 收盘指数
    public double close;

    // 昨收
    public double preClose;

    // 复权后的收盘指数
    public double adjClose;

    // 交易量
    public double volume;

    public StrategyStock(Stock stock) {
        this.date = stock.getStockID().getDate();
        this.close = stock.getClose();
        this.preClose = stock.getPreClose();
        this.adjClose = stock.getAdjClose();
        this.volume = new Double(stock.getVolume());
    }

}
