package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;

import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.infoCarrier.traceBack.FilterConditionRate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 形成期的动量策略
 */
public class MomentumFormateStrategy extends AllFormateStrategy {

    public MomentumFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData) {
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
            List<Stock> stockVOList = findStockVOsWithinDay(stockCodes.get(i), startOfFormative, endOfFormative);
            //说明为该形成期没有数据
            if(null == stockVOList){
                continue;
            }

            //形成期内收益率
            double rate = (stockVOList.get(stockVOList.size()-1).getClose() - stockVOList.get(0).getPreClose()) / stockVOList.get(0).getPreClose();

            //初始得分为0
            filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), rate, 0));
        }

        return filterConditionRate;
    }
}
