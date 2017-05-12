package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;

import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.infoCarrier.traceBack.FormativePeriodRate;
import com.edu.nju.asi.infoCarrier.traceBack.StrategyStock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 形成期的涨幅策略
 */
public class IncreseAmountFormateStrategy extends AllFormateStrategy {

    public IncreseAmountFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<StrategyStock>> stockData) {
        super(allDatesWithData, stockData);
    }

    @Override
    public List<FormativePeriodRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {
        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        LocalDate endOfFormative = allDatesWithData.get(periodStartIndex - 1);
        LocalDate startOfFormative = allDatesWithData.get(periodStartIndex - formativePeriod);


        List<FormativePeriodRate> formativePeriodRate = new ArrayList<>();
        for(int i = 0; i < stockCodes.size(); i++){
            List<StrategyStock> stockVOList = findStockVOsWithinDay(stockCodes.get(i), startOfFormative, endOfFormative);
            //说明为该形成期没有数据
            if(null == stockVOList){
                continue;
            }

            double rate = (stockVOList.get(stockVOList.size()-1).close - stockVOList.get(0).close) / stockVOList.get(0).close;

            formativePeriodRate.add(new FormativePeriodRate(stockCodes.get(i), rate));
        }

        return formativePeriodRate;
    }
}
