package com.edu.nju.asi.service.serviceImpl.TraceBackService.traceBackStrategy.marketSelectingStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.MarketSelectingResult;
import com.edu.nju.asi.model.BaseStock;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/6/11.
 *
 * DIF(快线) = EMA短线 - EMA长线 （EMA短线默认为上证12日指数移动平均线，EMA长线默认为上证26日指数移动平均线。）
 * DEA(慢线)= EMA(DIF, M ) (DEA 默认为DIF的9日指数移动平均线。)
 * 金叉条件：DIF(t) > 0 and DIF(t) >DIF(t-1) and DIF (t) > DEA(t) and DIF (t -1) < DEA(t-1)
 * 死叉条件：DIF(t)<0 and DIF(t) < DIF(t-1) and DIF (t) < DEA(t) and DIF (t -1) > DEA(t-1)
 */
public class MACD_MSS extends AllMarketSelectingStrategy {

    public MACD_MSS(List<LocalDate> allDatesWithData, List<BaseStock> baseStocks) {
        super(allDatesWithData, baseStocks);
    }

    @Override
    public MarketSelectingResult marketSelecting(LocalDate periodStart, int criteria2, int criteria3, int criteria1) {
        return null;
    }
}
