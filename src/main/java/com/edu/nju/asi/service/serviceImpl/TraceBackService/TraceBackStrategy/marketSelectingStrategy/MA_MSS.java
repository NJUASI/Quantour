package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.marketSelectingStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.MarketSelectingResult;
import com.edu.nju.asi.model.BaseStock;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/6/11.
 * <p>
 * DMA = MA短线 – MA长线 （MA短线默认为上证5日均线， MA长线默认为上证60日均线。）
 * 金叉条件: MA短线(t) > MA短线(t-1) and DMA (t) > 0 and DMA (t-1) < 0
 * 死叉条件 MA短线(t)< MA短线(t-1) and DMA (t) < 0 and DMA (t-1) > 0
 */
public class MA_MSS extends AllMarketSelectingStrategy {

    public MA_MSS(List<LocalDate> allDatesWithData, List<BaseStock> baseStocks) {
        super(allDatesWithData, baseStocks);
    }

    @Override
    public MarketSelectingResult marketSelecting(LocalDate periodStart, int criteria2, int criteria3, int criteria1) {
        return null;
    }
}
