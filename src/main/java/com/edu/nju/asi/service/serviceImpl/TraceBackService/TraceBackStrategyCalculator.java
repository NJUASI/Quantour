package com.edu.nju.asi.service.serviceImpl.TraceBackService;

import com.edu.nju.asi.dao.BasicDataDao;
import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.dao.daoImpl.BasicDataDaoImpl;
import com.edu.nju.asi.dao.daoImpl.StockDaoImpl;
import com.edu.nju.asi.infoCarrier.traceBack.*;
import com.edu.nju.asi.model.BasicData;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy.AllFormateStrategy;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategyFactory;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy.AllPickStrategy;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategyFactory;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.RankStrategy;
import com.edu.nju.asi.utilities.enums.IndicatorType;
import com.edu.nju.asi.utilities.enums.RankType;
import com.edu.nju.asi.utilities.exceptions.*;

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

    private double initMoney = 1;
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
    protected Map<String, List<Stock>> stockData;

    /**
     * 当前持有的股票
     */
    protected List<TransferDayDetail> currentHoldingStocks = new ArrayList<>();

    /**
     * 最近卖出的股票
     */
    protected List<TransferDayDetail> lastSoldStocks = new ArrayList<>();

    /**
     * 筛选条件
     */
    protected List<FilterCondition> filterConditions;

    /**
     * 排名条件
     */
    protected List<RankCondition> rankConditions;

    /**
     * 财务指标
     */
    protected Map<String,List<BasicData>> financialData;

    /**
     * 形成策略和选择策略工厂
     */
    FormateStrategyFactory formateStrategyFactory = new FormateStrategyFactory();
    PickStrategyFactory pickStrategyFactory = new PickStrategyFactory();

    BasicDataDao basicDataDao = new BasicDataDaoImpl();

    public TraceBackStrategyCalculator(List<String> traceBackStockPool, TraceBackCriteria traceBackCriteria, List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData) {
        this.traceBackStockPool = traceBackStockPool;
        this.traceBackCriteria = traceBackCriteria;
        this.allDatesWithData = allDatesWithData;
        this.stockData = stockData;

        this.filterConditions = traceBackCriteria.filterConditions;
        this.rankConditions = traceBackCriteria.rankConditions;

        setUpFinancialIndicators();
    }

    private void setUpFinancialIndicators() {
        financialData = basicDataDao.getAllBasicData(traceBackStockPool);
    }

    /**
     * 根据目标股票池及所给的标准，返回策略的累计收益率
     *
     * @return StrategyCumulativeAndTransferDetail 策略的累计收益率和调仓日期的详情
     */
    public StrategyCumulativeAndTransferDetail traceBack(TraceBackCriteria traceBackCriteria) throws DataSourceFirstDayException, NoDataWithinException {


        //调仓日为持有期的后一天，故把调仓日放到周期中，每个周期的起始调仓日不参与收益计算，末尾调仓日参与收益计算
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
            //中间没有数据
            if(thisStart.isAfter(thisEnd)){
                throw new NoDataWithinException("all");
            }
        }
        allStartIndex = allDatesWithData.indexOf(thisStart);
        allEndIndex = allDatesWithData.indexOf(thisEnd);

        int cycles = (allEndIndex - allStartIndex + 1) / holdingPeriod;

        // 回测时间太短，不足一个持有期 或者刚好满一个周期，则没有末尾的调仓期
        if (cycles == 0) {
            strategyCumulativeReturn.addAll(cycleCalcu(allStartIndex, allEndIndex, cycles+1, traceBackCriteria.maxHoldingNum));
        }
        else {
            // 至少一个持有期，整个周期的计算
            for (int i = 0; i < cycles; i++) {
                int startIndex = allStartIndex + i * holdingPeriod;
                int endIndex = startIndex + holdingPeriod;
                //最后一个周期且刚好满足整数的周期,没有末尾的调仓日
                if(i == cycles-1 && (allEndIndex - allStartIndex + 1) % holdingPeriod == 0){
                    endIndex = endIndex - 1;
                }
                strategyCumulativeReturn.addAll(cycleCalcu(startIndex, endIndex, i+1, traceBackCriteria.maxHoldingNum));
            }

            // 最后一个不足周期的计算
            if ((allEndIndex - allStartIndex + 1) % holdingPeriod != 0) {
                int startIndex = allStartIndex + cycles * holdingPeriod;
                int endIndex = allEndIndex;
                strategyCumulativeReturn.addAll(cycleCalcu(startIndex, endIndex, cycles+1, traceBackCriteria.maxHoldingNum));
            }
        }

        // 根据果仁网，第一天数据设置为0
        strategyCumulativeReturn.add(0, new CumulativeReturn(allDatesWithData.get(allStartIndex), 0, false));

        StrategyCumulativeAndTransferDetail strategyCumulativeAndTransferDetail = new StrategyCumulativeAndTransferDetail();

        strategyCumulativeAndTransferDetail.strategyCumulativeReturn = strategyCumulativeReturn;
        strategyCumulativeAndTransferDetail.transferDayDetails = lastSoldStocks;
        return strategyCumulativeAndTransferDetail;
    }

    private List<CumulativeReturn> cycleCalcu(int startIndex, int endIndex, int periodSerial,int maxHoldingNum) throws DataSourceFirstDayException {
        System.out.println("calculate cycle: " + periodSerial);

        LocalDate periodStart = allDatesWithData.get(startIndex);
        LocalDate periodEnd = allDatesWithData.get(endIndex);

        List<String> filterWantedCodes = new ArrayList<>();
        //通过筛选条件筛选出该周期需要回测的股票代码
        if(filterConditions.size() != 0){
            filterWantedCodes = filterCodes(filterConditions, periodStart);
        }

        //通过排名条件排名
        List<String> codesNeedToRank = filterConditions.size() != 0 ? filterWantedCodes : traceBackStockPool;
        List<RankConditionRate> rankConditionRates = rankCodes(codesNeedToRank, periodStart);

        // 筛选排名后的股票数依然大于最大持有股票数,应该按评分排名
        if(rankConditionRates.size() > maxHoldingNum){
            rankConditionRates.sort(new Comparator<RankConditionRate>() {
                @Override
                public int compare(RankConditionRate o1, RankConditionRate o2) {
                    return (int) Math.ceil(o1.score-o2.score);
                }
            });
            rankConditionRates = rankConditionRates.subList(0,maxHoldingNum);
        }

        //添加计算的股票代码
        List<String> wantedStockCodes = new ArrayList<>();
        for(RankConditionRate rankConditionRate : rankConditionRates) {
            wantedStockCodes.add(rankConditionRate.stockCode);
        }

        return calculate(wantedStockCodes, periodStart, periodEnd, periodSerial, startIndex);
    }

    /**
     * @param pickedStockCodes 持有期参与收益的股票集合
     * @param periodStart      持有期起始日期
     * @param periodEnd        持有期结束日期
     * @param periodSerial     调仓周期
     * @return 此持有期的累计收益率
     */
    private List<CumulativeReturn> calculate(List<String> pickedStockCodes, LocalDate periodStart, LocalDate periodEnd, int periodSerial, int startIndex) {

        //第一个周期，没有卖出的股票
        if(periodSerial == 1){
            for(int i = 0; i < pickedStockCodes.size(); i++){
                Stock stock = findStock(pickedStockCodes.get(i), allDatesWithData.get(startIndex));
                currentHoldingStocks.add(new TransferDayDetail(stock.getName(),pickedStockCodes.get(i),stock.getStockID().getDate(),stock.getClose()));
            }
        }
        //不是第一个周期
        else {
            //若挑选的股票代码不在当前持有的股票代码中，添加进去
            for(int i = 0; i < pickedStockCodes.size(); i++){
                for(int j = 0; j < currentHoldingStocks.size(); j++){
                    boolean isNew = true;
                    if(pickedStockCodes.get(i).equals(currentHoldingStocks.get(j).stockCode)){
                        isNew = false;
                    }
                    if(isNew){
                        Stock stock = findStock(pickedStockCodes.get(i), allDatesWithData.get(startIndex));
                        currentHoldingStocks.add(new TransferDayDetail(stock.getName(),pickedStockCodes.get(i),stock.getStockID().getDate(),stock.getClose()));
                        j++;
                    }
                }
            }

            // 若挑选的股票代码中没有当前持有股票的代码，则将该股票加入卖出的队列
            for(int i = 0; i < currentHoldingStocks.size();){
                boolean isSold = true;
                for(int j = 0; j < pickedStockCodes.size(); j++){
                    if(currentHoldingStocks.get(i).stockCode.equals(pickedStockCodes.get(j))){
                        isSold = false;
                        i++;
                        break;
                    }
                }
                //TODO 这里的卖出价格有点儿问题 被卖出，加入最近被卖出的队列，卖的是调仓日期的前复权开盘价
                if(isSold){
                    Stock stock = findStock(pickedStockCodes.get(i), allDatesWithData.get(startIndex));

                    //每个持仓期的第一天作为调仓日期
                    lastSoldStocks.add(new TransferDayDetail(currentHoldingStocks.get(i), stock.getStockID().getDate(), stock.getClose()));
                    currentHoldingStocks.remove(i);
                }
            }
        }

        List<CumulativeReturn> strategyCumulativeReturn = new LinkedList<>();

        Map<LocalDate, List<Double>> forCalcu = new TreeMap<>();

        //每个周期的起始调仓日不计算入收益中
        periodStart = allDatesWithData.get(startIndex+1);

        // 对阶段内的每只股票进行数据读取
        for (String s : pickedStockCodes) {
            List<Stock> ss = findStockVOsWithinDay(s, periodStart, periodEnd);

            double basePrice = ss.get(0).getPreClose();
            for (int i = 0; i < ss.size(); i++) {
                LocalDate thisDate = ss.get(i).getStockID().getDate();
                double profit = ss.get(i).getClose() / basePrice - 1;

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

        double thisPeriodStartMoney = nowMoney;
        for (Map.Entry<LocalDate, List<Double>> entry : forCalcu.entrySet()) {

            double thisYield = getAveYield(entry.getValue());

            nowMoney = (1 + thisYield) * thisPeriodStartMoney;
            thisYield = nowMoney / initMoney - 1;

            strategyCumulativeReturn.add(new CumulativeReturn(entry.getKey(), thisYield, false));
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

    /**
     * 筛选股票
     * @param filterConditions
     * @param periodStart
     * @return
     */
    private List<String> filterCodes(List<FilterCondition> filterConditions, LocalDate periodStart){

        // 通过不同的筛选条件进行筛选
        List<List<String>> allFilterWantedCodes = new ArrayList<>();

        for(FilterCondition filterCondition : filterConditions){
            AllFormateStrategy formateStrategy = formateStrategyFactory.createFormateStrategy(filterCondition.indicatorType,allDatesWithData,stockData,financialData);
            AllPickStrategy pickStrategy = pickStrategyFactory.createPickStrategy(filterCondition.comparatorType, filterCondition.value);
            try {
                allFilterWantedCodes.add(pickStrategy.pick(formateStrategy.formate(traceBackStockPool, periodStart, filterCondition.formativePeriod)));
            } catch (DataSourceFirstDayException e) {
                e.printStackTrace();
            }
        }

        //选出经不同筛选条件筛选出来的相同的股票
        List<String> filterWantedCodes = allFilterWantedCodes.get(0);

        //多于一个筛选条件
        if(filterConditions.size() > 1){
            for(int i = 1 ; i < allFilterWantedCodes.size();i++){
                for(int j = 0; j < filterWantedCodes.size();){
                    boolean isFound = false;
                    for(int k = 0; k < allFilterWantedCodes.get(i).size(); k++){
                        if(filterWantedCodes.get(j).equals(allFilterWantedCodes.get(i).get(k))){
                            isFound = true;
                            break;
                        }
                    }
                    if(isFound){
                        j++;
                    }
                    else {
                        filterWantedCodes.remove(j);
                    }
                }
            }
        }

        return filterWantedCodes;
    }

    /**
     * 通过排名筛选应该回测股票
     * @param codesNeedToRank
     * @param periodStart
     * @return 需要回测股票的代码
     */
    private List<RankConditionRate> rankCodes(List<String> codesNeedToRank, LocalDate periodStart){

        if(rankConditions.size() == 0){
            //若排名条件为空，则默认以当日成交量从大到小排名
            rankConditions.add(new RankCondition(IndicatorType.VOLUME, RankType.DESC_RANK, 1, 1));
        }

        // 通过不同的筛选条件进行筛选
        List<List<RankConditionRate>> allRankConditionRates = new ArrayList<>();

        for(RankCondition rankCondition : rankConditions){
            AllFormateStrategy formateStrategy = formateStrategyFactory.createFormateStrategy(rankCondition.indicatorType,allDatesWithData,stockData,financialData);
            RankStrategy rankStrategy = new RankStrategy(rankCondition.weight, rankCondition.rankType);
            try {
                allRankConditionRates.add(rankStrategy.mark(formateStrategy.formate(codesNeedToRank, periodStart, rankCondition.formativePeriod)));
            } catch (DataSourceFirstDayException e) {
                e.printStackTrace();
            }
        }

        //选出经不同筛选条件筛选出来的相同的股票
        List<RankConditionRate> rankConditionRates = allRankConditionRates.get(0);

        //多于一个筛选条件
        if(rankConditions.size() > 1){
            for(int i = 1 ; i < allRankConditionRates.size();i++){
                for(int j = 0; j < rankConditionRates.size();){
                    boolean isFound = false;
                    for(int k = 0; k < allRankConditionRates.get(i).size(); k++){
                        if(rankConditionRates.get(j) .equals(allRankConditionRates.get(i).get(k))){
                            isFound = true;
                            break;
                        }
                    }
                    if(isFound){
                        j++;
                    }
                    else {
                        rankConditionRates.remove(j);
                    }
                }
            }
        }

        return rankConditionRates;
    }

    protected Stock findStock(String stockCode, LocalDate date){

        List<Stock> stockVOList = stockData.get(stockCode);

        List<LocalDate> dates = new ArrayList<>();
        for(int j = 0; j < stockVOList.size(); j++){
            dates.add(stockVOList.get(j).getStockID().getDate());
        }

        if(dates.contains(date)){
            return stockVOList.get(dates.indexOf(date));
        }
        else {
            return null;
        }
    }

    protected List<Stock> findStockVOsWithinDay(String stockCode, LocalDate start, LocalDate end){
        LocalDate thisStart = start;
        LocalDate thisEnd = end;

        List<Stock> stockVOList = stockData.get(stockCode);

        List<LocalDate> dates = new ArrayList<>();
        for(int j = 0; j < stockVOList.size(); j++){
            dates.add(stockVOList.get(j).getStockID().getDate());
        }

        while(!dates.contains(thisStart) || !dates.contains(thisEnd)){
            if(!dates.contains(thisStart)){
                thisStart = thisStart.plusDays(1);
            }
            if(!dates.contains(thisEnd)){
                thisEnd = thisEnd.minusDays(1);
            }
            //中间没有数据
            if(thisStart.isAfter(thisEnd)){
                return null;
            }
        }
        int startIndex = dates.indexOf(thisStart);
        int endIndex = dates.indexOf(thisEnd);

        //没有数据
        if(startIndex == -1){
            return null;
        }

        return stockVOList.subList(startIndex, endIndex+1);
    }
}
