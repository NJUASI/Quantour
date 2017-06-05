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

        LocalDate startOfFormative = allDatesWithData.get(periodStartIndex - formativePeriod);
        LocalDate endOfFormative = allDatesWithData.get(periodStartIndex - 1);


        List<FilterConditionRate> filterConditionRate = new ArrayList<>();

        for(int i = 0; i < stockCodes.size(); i++){
            double totalIncreaseAmount = 0;
            List<Stock> stockVOList = findStockVOsWithinDay(stockCodes.get(i), startOfFormative, endOfFormative);
            //说明为该形成期没有数据
            if(null == stockVOList){
                continue;
            }

            for(int j = 0; j < stockVOList.size(); j++){
                totalIncreaseAmount += (stockVOList.get(j).getHigh() - stockVOList.get(j).getLow()) / stockVOList.get(j).getPreClose();
            }

            filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), totalIncreaseAmount / stockVOList.size(), 0));
        }

        return filterConditionRate;
    }
}
