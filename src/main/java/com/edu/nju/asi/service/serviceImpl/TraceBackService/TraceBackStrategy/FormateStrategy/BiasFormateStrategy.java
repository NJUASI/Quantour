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
 * 乖离率
 *
 * 股票当日的收盘价与5日/20日/60日/120日/250日均线之间的差距。 等于后复权收盘价除以N日复权均价 -1
 */
public class BiasFormateStrategy extends AllFormateStrategy {

    public BiasFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData) {
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
            double total = 0;
            List<Stock> stockVOList = findStockVOsWithinDay(stockCodes.get(i), startOfFormative, endOfFormative);
            //说明为该形成期没有数据
            if(null == stockVOList){
                continue;
            }
            else{
                // 停牌日不算入交易日内
                while(stockVOList.size() < formativePeriod){
                    int k = formativePeriod - stockVOList.size();
                    LocalDate start = null;
                    LocalDate end = null;
                    if(k == 1){
                        start = allDatesWithData.get(periodStartIndex - formativePeriod - 1);
                        end = start;
                    }
                    else {
                        start = allDatesWithData.get(periodStartIndex - formativePeriod - k);
                        end = allDatesWithData.get(periodStartIndex - formativePeriod - 1);
                    }
                    stockVOList.addAll(0,findStockVOsWithinDay(stockCodes.get(i), start, end));
                }
            }

            for(int j = 0; j < stockVOList.size(); j++){
                total += stockVOList.get(j).getClose();
            }

            double average = total / stockVOList.size();
            double biasRatio = (average - stockVOList.get(stockVOList.size()).getClose()) / average;

            filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), biasRatio, 0));
        }

        return filterConditionRate;
    }
}
