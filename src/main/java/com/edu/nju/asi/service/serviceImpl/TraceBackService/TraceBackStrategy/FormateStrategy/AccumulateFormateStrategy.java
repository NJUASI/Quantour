package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FilterConditionRate;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/6/5.
 */
public class AccumulateFormateStrategy extends AllFormateStrategy {

    /**
     * 指标在Stock中的名称
     */
    protected String indicatorSpell;

    public AccumulateFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData, String indicatorSpell) {
        super(allDatesWithData, stockData);
        this.indicatorSpell = indicatorSpell;
    }

    @Override
    public List<FilterConditionRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {
        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        LocalDate endOfFormative = allDatesWithData.get(periodStartIndex - 1);
        LocalDate startOfFormative = allDatesWithData.get(periodStartIndex - formativePeriod);

        List<FilterConditionRate> filterConditionRate = new ArrayList<>();

        //拿到StrategyStock的类
        Class<Stock> clazz = Stock.class;

        for(int i = 0; i < stockCodes.size(); i++){
            double total = 0;
            List<Stock> stockVOList = findStockVOsWithinDay(stockCodes.get(i), startOfFormative, endOfFormative);
            //说明为该形成期没有数据
            if(null == stockVOList){
                continue;
            }

            for(int j = 0; j < stockVOList.size(); j++){
                try {
                    //反射拿到对象的值,并抑制java对修饰符的检查
                    Field field = clazz.getDeclaredField(indicatorSpell);
                    field.setAccessible(true);

                    total += new Double(field.get(stockVOList.get(j)).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }

            filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), total, 0));
        }

        return filterConditionRate;
    }
}
