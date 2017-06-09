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
 * Created by Harvey on 2017/6/8.
 *
 * 市净率形成，总市值/归属母公司股东权益合计
 */
public class PB_FormateStrategy extends FinancialFormateStrategy{

    public PB_FormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData, Map<String, List<BasicData>> financialData) {
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
            //归属母公司股东权益合计（选择当季的数据，若没有当季的数据，则选择前面一个季度的数据）
            LocalDate date = stockList.get(0).getStockID().getDate();
            BasicData basicData = findBasicData(stockCodes.get(i), date);

            if(basicData == null){
                formateRate.add(new FormateRate(stockCodes.get(i), null));
            }
            double totalEquityAttr = 0;
            formateRate.add(new FormateRate(stockCodes.get(i), totalMarket / totalEquityAttr));
        }

        return formateRate;
    }
}
