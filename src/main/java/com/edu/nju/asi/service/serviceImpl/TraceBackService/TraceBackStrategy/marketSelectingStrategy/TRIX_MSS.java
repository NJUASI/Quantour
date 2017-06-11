package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.marketSelectingStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.MarketSelectingResult;
import com.edu.nju.asi.model.BaseStock;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/6/11.
 *
 * TR = EMA(EMA(EMA(上证指数，N),N),N) （N默认值为120天）
 * TRIX = （TR(t) – TR(t-1)）/TR(t-1)
 * MATRIX = MA(TRIX， M) （M默认为5天）
 * 金叉条件: TRIX(t) > TRIX (t-1) and TRIX (t) > MATRIX (t) and TRIX (t -1) < MATRIX (t-1)
 * 死叉条件: TRIX(t) < TRIX (t-1) and TRIX (t) < MATRIX (t) and TRIX (t -1) > MATRIX (t-1)
 */
public class TRIX_MSS extends AllMarketSelectingStrategy {

    public TRIX_MSS(List<LocalDate> allDatesWithData, List<BaseStock> baseStocks) {
        super(allDatesWithData, baseStocks);
    }

    @Override
    public MarketSelectingResult marketSelecting(LocalDate periodStart, int criteria2, int criteria3, int criteria1) {
        return null;
    }
}
