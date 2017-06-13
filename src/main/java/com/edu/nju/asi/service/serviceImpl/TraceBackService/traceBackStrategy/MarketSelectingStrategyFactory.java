package com.edu.nju.asi.service.serviceImpl.TraceBackService.traceBackStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.MarketSelectingCondition;
import com.edu.nju.asi.model.BaseStock;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.traceBackStrategy.marketSelectingStrategy.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihua on 2017/6/11.
 * <p>
 * 创建基于股指条件的择时
 */
public class MarketSelectingStrategyFactory {

    public static AllMarketSelectingStrategy createMarketSelectingStrategyFactory(MarketSelectingCondition condition,
                                                                                  List<LocalDate> allDatesWithData,
                                                                                  Map<String, List<BaseStock>> baseStockData) {

        List<BaseStock> baseStocks = baseStockData.get(condition.baseStockName);

        switch (condition.type) {
            case MA:
                return new MA_MSS(allDatesWithData, baseStocks);
            case DMA:
                return new DMA_MSS(allDatesWithData, baseStocks);
            case MACD:
                return new MACD_MSS(allDatesWithData, baseStocks);
            case MAVol:
                return new MAVol_MSS(allDatesWithData, baseStocks);
            case TRIX:
                return new TRIX_MSS(allDatesWithData, baseStocks);
        }

        return null;
    }
}
