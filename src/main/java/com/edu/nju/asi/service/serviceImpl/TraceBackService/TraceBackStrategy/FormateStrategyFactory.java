package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.StrategyStock;
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

    public static AllFormateStrategy createFormateStrategy(IndicatorType indicatorType, List<LocalDate> allDatesWithData, Map<String, List<StrategyStock>> stockData){
        switch (indicatorType){
            case MOMENTUM:
                return new MomentumFormateStrategy(allDatesWithData, stockData);
            case BIAS:
                return new BiasFormateStrategy(allDatesWithData, stockData);
            case VOLUME:
                return new VolumeFormateStrategy(allDatesWithData, stockData);
            case INCREASE_AMOUNT:
                return new IncreaseAmountStrategy(allDatesWithData, stockData);

        }
        return null;
    }

}
