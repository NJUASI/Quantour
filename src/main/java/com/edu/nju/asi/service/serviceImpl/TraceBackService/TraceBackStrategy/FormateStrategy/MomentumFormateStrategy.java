package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;

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
 * 形成期的动量策略
 */
public class MomentumFormateStrategy extends AllFormateStrategy {

    public MomentumFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData) {
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

            //形成期内收益率
            double rate = (stockList.get(stockList.size()-1).getClose() - stockList.get(0).getPreClose()) / stockList.get(0).getPreClose();

            //初始得分为0
            formateRate.add(new FormateRate(stockCodes.get(i), rate));
        }

        return formateRate;
    }
}
