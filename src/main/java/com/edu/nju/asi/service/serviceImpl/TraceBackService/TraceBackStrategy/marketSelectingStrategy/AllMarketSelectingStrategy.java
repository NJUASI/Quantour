package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.marketSelectingStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.MarketSelectingResult;
import com.edu.nju.asi.model.BaseStock;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/6/11.
 *
 * 股指进行择时的接口
 *
 * 趋势择时，即根据大盘指数的均线金叉死叉来判断趋势的转换。趋势择时有一定的滞后性，即大盘趋势转换一定时间后，才会发出信号。
 * DMA_MSS, MA_MSS, MACD_MSS, MAVol_MSS, TRIX_MSS
 *
 * 反转择时，即抄底逃顶择时，就是在大盘跌破某个下限时转为买入，在大盘涨破上限时卖出。反转择时有一定的提前性，即大盘趋势还没有发生转换就会发出信号。
 * PB_MSS, PE_MMS
 */
public abstract class AllMarketSelectingStrategy {

    /**
     * 所有有股票数据的交易日
     */
    protected List<LocalDate> allDatesWithData;

    /**
     * 用于择时的股指数据
     */
    protected List<BaseStock> baseStocks;


    public AllMarketSelectingStrategy(List<LocalDate> allDatesWithData, List<BaseStock> baseStocks) {
        this.allDatesWithData = allDatesWithData;
        this.baseStocks = baseStocks;
    }

    /**
     * 进行选择的市场择时条件判断是逗形成金叉、死叉
     */
    public abstract MarketSelectingResult marketSelecting(LocalDate periodStart, int criteria2, int criteria3, int criteria1);

}
