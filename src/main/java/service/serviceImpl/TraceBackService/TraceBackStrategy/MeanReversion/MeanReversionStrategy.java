package service.serviceImpl.TraceBackService.TraceBackStrategy.MeanReversion;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import service.serviceImpl.TraceBackService.AllTraceBackStrategy;
import service.serviceImpl.TraceBackService.TraceBackStrategy.StrategyStock;
import utilities.exceptions.*;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by cuihua on 2017/3/31.
 */
public class MeanReversionStrategy extends AllTraceBackStrategy {

    private StockDao stockDao;


    public MeanReversionStrategy(List<String> traceBackStockPool, TraceBackCriteriaVO traceBackCriteriaVO, List<LocalDate> allDatesWithData, Map<String, List<StrategyStock>> stockData) throws IOException {
        super(traceBackStockPool, traceBackCriteriaVO, allDatesWithData, stockData);
        stockDao = new StockDaoImpl();
    }

    @Override
    protected List<FormativePeriodRateVO> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException, NoMatchEnumException, DataSourceFirstDayException {
        List<FormativePeriodRateVO> result = new LinkedList<>();

        for (String s : traceBackStockPool) {
            List<StrategyStock> thisStockData = stockData.get(s);

            // 获得前一个交易日
            LocalDate periodJudge;
            int startIndex = allDatesWithData.indexOf(periodStart);
            if (startIndex == 0) {
                throw new DataSourceFirstDayException();
            }
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
            double average = calculateAve(temp);

            double biasRatio = (average - thisStockData.get(neededMRStockIndex).adjClose) / average;
            result.add(new FormativePeriodRateVO(s, biasRatio));
        }
        return result;
    }

    @Override
    protected List<String> pickStocks(List<FormativePeriodRateVO> formativePeriodRate) {
        // 股票代码，偏离度
        Map<String, Double> result = new HashMap<>(holdingNum);

        // 先加入前 持有股票数 支股票，再计算后面的
        int i = 0;
        for (FormativePeriodRateVO vo : formativePeriodRate) {
            if (i < holdingNum) {
                result.put(vo.stockCode, vo.periodReturn);
                i++;
                continue;
            }

            String min = getMin(result);
            if (vo.periodReturn > result.get(min)) {
                result.remove(min);
                result.put(vo.stockCode, vo.periodReturn);
            }
        }
        return new LinkedList<>(result.keySet());
    }

    private String getMin(Map<String, Double> result) {
        String min = result.keySet().iterator().next();
        for (Map.Entry<String, Double> entry : result.entrySet()) {
            if (entry.getValue() < result.get(min)) min = entry.getKey();
        }
        return min;
    }

    /**
     * @param dataList 需要计算的数据集合
     * @return 数据的平均值
     */
    private double calculateAve(List<StrategyStock> dataList) {
        double sum = 0;
        for (StrategyStock stock : dataList) {
            sum += stock.adjClose;
        }
        return sum / dataList.size();
    }
}
