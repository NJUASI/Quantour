package com.edu.nju.asi.service.serviceImpl.traceBackService.traceBackStrategy.formateStrategy;

import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;

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
    public List<FormateRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {

        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        List<FormateRate> formateRate = new ArrayList<>();

        for(int i = 0; i < stockCodes.size(); i++){

            List<Stock> stockList = getDataWithoutHaltDay(stockCodes.get(i), periodStartIndex-1, formativePeriod);
            if(stockList == null){
                formateRate.add(new FormateRate(stockCodes.get(i), null));
                continue;
            }

            double total = 0;
            for(int j = 0; j < stockList.size(); j++){
                total += stockList.get(j).getClose();
            }

            double average = total / stockList.size();
            double biasRatio = (average - stockList.get(stockList.size()-1).getClose()) / average;

            formateRate.add(new FormateRate(stockCodes.get(i), biasRatio));
        }

        return formateRate;
    }
}
