package vo;

import po.StockSituationPO;

/**
 * Created by cuihua on 2017/3/4.
 *
 * 市场情况温度计
 */
public class StockSituationVO {

    // 当日总交易量
    public String volume;

    // 涨停股票数（使用复权收盘价进行计算）
    public int limitUpNum;

    // 跌停股票数（使用复权收盘价进行计算）
    public int limitDownNum;

    // 涨幅超过5%的股票数（使用复权收盘价进行计算）
    public int surgingNum;

    // 跌幅超过5%的股票数（使用复权收盘价进行计算）
    public int slumpingNum;

    // 开盘‐收盘小于‐5% * 上一个交易日收盘价的股票个数（使用收盘价进行计算）
    public int climbingNum;

    // 开盘‐收盘大于5% * 上一个交易日收盘价的股票个数（使用收盘价进行计算）
    public int slipingNum;

    public StockSituationVO(String volume, int limitUpNum, int limitDownNum, int surgingNum, int slumpingNum, int climbingNum, int slipingNum) {
        this.volume = volume;
        this.limitUpNum = limitUpNum;
        this.limitDownNum = limitDownNum;
        this.surgingNum = surgingNum;
        this.slumpingNum = slumpingNum;
        this.climbingNum = climbingNum;
        this.slipingNum = slipingNum;
    }

    public StockSituationVO(StockSituationPO stockSituation) {
        this.volume = stockSituation.getVolume();
        this.limitUpNum = stockSituation.getLimitUpNum();
        this.limitDownNum = stockSituation.getLimitDownNum();
        this.surgingNum = stockSituation.getSurgingNum();
        this.slumpingNum = stockSituation.getSlumpingNum();
        this.climbingNum = stockSituation.getClimbingNum();
        this.slipingNum = stockSituation.getSlipingNum();
    }
}
