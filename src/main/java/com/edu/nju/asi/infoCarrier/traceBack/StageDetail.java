package com.edu.nju.asi.infoCarrier.traceBack;

/**
 * Created by Harvey on 2017/6/10.
 *
 * 阶段持仓详情的每一周期的每只股票详情
 */
public class StageDetail {

    /**
     * 股票代码
     */
    public String stockCode;

    /**
     * 股票名
     */
    public String stockName;

    /**
     * 开始价格（不复权）
     */
    public double startPrice;

    /**
     * 结束价格（不复权）
     */
    public double endPrice;

    /**
     * 涨跌幅
     */
    public double changeRate;

    /**
     * 当前仓位
     */
    public double curPosition;

    /**
     * 当前仓位所占的价值
     */
    public double curPositionMoney;



    public StageDetail() {
    }

    public StageDetail(String stockCode, String stockName, double startPrice, double endPrice, double curPosition, double curPositionMoney) {
        this.stockCode = stockCode;
        this.stockName = stockName;

        this.startPrice = startPrice;
        this.endPrice = endPrice;
        this.changeRate = (endPrice - startPrice) / startPrice;

        this.curPosition = curPosition;
        this.curPositionMoney = curPositionMoney;
    }
}
