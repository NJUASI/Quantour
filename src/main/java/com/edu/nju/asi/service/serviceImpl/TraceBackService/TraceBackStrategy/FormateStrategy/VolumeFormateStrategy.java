package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;


import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.infoCarrier.traceBack.FilterConditionRate;
import com.edu.nju.asi.infoCarrier.traceBack.StrategyStock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/4/20.
 *
 * 根据当日或N日平均成交量形成
 */
public class VolumeFormateStrategy extends AllFormateStrategy {

    public VolumeFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<StrategyStock>> stockData) {
        super(allDatesWithData, stockData);
    }


    @Override
    public List<FilterConditionRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {

        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        LocalDate endOfFormative = allDatesWithData.get(periodStartIndex - 1);
        LocalDate startOfFormative = allDatesWithData.get(periodStartIndex - formativePeriod);

        List<FilterConditionRate> filterConditionRate = new ArrayList<>();

        for(int i = 0; i < stockCodes.size(); i++){
            double totalVolume = 0;
            List<StrategyStock> stockVOList = findStockVOsWithinDay(stockCodes.get(i), startOfFormative, endOfFormative);
            //说明为该形成期没有数据
            if(null == stockVOList){
                continue;
            }

            for(int j = 0; j < stockVOList.size(); j++){
                totalVolume += stockVOList.get(j).volume;
            }

            filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), totalVolume / stockVOList.size(), 0));
        }

        return filterConditionRate;
    }
}
