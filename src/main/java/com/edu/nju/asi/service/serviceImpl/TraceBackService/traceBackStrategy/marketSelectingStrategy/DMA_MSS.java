package com.edu.nju.asi.service.serviceImpl.traceBackService.traceBackStrategy.marketSelectingStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.MarketSelectingResult;
import com.edu.nju.asi.model.BaseStock;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/6/11.
 * <p>
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
    public MarketSelectingResult marketSelecting(int neededSelectDayIndex, int criteria1, int criteria2, int criteria3) {

        double dma_today = dma(neededSelectDayIndex, criteria1, criteria2);
        double dma_pre = dma(neededSelectDayIndex - 1, criteria1, criteria2);

        double ama_today = ama(neededSelectDayIndex, criteria1, criteria2, criteria3);
        double ama_pre = ama(neededSelectDayIndex - 1, criteria1, criteria2, criteria3);

        MarketSelectingResult result = new MarketSelectingResult();
        if (dma_today > dma_pre && dma_today > ama_today && dma_pre < ama_pre) {
            result.isGoldenFork = true;
        }

        if (dma_today < dma_pre && dma_today < ama_today && dma_pre > ama_pre) {
            result.isDeathCross = true;

        }

        return result;
    }
}
