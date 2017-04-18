package service.serviceImpl.TraceBackService;

import service.serviceImpl.TraceBackService.TraceBackStrategy.StrategyStock;
import utilities.exceptions.*;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by harvey on 17-4-3.
 */
public abstract class AllTraceBackStrategy {

    /**
     * 目标股票池
     */
    public List<String> traceBackStockPool;

    /**
     * 回测标准
     */
    protected TraceBackCriteriaVO traceBackCriteriaVO;

    // 默认1000元初始投资资本
    private final double initMoney = 1;
    private double nowMoney;

    // 持股数，持有期，N日均值（MR）／形成期（MS）
    protected int holdingNum, holdingPeriod, formativePeriod;



    /*
    需要重复计算的一些东西，故保存
     */
    /**
     * 所有有股票数据的交易日
     */
    protected List<LocalDate> allDatesWithData;

    /**
     * 所有股票池中的股票数据
     */
    protected Map<String, List<StrategyStock>> stockData;


    public AllTraceBackStrategy(List<String> traceBackStockPool, TraceBackCriteriaVO traceBackCriteriaVO, List<LocalDate> allDatesWithData, Map<String, List<StrategyStock>> stockData) {
        this.traceBackStockPool = traceBackStockPool;
        this.traceBackCriteriaVO = traceBackCriteriaVO;
        this.allDatesWithData = allDatesWithData;
        this.stockData = stockData;
    }

    /**
     * 形成期／N日均值，用于后续策略筛选
     *
     * @param stockCodes      股票列表
     * @param periodStart     持有期起始日期
     * @param formativePeriod 形成期长度（MS）／N日均值偏离度（MR）
     * @return 形成的
     */
    protected abstract List<FormativePeriodRateVO> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException, NoMatchEnumException, DataSourceFirstDayException;

    /**
     * 挑选形成期后的股票数据，用于后续策略计算
     *
     * @param formativePeriodRate 形成期长度（MS）／N日均值偏离度（MR）
     * @return 持有期中会持有的股票代码
     */
    protected abstract List<String> pickStocks(List<FormativePeriodRateVO> formativePeriodRate);


    /**
     * 根据目标股票池及所给的标准，返回策略的累计收益率
     *
     * @return List<CumulativeReturnVO> 策略的累计收益率
     */
    public List<CumulativeReturnVO> traceBack(TraceBackCriteriaVO traceBackCriteriaVO) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException, NoMatchEnumException, DataSourceFirstDayException {
        setTraceBackInfo(traceBackCriteriaVO);

        // 保存相应要返回的数据
        List<CumulativeReturnVO> strategyCumulativeReturn = new LinkedList<>();


        //区间第一个交易日在allDatesWithData中的位置
        int allStartIndex, allEndIndex;
        LocalDate thisStart = this.traceBackCriteriaVO.startDate;
        LocalDate thisEnd = this.traceBackCriteriaVO.endDate;
        while (!allDatesWithData.contains(thisStart) || !allDatesWithData.contains(thisEnd)) {
            if (!allDatesWithData.contains(thisStart)) {
                thisStart = thisStart.plusDays(1);
            }
            if (!allDatesWithData.contains(thisEnd)) {
                thisEnd = thisEnd.minusDays(1);
            }
        }
        allStartIndex = allDatesWithData.indexOf(thisStart);
        allEndIndex = allDatesWithData.indexOf(thisEnd);


        int cycles = (allEndIndex - allStartIndex + 1) / holdingPeriod;

        // 回测时间太短，不足一个持有期
        if (cycles == 0) {
            cycleCalcu(allStartIndex, allEndIndex, 0);
            return strategyCumulativeReturn;
        }

        // 至少一个持有期，整个周期的计算
        for (int i = 0; i < cycles; i++) {
            int startIndex = allStartIndex + i * holdingPeriod;
            int endIndex = startIndex + holdingPeriod - 1;
            strategyCumulativeReturn.addAll(cycleCalcu(startIndex, endIndex, i));
        }

        // 最后一个不足周期的计算
        if ((allEndIndex - allStartIndex + 1) % holdingPeriod != 0) {
            int startIndex = allStartIndex + cycles * holdingPeriod;
            int endIndex = allEndIndex;
            strategyCumulativeReturn.addAll(cycleCalcu(startIndex, endIndex, cycles));
        }

        // 根据果仁网，第一天数据设置为0
        strategyCumulativeReturn.get(0).cumulativeReturn = 0;

        return maxRetracement(strategyCumulativeReturn);
    }

    /**
     * 计算最大回撤点
     *
     * @param cumulativeReturnVOS 未计算最大回测的累计收益率
     * @return List<CumulativeReturnVO> 标记了两个最大回撤点的累计收益率，标记点的isTraceBack为true
     */
    public List<CumulativeReturnVO> maxRetracement(List<CumulativeReturnVO> cumulativeReturnVOS) {

        //TODO gcm 用了两个循环，不知道怎么改进算法，你们可以帮下忙

        //回撤点的峰值在list中的位置
        int top = 0;
        //回撤点的谷值在list中的位置
        int down = 0;

        //将第一个位置默认为最大回撤值点
        cumulativeReturnVOS.get(0).isTraceBack = true;

        double max = 0;

        for (int i = 0; i < cumulativeReturnVOS.size(); i++) {
            for (int j = i + 1; j < cumulativeReturnVOS.size(); j++) {
                double diff = cumulativeReturnVOS.get(i).cumulativeReturn - cumulativeReturnVOS.get(j).cumulativeReturn;
                if (max < diff) {
                    //重新设置最大回撤点
                    cumulativeReturnVOS.get(top).isTraceBack = false;
                    cumulativeReturnVOS.get(down).isTraceBack = false;
                    top = i;
                    down = j;
                    cumulativeReturnVOS.get(top).isTraceBack = true;
                    cumulativeReturnVOS.get(down).isTraceBack = true;
                    max = diff;
                }
            }
        }

        return cumulativeReturnVOS;
    }

    private void setTraceBackInfo(TraceBackCriteriaVO traceBackCriteriaVO) {
        this.traceBackCriteriaVO = traceBackCriteriaVO;

        nowMoney = initMoney;
        holdingNum = traceBackCriteriaVO.holdingNum;
        holdingPeriod = traceBackCriteriaVO.holdingPeriod;
        formativePeriod = traceBackCriteriaVO.formativePeriod;
    }

    private List<CumulativeReturnVO> cycleCalcu(int startIndex, int endIndex, int periodSerial) throws DateNotWithinException, NoMatchEnumException, IOException, NoDataWithinException, CodeNotFoundException, DateShortException, DataSourceFirstDayException {
        System.out.println("calculate cycle: " + periodSerial);

        LocalDate periodStart = allDatesWithData.get(startIndex);
        LocalDate periodEnd = allDatesWithData.get(endIndex);

        List<String> wantedStockCodes = pickStocks(formate(traceBackStockPool, periodStart, formativePeriod));

        for (String s : wantedStockCodes) {
            System.out.println("select: " + s);
        }

        return calculate(wantedStockCodes, periodStart, periodEnd);
    }

    /**
     * @param pickedStockCodes 持有期参与收益的股票集合
     * @param periodStart      持有期起始日期
     * @param periodEnd        持有期结束日期
     * @return 此持有期的累计收益率饿
     */
    private List<CumulativeReturnVO> calculate(List<String> pickedStockCodes, LocalDate periodStart, LocalDate periodEnd) throws DateNotWithinException, NoDataWithinException, IOException {
        List<CumulativeReturnVO> strategyCumulativeReturn = new LinkedList<>();

        Map<LocalDate, List<Double>> forCalcu = new TreeMap<>();

        // 对阶段内的每只股票进行数据读取
        for (String s : pickedStockCodes) {
            List<StrategyStock> ss = stockData.get(s);
            for (StrategyStock stock : ss) {
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
            double thisYield = getAveYield(entry.getValue());

            nowMoney *= (thisYield + 1);
            double cumulativeYield = nowMoney / initMoney - 1;

            strategyCumulativeReturn.add(new CumulativeReturnVO(entry.getKey(), cumulativeYield, false));
        }
        return strategyCumulativeReturn;
    }


    // 计算单只股票在单日对此日造成的收益率影响
    private double getProfit(StrategyStock stock) {
        return stock.close / stock.preClose - 1;
    }

    // 计算value的平均值
    private double getAveYield(List<Double> value) {
        double sum = 0;
        for (double temp : value) {
            sum += temp;
        }
        return sum / value.size();
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
