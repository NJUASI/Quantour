package com.edu.nju.asi.service.serviceImpl.TraceBackService;

import com.edu.nju.asi.dao.BasicDataDao;
import com.edu.nju.asi.dao.daoImpl.BasicDataDaoImpl;
import com.edu.nju.asi.infoCarrier.traceBack.*;
import com.edu.nju.asi.model.BaseStock;
import com.edu.nju.asi.model.BasicData;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy.AllFormateStrategy;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategyFactory;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.MarketSelectingStrategyFactory;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy.AllPickStrategy;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategyFactory;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.RankStrategy;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.marketSelectingStrategy.AllMarketSelectingStrategy;
import com.edu.nju.asi.utilities.enums.IndicatorType;
import com.edu.nju.asi.utilities.enums.RankType;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;
import com.edu.nju.asi.utilities.exceptions.NoDataWithinException;

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

    /**
     * 当前仓位（默认初始为1）
     */
    private double nowPosition = 1;

    /**
     * 按照回测周期的每一天保存当日是否需要进行调仓
     */
    private List<Boolean> isBullPositions;


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
     * 所有的股指数据，键值为股指名称（如沪深300）
     */
    protected Map<String, List<BaseStock>> baseStockData;

    /**
     * 当前持有的股票
     */
    protected List<TransferDayDetail> currentHoldingStocks = new ArrayList<>();

    /**
     * 因为调仓日停牌而不能被卖出的股票
     */
    protected List<String> canNotBeSoldStockCodes = new ArrayList<>();

    /**
     * 最近卖出的股票
     */
    protected List<TransferDayDetail> lastSoldStocks = new ArrayList<>();

    /**
     * 阶段持仓详单
     */
    protected Map<Integer, List<StageDetail>> stageDetails = new TreeMap<>();

    /**
     * 筛选条件
     */
    protected List<FilterCondition> filterConditions;

    /**
     * 排名条件
     */
    protected List<RankCondition> rankConditions;

    /**
     * 市场择时条件
     */
    protected List<MarketSelectingCondition> marketSelectingConditions;

    /**
     * 财务指标
     */
    protected Map<String,List<BasicData>> financialData;

    BasicDataDao basicDataDao = new BasicDataDaoImpl();

    public TraceBackStrategyCalculator(List<String> traceBackStockPool, TraceBackCriteria traceBackCriteria, List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData, Map<String, List<BaseStock>> baseStockData) {
        this.traceBackStockPool = traceBackStockPool;
        this.traceBackCriteria = traceBackCriteria;
        this.allDatesWithData = allDatesWithData;
        this.stockData = stockData;
        this.baseStockData = baseStockData;


        this.filterConditions = traceBackCriteria.filterConditions;
        this.rankConditions = traceBackCriteria.rankConditions;
        this.marketSelectingConditions = traceBackCriteria.marketSelectingConditions;

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


        // 一次性获得所有择时调仓的结果，以便之后查看
//        setUpMarketSelectingResults(traceBackCriteria.marketSelectingConditions, allStartIndex, allEndIndex);


        // 回测周期开始
        int cycles = (allEndIndex - allStartIndex + 1) / holdingPeriod;

        // 回测时间太短，不足一个持有期 或者刚好满一个周期，则没有末尾的调仓期
        if (cycles == 0) {
            strategyCumulativeReturn.addAll(cycleCalcu(allStartIndex, allEndIndex, cycles + 1, traceBackCriteria.maxHoldingNum));
        } else {
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


        //装载数据
        StrategyCumulativeAndTransferDetail strategyCumulativeAndTransferDetail = new StrategyCumulativeAndTransferDetail();

        strategyCumulativeAndTransferDetail.strategyCumulativeReturn = strategyCumulativeReturn;
        strategyCumulativeAndTransferDetail.lastSoldStocks = lastSoldStocks;
        strategyCumulativeAndTransferDetail.stageDetails = stageDetails;
        return strategyCumulativeAndTransferDetail;
    }


    /**
     * 所有持有期的择时选择结果，依次存入由当日死叉个数，是否需要进行调仓
     */
    private void setUpMarketSelectingResults(List<MarketSelectingCondition> conditions, int allStartIndex, int allEndIndex) {

        isBullPositions = new LinkedList<>();
        boolean curIsBull = true;

        // 依次对每天进行处理
        for (int i = allStartIndex; i <= allEndIndex; i++) {
            int goldenFork = 0;
            int deathCross = 0;

            // 依次处理每种择时情况，只有在周期上才检查 TODO 冯俊杰 择时对前一日进行检查，然后择时
            for (MarketSelectingCondition condition : conditions) {
                if ((i - allStartIndex + 1) % condition.cycle == 0) {
                    AllMarketSelectingStrategy mss = MarketSelectingStrategyFactory.createMarketSelectingStrategyFactory(condition, allDatesWithData, baseStockData);
                    MarketSelectingResult tempResult = mss.marketSelecting(i, condition.criteria1, condition.criteria2, condition.criteria3);
                    if (tempResult.isGoldenFork) goldenFork++;
                    if (tempResult.isDeathCross) deathCross++;
                }
            }

            if (curIsBull) {
                // 之前为为金叉形成的牛市，只因死叉而调仓
                if (deathCross >= traceBackCriteria.bullToBear_num) curIsBull = false;
            } else {
                // 之前为为死叉形成的熊市，只因金叉而调仓
                if (goldenFork >= traceBackCriteria.bearToBull_num) curIsBull = true;
            }
            isBullPositions.add(curIsBull);

        }
    }


    private List<CumulativeReturn> cycleCalcu(int startIndex, int endIndex, int periodSerial, int maxHoldingNum) throws DataSourceFirstDayException {
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
                    return (int) Math.ceil(o2.score-o1.score);
                }
            });
            rankConditionRates = rankConditionRates.subList(0,maxHoldingNum);
        }

        //添加计算的股票代码
        List<String> wantedStockCodes = new ArrayList<>();
        for(RankConditionRate rankConditionRate : rankConditionRates) {
            wantedStockCodes.add(rankConditionRate.stockCode);
        }

        return calculate(wantedStockCodes, periodStart, periodEnd, periodSerial, startIndex, endIndex, maxHoldingNum);
    }

    /**
     * @param pickedStockCodes 持有期参与收益的股票集合
     * @param periodStart      持有期起始日期
     * @param periodEnd        持有期结束日期
     * @param periodSerial     调仓周期
     * @return 此持有期的累计收益率
     */
    private List<CumulativeReturn> calculate(List<String> pickedStockCodes, LocalDate periodStart, LocalDate periodEnd, int periodSerial, int startIndex, int endIndex, int maxHoldingNum) {

        // 根据市场情况进行市场择时，调整仓位比例（holdingPeriod = endIndex - startIndex）
//        boolean nowIsBullPosition = isBullPositions.get(periodSerial * (endIndex - startIndex));
//        if (nowIsBullPosition) nowPosition = 1;
//        else nowPosition = traceBackCriteria.adjustPositionPercent;

        //对挑选的股票做精细处理
        pickedStockCodes = dealPickedStockCodes(pickedStockCodes, periodStart, periodEnd, periodSerial, startIndex, endIndex, maxHoldingNum);

        List<CumulativeReturn> strategyCumulativeReturn = new LinkedList<>();

        //初始化
        Map<LocalDate, List<Double>> forCalcu = new TreeMap<>();
        for(int i = startIndex+1; i <= endIndex; i++){
            forCalcu.put(allDatesWithData.get(i), new ArrayList<>());
        }

        //每个周期的起始调仓日不计算入收益中
        periodStart = allDatesWithData.get(startIndex + 1);

        // 对阶段内的每只股票进行数据读取
        for (int k = 0; k < pickedStockCodes.size(); k++) {
            List<Stock> ss = findStockVOsWithinDay(pickedStockCodes.get(k), periodStart, periodEnd);

            List<StageDetail> thisStageDetails = stageDetails.get(periodSerial);

            double curPosition = 0;
            for (StageDetail stageDetail : stageDetails.get(periodSerial)) {
                if (stageDetail.stockCode.equals(pickedStockCodes.get(k))) {
                    curPosition = stageDetail.curPosition;
                }
            }


            double basePrice = thisStageDetails.get(k).startPrice;
            if (ss == null) {
                for (int i = startIndex + 1; i <= endIndex; i++) {
                    forCalcu.get(allDatesWithData.get(i)).add(0.0);
                }
            } else {
                int stockIndex = 0;
                double prePrice = basePrice;
                for (int i = 1; i <= endIndex - startIndex; i++) {

                    double profit = 0;
                    if (ss.get(stockIndex).getStockID().getDate().equals(allDatesWithData.get(startIndex + i))) {
                        profit = ss.get(stockIndex).getClose() / basePrice - 1;

                        forCalcu.get(ss.get(stockIndex).getStockID().getDate()).add(profit * curPosition);
                        prePrice = ss.get(stockIndex).getClose();
                        stockIndex++;
                    } else {
                        profit = prePrice / basePrice - 1;
                        forCalcu.get(allDatesWithData.get(i)).add(profit * curPosition);
                    }

                    if (stockIndex == ss.size()) {
                        while (startIndex + i + 1 <= endIndex) {
                            profit = prePrice  / basePrice - 1;
                            forCalcu.get(allDatesWithData.get(startIndex + i + 1)).add(profit * curPosition);
                            i++;
                        }
                        break;
                    }
                }

            }
        }

        // 依次处理每一交易日
        double thisPeriodStartMoney = nowMoney;
        for (Map.Entry<LocalDate, List<Double>> entry : forCalcu.entrySet()) {

            double thisYield = getYield(entry.getValue());

            nowMoney = (1 + thisYield) * thisPeriodStartMoney;
            thisYield = nowMoney / initMoney - 1;

            strategyCumulativeReturn.add(new CumulativeReturn(entry.getKey(), thisYield, false));
        }
        return strategyCumulativeReturn;
    }

    private List<String> dealPickedStockCodes(List<String> pickedStockCodes, LocalDate periodStart, LocalDate periodEnd, int periodSerial, int startIndex, int endIndex, int maxHoldingNum){

        // 第一个周期，没有卖出的股票
        if(periodSerial == 1){
            List<StageDetail> stageDetailList = new ArrayList<>();

            // 先遍历，得到根据形成挑选出股票中真实能买到的股票
            List<String> canBeHoldStocks = new ArrayList<>();
            for (String tempCode : pickedStockCodes) {
                Stock startDayStock = findStock(tempCode, periodStart);
                if (startDayStock != null) {
                    canBeHoldStocks.add(tempCode);
                }
            }

            // 再便利，精细处理
            for (String tempCode : canBeHoldStocks) {
                Stock startDayStock = findStock(tempCode, periodStart);
                Stock endDayStock = findStock(tempCode, periodEnd);
                if (endDayStock == null) {
                    // 该股票在末尾调仓日停牌，标志不能被卖出，且末尾调仓日的前复权为最后一个有值日期的股票值
                    List<Stock> tempStocks = findStockVOsWithinDay(tempCode, periodStart, periodEnd);
                    endDayStock = tempStocks.get(tempStocks.size() - 1);

                    canNotBeSoldStockCodes.add(tempCode);
                }

                stageDetailList.add(new StageDetail(tempCode, startDayStock.getName(), startDayStock.getClose(), endDayStock.getClose(),
                        nowPosition / canBeHoldStocks.size(), nowMoney / canBeHoldStocks.size()));
                currentHoldingStocks.add(new TransferDayDetail(startDayStock.getName(), tempCode, startDayStock.getStockID().getDate(), startDayStock.getClose()));
            }

            stageDetails.put(periodSerial, stageDetailList);
            return canBeHoldStocks;
        }
        // 不是第一个周期
        else {
            // 取形成选取的股票的无重复并集
            List<String> pickedAgainStockCodes = new ArrayList<>();
            for (TransferDayDetail transferDayDetail : currentHoldingStocks) {
                if (pickedStockCodes.contains(transferDayDetail.stockCode)){
                    pickedAgainStockCodes.add(transferDayDetail.stockCode);
                }
            }

            pickedStockCodes.removeAll(canNotBeSoldStockCodes);
            // 两个集合相并大于最大持有股票数
            if(pickedStockCodes.size() + canNotBeSoldStockCodes.size() > maxHoldingNum){
                //此时已经是排好名的codes，可以直接截取
                pickedStockCodes = pickedStockCodes.subList(0, maxHoldingNum - canNotBeSoldStockCodes.size());
            }

            // 当前应该被买入卖出的股票
            List<String> canBeHoldStocks = new ArrayList<>();
            canBeHoldStocks.addAll(pickedStockCodes);
            canBeHoldStocks.addAll(canNotBeSoldStockCodes);

            // 若挑选的股票代码中没有当前持有股票的代码，则将该股票加入卖出的队列
            for(int i = 0; i < currentHoldingStocks.size(); ){
                boolean isSold = true;
                for(int j = 0; j < canBeHoldStocks.size(); j++){
                    if(currentHoldingStocks.get(i).stockCode.equals(canBeHoldStocks.get(j))){
                        isSold = false;
                        break;
                    }
                }
                // 需要被卖出的
                if(isSold){
                    Stock stock = findStock(currentHoldingStocks.get(i).stockCode, allDatesWithData.get(startIndex));
                    // 此时的stock已经不可能为空
                    assert (stock != null) : "逻辑有错，stock为空了";
                    // 每个持仓期的第一天作为调仓日期
                    lastSoldStocks.add(new TransferDayDetail(currentHoldingStocks.get(i), stock.getStockID().getDate(), stock.getClose()));
                    // 该支股票被卖出
                    currentHoldingStocks.remove(i);
                }else {
                    i++;
                }
            }

            // 更新当前持有股票、当前不能被卖出的股票
            List<StageDetail> stageDetailList = new ArrayList<>();
            List<String> thisCanNotBeSoldStocks = new ArrayList<>();


            // 不会被卖掉的股票因为股票价格变动，自己的仓位会变化，其他新进的均分仓位
            double canNotBeSoldPercent = 0;
            for (StageDetail stageDetail : stageDetails.get(periodSerial - 1)) {
                if (canNotBeSoldStockCodes.contains(stageDetail.stockCode)){
                    canNotBeSoldPercent += adjustedPosition(stageDetail.curPositionMoney * (stageDetail.endPrice / stageDetail.startPrice), nowMoney);
                }
            }
            double remainPosition = nowPosition - canNotBeSoldPercent;
            double remainMoney = remainPosition * nowMoney;

            assert remainPosition > 0 : "remainPosition < 0 ！！震惊！！";
            if (remainPosition > 0) {
                for(int i = 0; i < canBeHoldStocks.size(); ){
                    Stock startDayStock = findStock(canBeHoldStocks.get(i), periodStart);
                    Stock endDayStock = findStock(canBeHoldStocks.get(i), periodEnd);

                    List<Stock> tempStocks = findStockVOsWithinDay(canBeHoldStocks.get(i), periodStart, periodEnd);

                    // 要是这只股票这个周期一直停盘，它的周期起始价格由上一个周期这只股票的周期结束价格决定
                    StageDetail pre = null;
                    for (StageDetail temp : stageDetails.get(periodSerial - 1)) {
                        if (temp.stockCode.equals(canBeHoldStocks.get(i))) {
                            pre = temp;
                            break;
                        }
                    }


                    // 当日所选股票停牌，标志不能被买入
                    if(startDayStock == null){
                        if(canNotBeSoldStockCodes.contains(canBeHoldStocks.get(i))){

                            double curStockPosition = adjustedPosition(pre.curPositionMoney * (pre.endPrice / pre.startPrice), nowMoney);

                            if (endDayStock == null) {
                                // 不能买不能卖。虽然该股票在末尾调仓日停牌，如果是上个周期就持有的，还是继续持有

                                if (tempStocks == null) {
                                    // 这只股票在这个周期内一直停盘
                                    stageDetailList.add(new StageDetail(canBeHoldStocks.get(i), pre.stockName, pre.endPrice,
                                            pre.endPrice, curStockPosition, pre.curPositionMoney));

                                } else {
                                    double cumulative = tempStocks.get(tempStocks.size()-1).getClose() / pre.endPrice;

                                    stageDetailList.add(new StageDetail(canBeHoldStocks.get(i), pre.stockName, pre.endPrice,
                                            tempStocks.get(tempStocks.size()-1).getClose(), curStockPosition, pre.curPositionMoney * cumulative));

                                }

                                thisCanNotBeSoldStocks.add(canBeHoldStocks.get(i));
                            } else {
                                // 不能买，能卖。
                                double cumulative = endDayStock.getClose() / pre.endPrice;

                                stageDetailList.add(new StageDetail(canBeHoldStocks.get(i), pre.stockName, pre.endPrice,
                                        endDayStock.getClose(), curStockPosition, pre.curPositionMoney * cumulative));
                            }
                            i++;
                        } else {
                            // 所选的停盘了不能被买，原来又不持有这只股票，则直接舍弃这只股票
                            canBeHoldStocks.remove(i);
                        }
                    } else {
                        if (endDayStock == null) {
                            // 能买，不能卖。该股票在末尾调仓日停牌，标志不能被卖出
                            stageDetailList.add(new StageDetail(canBeHoldStocks.get(i), startDayStock.getName(), startDayStock.getClose(),
                                    tempStocks.get(tempStocks.size() - 1).getClose(),
                                    remainPosition / (canBeHoldStocks.size() - canNotBeSoldStockCodes.size()),
                                    remainMoney / (canBeHoldStocks.size() - canNotBeSoldStockCodes.size())));

                            thisCanNotBeSoldStocks.add(canBeHoldStocks.get(i));

                        } else {
                            // 能买，能卖。
                            stageDetailList.add(new StageDetail(canBeHoldStocks.get(i), startDayStock.getName(), startDayStock.getClose(),
                                    endDayStock.getClose(), remainPosition / (canBeHoldStocks.size() - canNotBeSoldStockCodes.size()),
                                    remainMoney / (canBeHoldStocks.size() - canNotBeSoldStockCodes.size())));
                        }

                        // 因为没有被再次选中，需要添加了，否则不需要添加
                        if (!pickedAgainStockCodes.contains(canBeHoldStocks.get(i))){
                            currentHoldingStocks.add(new TransferDayDetail(startDayStock.getName(), canBeHoldStocks.get(i),
                                    startDayStock.getStockID().getDate(), startDayStock.getClose()));
                        }

                        i++;
                    }
                }

//                pickedStockCodes.addAll(canNotBeSoldStockCodes);
                //更新因调仓日停牌而不能卖出的股票
                canNotBeSoldStockCodes = thisCanNotBeSoldStocks;
                stageDetails.put(periodSerial, stageDetailList);

            } else {
                // TODO 把因为占仓比太大的canNotBeSold的股票穿出去
                return new LinkedList<>();
            }

            return canBeHoldStocks;
        }
    }


    /**
     * 对已有股票进行仓位的调整
     */
    private double adjustedPosition(double prePositionMoney, double totalMoney) {
        return prePositionMoney / totalMoney;
    }



    // 计算value的累计总值
    private double getYield(List<Double> value) {
        double sum = 0;
        for (double temp : value) {
            sum += temp;
        }

        return sum;
    }


    /**
     * 筛选股票
     */
    private List<String> filterCodes(List<FilterCondition> filterConditions, LocalDate periodStart){

        // 通过不同的筛选条件进行筛选
        List<List<String>> allFilterWantedCodes = new ArrayList<>();

        for(FilterCondition filterCondition : filterConditions){
            AllFormateStrategy formateStrategy = FormateStrategyFactory.createFormateStrategy(filterCondition.indicatorType,allDatesWithData,stockData,financialData);
            AllPickStrategy pickStrategy = PickStrategyFactory.createPickStrategy(filterCondition.comparatorType, filterCondition.value);
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
            //若排名条件为空，则默认以当日成交额从大到小排名
            rankConditions.add(new RankCondition(IndicatorType.TRANSACTION_AMOUNT, RankType.DESC_RANK, 1, 1));
        }

        // 通过不同的筛选条件进行筛选
        List<List<RankConditionRate>> allRankConditionRates = new ArrayList<>();

        for(RankCondition rankCondition : rankConditions){
            AllFormateStrategy formateStrategy = FormateStrategyFactory.createFormateStrategy(rankCondition.indicatorType,allDatesWithData,stockData,financialData);
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
