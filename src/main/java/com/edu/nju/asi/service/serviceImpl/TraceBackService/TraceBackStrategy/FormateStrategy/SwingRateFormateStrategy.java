package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FilterConditionRate;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/6/5.
 *
 * 股价振幅 ：股票当日最高价最低价之差除以前日收盘价。
 */
public class SwingRateFormateStrategy extends AllFormateStrategy{

    public SwingRateFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData) {
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
            if(stockList == null){
                filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), null, 0));
                continue;
            }

            double totalIncreaseAmount = 0;
            for(int j = 0; j < stockList.size(); j++){
                totalIncreaseAmount += (stockList.get(j).getHigh() - stockList.get(j).getLow()) / stockList.get(j).getPreClose();
            }

            filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), totalIncreaseAmount / stockList.size(), 0));
        }

        return filterConditionRate;
    }
}
