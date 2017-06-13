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
 * Created by Harvey on 2017/6/6.
 *
 *
 * ATR ：平均真实波动范围 (Average True Range)。真实波幅等于当日股价振幅、最高与昨收差价、最低与昨收差价中的最大值。ATR = 真实波幅的14日移动平均。
 */
public class ATR_FormateStrategy extends AllFormateStrategy {

    public ATR_FormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData) {
        super(allDatesWithData, stockData);
    }

    @Override
    public List<FormateRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {
        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        List<FormateRate> formateRate = new ArrayList<>();

        for(int i = 0; i < stockCodes.size(); i++){

            List<Stock> stockList_14day = getDataWithoutHaltDay(stockCodes.get(i), periodStartIndex-1, 14);

            if(stockList_14day == null){
                formateRate.add(new FormateRate(stockCodes.get(i), null));
            }

            double[] trueRanges = new double[14];

            for(int j = 0; j < stockList_14day.size(); j++){

                Stock stock = stockList_14day.get(j);

                double swingRate = (stock.getHigh() - stock.getLow()) / stock.getPreClose();
                double high_preClose = stock.getHigh() - stock.getPreClose();
                double low_preClose = stock.getLow() - stock.getPreClose();

                trueRanges[j] = Math.max(Math.max(high_preClose,low_preClose), swingRate);

            }

            StandardDeviation stdev = new StandardDeviation();
            double ATR_val = stdev.evaluate(trueRanges);

            formateRate.add(new FormateRate(stockCodes.get(i), ATR_val));
        }

        return formateRate;
    }
}
