package service.serviceImpl.TraceBackService.TraceBackStrategy.MeanReversion;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import po.StockPO;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import service.serviceImpl.TraceBackService.AllTraceBackStrategy;
import utilities.enums.MovingAverageType;
import utilities.exceptions.*;
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
    private List<LocalDate> withinDates;

    // 挑选出的持有期N支股票
    private List<String> wantedStockCodes;

    /**
     * 用于保存均值回归计算中所需的所有股票数据
     */
    Map<String, List<MRStock>> stockData;

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

        strategyCumulativeReturn = new LinkedList<>();
        holdingDetailVOS = new LinkedList<>();

        initStrategyData();
    }

    private void initStrategyData() throws IOException {
        withinDates = stockDao.getDateWithData();

        stockData = new HashMap<>();
        for (String thisStockCode : stockPoolCodes) {
            List<StockPO> tempPOS = stockDao.getStockData(thisStockCode);
            stockData.put(thisStockCode, convertStockPOS(tempPOS));
        }
    }

    @Override
    public TraceBackStrategyVO traceBack() throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException, NoMatchEnumException, DataSourceFirstDayException {
        int allStartIndex = withinDates.indexOf(getClosestWithinDate(traceBackCriteriaVO.startDate, true));
        int allEndIndex = withinDates.indexOf(getClosestWithinDate(traceBackCriteriaVO.endDate, false));

        int cycles = (allEndIndex - allStartIndex + 1) / holdingPeriod;

        // 回测时间太短，不足一个持有期
        if (cycles == 0) {
            calculate(traceBackCriteriaVO.startDate, traceBackCriteriaVO.endDate, 0);
            return new TraceBackStrategyVO(strategyCumulativeReturn, holdingDetailVOS);
        }

        // 至少一个持有期，整个周期的计算
        for (int i = 0; i < cycles; i++) {
            int startIndex = allStartIndex + i * holdingPeriod;
            int endIndex = startIndex + holdingPeriod - 1;
            cycleCalcu(startIndex, endIndex, i);
        }

        // 最后一个不足周期的计算
        if ((allEndIndex - allStartIndex + 1) % holdingPeriod != 0) {
            int startIndex = allStartIndex + cycles * holdingPeriod;
            int endIndex = allEndIndex;
            cycleCalcu(startIndex, endIndex, cycles);
        }

        // 根据果仁网，第一天数据设置为0
        strategyCumulativeReturn.get(0).cumulativeReturn = 0;

        return new TraceBackStrategyVO(maxRetracement(strategyCumulativeReturn), holdingDetailVOS);
    }

    private void cycleCalcu(int startIndex, int endIndex, int periodSerial) throws DateNotWithinException, NoMatchEnumException, IOException, NoDataWithinException, CodeNotFoundException, DateShortException, DataSourceFirstDayException {
        System.out.println("calculate cycle: " + periodSerial);

        LocalDate periodStart = withinDates.get(startIndex);
        LocalDate periodEnd = withinDates.get(endIndex);
        wantedStockCodes = pickStocks(formate(stockPoolCodes, periodStart, formativePeriod));
        calculate(periodStart, periodEnd, periodSerial);
    }

    @Override
    protected List<FormativePeriodRateVO> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException, NoMatchEnumException, DataSourceFirstDayException {
        List<FormativePeriodRateVO> result = new LinkedList<>();

        for (String s : stockPoolCodes) {
            List<MRStock> thisStockData = stockData.get(s);

            // 获得前一个交易日
            LocalDate periodJudge;
            int startIndex = withinDates.indexOf(periodStart);
            if (startIndex == 0) {
                throw new DataSourceFirstDayException();
            }
            periodJudge = withinDates.get(withinDates.indexOf(periodStart) - 1);

            int neededMRStockIndex = findStockWithEspecialDate(thisStockData, periodJudge);
            // 此股票没有这一天的数据，则不参与形成
            if (neededMRStockIndex == -1) {
                continue;
            }

            // 前一交易日的均值（一定能够计算）
            List<MRStock> temp = thisStockData;
            if (neededMRStockIndex + 1 >= formativePeriod){
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

    @Override
    protected void calculate(LocalDate periodStart, LocalDate periodEnd, int periodSerial) throws DateNotWithinException, NoDataWithinException, IOException {
        Map<LocalDate, List<Double>> forCalcu = new TreeMap<>();
        List<Double> tempYields = new LinkedList<>();

        // 对阶段内的每只股票进行数据读取
        for (String s : wantedStockCodes) {
            List<MRStock> stocks = stockData.get(s);
            for (MRStock stock : stocks) {

                if (isDateWithinWanted(periodStart, periodEnd, stock.date)) {
                    LocalDate thisDate = stock.date;
                    double profit = getProfit(stock);

                    if (forCalcu.keySet().contains(thisDate)) {
                        forCalcu.get(thisDate).add(profit);
                    } else {
                        List<Double> values = new LinkedList<>();
                        values.add(profit);
                        forCalcu.put(thisDate, values);
                    }
                }
            }
        }

        // 依次处理每一交易日
        for (Map.Entry<LocalDate, List<Double>> entry : forCalcu.entrySet()) {
            double thisYield = getYield(entry.getValue());
            strategyCumulativeReturn.add(new CumulativeReturnVO(entry.getKey(), thisYield, false));
            tempYields.add(thisYield);
        }

        // 对这整个周期进行处理 TODO sum有问题
        double yieldSum = 0;
        for (double tempYield : tempYields) {
            yieldSum += tempYield;
        }
        nowMoney *= (yieldSum + 1);
        holdingDetailVOS.add(new HoldingDetailVO(periodSerial, periodStart, periodEnd, holdingNum, yieldSum, nowMoney));
    }



    private LocalDate getClosestWithinDate(LocalDate thisDate, boolean biggerThanThisDate) {
        int index = withinDates.indexOf(thisDate);
        if (index != -1) return thisDate;
        else {
            if (biggerThanThisDate) {
                for (LocalDate temp : withinDates) {
                    if (temp.isBefore(thisDate)) continue;
                    return temp;
                }
            } else {
                LocalDate result = null;
                for (LocalDate temp : withinDates) {
                    if (temp.isBefore(thisDate)) {
                        result = temp;
                    } else if (temp.isAfter(thisDate)) {
                        return result;
                    }
                }
            }
        }
        return null;
    }


    private List<MRStock> convertStockPOS(List<StockPO> pos) {
        List<MRStock> result = new LinkedList<>();
        for (StockPO thisPO : pos) {
            result.add(new MRStock(thisPO));
        }
        return result;
    }

    private String getMin(Map<String, Double> result) {
        String min = result.keySet().iterator().next();
        for (Map.Entry<String, Double> entry : result.entrySet()) {
            if (entry.getValue() < result.get(min)) min = entry.getKey();
        }
        return min;
    }


    // 计算单只股票在单日对此日造成的收益率影响
    private double getProfit(MRStock stock) {
        return stock.close / stock.preClose - 1;
    }

    // 计算value的平均值
    private double getYield(List<Double> value) {
        double sum = 0;
        for (double temp : value) {
            sum += temp;
        }
        return sum / value.size();
    }


    /**
     * @param stocks       股票池
     * @param especialDate 指定日期
     * @return 池中指定日期的MRStock序号，没有返回-1
     */
    private int findStockWithEspecialDate(List<MRStock> stocks, LocalDate especialDate) {
        int index = 0;
        for (; index < stocks.size(); index++) {
            MRStock thisStock = stocks.get(index);
            if (thisStock.date.isEqual(especialDate)) {
                return index;
            }
            if (thisStock.date.isAfter(especialDate)) break;
        }
        return -1;
    }

    /**
     * @param dataList 需要计算的数据集合
     * @return 数据的平均值
     */
    private double calculateAve(List<MRStock> dataList) {
        double sum = 0;
        for (MRStock stock : dataList) {
            sum += stock.close;
        }
        return sum / dataList.size();
    }

    private boolean isDateWithinWanted(LocalDate start, LocalDate end, LocalDate now) {
        if (now.isEqual(start) || now.isEqual(end)) {
            return true;
        }
        if (now.isAfter(start) && now.isBefore(end)) {
            return true;
        }
        return false;
    }
}
