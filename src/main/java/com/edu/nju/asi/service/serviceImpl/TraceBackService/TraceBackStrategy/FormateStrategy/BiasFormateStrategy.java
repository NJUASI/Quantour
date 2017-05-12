package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;

import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.infoCarrier.traceBack.FormativePeriodRate;
import com.edu.nju.asi.infoCarrier.traceBack.StrategyStock;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 形成期的乖离率策略
 */
public class BiasFormateStrategy extends AllFormateStrategy {
    public BiasFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<StrategyStock>> stockData) {
        super(allDatesWithData, stockData);
    }

    /**
     * 形成期／N日均值，用于后续策略筛选
     *
     * @param stockCodes      股票列表
     * @param periodStart     持有期起始日期;
     * @param formativePeriod 形成期长度（MS）／N日均值偏离度（MR）
     * @return 形成的
     */
    @Override
    public List<FormativePeriodRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {
        List<FormativePeriodRate> result = new LinkedList<>();

        for (String s : stockCodes) {
            List<StrategyStock> thisStockData = stockData.get(s);

            // 获得前一个交易日
            LocalDate periodJudge;
            int startIndex = allDatesWithData.indexOf(periodStart);
            if (startIndex == 0) throw new DataSourceFirstDayException();
            periodJudge = allDatesWithData.get(allDatesWithData.indexOf(periodStart) - 1);

            int neededMRStockIndex = thisStockData.indexOf(periodJudge);
            // 此股票没有这一天的数据，则不参与形成
            if (neededMRStockIndex == -1) {
                continue;
            }

            // 前一交易日的均值（一定能够计算）
            List<StrategyStock> temp = thisStockData;
            if (neededMRStockIndex + 1 >= formativePeriod) {
                // 之前的数据充足
                temp = temp.subList(neededMRStockIndex - formativePeriod + 1, neededMRStockIndex + 1);
            }

            //计算数据的平均值
            double sum = 0;
            for (StrategyStock stock : temp) {
                sum += stock.adjClose;
            }
            double average = sum / temp.size();

            double biasRatio = (average - thisStockData.get(neededMRStockIndex).adjClose) / average;
            result.add(new FormativePeriodRate(s, biasRatio));
        }
        return result;
    }
}
