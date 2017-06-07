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
 * BBIC ：多空指标，等于BBI除以收盘价，BBIC越小股价越强势，BBIC < 1 为多头行情， BBIC>1 为空头行情。BBI等于(3日均价+6日均价+12日均价+24日均价)/4。计算使用后复权收盘价。
 */
public class BBIC_FormateStrategy extends AllFormateStrategy {

    public BBIC_FormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData) {
        super(allDatesWithData, stockData);
    }


    @Override
    public List<FilterConditionRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {
        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        List<FilterConditionRate> filterConditionRate = new ArrayList<>();

        for(int i = 0; i < stockCodes.size(); i++){

            List<Stock> stockList_24day = getDataWithoutHaltDay(stockCodes.get(i), periodStartIndex-1, 24);
            List<Stock> stockList_12day = stockList_24day.subList(12,24);
            List<Stock> stockList_6day = stockList_24day.subList(18,24);
            List<Stock> stockList_3day = stockList_24day.subList(21,24);

            if(stockList_24day == null){
                filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), null, 0));
                continue;
            }

            double mean_3day = computeMeanValue(stockList_3day, "afterAdjClose");
            double mean_6day = computeMeanValue(stockList_6day, "afterAdjClose");
            double mean_12day = computeMeanValue(stockList_12day, "afterAdjClose");
            double mean_24day = computeMeanValue(stockList_24day, "afterAdjClose");

            double BBIC_val = (mean_3day + mean_6day + mean_12day + mean_24day) / 4;
            filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), BBIC_val, 0));
        }

        return filterConditionRate;
    }
}
