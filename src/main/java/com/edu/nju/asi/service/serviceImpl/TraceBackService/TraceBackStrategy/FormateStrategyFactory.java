package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy;

import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy.*;
import com.edu.nju.asi.utilities.enums.IndicatorType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/4/19.
 * <p>
 * 创建形成期形成
 */
public class FormateStrategyFactory {

    public static AllFormateStrategy createFormateStrategy(IndicatorType indicatorType, List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData) {
        switch (indicatorType) {

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


            // 可直接拿到的数据，并累计形成（涨幅、换手率）
            case INCREASE_MARGIN:
                return new AccumulateFormateStrategy(allDatesWithData, stockData, "increaseMargin");
            case TURNOVER_RATE:
                return new AccumulateFormateStrategy(allDatesWithData, stockData, "turnoverRate");


            // 总股本/流通股本 形成
            case GENERAL_CAPITAL:
                return new CapitalFormateStrategy(allDatesWithData, stockData, "totalValue");
            case NEGOTIABLE_CAPITAL:
                return new CapitalFormateStrategy(allDatesWithData, stockData, "circulationMarketValue");


            // 振幅
            case SWING_RATE:
                return new SwingRateFormateStrategy(allDatesWithData, stockData);


            // 日均成交价
            case DAILY_AVE_PRICE:
                return new DailyAvePriceFormateStrategy(allDatesWithData, stockData);


            // 动量策略
            case MOMENTUM:
                return new MomentumFormateStrategy(allDatesWithData, stockData);

            /**
             * 技术指标
             */
            // 乖离率
            case BIAS:
                return new BiasFormateStrategy(allDatesWithData, stockData);
            // 收益波动率
            case RETURN_VOLATILITY:
                return new ReturnVolatilityFormateStrategy(allDatesWithData, stockData);


        }
        return null;
    }

}
