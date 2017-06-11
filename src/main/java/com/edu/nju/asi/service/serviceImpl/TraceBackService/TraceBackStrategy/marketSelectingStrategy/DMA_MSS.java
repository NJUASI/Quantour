package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.marketSelectingStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.MarketSelectingResult;
import com.edu.nju.asi.model.BaseStock;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/6/11.
 *
 * DMA = MA短线 – MA长线 （MA短线默认为上证5日均线， MA长线默认为上证60日均线。）
 * AMA = MA（DMA， M） （AMA默认为20日DMA移动平均）
 * 金叉条件: DMA(t) > DMA(t-1) and DMA (t) > AMA (t) and DMA (t -1) < AMA (t-1)
 * 死叉条件:DMA(t) < DMA(t-1) and DMA (t) < AMA (t) and DMA (t -1) > AMA (t-1)
 */
public class DMA_MSS extends AllMarketSelectingStrategy {

    public DMA_MSS(List<LocalDate> allDatesWithData, List<BaseStock> baseStocks) {
        super(allDatesWithData, baseStocks);
    }

    @Override
    public MarketSelectingResult marketSelecting(LocalDate periodStart, int criteria2, int criteria3, int criteria1) {
        return null;
    }
}
