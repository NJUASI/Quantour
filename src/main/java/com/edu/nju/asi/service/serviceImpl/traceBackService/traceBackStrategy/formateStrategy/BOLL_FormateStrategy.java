package com.edu.nju.asi.service.serviceImpl.traceBackService.traceBackStrategy.formateStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.enums.IndicatorType;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/6/6.
 *
 * 布林线上线等于20日复权均价+2*stdev(后复权收盘价，20), 布林线下线等于20日复权均价-2*stdev(后复权收盘价，20)
 */
public class BOLL_FormateStrategy extends AllFormateStrategy {

    private IndicatorType indicatorType;

    public BOLL_FormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData, IndicatorType indicatorType) {
        super(allDatesWithData, stockData);
        this.indicatorType = indicatorType;
    }

    @Override
    public List<FormateRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {

        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        List<FormateRate> formateRate = new ArrayList<>();

        StandardDeviation stdev = new StandardDeviation();
        Mean mean = new Mean();

        for(int i = 0; i < stockCodes.size(); i++){

            List<Stock> stockList_20Days = getDataWithoutHaltDay(stockCodes.get(i), periodStartIndex-1, 20);
            if(stockList_20Days == null){
                formateRate.add(new FormateRate(stockCodes.get(i), null));
                continue;
            }

            double[] afterAdjClose = new double[stockList_20Days.size()];

            for(int j = 0; j < stockList_20Days.size(); j++){
                afterAdjClose[j] = stockList_20Days.get(j).getAfterAdjClose();
            }

            double mean_20Days = mean.evaluate(afterAdjClose);
            double stdev_afterAdjClose = stdev.evaluate(afterAdjClose);

            double value = 0;

            switch (indicatorType){
                case BOLL_DOWN_BANDS:
                    value = mean_20Days - 2 * stdev_afterAdjClose;
                    break;
                case BOLL_UP_BANDS:
                    value = mean_20Days + 2 * stdev_afterAdjClose;
                    break;
            }

            formateRate.add(new FormateRate(stockCodes.get(i), value));
        }

        return formateRate;


    }
}
