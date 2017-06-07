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
 * 多头排列标记 ：标记为1，表示5日均线、20日均线、60日均线、120日均线依次排列，短线在长线的上方， 股价呈上升趋势。
 */
public class MulArrMarkFormateStrategy extends AllFormateStrategy{

    public MulArrMarkFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData) {
        super(allDatesWithData, stockData);
    }


    @Override
    public List<FormateRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {

        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        List<FormateRate> formateRate = new ArrayList<>();

        for (int i = 0; i < stockCodes.size(); i++) {


            List<Stock> stockList_120day = getDataWithoutHaltDay(stockCodes.get(i), periodStartIndex - 1, 24);
            List<Stock> stockList_60day = stockList_120day.subList(60, 120);
            List<Stock> stockList_20day = stockList_120day.subList(100, 120);
            List<Stock> stockList_5day = stockList_120day.subList(115, 120);

            if(stockList_120day == null){
                formateRate.add(new FormateRate(stockCodes.get(i), null));
                continue;
            }

            double mean_5day = computeMeanValue(stockList_5day, "close");
            double mean_20day = computeMeanValue(stockList_20day, "close");
            double mean_60day = computeMeanValue(stockList_60day, "close");
            double mean_120day = computeMeanValue(stockList_120day, "close");

            double up = 0;
            // 短线在长线上方
            if (mean_5day > mean_20day && mean_20day > mean_60day && mean_60day > mean_120day) {
                up = 1;
            }
            formateRate.add(new FormateRate(stockCodes.get(i), up));
        }

        return formateRate;
    }
}
