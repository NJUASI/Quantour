package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy;

import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy.*;
import com.edu.nju.asi.utilities.enums.IndicatorType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 创建形成期形成
 */
public class FormateStrategyFactory {

    public static AllFormateStrategy createFormateStrategy(IndicatorType indicatorType, List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData){
        switch (indicatorType){
            case MOMENTUM:
                return new MomentumFormateStrategy(allDatesWithData, stockData);


                //可直接拿到的数据，并通过均值形成
            case OPEN:
                return new MeanFormateStrategy(allDatesWithData, stockData, "open");
            case HIGH:
                return new MeanFormateStrategy(allDatesWithData, stockData, "high");
            case LOW:
                return new MeanFormateStrategy(allDatesWithData, stockData, "low");
            case CLOSE:
                return new MeanFormateStrategy(allDatesWithData, stockData, "close");
            case VOLUME:
                return new MeanFormateStrategy(allDatesWithData, stockData, "volume");
            case TRANSACTION_AMOUNT:
                return new MeanFormateStrategy(allDatesWithData, stockData, "transactionAmount");
            case PRE_CLOSE:
                return new MeanFormateStrategy(allDatesWithData, stockData, "preClose");
            case TURNOVER_RATE:
                return new MeanFormateStrategy(allDatesWithData, stockData, "turnoverRate");
            case TOTAL_VALUE:
                return new MeanFormateStrategy(allDatesWithData, stockData, "totalValue");
            case CIRCULATION_MARKET_VALUE:
                return new MeanFormateStrategy(allDatesWithData, stockData, "circulationMarketValue");
            case AFTER_ADJ_OPEN:
                return new MeanFormateStrategy(allDatesWithData, stockData, "afterAdjOpen");
            case AFTER_ADJ_HIGH:
                return new MeanFormateStrategy(allDatesWithData, stockData, "afterAdjHigh");
            case AFTER_ADJ_LOW:
                return new MeanFormateStrategy(allDatesWithData, stockData, "afterAdjLow");
            case AFTER_ADJ_CLOSE:
                return new MeanFormateStrategy(allDatesWithData, stockData, "afterAdjClose");

                // 日均成交价
            case DAILY_AVE_PRICE:
                return new DailyAvePriceFormateStrategy(allDatesWithData, stockData);

                // 累计涨幅
            case INCREASE_MARGIN:
                return new IncreaseAmountFormateStrategy(allDatesWithData, stockData);


        }
        return null;
    }

}
