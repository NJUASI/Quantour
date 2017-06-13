package com.edu.nju.asi.service.serviceImpl.traceBackService.traceBackStrategy.formateStrategy;

import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;

import java.lang.reflect.Field;
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
     * 回测股票池中的股票数据
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
    public abstract List<FormateRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException;


    /**
     * 计算一个指标N日平均值
     * @param stockList 需要计算的某只股票的数据
     * @param indicatorSpell    指标
     * @return
     */
    protected Double computeMeanValue(List<Stock> stockList, String indicatorSpell){

        //拿到StrategyStock的类
        Class<Stock> clazz = Stock.class;

        double total = 0;

        for(int j = 0; j < stockList.size(); j++){
            try {
                //反射拿到对象的值,并抑制java对修饰符的检查
                Field field = clazz.getDeclaredField(indicatorSpell);
                field.setAccessible(true);

                total += new Double(field.get(stockList.get(j)).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        return total/stockList.size();
    }

    /**
     * 计算一个指标N日累计值
     * @param stockList 需要计算的某只股票的数据
     * @param indicatorSpell    指标
     * @return
     */
    protected Double computeAccumulativeValue(List<Stock> stockList, String indicatorSpell){

        //拿到StrategyStock的类
        Class<Stock> clazz = Stock.class;

        double total = 0;

        for(int j = 0; j < stockList.size(); j++){
            try {
                //反射拿到对象的值,并抑制java对修饰符的检查
                Field field = clazz.getDeclaredField(indicatorSpell);
                field.setAccessible(true);

                total += new Double(field.get(stockList.get(j)).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        return total;
    }

    /**
     * N日的某支股票的数据，停牌日不计算在内
     * @param code      要获取数据的股票代码
     * @param endIndex  要计算日期的最后一天
     * @param period    要计算的时长（停牌日不计算在内）
     * @return N日的某支股票的数据，停牌日不计算在内
     */
    public List<Stock> getDataWithoutHaltDay(String code, int endIndex, int period){

        int startIndex = endIndex - period + 1;

        LocalDate start = allDatesWithData.get(startIndex);
        LocalDate end = allDatesWithData.get(endIndex);

        List<Stock> stockList = findStockVOsWithinDay(code, start, end);
        // 说明为该形成期没有数据
        if(null == stockList){
            return null;
        }
        // 数据库内交易日小于n, 数据不足
        else if(findStockVOsWithinDay(code, stockData.get(code).get(0).getStockID().getDate(), end).size() < period){
            return null;
        }
        else {
            // 停牌日不算入交易日内, 补足缺少的数据
            int i = 0;
            if(code.equals("002861")){
                System.out.println(i);
            }
            while(stockList.size() < period){
                int k = period - stockList.size();
                i++;
                if(k == 1){
                    if((startIndex - period - i) < 0){
                        return null;
                    }
                    start = allDatesWithData.get(startIndex - period - i);
                    Stock stock = findStock(code, start);
                    if(stock == null){
                        continue;
                    }else {
                        stockList.add(0, stock);
                    }
                }
                else {
                    if((startIndex - period - i*k) < 0){
                        return null;
                    }
                    start = allDatesWithData.get(startIndex - period - i*k);
                    end = allDatesWithData.get(startIndex - period - (i-1)*k - 1);
                    List<Stock> stocks = findStockVOsWithinDay(code, start, end);
                    if(stocks == null){
                        continue;
                    }else {
                        stockList.addAll(0,findStockVOsWithinDay(code, start, end));
                    }
                }
            }
        }
        return stockList;
    }

    /**
     * N日的某支股票的数据，停牌日计算在内
     * @param code      要获取数据的股票代码
     * @param endIndex  要计算日期的最后一天
     * @param period    要计算的时长（停牌日不计算在内）
     * @return N日的某支股票的数据，停牌日计算在内
     */
    protected List<Stock> getDataWithHaltDay(String code, int endIndex, int period){
        int startIndex = endIndex - period + 1;

        LocalDate start = allDatesWithData.get(startIndex);
        LocalDate end = allDatesWithData.get(endIndex);

        List<Stock> stockList = findStockVOsWithinDay(code, start, end);
        // 说明为该形成期没有数据
        if(null == stockList){
            return null;
        }

        return stockList;
    }

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

    protected Stock findStock(String stockCode, LocalDate date){

        List<Stock> stockVOList = stockData.get(stockCode);

        List<LocalDate> dates = new ArrayList<>();
        for(int j = 0; j < stockVOList.size(); j++){
            dates.add(stockVOList.get(j).getStockID().getDate());
        }

        if(dates.contains(date)){
            return stockVOList.get(dates.indexOf(date));
        }
        else {
            return null;
        }
    }
}
