package com.edu.nju.asi.service.serviceImpl.TraceBackService;

import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.dao.daoImpl.StockDaoImpl;
import com.edu.nju.asi.infoCarrier.traceBack.*;
import com.edu.nju.asi.model.BaseStock;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.service.StockService;
import com.edu.nju.asi.service.TraceBackService;
import com.edu.nju.asi.service.serviceImpl.StockService.StockServiceImpl;
import com.edu.nju.asi.utilities.StrategyStockList;
import com.edu.nju.asi.utilities.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by harvey on 17-3-28.
 */
@Service("TraceBackService")
public class TraceBackServiceImpl implements TraceBackService {

    @Autowired
    private StockService stockService;
    @Autowired
    private StockDao stockDao;

    //自选股票池，用户回测自选股票池时才对此成员变量赋值
//    private List<String> baseStockPool;

    private List<String> traceBackStockPool;

    //自选股票池的所有数据
//    private Map<String, List<StrategyStock>> baseStockData;

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
//        stockService = new StockServiceImpl();
//        stockDao = new StockDaoImpl();

        //获取所有数据的日期
//        allDatesWithData = stockDao.getDateWithData();
    }

    /**
     * 因为对springMVC未找到自动注入时初始化的方法，故在使用成员变量前先初始化
     */
    private void init() throws IOException {
        if (allDatesWithData == null) {
            //获取所有数据的日期
            allDatesWithData = stockDao.getDateWithData();
        }
    }


    @Override
    public TraceBackInfo traceBack(TraceBackCriteria traceBackCriteria) throws IOException, DataSourceFirstDayException, DateNotWithinException, NoDataWithinException, UnhandleBlockTypeException {
        init();

        long enter = System.currentTimeMillis();

        TraceBackInfo traceBackInfo = new TraceBackInfo();

        // 选取回测的股票池为自选股票池／板块股票池

//        //是自选股票池
//        if (traceBackCriteria.isCustomized){
//            traceBackStockPool = stockPool;
//            //给基准股票池赋值，即为自选股票池
//            baseStockPool = stockPool;
//            setUp(traceBackStockPool);
//            //获取所有自选股的所有数据
//            baseStockData = stockData;
//        }
//        //不是自选股票池
//        else {
            traceBackStockPool = stockService.getStockPool(traceBackCriteria.stockPoolCriteria);
            setUp(traceBackStockPool);
//        }

        System.out.println("---------------set完毕------------");

        // 累计基准收益率
        baseCumulativeReturn = getBase(traceBackCriteria);
        traceBackInfo.baseCumulativeReturn = baseCumulativeReturn;
        System.out.println("---------------1------------");

        //选择策略
        traceBackStrategyCalculator = new TraceBackStrategyCalculator(traceBackStockPool, traceBackCriteria, allDatesWithData, stockData);
        System.out.println("---------------2------------");

        //策略回测
        traceBackInfo.strategyCumulativeReturn = traceBackStrategyCalculator.traceBack(traceBackCriteria);
        System.out.println("---------------3------------");

        //计算策略回撤的相关信息
        traceBackInfo.maxTraceBack = maxRetracement(traceBackInfo.strategyCumulativeReturn, baseCumulativeReturn);
        System.out.println("---------------4------------");

        // 计算持仓详情的基准收益率和超额收益率
        traceBackInfo.holdingDetails = calcuHoldingDetail(traceBackInfo.baseCumulativeReturn, traceBackInfo.strategyCumulativeReturn, traceBackCriteria.holdingPeriod);
        System.out.println("---------------5------------");

        // 计算绝对收益周期和相对收益周期
        traceBackInfo.absoluteReturnPeriod = countReturnPeriod(traceBackInfo.holdingDetails, true);
        traceBackInfo.relativeReturnPeriod = countReturnPeriod(traceBackInfo.holdingDetails, false);
        System.out.println("---------------6------------");

        System.out.println("计算目标策略算法给定形成期、持有期所用时间: "+ (System.currentTimeMillis()-enter));

        // TraceBackParameter 计算贝塔系数等
        List<BaseStock> baseStockList = stockService.getBaseStockData(traceBackCriteria.baseStockName, traceBackCriteria.startDate, traceBackCriteria.endDate);
        TraceBackParameter traceBackParameter = new TraceBackParameter(traceBackCriteria, traceBackInfo, stockData, traceBackStockPool, baseStockList);
        System.out.println("---------------7------------");
        return traceBackParameter.getTraceBackInfo();

    }

    private void setUp(List<String> traceBackStockPool) throws IOException {

        stockData = new HashMap<>();
        for (String thisStockCode : traceBackStockPool) {
            List<Stock> stocks = stockDao.getStockData(thisStockCode);
            stockData.put(thisStockCode, convertStocks(stocks));
        }

    }

    private List<StrategyStock> convertStocks(List<Stock> stocks) {
        StrategyStockList result = new StrategyStockList();
        for (Stock thisStock : stocks) {
            result.add(new StrategyStock(thisStock));
        }
        return result;
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

            preBaseCumulativeReturn = curBaseCumulativeReturn;
            preStrategyCumulativeReturn = curStrategyCumulativeReturn;
            holdingSerial++;

            holdingDetails.add(new HoldingDetail(holdingSerial, baseCumulativeReturn.get(i).currentDate, baseCumulativeReturn.get(periodIndex).currentDate,
                    strategyReturn, baseReturn, excessReturn, investment * (1 + lastStrategyRate)));
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

        return getCumulativeReturnOfOneStock(traceBackCriteria.baseStockName, start, end);
    }

    /**
     * 计算一只基准股的累计收益率
     * @param stockName 单一股票的信息
     * @param start     起始日期
     * @param end       结束日期
     * @return List<CumulativeReturn> 一只基准股的累计收益率
     */
    private List<CumulativeReturn> getCumulativeReturnOfOneStock(String stockName, LocalDate start, LocalDate end) {

        System.out.println("in getCumulativeReturnOfOneStock-------------"+stockName+"--------------");

        List<BaseStock> list = null;
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

        //累计收益率以第一个交易日的收益率来对比计算
        double closeOfFirstDay = list.get(0).getClose();

        System.out.println("Begin loop");
        for (int i = 0; i < list.size(); i++) {
            double sucClose = list.get(i).getClose();
            double cumulativeReturn = (sucClose - closeOfFirstDay) / closeOfFirstDay;
            //先将所有的最大回测点设为false
            cumulativeReturns.add(new CumulativeReturn(list.get(i).getStockID().getDate(), cumulativeReturn, false));
        }

        //将第一天的收益率设置为0
        cumulativeReturns.get(0).cumulativeReturn = 0;
        System.out.println("finished getCumulativeReturnOfOneStock--------------"+stockName+"----------------");

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
