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
 * 形成期的形成策略接口
 */
public abstract class AllFormateStrategy {

    /**
     * 所有有股票数据的交易日
     */
    protected List<LocalDate> allDatesWithData;

    /**
     * 所有股票池中的股票数据
     */
    protected Map<String, List<Stock>> stockData;

    public AllFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData) {
        this.allDatesWithData = allDatesWithData;
        this.stockData = stockData;
    }

    /**
     * 根据当日或N日平均形成
     *
     * @param stockCodes      股票列表
     * @param periodStart     持有期起始日期;
     * @param formativePeriod 形成期长度
     * @return 形成的数据
     */
    public abstract List<FilterConditionRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException;


    protected List<Stock> findStockVOsWithinDay(String stockCode, LocalDate start, LocalDate end){
        LocalDate thisStart = start;
        LocalDate thisEnd = end;

        List<Stock> stockVOList = stockData.get(stockCode);

        List<LocalDate> dates = new ArrayList<>();
        for(int j = 0; j < stockVOList.size(); j++){
            dates.add(stockVOList.get(j).getStockID().getDate());
        }

        while(!dates.contains(thisStart) || !dates.contains(thisEnd)){
            if(!dates.contains(thisStart)){
                thisStart = thisStart.plusDays(1);
            }
            if(!dates.contains(thisEnd)){
                thisEnd = thisEnd.minusDays(1);
            }
            //中间没有数据
            if(thisStart.isAfter(thisEnd)){
                return null;
            }
        }
        int startIndex = dates.indexOf(thisStart);
        int endIndex = dates.indexOf(thisEnd);

        //没有数据
        if(startIndex == -1){
            return null;
        }

        return stockVOList.subList(startIndex, endIndex+1);
    }
}
