package com.edu.nju.asi.service.serviceImpl.traceBackService.traceBackStrategy.formateStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;
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
    public List<FormateRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {

        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        List<FormateRate> formateRate = new ArrayList<>();

        StandardDeviation stdev = new StandardDeviation();

        for(int i = 0; i < stockCodes.size(); i++){

            List<Stock> stockList = getDataWithoutHaltDay(stockCodes.get(i), periodStartIndex-1, formativePeriod);
            if(stockList == null){
                formateRate.add(new FormateRate(stockCodes.get(i), null));
                continue;
            }

            double[] increaseMargin = new double[stockList.size()];

            for(int j = 0; j < stockList.size(); j++){
                increaseMargin[j] = stockList.get(j).getIncreaseMargin();
            }

            double returnVolatility = stdev.evaluate(increaseMargin) * Math.sqrt(formativePeriod);

            formateRate.add(new FormateRate(stockCodes.get(i), returnVolatility));
        }

        return formateRate;
    }
}
