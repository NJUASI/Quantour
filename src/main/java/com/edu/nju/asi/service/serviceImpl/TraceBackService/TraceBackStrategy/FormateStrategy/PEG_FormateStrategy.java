package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;
import com.edu.nju.asi.model.BasicData;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;

import java.time.LocalDate;
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
        return null;
    }
}
