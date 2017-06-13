package com.edu.nju.asi.service.serviceImpl.TraceBackService;

import com.edu.nju.asi.dao.BaseStockDao;
import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.dao.daoImpl.BaseStockDaoImpl;
import com.edu.nju.asi.dao.daoImpl.StockDaoImpl;
import com.edu.nju.asi.infoCarrier.traceBack.*;
import com.edu.nju.asi.model.BaseStock;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.service.StockService;
import com.edu.nju.asi.service.TraceBackService;
import com.edu.nju.asi.service.serviceImpl.StockService.StockServiceImpl;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;
import com.edu.nju.asi.utilities.exceptions.DateNotWithinException;
import com.edu.nju.asi.utilities.exceptions.NoDataWithinException;
import com.edu.nju.asi.utilities.exceptions.UnhandleBlockTypeException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by harvey on 17-3-28.
 */
@Service("TraceBackService")
public class TraceBackServiceImpl implements TraceBackService {

//    @Autowired
    private StockService stockService;
//    @Autowired
    private StockDao stockDao;
//    @Autowired
    private BaseStockDao baseStockDao;

    private List<String> traceBackStockPool;

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
     * 所有股票池中的股票数据，键值为股票代码（如000010）
     */
    protected Map<String, List<Stock>> stockData;

    /**
     * 回测所使用的基准股票，键值为股指名称（如沪深300）
     */
    protected Map<String, List<BaseStock>> baseStockData;


    /**
     * 回测区间内实用的股票池中的股指数据
     */
    private List<BaseStock> baseStocks;


    public TraceBackServiceImpl() throws IOException {
        stockService = new StockServiceImpl();
        stockDao = new StockDaoImpl();
        baseStockDao = new BaseStockDaoImpl();

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

        traceBackStockPool = stockService.getStockPool(traceBackCriteria.stockPoolCriteria);
        setUpStockData(traceBackCriteria);

        System.out.println("---------------set完毕------------");

        // 累计基准收益率
        baseCumulativeReturn = getBase(traceBackCriteria);
        traceBackInfo.baseCumulativeReturn = baseCumulativeReturn;
        System.out.println("---------------1------------");

        //选择策略
        traceBackStrategyCalculator = new TraceBackStrategyCalculator(traceBackStockPool, traceBackCriteria, allDatesWithData, stockData, baseStockData);
        System.out.println("---------------2------------");

        //策略回测
        StrategyCumulativeAndTransferDetail strategyCumulativeAndTransferDetail = traceBackStrategyCalculator.traceBack(traceBackCriteria);
        traceBackInfo.strategyCumulativeReturn = strategyCumulativeAndTransferDetail.strategyCumulativeReturn;
        traceBackInfo.transferDayDetails = strategyCumulativeAndTransferDetail.lastSoldStocks;
        traceBackInfo.stageDetails = strategyCumulativeAndTransferDetail.stageDetails;
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
        TraceBackParam param = new TraceBackParam(traceBackCriteria, traceBackInfo, stockData, traceBackStockPool, baseStocks);
        traceBackInfo.traceBackNumVal = param.getNumericalVal();
        System.out.println("---------------7------------");

        return traceBackInfo;
    }

    /**
     * 策略优化时，先设置原策略的条件
     *
     * @param traceBackCriteria 原策略的标准
     * @return
     */
    @Override
    public void setOriginTraceBackCriteria(TraceBackCriteria traceBackCriteria) {
        //TODO gcm
    }

    /**
     * 进行一次适应函数的计算
     *
     * @param filterConditions 经过遗传算法变更过参数的筛选条件
     * @param rankConditions   经过遗传算法变更过参数的排名条件
     * @return 回测结果
     */
    @Override
    public TraceBackInfo optimize(List<FilterCondition> filterConditions, List<RankCondition> rankConditions) {
        //TODO
        return null;
    }

    private void setUpStockData(TraceBackCriteria criteria) throws IOException {

        // 所有股票数据
        System.out.println("enter");
        stockData = stockDao.getAllStockData(traceBackStockPool);
        System.out.println("out");

        // 所有回测股指数据
        baseStocks = baseStockDao.getBaseStockData(criteria.baseStockName, criteria.startDate, criteria.endDate);

        // 获取所有股指信息
        Set<String> baseStockNames = new HashSet<>();
        baseStockNames.add(criteria.baseStockName);
//        for (MarketSelectingCondition condition : criteria.marketSelectingConditions) {
//            baseStockNames.add(condition.baseStockName);
//        }

        baseStockData = new HashMap<>();
        for (String bsName : baseStockNames) {
            baseStockData.put(bsName, baseStockDao.getBaseStockData(bsName));
        }
    }

    /**
     * @param baseCumulativeReturn 累计基准收益率
     * @param holdingPeriod        持有期长度
     * @return 根据基准收益率和超额收益率的完整的HoldingDetailVO列表
     */
    private List<HoldingDetail> calcuHoldingDetail(List<CumulativeReturn> baseCumulativeReturn, List<CumulativeReturn> strategyCumulativeReturn, int holdingPeriod) {
        List<HoldingDetail> holdingDetails = new LinkedList<>();

        double investment = 1000;

        //初始化持仓详情周期的起始日期和结束日期
        int holdingPeriodStart = 0;
        int holdingPeriodEnd = 0;

        int holdingSerial = 1;

        //每个持仓周期包含进起始和末尾的调仓日
        for (int i = 0; i < baseCumulativeReturn.size(); i += (holdingPeriod+1)) {
            holdingPeriodStart = i;
            holdingPeriodEnd = i + holdingPeriod;

            if(holdingSerial != 1){
                holdingPeriodStart = i - 1;
            }

            if(holdingPeriodEnd > baseCumulativeReturn.size()-1){
                holdingPeriodEnd = baseCumulativeReturn.size()-1;
            }

            double startBaseReturn = baseCumulativeReturn.get(holdingPeriodStart).cumulativeReturn;
            double startStrategyReturn = strategyCumulativeReturn.get(holdingPeriodStart).cumulativeReturn;
            double endBaseReturn = baseCumulativeReturn.get(holdingPeriodEnd).cumulativeReturn;
            double endStrategyReturn = strategyCumulativeReturn.get(holdingPeriodEnd).cumulativeReturn;


            double periodBaseReturn = (endBaseReturn - startBaseReturn) / (1 + startBaseReturn);
            double periodStrategyReturn = (endStrategyReturn - startStrategyReturn) / (1 + startStrategyReturn);

            // 超额收益率
            double excessReturn = periodStrategyReturn - periodBaseReturn;

            holdingDetails.add(new HoldingDetail(holdingSerial, baseCumulativeReturn.get(holdingPeriodStart).currentDate, baseCumulativeReturn.get(holdingPeriodEnd).currentDate,
                    periodStrategyReturn, periodBaseReturn, excessReturn, investment * (1 + endStrategyReturn)));

            holdingSerial++;
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

        List<CumulativeReturn> cumulativeReturns = new ArrayList<>();

        //累计收益率以第一个交易日的收益率来对比计算
        double closeOfFirstDay = baseStocks.get(0).getClose();

        System.out.println("Begin loop");
        for (int i = 0; i < baseStocks.size(); i++) {
            double sucClose = baseStocks.get(i).getClose();
            double cumulativeReturn = (sucClose - closeOfFirstDay) / closeOfFirstDay;
            //先将所有的最大回测点设为false
            cumulativeReturns.add(new CumulativeReturn(baseStocks.get(i).getStockID().getDate(), cumulativeReturn, false));
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
