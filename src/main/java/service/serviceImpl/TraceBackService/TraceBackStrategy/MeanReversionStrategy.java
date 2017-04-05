package service.serviceImpl.TraceBackService.TraceBackStrategy;

import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import service.serviceImpl.TraceBackService.AllTraceBackStrategy;
import utilities.exceptions.CodeNotFoundException;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.DateShortException;
import utilities.exceptions.NoDataWithinException;
import vo.ChartShowCriteriaVO;
import vo.CumulativeReturnVO;
import vo.MovingAverageVO;
import vo.TraceBackCriteriaVO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Created by cuihua on 2017/3/31.
 */
public class MeanReversionStrategy implements AllTraceBackStrategy {

    ChartService service;

    final double initMpney = 10000;
    double nowMoney;

    public MeanReversionStrategy() {
        this.service = new ChartServiceImpl();
        nowMoney = initMpney;
    }

    @Override
    public List<CumulativeReturnVO> traceBack(List<String> stockPoolCodes, TraceBackCriteriaVO traceBackCriteriaVO) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException {
        // 一次性获得所有的均线数据（整体区间向前推了一天，因为均值回归当日的调仓标准是前一日的均线数据）
        Map<String, List<MovingAverageVO>> allAves = new TreeMap<>();
        for (String s : stockPoolCodes) {
            ChartShowCriteriaVO criteriaVO = new ChartShowCriteriaVO(s, traceBackCriteriaVO.startDate.minusDays(1), traceBackCriteriaVO.endDate.minusDays(1));

            List<Integer> formatAve = new LinkedList<>();
            formatAve.add(traceBackCriteriaVO.formativePeriod);
            Map<Integer, List<MovingAverageVO>> aveInfo = service.getAveData(criteriaVO, formatAve);
            List<MovingAverageVO> aves = aveInfo.values().iterator().next();

            allAves.put(s, aves);
        }


        int holdingNum = traceBackCriteriaVO.holdingNum;
        int holdingPeriod = traceBackCriteriaVO.holdingPeriod;

        int cycles = (int) traceBackCriteriaVO.startDate.until(traceBackCriteriaVO.endDate, ChronoUnit.DAYS) / holdingPeriod;

        LocalDate start, end;

        // 整个周期的计算
        for (int i = 0; i < cycles; i++) {
            start = traceBackCriteriaVO.startDate.plusDays(i * holdingPeriod);
            end = start.plusDays(holdingPeriod-1);

            List<String> thisHoldingPeriodStocks = getTopStockCodes(holdingNum, allAves, start.minusDays(1));

            // TODO
            getPeriodYield(thisHoldingPeriodStocks, start, end);



        }

        // 最后一个不足周期的计算
        start = traceBackCriteriaVO.startDate.plusDays(cycles * holdingPeriod);
        end = traceBackCriteriaVO.endDate;

        List<String> thisHoldingPeriodStocks = getTopStockCodes(holdingNum, allAves, start.minusDays(1));

        // TODO
        getPeriodYield(thisHoldingPeriodStocks, start, end);


        return null;
    }

    private List<String> getTopStockCodes(int holdingNum, Map<String, List<MovingAverageVO>> allAves, LocalDate thisDate) {
        Map<String, Double> result = new HashMap<>(holdingNum);

        // 先加入前 持有股票数 支股票，再计算后面的
        int i = 0;
        for (Map.Entry<String, List<MovingAverageVO>> entry : allAves.entrySet()) {
            if (i < holdingNum) {
                String s = entry.getKey();
                List<MovingAverageVO> thisMA = entry.getValue();
                for (MovingAverageVO vo : thisMA) {
                    if (vo.date == thisDate) {
                        result.put(s, vo.average);
                        break;
                    }
                }
                i++;
            }


            List<MovingAverageVO> temp = entry.getValue();
            for (MovingAverageVO vo : temp) {
                String min = getMin(result);
                if (vo.date == thisDate && vo.average > result.get(min)) {
                    result.remove(min);
                    result.put(entry.getKey(), vo.average);
                }
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


    // 一个持有期周期内的收益率
    private double getPeriodYield(List<String> stockCodes, LocalDate start, LocalDate end) {


        return 0;
    }
}
