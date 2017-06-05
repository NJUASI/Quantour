package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FilterConditionRate;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/6/5.
 *
 * 收益波动率
 *
 * 股票20日/60日/120日/250日收益波动率。停牌日不算入交易日内。 等于于stdev(1日涨幅，N日) * sqrt(N日) 。
 */
public class ReturnVolatilityFormateStrategy extends AllFormateStrategy{

    public ReturnVolatilityFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData) {
        super(allDatesWithData, stockData);
    }


    @Override
    public List<FilterConditionRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {

        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        LocalDate endOfFormative = allDatesWithData.get(periodStartIndex - 1);
        LocalDate startOfFormative = allDatesWithData.get(periodStartIndex - formativePeriod);

        List<FilterConditionRate> filterConditionRate = new ArrayList<>();

        StandardDeviation stdev = new StandardDeviation();

        for(int i = 0; i < stockCodes.size(); i++){
            List<Stock> stockVOList = findStockVOsWithinDay(stockCodes.get(i), startOfFormative, endOfFormative);

            double[] increaseMargin = new double[stockVOList.size()];
            //说明为该形成期没有数据
            if(null == stockVOList){
                continue;
            }

            for(int j = 0; j < stockVOList.size(); j++){
                increaseMargin[j] = stockVOList.get(j).getIncreaseMargin();
            }

            double returnVolatility = stdev.evaluate(increaseMargin) * Math.sqrt(formativePeriod);

            filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), returnVolatility, 0));
        }

        return filterConditionRate;
    }
}
