package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/6/5.
 *
 * 总股本/流通股本 ：A股当日总股数或总流通股数 = A股当日总市值或总流通市值 / 当日收盘价
 *
 */
public class CapitalFormateStrategy extends AllFormateStrategy{

    /**
     * 指标在Stock中的名称
     */
    protected String indicatorSpell;

    public CapitalFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData, String indicatorSpell) {
        super(allDatesWithData, stockData);
        this.indicatorSpell = indicatorSpell;
    }

    @Override
    public List<FormateRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {

        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        List<FormateRate> formateRate = new ArrayList<>();

        Class<Stock> clazz = Stock.class;

        for(int i = 0; i < stockCodes.size(); i++){

            List<Stock> stockList = getDataWithoutHaltDay(stockCodes.get(i), periodStartIndex-1, formativePeriod);
            if(stockList == null){
                formateRate.add(new FormateRate(stockCodes.get(i), null));
                continue;
            }

            Field field = null;
            try {
                field = clazz.getDeclaredField(indicatorSpell);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            field.setAccessible(true);

            try {
                formateRate.add(new FormateRate(stockCodes.get(i), new Double(field.get(stockList.get(0)).toString()) / stockList.get(0).getClose()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return formateRate;
    }
}
