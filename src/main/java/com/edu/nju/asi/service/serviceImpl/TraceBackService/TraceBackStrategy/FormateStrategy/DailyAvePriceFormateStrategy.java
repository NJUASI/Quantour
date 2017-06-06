package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FilterConditionRate;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/6/5.
 *
 * 日均成交价
 *
 * 当日股票平均成交价格，等于当日成交额除以当日成交量。
 */
public class DailyAvePriceFormateStrategy extends AllFormateStrategy{

    public DailyAvePriceFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData) {
        super(allDatesWithData, stockData);
    }

    @Override
    public List<FilterConditionRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {
        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        List<FilterConditionRate> filterConditionRate = new ArrayList<>();

        for(int i = 0; i < stockCodes.size(); i++){

            List<Stock> stockList = getDataWithoutHaltDay(stockCodes.get(i), periodStartIndex-1, formativePeriod);

            double total = 0;

            for(int j = 0; j < stockList.size(); j++){
                total = new Double(stockList.get(j).getTransactionAmount()) / new Double(stockList.get(j).getVolume());
            }

            filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), total / stockList.size(), 0));
        }

        return filterConditionRate;
    }
}
