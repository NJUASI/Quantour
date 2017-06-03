package com.edu.nju.asi.service.serviceImpl.TraceBackService;

import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.dao.daoImpl.StockDaoImpl;
import com.edu.nju.asi.infoCarrier.traceBack.*;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy.AllFormateStrategy;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategyFactory;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy.AllPickStrategy;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategyFactory;
import com.edu.nju.asi.utilities.exceptions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by harvey on 17-4-3.
 */
public class TraceBackStrategyCalculator {

    /**
     * 目标股票池
     */
    public List<String> traceBackStockPool;

    /**
     * 回测标准
     */
    protected TraceBackCriteria traceBackCriteria;

    // 默认1000元初始投资资本
    private final double initMoney = 1;
    private double nowMoney = 1;

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

    /**
     * 当前持有的股票
     */
    protected List<HoldOrSoldStocks> currentHoldingStocks = new ArrayList<>();

    /**
     * 最近卖出的股票
     */
    protected List<HoldOrSoldStocks> lastSoldStocks = new ArrayList<>();

    /**
     * 形成策略和选择策略工厂
     */
    FormateStrategyFactory formateStrategyFactory = new FormateStrategyFactory();
    PickStrategyFactory pickStrategyFactory = new PickStrategyFactory();

    StockDao stockDao = new StockDaoImpl();


    public TraceBackStrategyCalculator(List<String> traceBackStockPool, TraceBackCriteria traceBackCriteria, List<LocalDate> allDatesWithData, Map<String, List<StrategyStock>> stockData) {
        this.traceBackStockPool = traceBackStockPool;
        this.traceBackCriteria = traceBackCriteria;
        this.allDatesWithData = allDatesWithData;
        this.stockData = stockData;
    }

    /**
     * 根据目标股票池及所给的标准，返回策略的累计收益率
     *
     * @return List<CumulativeReturn> 策略的累计收益率
     */
    public List<CumulativeReturn> traceBack(TraceBackCriteria traceBackCriteria) throws DataSourceFirstDayException {

        //持有期
        int holdingPeriod = traceBackCriteria.holdingPeriod;

        // 保存相应要返回的数据
        List<CumulativeReturn> strategyCumulativeReturn = new LinkedList<>();


        //区间第一个交易日在allDatesWithData中的位置
        int allStartIndex, allEndIndex;
        LocalDate thisStart = this.traceBackCriteria.startDate;
        LocalDate thisEnd = this.traceBackCriteria.endDate;
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
            strategyCumulativeReturn.addAll(cycleCalcu(allStartIndex+1, allEndIndex, cycles+1, traceBackCriteria.maxHoldingNum, traceBackCriteria.filterConditions));
            return strategyCumulativeReturn;
        }

        // 至少一个持有期，整个周期的计算
        for (int i = 0; i < cycles; i++) {
            int startIndex = allStartIndex + i * holdingPeriod;
            int endIndex = startIndex + holdingPeriod - 1;
            if(i == 0){
                startIndex = allStartIndex+1;
            }
            strategyCumulativeReturn.addAll(cycleCalcu(startIndex, endIndex, i+1, traceBackCriteria.maxHoldingNum, traceBackCriteria.filterConditions));
        }

        // 最后一个不足周期的计算
        if ((allEndIndex - allStartIndex + 1) % holdingPeriod != 0) {
            int startIndex = allStartIndex + cycles * holdingPeriod;
            int endIndex = allEndIndex;
            strategyCumulativeReturn.addAll(cycleCalcu(startIndex, endIndex, cycles+1, traceBackCriteria.maxHoldingNum, traceBackCriteria.filterConditions));
        }

        // 根据果仁网，第一天数据设置为0
        strategyCumulativeReturn.add(0,new CumulativeReturn(allDatesWithData.get(allStartIndex), 0 , false));

        return strategyCumulativeReturn;
    }

    private List<CumulativeReturn> cycleCalcu(int startIndex, int endIndex, int periodSerial,int maxHoldingNum, List<FilterCondition> filterConditions) throws DataSourceFirstDayException {
        System.out.println("calculate cycle: " + periodSerial);

        LocalDate periodStart = allDatesWithData.get(startIndex);
        LocalDate periodEnd = allDatesWithData.get(endIndex);

        List<List<FilterConditionRate>> filterConditionRates = new ArrayList<>();

        // 通过不同的筛选条件进行筛选
        for(FilterCondition filterCondition : filterConditions){
            AllFormateStrategy formateStrategy = formateStrategyFactory.createFormateStrategy(filterCondition.indicatorType,allDatesWithData,stockData);
            AllPickStrategy pickStrategy = pickStrategyFactory.createPickStrategy(filterCondition.comparatorType, filterCondition.value, filterCondition.weight, traceBackStockPool.size());
            filterConditionRates.add(pickStrategy.pick(formateStrategy.formate(traceBackStockPool, periodStart, filterCondition.formativePeriod)));
        }

        // 选出经不同筛选条件筛选出来的相同的股票
        List<FilterConditionRate> wantedFilterConditionRates = filterConditionRates.get(0);
        //多于一个筛选条件
        if(filterConditions.size() > 1){
            for(int i = 1 ; i < filterConditionRates.size();i++){
                for(int j = 0; j < wantedFilterConditionRates.size();){
                    boolean isFound = false;
                    for(int k = 0; k < filterConditionRates.get(i).size(); k++){
                        if(wantedFilterConditionRates.get(j).stockCode.equals(filterConditionRates.get(i).get(k).stockCode)){
                            wantedFilterConditionRates.get(j).score += filterConditionRates.get(i).get(k).score;
                            isFound = true;
                            break;
                        }
                    }
                    if(isFound){
                        j++;
                    }
                    else {
                        wantedFilterConditionRates.remove(j);
                    }
                }
            }
        }

        List<String> wantedStockCodes = new ArrayList<>();

        // 筛选出来的股票大于最大持有股票数,应该按评分排名
        if(wantedFilterConditionRates.size() > maxHoldingNum){
            wantedFilterConditionRates.sort(new Comparator<FilterConditionRate>() {
                @Override
                public int compare(FilterConditionRate o1, FilterConditionRate o2) {
                    return (int) Math.ceil(o1.score-o2.score);
                }
            });

            wantedFilterConditionRates = wantedFilterConditionRates.subList(0,maxHoldingNum);
        }
        for(FilterConditionRate filterConditionRate : wantedFilterConditionRates){
            wantedStockCodes.add(filterConditionRate.stockCode);
        }

        //第一个周期，没有卖出的股票
        if(periodSerial == 1){
            for(int i = 0; i < wantedStockCodes.size(); i++){
                Stock stock = null;
                try {
                    stock = stockDao.getStockData(wantedStockCodes.get(i), allDatesWithData.get(startIndex-1));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                currentHoldingStocks.add(new HoldOrSoldStocks(stock.getName(),wantedStockCodes.get(i),allDatesWithData.get(startIndex-1),stock.getFrontAdjClose()));
            }
        }
        //不是第一个周期
        else {
            for(int i = 0; i < currentHoldingStocks.size();){
                boolean isSold = false;
                for(int j = 0; j < wantedStockCodes.size(); j++){
                    if(currentHoldingStocks.get(i).stockCode.equals(wantedStockCodes.get(j))){
                        isSold = true;
                        i++;
                        break;
                    }
                }
                //被卖出，加入最近被卖出的队列
                if(!isSold){
                    Stock stock = null;
                    try {
                         stock = stockDao.getStockData(currentHoldingStocks.get(i).stockCode, allDatesWithData.get(endIndex));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lastSoldStocks.add(new HoldOrSoldStocks(currentHoldingStocks.get(i), allDatesWithData.get(endIndex), stock.getFrontAdjClose()));
                    currentHoldingStocks.remove(i);
                }
            }
        }

        return calculate(wantedStockCodes, periodStart, periodEnd);
    }

    /**
     * @param pickedStockCodes 持有期参与收益的股票集合
     * @param periodStart      持有期起始日期
     * @param periodEnd        持有期结束日期
     * @return 此持有期的累计收益率
     */
    private List<CumulativeReturn> calculate(List<String> pickedStockCodes, LocalDate periodStart, LocalDate periodEnd) {
        List<CumulativeReturn> strategyCumulativeReturn = new LinkedList<>();

        Map<LocalDate, List<Double>> forCalcu = new TreeMap<>();

        // 对阶段内的每只股票进行数据读取
        for (String s : pickedStockCodes) {
            List<StrategyStock> ss = stockData.get(s);
            for (StrategyStock stock : ss) {
                if (isDateWithinWanted(periodStart, periodEnd, stock.date)) {
                    LocalDate thisDate = stock.date;
                    double profit = stock.close / stock.preClose - 1;

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

            strategyCumulativeReturn.add(new CumulativeReturn(entry.getKey(), cumulativeYield, false));
        }
        return strategyCumulativeReturn;
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
