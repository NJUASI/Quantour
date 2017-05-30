package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.StrategyStock;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy.*;
import com.edu.nju.asi.utilities.enums.FormateType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 创建形成期形成
 */
public class FormateStrategyFactory {

    public static AllFormateStrategy createFormateStrategy(FormateType formateType, List<LocalDate> allDatesWithData, Map<String, List<StrategyStock>> stockData){
        switch (formateType){
            case INCEREASE_AMOUNT:
                return new IncreseAmountFormateStrategy(allDatesWithData, stockData);
            case BIAS:
                return new BiasFormateStrategy(allDatesWithData, stockData);
            case VOLUME:
                return new VolumeFormateStrategy(allDatesWithData, stockData);
            case CIRCULATIONMARKETVALUEFORMATESTRATEGY:
                return new CirculationMarketValueFormateStrategy(allDatesWithData, stockData);

        }
        return null;
    }

}
