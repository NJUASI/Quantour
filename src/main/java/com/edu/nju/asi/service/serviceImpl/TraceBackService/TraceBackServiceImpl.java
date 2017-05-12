package com.edu.nju.asi.service.serviceImpl.TraceBackService;

import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.dao.daoImpl.StockDaoImpl;
import com.edu.nju.asi.infoCarrier.traceBack.*;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.service.StockService;
import com.edu.nju.asi.service.TraceBackService;
import com.edu.nju.asi.service.serviceImpl.StockService.StockServiceImpl;
import com.edu.nju.asi.utilities.StrategyStockList;
import com.edu.nju.asi.utilities.exceptions.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by harvey on 17-3-28.
 */
@Service("TraceBackService")
public class TraceBackServiceImpl implements TraceBackService {

    private StockService stockService;
    private StockDao stockDao;

    //自选股票池，用户回测自选股票池时才对此成员变量赋值
    private List<String> baseStockPool;

    private List<String> traceBackStockPool;

    //自选股票池的所有数据
    private Map<String, List<StrategyStock>> baseStockData;

    private List<CumulativeReturn> baseCumulativeReturn;

    private TraceBackStrategyCalculator traceBackStrategyCalculator;

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


    public TraceBackServiceImpl() throws IOException {
        stockService = new StockServiceImpl();
        stockDao = new StockDaoImpl();

        //获取所有数据的日期
        allDatesWithData = stockDao.getDateWithData();
    }

    @Override
    public TraceBackInfo traceBack(TraceBackCriteria traceBackCriteria, List<String> stockPool) throws IOException, DataSourceFirstDayException, DateNotWithinException, NoDataWithinException, UnhandleBlockTypeException {

        long enter = System.currentTimeMillis();

        TraceBackInfo traceBackInfo = new TraceBackInfo();

        // 选取回测的股票池为自选股票池／板块股票池

        //是自选股票池
        if (traceBackCriteria.isCustomized){
            traceBackStockPool = stockPool;
            //给基准股票池赋值，即为自选股票池
            baseStockPool = stockPool;
            setUp(traceBackStockPool);
            //获取所有自选股的所有数据
            baseStockData = stockData;
        }
        //不是自选股票池
        else {
            traceBackStockPool = stockService.getStockPool(traceBackCriteria.stockPoolVO);
            setUp(traceBackStockPool);
        }

        // 累计基准收益率
        baseCumulativeReturn = getBase(traceBackCriteria);
        traceBackInfo.baseCumulativeReturn = baseCumulativeReturn;

        //选择策略
        traceBackStrategyCalculator = new TraceBackStrategyCalculator(traceBackStockPool, traceBackCriteria, allDatesWithData, stockData);

        //策略回测
        traceBackInfo.strategyCumulativeReturn = traceBackStrategyCalculator.traceBack(traceBackCriteria);

        //计算策略回撤的相关信息
        traceBackInfo.maxTraceBack = maxRetracement(traceBackInfo.strategyCumulativeReturn, baseCumulativeReturn);

        // 计算持仓详情的基准收益率和超额收益率
        traceBackInfo.holdingDetails = calcuHoldingDetail(traceBackInfo.baseCumulativeReturn, traceBackInfo.strategyCumulativeReturn, traceBackCriteria.holdingPeriod);

        // 计算绝对收益周期和相对收益周期
        traceBackInfo.absoluteReturnPeriod = countReturnPeriod(traceBackInfo.holdingDetails, true);
        traceBackInfo.relativeReturnPeriod = countReturnPeriod(traceBackInfo.holdingDetails, false);

        System.out.println("计算给定形成期、持有期所用时间: "+ (System.currentTimeMillis()-enter));

        // 提前保存TraceBackCriteriaVO，以便后续固定持有期计算形成期时使用
        TraceBackCriteria criteriaVOToHold = new TraceBackCriteria(traceBackCriteria);

        // 计算超额收益率/策略胜率，给定持有期/形成期
        traceBackInfo.certainFormates = findHoldingWithCertainFormate(traceBackCriteria);
        System.out.println("计算给定形成期所用时间: "+ (System.currentTimeMillis()-enter));

//        System.out.println("--------------------------------");
//        System.out.println(traceBackCriteria.formativePeriod +  "    " + traceBackCriteria.holdingPeriod);
//        System.out.println(criteriaVOToHold.formativePeriod +  "    " + criteriaVOToHold.holdingPeriod);
//        System.out.println("--------------------------------");

        traceBackInfo.certainHoldings = findFormateWithCertainHolding(criteriaVOToHold);
        System.out.println("计算给定持有期所用时间: "+ (System.currentTimeMillis()-enter));

        // TraceBackParameter 计算贝塔系数等
        TraceBackParameter traceBackParameter = new TraceBackParameter(traceBackCriteria, traceBackInfo,stockData, traceBackStockPool);
        return traceBackParameter.getTraceBackInfo();

    }

    private void setUp(List<String> traceBackStockPool) throws IOException {

        stockData = new HashMap<>();
        for (String thisStockCode : traceBackStockPool) {
            List<Stock> tempPOS = stockDao.getStockData(thisStockCode);
            stockData.put(thisStockCode, convertStockPOS(tempPOS));
        }

    }

    private List<StrategyStock> convertStockPOS(List<Stock> stocks) {
        StrategyStockList result = new StrategyStockList();
        for (Stock thisStock : stocks) {
            result.add(new StrategyStock(thisStock));
        }
        return result;
    }

    /**
     * 给定持有期，计算不同形成期下，超额收益和策略胜率的分布信息
     *
     * @return
     * @throws DateNotWithinException
     * @throws NoDataWithinException
     * @throws IOException
     * @throws DateShortException
     * @throws CodeNotFoundException
     */
    private List<ExcessAndWinRateDist> findFormateWithCertainHolding(TraceBackCriteria traceBackCriteria) throws DataSourceFirstDayException {
        return findBestFormateOrHolding(traceBackCriteria, false);
    }

    /**
     * 给定形成期，计算不同持有期下，超额收益和策略胜率的分布信息
     *
     * @return
     * @throws CodeNotFoundException
     * @throws DateShortException
     * @throws DateNotWithinException
     * @throws NoDataWithinException
     * @throws IOException
     */
    private List<ExcessAndWinRateDist> findHoldingWithCertainFormate(TraceBackCriteria traceBackCriteria) throws DataSourceFirstDayException {
        return findBestFormateOrHolding(traceBackCriteria,true);
    }

    private List<ExcessAndWinRateDist> findBestFormateOrHolding(TraceBackCriteria traceBackCriteria, boolean certainFormate) throws DataSourceFirstDayException {
        List<ExcessAndWinRateDist> certainHoldings = new ArrayList<>();
        int initHoldingPeriod = traceBackCriteria.holdingPeriod;
        int initFormativePeriod = traceBackCriteria.formativePeriod;

        for (int i = 2; i <= 50; i = i+2) {
            ExcessAndWinRateDist excessAndWinRateDist = new ExcessAndWinRateDist();
            //给定形成期
            if (certainFormate) {
                //持有期太大，不能形成一个周期
                if((initHoldingPeriod * i) > baseCumulativeReturn.size() )
                {
                    break;
                }
                //新的持有期
                traceBackCriteria.holdingPeriod = initHoldingPeriod * i;
                System.out.println("形成期：" + traceBackCriteria.formativePeriod +"持有期: " + traceBackCriteria.holdingPeriod);
            }
            //给定持有期
            else {
                //新的形成期
                traceBackCriteria.formativePeriod = initFormativePeriod * i;
                System.out.println("形成期：" + traceBackCriteria.formativePeriod +"持有期: " + traceBackCriteria.holdingPeriod);
            }

            TraceBackInfo traceBackInfo = new TraceBackInfo();

            //累计基准收益率,直接赋值即可，不用再次计算
            traceBackInfo.baseCumulativeReturn = baseCumulativeReturn;

            //策略回测
            traceBackInfo.strategyCumulativeReturn = traceBackStrategyCalculator.traceBack(traceBackCriteria);
            System.out.println();
            System.out.println();

            traceBackInfo.holdingDetails = calcuHoldingDetail(traceBackInfo.baseCumulativeReturn, traceBackInfo.strategyCumulativeReturn, traceBackCriteria.holdingPeriod);
            //计算相对收益周期
            traceBackInfo.relativeReturnPeriod = countReturnPeriod(traceBackInfo.holdingDetails, false);

            //相对强弱计算周期
            excessAndWinRateDist.relativeCycle = i;
            //总超额收益
            double strategyLastRate = traceBackInfo.strategyCumulativeReturn.get(traceBackInfo.strategyCumulativeReturn.size() - 1).cumulativeReturn;
            double baseLastRate = traceBackInfo.baseCumulativeReturn.get(traceBackInfo.baseCumulativeReturn.size() - 1).cumulativeReturn;
            excessAndWinRateDist.excessRate = strategyLastRate - baseLastRate;
            //策略胜率
            excessAndWinRateDist.winRate = traceBackInfo.relativeReturnPeriod.winRate;

            certainHoldings.add(excessAndWinRateDist);
        }

        return certainHoldings;
    }

    /**
     * @param baseCumulativeReturn 累计基准收益率
     * @param holdingPeriod        持有期长度
     * @return 根据基准收益率和超额收益率的完整的HoldingDetailVO列表
     */
    private List<HoldingDetail> calcuHoldingDetail(List<CumulativeReturn> baseCumulativeReturn, List<CumulativeReturn> strategyCumulativeReturn, int holdingPeriod) {
        List<HoldingDetail> holdingDetails = new LinkedList<>();

        double investment = 1000;

        int holdingSerial = 1;

        //保存之前一个周期的最后一天的基准累计收益率, 初始值为1
        double curBaseCumulativeReturn = 1;
        double preBaseCumulativeReturn = 1;
        //最后一天的策略累计收益率, 初始值为1
        double curStrategyCumulativeReturn = 1;
        double preStrategyCumulativeReturn = 1;

        for (int i = 0; i < baseCumulativeReturn.size(); i += holdingPeriod) {
            double lastBaseRate;
            double lastStrategyRate;
            int periodIndex = 0;
            if ((i + holdingPeriod - 1) < baseCumulativeReturn.size()) {
                //满持仓周期数
                periodIndex = i + holdingPeriod -1;
            } else {
                //不满持仓周期数
                periodIndex = baseCumulativeReturn.size() - 1;
            }
            lastBaseRate = baseCumulativeReturn.get(periodIndex).cumulativeReturn;
            lastStrategyRate = strategyCumulativeReturn.get(periodIndex).cumulativeReturn;

            // 当前持仓期的基准收益率
            curBaseCumulativeReturn = 1+lastBaseRate;
            curStrategyCumulativeReturn = 1+lastStrategyRate;
            double baseReturn = curBaseCumulativeReturn / preBaseCumulativeReturn - 1;
            double strategyReturn = curStrategyCumulativeReturn / preStrategyCumulativeReturn - 1;
            // 超额收益率
            double excessReturn = strategyReturn - baseReturn;

            HoldingDetail holdingDetail = new HoldingDetail();
            holdingDetail.periodSerial = holdingSerial;
            holdingDetail.startDate = baseCumulativeReturn.get(i).currentDate;
            holdingDetail.endDate = baseCumulativeReturn.get(periodIndex).currentDate;
            holdingDetail.strategyReturn = strategyReturn;
            holdingDetail.baseReturn = baseReturn;
            holdingDetail.excessReturn = excessReturn;
            holdingDetail.remainInvestment = investment * (1 + lastStrategyRate);

            preBaseCumulativeReturn = curBaseCumulativeReturn;
            preStrategyCumulativeReturn = curStrategyCumulativeReturn;

            holdingSerial++;

            holdingDetails.add(holdingDetail);
        }
        return holdingDetails;
    }

    /**
     * @param traceBackCriteria 回测标准
     * @return
     * @throws IOException
     * @throws NoDataWithinException
     * @throws DateNotWithinException
     */
    private List<CumulativeReturn> getBase(TraceBackCriteria traceBackCriteria) {
        LocalDate start = traceBackCriteria.startDate;
        LocalDate end = traceBackCriteria.endDate;

        if (!traceBackCriteria.isCustomized) {
            return getCumulativeReturnOfOneStock(traceBackCriteria.baseStockName, start, end);
        } else {
            return getCustomizedCumulativeReturn(traceBackCriteria, start, end);
        }
    }

    //TODO gcm 看看自选股和非自选股可否分开两个类，帮忙看

    /**
     * 获取基准累计收益率，自选股
     *
     * @param start 回测区间起始日期
     * @param end   回测区间结束日期
     * @return List<CumulativeReturn> 基准累计收益率的列表
     */
    private List<CumulativeReturn>  getCustomizedCumulativeReturn(TraceBackCriteria traceBackCriteria, LocalDate start, LocalDate end) {

        List<CumulativeReturn> cumulativeReturns = new ArrayList<>();

        LocalDate thisStart = traceBackCriteria.startDate;
        LocalDate thisEnd = traceBackCriteria.endDate;

        while(!allDatesWithData.contains(thisStart) || !allDatesWithData.contains(thisEnd)){
            if(!allDatesWithData.contains(thisStart)){
                thisStart = thisStart.plusDays(1);
            }
            if(!allDatesWithData.contains(thisEnd)){
                thisEnd = thisEnd.minusDays(1);
            }
        }

        LocalDate temp = thisStart;
        double baseStockCumulative = 1;
        while(temp.isBefore(thisEnd) || temp.isEqual(thisEnd)){
            StrategyStock stock = null;
            double totalCumulativeReturn = 0;
            int notSuspend = 0;
            for(int j = 0; j < baseStockPool.size(); j++){
                int index = baseStockData.get(baseStockPool.get(j)).indexOf(temp);
                //当天有数据
                if(-1 != index){
                    stock = baseStockData.get(baseStockPool.get(j)).get(index);
                    totalCumulativeReturn += (stock.close/stock.preClose - 1);
                    notSuspend += 1;
                }
            }

            //即该天有股票开盘
            if(notSuspend != 0){
                baseStockCumulative = baseStockCumulative * (1 + (totalCumulativeReturn / notSuspend));
                cumulativeReturns.add(new CumulativeReturn(stock.date, baseStockCumulative-1, false));
            }

            temp = temp.plusDays(1);
        }

        //修改第一天的基准收益率为0
        cumulativeReturns.get(0).cumulativeReturn = 0;

        return cumulativeReturns;
    }

    /**
     * 当不是回测自选股时，计算一只基准股的累计收益率
     * @param stockName 单一股票的信息
     * @param start     起始日期
     * @param end       结束日期
     * @return List<CumulativeReturn> 一只基准股的累计收益率
     */
    private List<CumulativeReturn> getCumulativeReturnOfOneStock(String stockName, LocalDate start, LocalDate end) {

        List<Stock> list = null;
        try {
            list = stockService.getBaseStockData(stockName, start, end);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoDataWithinException e) {
            e.printStackTrace();
        } catch (DateNotWithinException e) {
            e.printStackTrace();
        }

        List<CumulativeReturn> cumulativeReturns = new ArrayList<CumulativeReturn>();

        //累计收益率以第一个交易日前一天的收益率来对比计算
        double closeOfFirstDay = list.get(0).getPreClose();

        for (int i = 0; i < list.size(); i++) {
            double sucClose = list.get(i).getClose();
            double cumulativeReturn = (sucClose - closeOfFirstDay) / closeOfFirstDay;
            //先将所有的最大回测点设为false
            cumulativeReturns.add(new CumulativeReturn(list.get(i).getStockID().getDate(), cumulativeReturn, false));
        }

        //将第一天的收益率设置为0
        cumulativeReturns.get(0).cumulativeReturn = 0;

        return cumulativeReturns;
    }

    /**
     * 计算最大回撤点
     *
     * @param strategyCumulativeReturn 未计算最大回撤的基准累计收益率
     * @param baseCumulativeReturn 未计算回撤的策略累计收益率
     * @return MaxTraceBack 记录基准和策略最大回撤信息的载体
     */
    public MaxTraceBack maxRetracement(List<CumulativeReturn> strategyCumulativeReturn, List<CumulativeReturn> baseCumulativeReturn) {

        MaxTraceBack maxTraceBack = new MaxTraceBack();

        //TODO gcm 用了两个循环，不知道怎么改进算法，你们可以帮下忙

        //回撤点的峰值在list中的位置
        int strategyTop = 0;
        //回撤点的谷值在list中的位置
        int strategyDown = 0;

        //将第一个位置默认为最大回撤值点
        strategyCumulativeReturn.get(0).isTraceBack = true;
        baseCumulativeReturn.get(0).isTraceBack = true;

        double strategyMax = 0;
        double baseMax = 0;

        for (int i = 0; i < strategyCumulativeReturn.size(); i++) {
            for (int j = i + 1; j < strategyCumulativeReturn.size(); j++) {
                double strategyDiff = strategyCumulativeReturn.get(i).cumulativeReturn - strategyCumulativeReturn.get(j).cumulativeReturn;
                double baseDiff = baseCumulativeReturn.get(i).cumulativeReturn - baseCumulativeReturn.get(j).cumulativeReturn;
                if (strategyMax < strategyDiff) {
                    //重新设置最大回撤点
                    strategyCumulativeReturn.get(strategyTop).isTraceBack = false;
                    strategyCumulativeReturn.get(strategyDown).isTraceBack = false;
                    strategyTop = i;
                    strategyDown = j;
                    strategyCumulativeReturn.get(strategyTop).isTraceBack = true;
                    strategyCumulativeReturn.get(strategyDown).isTraceBack = true;
                    strategyMax = strategyDiff;
                }
                baseMax = baseMax < baseDiff ? baseDiff : baseMax;
            }
        }


        maxTraceBack.maxBaseTraceBackRate = baseMax;
        maxTraceBack.maxStrategyTraceBackRate = strategyMax;
        maxTraceBack.maxStartIndex = strategyTop;
        maxTraceBack.maxEndIndex = strategyDown;
        maxTraceBack.maxStartDay = strategyCumulativeReturn.get(strategyTop).currentDate;
        maxTraceBack.maxEndDay = strategyCumulativeReturn.get(strategyDown).currentDate;

        return maxTraceBack;
    }

    /**
     *
     * @param holdingDetails 持仓周期详情用于计算
     * @param isAbsolute true计算绝对，false计算相对
     * @return 绝对／相对收益
     */
    private ReturnPeriod countReturnPeriod(List<HoldingDetail> holdingDetails, boolean isAbsolute) {
        ReturnPeriod returnPeriod = new ReturnPeriod();

        int positivePeriodsNum = 0;
        int negativePeriodNum = 0;
        Map<Double, Integer> positiveNums = new TreeMap<>();
        Map<Double, Integer> negativeNums = new TreeMap<>();

        for (int i = 0; i < holdingDetails.size(); i++) {
            double countedValue;
            if (isAbsolute) countedValue = holdingDetails.get(i).strategyReturn;
            else countedValue = holdingDetails.get(i).excessReturn;

            if (countedValue > 0) {
                // 为正并向上取整
                double rate = Math.ceil(countedValue * 100);
                if (positiveNums.containsKey(rate)) {
                    positiveNums.put(rate, positiveNums.get(rate) + 1);
                } else {
                    positiveNums.put(rate, 1);
                }
                positivePeriodsNum++;
            } else {
                if (countedValue < 0) {
                    // 为负取绝对值，并向上取整
                    double rate = (Math.ceil(Math.abs(countedValue * 100)));
                    if (negativeNums.containsKey(rate)) {
                        negativeNums.put(rate, negativeNums.get(rate) + 1);
                    } else {
                        negativeNums.put(rate, 1);
                    }
                    negativePeriodNum++;
                }
            }
        }

        returnPeriod.positivePeriodsNum = positivePeriodsNum;
        returnPeriod.negativePeriodNum = negativePeriodNum;
        returnPeriod.positiveNums = positiveNums;
        returnPeriod.negativeNums = negativeNums;
        returnPeriod.winRate = ((double)positivePeriodsNum) / holdingDetails.size();

        return returnPeriod;
    }
}
