package com.edu.nju.asi.service.serviceImpl.TraceBackService.traceBackStrategy.marketSelectingStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.MarketSelectingResult;
import com.edu.nju.asi.model.BaseStock;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/6/11.
 * <p>
 * 股指进行择时的接口
 * <p>
 * 趋势择时，即w。趋势择时有一定的滞后性，即大盘趋势转换一定时间后，才会发出信号。
 * DMA_MSS, MA_MSS, MACD_MSS, MAVol_MSS, TRIX_MSS
 * <p>
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
    public abstract MarketSelectingResult marketSelecting(int neededSelectDayIndex, int criteria1, int criteria2, int criteria3);


    /**
     * 计算MA(baseStockName收盘价, day)
     */
    protected double ma(int neededSelectDayIndex, int day) {
        System.out.println("need: " + baseStocks.get(neededSelectDayIndex).getStockID().getDate());
        List<BaseStock> wanted = findBaseStocksWithinDay(neededSelectDayIndex - day, neededSelectDayIndex - 1);

        double sum = 0;
        for (BaseStock bs : wanted) {
            sum += bs.getClose();
        }
        return sum / wanted.size();
    }

    /**
     * 计算AMA(baseStockName收盘价, DMA， M)
     */
    protected double ama(int neededSelectDayIndex, int shortCri, int longCri, int amaCri) {
        double sum = 0;

        for (int i = 0; i < amaCri; i++) {
            sum += dma(neededSelectDayIndex - amaCri + i, shortCri, longCri);
        }
        return sum / amaCri;
    }

    /**
     * 计算DMA(baseStockName收盘价, MA_short, MA_long)
     */
    protected double dma(int neededSelectDayIndex, int shortDay, int longDay) {
        return ma(neededSelectDayIndex, shortDay) - ma(neededSelectDayIndex, longDay);
    }


    /**
     * 计算EMA(baseStockName收盘价, day)
     */
    protected Double ema(int neededSelectDayIndex, int day) {
        List<BaseStock> wanted = findBaseStocksWithinDay(neededSelectDayIndex - day + 1, neededSelectDayIndex);

        if (wanted == null) {
            return null;
        }

        double k = 2.0 / (wanted.size() + 1);

        //第一天ema等于当天的收盘价
        double ema = wanted.get(0).getClose();
        for (int i = 1; i < wanted.size(); i++) {
            //第二天以后，当天收盘价 * 系数 加上昨天的ema*系数-1
            ema = k * wanted.get(i).getClose() + (1 - k) * ema;
        }

        return ema;
    }


    protected List<BaseStock> findBaseStocksWithinDay(int periodStart, int periodEnd) {
        System.out.println(baseStocks.get(periodStart).getStockID().getDate() + "   " + allDatesWithData.get(periodStart));
        System.out.println(baseStocks.get(periodEnd).getStockID().getDate() + "   " + allDatesWithData.get(periodEnd));


        if (periodStart < 0) return null;
        return baseStocks.subList(periodStart, periodEnd + 1);
    }
}
