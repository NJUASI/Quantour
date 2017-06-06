package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FilterConditionRate;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/6/6.
 *
 * 1日5日量比 ： 当日成交量与5日平均成交量比值。
 */
public class VolumeRatioIndexFormateStrategy extends AllFormateStrategy {

    public VolumeRatioIndexFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData) {
        super(allDatesWithData, stockData);
    }


    @Override
    public List<FilterConditionRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {
        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        List<FilterConditionRate> filterConditionRate = new ArrayList<>();

        for(int i = 0; i < stockCodes.size(); i++){

            List<Stock> stockList_1day = getDataWithoutHaltDay(stockCodes.get(i), periodStartIndex-1, 3);
            List<Stock> stockList_5day = getDataWithoutHaltDay(stockCodes.get(i), periodStartIndex-1, 6);
            if(stockList_5day == null){
                filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), null, 0));
                continue;
            }

            double mean_1day = computeMeanValue(stockList_1day, "volume");
            double mean_5day = computeMeanValue(stockList_5day, "volume");

            double ratio = mean_1day / mean_5day;
            filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), ratio, 0));
        }

        return filterConditionRate;
    }
}
