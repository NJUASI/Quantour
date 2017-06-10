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
 * Created by Harvey on 2017/6/9.
 *
 * PEG：市盈率相对于盈利增长比率
 */
public class PEG_FormateStrategy extends FinancialFormateStrategy {

    public PEG_FormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData, Map<String, List<BasicData>> financialData) {
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
            Double totalProfit = ttm(stockCodes.get(i), date, "netProfitAttributableToTheOwnerOfTheParentCompany", 0);
            Double forwardTotalProfit = ttm(stockCodes.get(i), date, "netProfitAttributableToTheOwnerOfTheParentCompany", 4);

            if(totalProfit == null || forwardTotalProfit == null){
                formateRate.add(new FormateRate(stockCodes.get(i), null));
                continue;
            }

            //净利润增长
            Double increase = (totalProfit - forwardTotalProfit) / Math.abs(forwardTotalProfit);

            //归属于母公司所有者的利润以万元为单位
            totalProfit *= 10000;

            //市盈率
            double PE_val = totalMarket / totalProfit;

            formateRate.add(new FormateRate(stockCodes.get(i), PE_val / increase));
        }

        return formateRate;
    }
}
