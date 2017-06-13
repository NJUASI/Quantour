package com.edu.nju.asi.service.serviceImpl.traceBackService.traceBackStrategy.formateStrategy;

import com.edu.nju.asi.model.BasicData;
import com.edu.nju.asi.model.Stock;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/6/9.
 *
 * 所有有关财务指标的形成策略都应该继承该类
 */
public abstract class FinancialFormateStrategy extends AllFormateStrategy{

    /**
     * 回测股票池的所有财务指标
     */
    Map<String, List<BasicData>> financialData;

    public FinancialFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData, Map<String, List<BasicData>> financialData) {
        super(allDatesWithData, stockData);
        this.financialData = financialData;
    }

    /**
     * 返回股票最新4个季度指标加在一起的值。例子：TTM（营业收入， 0）返回最新4个季报营业收入之和，
     * TTM（营业收入， 4）返回1年前的4个季报营业收入之和。
     * @param code 股票代码
     * @param date 日期
     * @param indicator 季报指标
     * @param forwardQuarter 前移季度数
     * @return
     */
    protected Double ttm(String code, LocalDate date, String indicator, int forwardQuarter){

        //多往前面取一个季度的数据
        List<BasicData> basicDatas = findBasicData(code, date,  5);
        if(basicDatas == null){
            return null;
        }

        Double total = new Double(0);
        Class<BasicData> clazz = BasicData.class;

        Field field = null;
        try {
            field = clazz.getDeclaredField(indicator);
            field.setAccessible(true);

            for(int i = 1; i < basicDatas.size(); i++){

                //是一年中第一个季度
                if(basicDatas.get(i).getBasicDataID().getDate().getMonthValue() == 3){
                    total += new Double(field.get(basicDatas.get(i)).toString());
                }
                //不是一年中的第一个季度，由于季度指标在一年中是累加的，则应该算出每个季度的值
                else {
                    total += new Double(field.get(basicDatas.get(i)).toString()) - new Double(field.get(basicDatas.get(i-1)).toString());
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return total;
    }

    /**
     * 返回季报指标的年报数据， 前移年数 = 0时， 返回最近年报数据。 例子：假设现在是2016Q2，
     * Annual（营业收入，0）就是2015年报的营业收入，而Annual（营业收入，1）就是2014年报收入。
     * @param code 股票代码
     * @param date 回测日期
     * @param indicator 季报指标
     * @param forwardYear 前移年数
     * @return
     */
    protected Double annual(String code,LocalDate date, String indicator, int forwardYear){

        //由于季报指标是累加的，则年报指标的就是12/31号的指标
        BasicData basicData = findBasicData(code, LocalDate.of(date.getYear()-1, 12, 31));
        if(basicData == null){
            return null;
        }

        Double total = new Double(0);
        Class<BasicData> clazz = BasicData.class;

        Field field = null;
        try {
            field = clazz.getDeclaredField(indicator);

            field.setAccessible(true);
            total += new Double(field.get(basicData).toString());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return total;

    }

    /**
     * 通过股票代码和日期，找到该日期的财务指标
     * @param code 股票代码
     * @param date 日期
     * @return
     */
    protected BasicData findBasicData(String code, LocalDate date){

        LocalDate target = findQuarter(date);

        List<BasicData> thisFinancialData = financialData.get(code);
        //如果target超出最小范围，则返回null
        if(target.isBefore(thisFinancialData.get(0).getBasicDataID().getDate())){
            return null;
        }
        //如果target超出最大范围，则返回最近一季度的财务指标
        if(target.isAfter(thisFinancialData.get(thisFinancialData.size() - 1).getBasicDataID().getDate())){
            return thisFinancialData.get(thisFinancialData.size() - 1);
        }

        for(BasicData basicData : thisFinancialData) {
            if (basicData.getBasicDataID().getDate().equals(basicData.getBasicDataID().getDate())) {
                return basicData;
            }
        }

        return null;
    }

    /**
     * 找到需要的时间段的某只股票的财务指标
     * @param code 股票代码
     * @param date 需要的财务指标的最后一个日期
     * @param num 数量
     * @return
     */
    protected List<BasicData> findBasicData(String code, LocalDate date, int num){

        List<BasicData> basicDatas = new ArrayList<>();

        LocalDate target = findQuarter(date);

        List<BasicData> thisFinancialData = financialData.get(code);
        //如果target超出最小范围，则返回null
        if(target.isBefore(thisFinancialData.get(0).getBasicDataID().getDate())){
            return null;
        }
        //如果target超出最大范围，则从最后一个日期为所存储的最后一个日期
        if(target.isAfter(thisFinancialData.get(thisFinancialData.size() - 1).getBasicDataID().getDate())){
            date = thisFinancialData.get(thisFinancialData.size() - 1).getBasicDataID().getDate();
        }

        for(int i = thisFinancialData.size()-1; i >= 0; i--){
            if(thisFinancialData.get(i).getBasicDataID().getDate().equals(date)){
                if(i-num+1 < 0){
                    return null;
                }else {
                    for(int j = 0; j < num; j++){
                        basicDatas.add(0, thisFinancialData.get(i - j));
                    }
                }
            }
        }

        return basicDatas;
    }

    /**
     * 找到对应该date的季度
     * @param date
     * @return
     */
    protected LocalDate findQuarter(LocalDate date){

        int year = date.getYear();
        int month = date.getMonthValue();

        if(month <= 3){
            return LocalDate.of(year--, 12, 31);
        }else if(month <= 6){
            return LocalDate.of(year, 3, 31);
        }else if(month <= 9){
            return LocalDate.of(year, 6, 30);
        }else if(month <= 12){
            return LocalDate.of(year, 9, 30);
        }

        return date;
    }
}
