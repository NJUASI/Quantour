package com.edu.nju.asi.service.serviceImpl.traceBackService.traceBackStrategy.formateStrategy;


import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/4/20.
 *
 *
 * 当日/N日均值（调仓日期前一日开始）  股票价格、成交额、成交量、振幅、总股本/流通股本、总市值/流通市值
 * 停牌日不计算在内
 */
public class MeanFormateStrategy extends AllFormateStrategy {

    /**
     * 指标在Stock中的名称
     */
    protected String indicatorSpell;

    public MeanFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData, String indicatorSpell) {
        super(allDatesWithData, stockData);
        this.indicatorSpell = indicatorSpell;
    }

    @Override
    public List<FormateRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {

        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        List<FormateRate> formateRate = new ArrayList<>();

        for(int i = 0; i < stockCodes.size(); i++){

//            System.out.println("code:" + stockCodes.get(i));
            List<Stock> stockList = getDataWithoutHaltDay(stockCodes.get(i), periodStartIndex-1, formativePeriod);
            if(stockList == null){
                formateRate.add(new FormateRate(stockCodes.get(i), null));
                continue;
            }

            double indicatorVal = computeMeanValue(stockList, indicatorSpell);
            formateRate.add(new FormateRate(stockCodes.get(i), indicatorVal));
        }

        return formateRate;
    }
}
