package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FilterConditionRate;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/6/5.
 *
 * 股价涨幅:  股票1日/5日/20日/60日/120日/250日的累计涨跌幅。N日累计涨跌幅，股票的停牌日计算在内。 股票交易日数少于N时，N日涨幅为空值。
 * 累计换手率: 股票当日/5日/20日/60日/120日/250日的累计换手率。 等于成交股数除以总股本。 N日累计换手率，股票的停牌日计算在内。 股票交易日数少于N时，N日累计换手率为空值。
 */
public class AccumulateFormateStrategy extends AllFormateStrategy {

    /**
     * 指标在Stock中的名称
     */
    protected String indicatorSpell;

    public AccumulateFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData, String indicatorSpell) {
        super(allDatesWithData, stockData);
        this.indicatorSpell = indicatorSpell;
    }

    @Override
    public List<FilterConditionRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {
        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        List<FilterConditionRate> filterConditionRate = new ArrayList<>();

        for (int i = 0; i < stockCodes.size(); i++) {
            double indicatorVal = calculateAccumulativeValue(getDateWithHaltDay(stockCodes.get(i), periodStartIndex - 1, formativePeriod), indicatorSpell);
            filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), indicatorVal, 0));
        }

        return filterConditionRate;
    }
}
