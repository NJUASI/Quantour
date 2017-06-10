package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;

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
 * 净资产收益率
 */
public class ROE_FormateStrategy extends FinancialFormateStrategy {

    public ROE_FormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData, Map<String, List<BasicData>> financialData) {
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

            LocalDate date = stockList.get(0).getStockID().getDate();
            BasicData basicData = findBasicData(stockCodes.get(i), date);

            //有数据不存在
            if(basicData == null){
                formateRate.add(new FormateRate(stockCodes.get(i), null));
                continue;
            }

            double roe = basicData.getReturnOnEquity();

            formateRate.add(new FormateRate(stockCodes.get(i), roe));
        }

        return formateRate;
    }
}
