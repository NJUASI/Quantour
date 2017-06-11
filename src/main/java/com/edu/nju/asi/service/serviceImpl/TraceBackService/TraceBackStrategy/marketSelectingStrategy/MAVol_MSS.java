package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.marketSelectingStrategy;

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
    public MarketSelectingResult marketSelecting(LocalDate periodStart, int criteria2, int criteria3, int criteria1) {
        return null;
    }
}
