package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;
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
    public List<FormateRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {
        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        List<FormateRate> formateRate = new ArrayList<>();

        for(int i = 0; i < stockCodes.size(); i++){

            List<Stock> stockList_5day = getDataWithoutHaltDay(stockCodes.get(i), periodStartIndex - 1, 5);
            List<Stock> stockList_1day = stockList_5day.subList(4, 5);
            if(stockList_5day == null){
                formateRate.add(new FormateRate(stockCodes.get(i), null));
                continue;
            }

            double mean_1day = computeMeanValue(stockList_1day, "volume");
            double mean_5day = computeMeanValue(stockList_5day, "volume");

            double ratio = mean_1day / mean_5day;
            formateRate.add(new FormateRate(stockCodes.get(i), ratio));
        }

        return formateRate;
    }
}
