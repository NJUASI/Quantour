package com.edu.nju.asi.service.serviceImpl.traceBackService.traceBackStrategy.marketSelectingStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.MarketSelectingResult;
import com.edu.nju.asi.model.BaseStock;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/6/11.
 *
 * DMAVol = MAVol短线 – MAVol长线 （MAVol短线默认为上证5日成交量均线， MA长线默认为上证60日成交量均线。）
 * 金叉条件: MAVol短线(t) > MAVol短线(t-1) and DMAVol (t) > 0 and DMAVol (t-1) < 0
 * 死叉条件 MAVol短线(t)< MAVol短线(t-1) and DMAVol (t) < 0 and DMAVol (t-1) > 0
 */
public class MAVol_MSS extends AllMarketSelectingStrategy {

    public MAVol_MSS(List<LocalDate> allDatesWithData, List<BaseStock> baseStocks) {
        super(allDatesWithData, baseStocks);
    }

    @Override
    public MarketSelectingResult marketSelecting(int neededSelectDayIndex, int criteria1, int criteria2, int criteria3) {
        double maVolShort_today = maVol(neededSelectDayIndex, criteria1);
        double maVolShort_pre = maVol(neededSelectDayIndex - 1, criteria1);

        double dmaVol_today = dmaVol(neededSelectDayIndex, criteria1, criteria2);
        double dmaVol_pre = dmaVol(neededSelectDayIndex - 1, criteria1, criteria2);

        MarketSelectingResult result = new MarketSelectingResult();
        if (maVolShort_today > maVolShort_pre && dmaVol_today > 0 && dmaVol_pre < 0) {
            result.isGoldenFork = true;
        }

        if (maVolShort_today < maVolShort_pre && dmaVol_today < 0 && dmaVol_pre > 0) {
            result.isDeathCross = true;
        }

        return result;
    }

    /**
     * 计算MA(baseStockName交易量, day)
     */
    protected double maVol(int neededSelectDayIndex, int day) {
        List<BaseStock> wanted = findBaseStocksWithinDay(neededSelectDayIndex - day + 1, neededSelectDayIndex);

        long sum = 0;
        for (BaseStock bs : wanted) {
            sum += Long.parseLong(bs.getVolume());
        }
        return sum / wanted.size();
    }

    /**
     * 计算DMA(baseStockName交易量, MAVOL_short, MAVOL_long)
     */
    protected double dmaVol(int neededSelectDayIndex, int shortDay, int longDay) {
        return maVol(neededSelectDayIndex, shortDay) - maVol(neededSelectDayIndex, longDay);
    }
}
