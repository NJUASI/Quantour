package service.serviceImpl.TraceBackService.TraceBackStrategy.MeanReversion;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import po.StockPO;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import service.serviceImpl.TraceBackService.AllTraceBackStrategy;
import utilities.enums.MovingAverageType;
import utilities.exceptions.CodeNotFoundException;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.DateShortException;
import utilities.exceptions.NoDataWithinException;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by cuihua on 2017/3/31.
 */
public class MeanReversionStrategy extends AllTraceBackStrategy {

    private ChartService chartService;
    private StockDao stockDao;

    // 默认1000元初始投资资本
    private final double initMpney = 1000;
    private double nowMoney;

    // 持股数，持有期，N日均值
    private final int holdingNum, holdingPeriod, formativePeriod;

    // 所有股票
    private final List<LocalDate> withinDates;

    // 挑选出的持有期N支股票
    private List<String> wantedStockCodes;

    // 保存相应要返回的数据
    List<CumulativeReturnVO> strategyCumulativeReturn;
    List<HoldingDetailVO> holdingDetailVOS;

    public MeanReversionStrategy(List<String> stockPoolCodes, TraceBackCriteriaVO traceBackCriteriaVO) throws IOException {
        super(stockPoolCodes, traceBackCriteriaVO);
        chartService = new ChartServiceImpl();
        stockDao = new StockDaoImpl();
        nowMoney = initMpney;

        holdingNum = traceBackCriteriaVO.holdingNum;
        holdingPeriod = traceBackCriteriaVO.holdingPeriod;
        formativePeriod = traceBackCriteriaVO.formativePeriod;

        withinDates = stockDao.getDateWithData(traceBackCriteriaVO.startDate, traceBackCriteriaVO.endDate);

        strategyCumulativeReturn = new LinkedList<>();
        holdingDetailVOS = new LinkedList<>();
    }

    @Override
    public TraceBackStrategyVO traceBack() throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException {
        int cycles = withinDates.size() / holdingPeriod;

        // 回测时间太短，不足一个持有期
        if (cycles == 0) {
            calculate(withinDates.get(0), withinDates.get(withinDates.size() - 1), 0);
            return new TraceBackStrategyVO(strategyCumulativeReturn, holdingDetailVOS);
        }

        // 至少一个持有期
        LocalDate periodStart, periodEnd;
        // 整个周期的计算
        for (int i = 0; i < cycles; i++) {
            int startIndex = i * holdingPeriod;
            periodStart = withinDates.get(startIndex);
            periodEnd = withinDates.get(startIndex + holdingPeriod - 1);

            wantedStockCodes = pickStocks(formate(stockPoolCodes, periodStart, formativePeriod));
            calculate(periodStart, periodEnd, i);
        }

        // 最后一个不足周期的计算
        if (withinDates.size() % holdingPeriod != 0) {
            periodStart = withinDates.get(cycles * holdingPeriod);
            periodEnd = withinDates.get(withinDates.size() - 1);

            wantedStockCodes = pickStocks(formate(stockPoolCodes, periodStart, formativePeriod));
            calculate(periodStart, periodEnd, cycles + 1);
        }

        // 根据果仁网，第一天数据设置为0
        strategyCumulativeReturn.get(0).cumulativeReturn = 0;

        return new TraceBackStrategyVO(maxRetracement(strategyCumulativeReturn), holdingDetailVOS);
    }

    @Override
    protected List<FormativePeriodRateVO> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException {
        List<FormativePeriodRateVO> result = new LinkedList<>();

        for (String s : stockPoolCodes) {
            // 获得前一个交易日的均值
            LocalDate periodJudge;
            int startIndex = withinDates.indexOf(periodStart);
            if (startIndex == 0) {
                List<LocalDate> allDates = stockDao.getDateWithData();
                periodJudge = allDates.get(allDates.indexOf(periodStart) - 1);
            } else {
                periodJudge = withinDates.get(withinDates.indexOf(periodStart) - 1);
            }

            ChartShowCriteriaVO criteriaVO = new ChartShowCriteriaVO(s, periodJudge, periodJudge);
            List<MovingAverageType> formatAve = new LinkedList<>();
            formatAve.add(MovingAverageType.getEnum(traceBackCriteriaVO.formativePeriod));
            Map<MovingAverageType, List<MovingAverageVO>> aveInfo = chartService.getAveData(criteriaVO, formatAve);
            MovingAverageVO ma = aveInfo.values().iterator().next().get(0);

            StockPO po = stockDao.getStockData(s, periodJudge);
            double biasRatio = (ma.average - po.getAdjClose()) / ma.average;

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

    @Override
    protected void calculate(LocalDate periodStart, LocalDate periodEnd, int periodSerial) throws DateNotWithinException, NoDataWithinException, IOException {
        Map<LocalDate, List<Double>> forCalcu = new TreeMap<>();
        List<Double> tempYields = new LinkedList<>();

        // 对阶段内的每只股票进行数据读取
        for (String s : wantedStockCodes) {
            List<StockPO> pos = stockDao.getStockData(s, periodStart, periodEnd);

            for (StockPO po : pos) {
                LocalDate thisDate = po.getDate();
                double profit = getProfit(po);

                if (forCalcu.keySet().contains(thisDate)) {
                    forCalcu.get(thisDate).add(profit);
                } else {
                    List<Double> values = new LinkedList<>();
                    values.add(profit);
                    forCalcu.put(thisDate, values);
                }
            }
        }

        // 依次处理每一交易日
        for (Map.Entry<LocalDate, List<Double>> entry : forCalcu.entrySet()) {
            double thisYield = getYield(entry.getValue());
            strategyCumulativeReturn.add(new CumulativeReturnVO(entry.getKey(), thisYield, false));
            tempYields.add(thisYield);
        }

        // 对这整个周期进行处理
        double yieldSum = 0;
        for (double tempYield : tempYields) {
            yieldSum += tempYield;
        }
        nowMoney *= (yieldSum+1);
        holdingDetailVOS.add(new HoldingDetailVO(periodSerial, periodStart, periodEnd, holdingNum, yieldSum, nowMoney));
    }


    private String getMin(Map<String, Double> result) {
        String min = result.keySet().iterator().next();
        for (Map.Entry<String, Double> entry : result.entrySet()) {
            if (entry.getValue() < result.get(min)) min = entry.getKey();
        }
        return min;
    }


    // 计算单只股票在单日对此日造成的收益率影响
    private double getProfit(StockPO po) {
        return po.getClose() / po.getPreClose() - 1;
    }

    // 计算value的平均值
    private double getYield(List<Double> value) {
        double sum = 0;
        for (double temp : value) {
            sum += temp;
        }
        return sum / value.size();
    }
}
