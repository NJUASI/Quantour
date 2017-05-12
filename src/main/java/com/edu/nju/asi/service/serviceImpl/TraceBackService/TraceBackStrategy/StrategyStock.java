package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy;

import com.edu.nju.asi.po.StockPO;

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

    public StrategyStock(StockPO po) {
        this.date = po.getDate();
        this.close = po.getClose();
        this.preClose = po.getPreClose();
        this.adjClose = po.getAdjClose();
        this.volume = new Double(po.getVolume());
    }

}
