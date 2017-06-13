package com.edu.nju.asi.service.serviceImpl.TraceBackService.traceBackStrategy.formateStrategy;

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
 * 动态市盈率:等于总市值/归属于母公司所有者的净利润，净利润根据每期财报动态扩展到年报数据。
 */
public class D_PE_TTM_FormateStrategy extends FinancialFormateStrategy {

    public D_PE_TTM_FormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData, Map<String, List<BasicData>> financialData) {
        super(allDatesWithData, stockData, financialData);
    }

    /**
     * 根据当日或N日平均形成
     *
     * @param stockCodes      股票列表
     * @param periodStart     持有期起始日期;
     * @param formativePeriod 形成期长度
     * @return 形成的数据
     */
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
            BasicData basicData1 = findBasicData(stockCodes.get(i), date);

            //有数据不存在
            if(basicData1 == null){
                formateRate.add(new FormateRate(stockCodes.get(i), null));
                continue;
            }

            //季度
            int quarter = basicData1.getBasicDataID().getDate().getMonthValue() / 4 + 1;
            double totalProfit = new Double(basicData1.getNetProfitAttributableToTheOwnerOfTheParentCompany()) * 10000;

            double D_PE_val = totalMarket / (totalProfit * 4 / quarter);

            formateRate.add(new FormateRate(stockCodes.get(i), D_PE_val));
        }

        return formateRate;
    }
}
