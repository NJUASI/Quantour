package com.edu.nju.asi.service.serviceImpl.traceBackService.traceBackStrategy.formateStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;
import com.edu.nju.asi.model.BasicData;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/6/10.
 *
 * 每股收益：用基本每股收益计算
 */
public class EPS_FormateStrategy extends FinancialFormateStrategy {

    public EPS_FormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData, Map<String, List<BasicData>> financialData) {
        super(allDatesWithData, stockData, financialData);
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
            //总市值
            double totalMarket = new Double(stockList.get(0).getTotalValue());

            //TTM(归属于母公司所有者的净利润)（选择当季的数据，若没有当季的数据，则选择前面一个季度的数据）
            LocalDate date = stockList.get(0).getStockID().getDate();

            LocalDate targetDate = findQuarter(date);
            BasicData basicData = findBasicData(stockCodes.get(i), date);

            //数据不存在或者数据和目标季度不能对应
            if(basicData == null || !targetDate.isEqual(basicData.getBasicDataID().getDate())){
                formateRate.add(new FormateRate(stockCodes.get(i), null));
                continue;
            }

            double eps_val = 0;

            //第一季度，直接用基本每股收益
            if(basicData.getBasicDataID().getDate().getMonthValue() == 3){
                eps_val = basicData.getBasicIncomePerStock();
            }else {
                LocalDate targetDate2 = findQuarter(targetDate.minusMonths(3));
                BasicData basicData2 = findBasicData(stockCodes.get(i), targetDate2);

                //数据不存在
                if(basicData2 == null){
                    formateRate.add(new FormateRate(stockCodes.get(i), null));
                    continue;
                }

                eps_val = basicData.getBasicIncomePerStock() - basicData2.getBasicIncomePerStock();
            }

            formateRate.add(new FormateRate(stockCodes.get(i), eps_val));
        }

        return formateRate;
    }
}
