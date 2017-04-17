package service.serviceImpl.TraceBackService;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import po.StockPO;
import service.StockService;
import service.TraceBackService;
import service.serviceImpl.StockService.StockServiceImpl;
import service.serviceImpl.TraceBackService.TraceBackStrategy.StrategyStock;
import utilities.exceptions.*;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by harvey on 17-3-28.
 */
public class TraceBackServiceImpl implements TraceBackService {

    private StockService stockService;
    private StockDao stockDao;

    private AllTraceBackStrategy traceBackStrategy;

    //基准累计收益率
    private double baseStockCumulative;

    //回测标准
    private TraceBackCriteriaVO traceBackCriteriaVO;

    //自选股票池
    private List<String> baseStockPool;

    private Map<String, List<StrategyStock>> baseStockData;

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

        //将基准累计收益率初始化为1
        baseStockCumulative = 1;
    }

    @Override
    public TraceBackVO traceBack(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockPool) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException, NoMatchEnumException, UnhandleBlockTypeException, DataSourceFirstDayException {
        this.traceBackCriteriaVO = traceBackCriteriaVO;

        TraceBackVO traceBackVO = new TraceBackVO();

        // 选取回测的股票池为自选股票池／板块股票池
        List<String> traceBackStockPool;
        if (traceBackCriteriaVO.isCustomized) traceBackStockPool = stockPool;
        else traceBackStockPool = stockService.getStockPool(traceBackCriteriaVO.stockPoolVO);

        setUp(traceBackStockPool);

        // 累计基准收益率
        traceBackVO.baseCumulativeReturn = getBase(traceBackCriteriaVO, stockPool);

        //选择策略
        traceBackStrategy = TraceBackStrategyFactory.createTraceBackStrategy(traceBackStockPool, traceBackCriteriaVO, allDatesWithData, stockData);

        //策略回测
        TraceBackStrategyVO traceBackStrategyVO = traceBackStrategy.traceBack();
        traceBackVO.strategyCumulativeReturn = traceBackStrategyVO.strategyCumulativeReturn;

        // 计算持仓详情的基准收益率和超额收益率
        traceBackVO.holdingDetailVOS = calcuHoldingDetail(traceBackStrategyVO.holdingDetailVOS, traceBackVO.baseCumulativeReturn, traceBackCriteriaVO.holdingPeriod);

        // 计算绝对收益周期
        traceBackVO.absoluteReturnPeriodVO = countAbsoluteReturnPeriod(traceBackVO.holdingDetailVOS);

        // 计算相对收益周期
        traceBackVO.relativeReturnPeriodVO = countRelativeReturnPeriod(traceBackVO.holdingDetailVOS);

        // 计算超额收益率/策略胜率，给定持有期/形成期
        traceBackVO.certainFormates = findHoldingWithCertainFormate(traceBackCriteriaVO, stockPool);
        traceBackVO.certainHoldings = findFormateWithCertainHolding(traceBackCriteriaVO, stockPool);

        // TraceBackParameter 计算贝塔系数等
        TraceBackParameter traceBackParameter = new TraceBackParameter(traceBackCriteriaVO, traceBackVO);
        return traceBackParameter.getTraceBackVO();

//        return traceBackVO;
    }

    private void setUp(List<String> traceBackStockPool) throws IOException {
        allDatesWithData = stockDao.getDateWithData();

        stockData = new HashMap<>();
        for (String thisStockCode : traceBackStockPool) {
            List<StockPO> tempPOS = stockDao.getStockData(thisStockCode);
            stockData.put(thisStockCode, convertStockPOS(tempPOS));
        }

    }

    private List<StrategyStock> convertStockPOS(List<StockPO> pos) {
        List<StrategyStock> result = new LinkedList<>();
        for (StockPO thisPO : pos) {
            result.add(new StrategyStock(thisPO));
        }
        return result;
    }

    /**
     * 给定持有期，计算不同形成期下，超额收益和策略胜率的分布信息
     *
     * @param traceBackCriteriaVO 用户所选的回测标准
     * @param stockPool           自选股票池（可能有）
     * @return
     * @throws DateNotWithinException
     * @throws NoDataWithinException
     * @throws IOException
     * @throws DateShortException
     * @throws CodeNotFoundException
     */
    private List<ExcessAndWinRateDistVO> findFormateWithCertainHolding(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockPool) throws DateNotWithinException, NoDataWithinException, IOException, DateShortException, CodeNotFoundException, NoMatchEnumException, DataSourceFirstDayException, UnhandleBlockTypeException {
        return findBestFormateOrHolding(traceBackCriteriaVO, stockPool, false);
    }

    /**
     * 给定形成期，计算不同持有期下，超额收益和策略胜率的分布信息
     *
     * @param traceBackCriteriaVO 用户所选的回测标准
     * @param stockPool           自选股票池（可能有）
     * @return
     * @throws CodeNotFoundException
     * @throws DateShortException
     * @throws DateNotWithinException
     * @throws NoDataWithinException
     * @throws IOException
     */
    private List<ExcessAndWinRateDistVO> findHoldingWithCertainFormate(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockPool) throws CodeNotFoundException, DateShortException, DateNotWithinException, NoDataWithinException, IOException, NoMatchEnumException, DataSourceFirstDayException, UnhandleBlockTypeException {
        return findBestFormateOrHolding(traceBackCriteriaVO, stockPool, true);
    }

    private List<ExcessAndWinRateDistVO> findBestFormateOrHolding(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockPool, boolean certainFormate) throws DateNotWithinException, NoDataWithinException, IOException, DateShortException, CodeNotFoundException, NoMatchEnumException, DataSourceFirstDayException, UnhandleBlockTypeException {

        List<ExcessAndWinRateDistVO> certainHoldings = new ArrayList<>();
        int initHoldingPeriod = traceBackCriteriaVO.holdingPeriod;
        int initFormativePeriod = traceBackCriteriaVO.formativePeriod;

        for (int i = 2; i <= 10; i = i + 2) {
            ExcessAndWinRateDistVO excessAndWinRateDistVO = new ExcessAndWinRateDistVO();
            //给定形成期
            if (certainFormate) {
                //新的持有期
                traceBackCriteriaVO.holdingPeriod = initHoldingPeriod * i;
            }
            //给定持有期
            else {
                //新的形成期
                traceBackCriteriaVO.formativePeriod = initFormativePeriod * i;
            }

            TraceBackVO traceBackVO = new TraceBackVO();

            //累计基准收益率
            traceBackVO.baseCumulativeReturn = getBase(traceBackCriteriaVO, stockPool);

            // 选取回测的股票池
            List<String> traceBackStockPool = null;
            //自定义股票池
            if (traceBackCriteriaVO.isCustomized) {
                traceBackStockPool = stockPool;
            } else {
                traceBackStockPool = stockService.getStockPool(traceBackCriteriaVO.stockPoolVO);
            }

            //选择策略
            AllTraceBackStrategy traceBackStrategy = TraceBackStrategyFactory.createTraceBackStrategy(traceBackStockPool, traceBackCriteriaVO, allDatesWithData, stockData);
            //策略回测
            TraceBackStrategyVO traceBackStrategyVO = traceBackStrategy.traceBack();
            traceBackVO.strategyCumulativeReturn = traceBackStrategyVO.strategyCumulativeReturn;

            //计算持仓详情的基准收益率和超额收益率
            traceBackVO.holdingDetailVOS = calcuHoldingDetail(traceBackStrategyVO.holdingDetailVOS, traceBackVO.baseCumulativeReturn, traceBackCriteriaVO.holdingPeriod);
            //计算相对收益周期
            traceBackVO.relativeReturnPeriodVO = countRelativeReturnPeriod(traceBackVO.holdingDetailVOS);

            //相对强弱计算周期
            excessAndWinRateDistVO.relativeCycle = i;
            //总超额收益
            double strategyLastRate = traceBackVO.strategyCumulativeReturn.get(traceBackVO.strategyCumulativeReturn.size() - 1).cumulativeReturn;
            double baseLastRate = traceBackVO.baseCumulativeReturn.get(traceBackVO.baseCumulativeReturn.size() - 1).cumulativeReturn;
            excessAndWinRateDistVO.excessRate = strategyLastRate - baseLastRate;
            //策略胜率
            excessAndWinRateDistVO.winRate = traceBackVO.relativeReturnPeriodVO.winRate;

            certainHoldings.add(excessAndWinRateDistVO);
        }

        return certainHoldings;
    }

    /**
     * 计算绝对收益周期
     *
     * @param holdingDetailVOS 历史持仓详情
     * @return ReturnPeriodVO 绝对收益周期信息载体
     */
    private ReturnPeriodVO countAbsoluteReturnPeriod(List<HoldingDetailVO> holdingDetailVOS) {

        ReturnPeriodVO returnPeriodVO = new ReturnPeriodVO();

        int positivePeriodsNum = 0;
        int negativePeriodNum = 0;

        Map<Double, Integer> positiveNums = new TreeMap<>();
        // TODO gcm 负收益的rate是取正还是负,现在取的负
        Map<Double, Integer> negativeNums = new TreeMap<>();

        for (int i = 0; i < holdingDetailVOS.size(); i++) {
            //为正
            if (holdingDetailVOS.get(i).strategyReturn > 0) {
                //向上取整
                double rate = Math.ceil(holdingDetailVOS.get(i).strategyReturn * 100);
                if (positiveNums.containsKey(rate)) {
                    positiveNums.put(rate, positiveNums.get(rate) + 1);
                } else {
                    positiveNums.put(rate, 1);
                }

                //正周期数+1
                positivePeriodsNum++;
            }
            //为负
            else {
                if (holdingDetailVOS.get(i).strategyReturn < 0) {
                    //取绝对值,并向上取整
                    double rate = (Math.ceil(Math.abs(holdingDetailVOS.get(i).strategyReturn * 100)));
                    if (negativeNums.containsKey(rate)) {
                        negativeNums.put(rate, negativeNums.get(rate) + 1);
                    } else {
                        negativeNums.put(rate, 1);
                    }
                }
                //负周期数+1
                negativePeriodNum++;
            }
        }

        returnPeriodVO.positivePeriodsNum = positivePeriodsNum;
        returnPeriodVO.negativePeriodNum = negativePeriodNum;
        returnPeriodVO.positiveNums = positiveNums;
        returnPeriodVO.negativeNums = negativeNums;
        returnPeriodVO.winRate = positivePeriodsNum / holdingDetailVOS.size();

        return returnPeriodVO;
    }

    //TODO gcm 有重复代码

    /**
     * 计算相对收益周期
     *
     * @param holdingDetailVOS 历史持仓详情
     * @return ReturnPeriodVO 相对收益周期信息载体
     */
    private ReturnPeriodVO countRelativeReturnPeriod(List<HoldingDetailVO> holdingDetailVOS) {
        ReturnPeriodVO returnPeriodVO = new ReturnPeriodVO();

        int positivePeriodsNum = 0;
        int negativePeriodNum = 0;

        Map<Double, Integer> positiveNums = new TreeMap<>();
        // TODO gcm 负收益的rate是取正还是负,现在取的负
        Map<Double, Integer> negativeNums = new TreeMap<>();

        for (int i = 0; i < holdingDetailVOS.size(); i++) {

            //相对收益率为正
            if (holdingDetailVOS.get(i).excessReturn > 0) {
                //向上取整
                double rate = Math.ceil(holdingDetailVOS.get(i).excessReturn * 100);
                if (positiveNums.containsKey(rate)) {
                    positiveNums.put(rate, positiveNums.get(rate) + 1);
                } else {
                    positiveNums.put(rate, 1);
                }

                //正周期数+1
                positivePeriodsNum++;
            }
            //为负
            else if (holdingDetailVOS.get(i).excessReturn < 0) {
                //取绝对值,并向上取整
                double rate = (Math.ceil(Math.abs(holdingDetailVOS.get(i).strategyReturn * 100)));
                if (negativeNums.containsKey(rate)) {
                    negativeNums.put(rate, negativeNums.get(rate) + 1);
                } else {
                    negativeNums.put(rate, 1);
                }
                //负周期数+1
                negativePeriodNum++;
            }
        }

        returnPeriodVO.positivePeriodsNum = positivePeriodsNum;
        returnPeriodVO.negativePeriodNum = negativePeriodNum;
        returnPeriodVO.positiveNums = positiveNums;
        returnPeriodVO.negativeNums = negativeNums;
        returnPeriodVO.winRate = positivePeriodsNum / holdingDetailVOS.size();

        return returnPeriodVO;
    }

    /**
     * @param holdingDetailVOS     只有策略收益率的持仓详情
     * @param baseCumulativeReturn 累计基准收益率
     * @param holdingPeriod        持有期长度
     * @return 补充了基准收益率和超额收益率的完整的HoldingDetailVO列表
     */
    private List<HoldingDetailVO> calcuHoldingDetail(List<HoldingDetailVO> holdingDetailVOS, List<CumulativeReturnVO> baseCumulativeReturn, int holdingPeriod) {

        int holdingSerial = 0;

        //保存之前一个周期的最后一天的累计收益率, 初始值为1
        double curCumulativeReturn = 1;
        double preCumulativeReturn = 1;
        for (int i = 0; i < baseCumulativeReturn.size(); i += holdingPeriod) {
            double lastRate;
            if ((i + holdingPeriod - 1) < baseCumulativeReturn.size()) {
                // 一个持仓周期最后一天的基准累计收益率
                lastRate = baseCumulativeReturn.get(i + holdingPeriod - 1).cumulativeReturn;
            } else {
                // 最后一个周期可能不满持仓周期数
                lastRate = baseCumulativeReturn.get(baseCumulativeReturn.size() - 1).cumulativeReturn;
            }

            // 当前持仓期的基准收益率
            curCumulativeReturn = 1+lastRate;
            double baseReturn = curCumulativeReturn / preCumulativeReturn - 1;
            holdingDetailVOS.get(holdingSerial).baseReturn = baseReturn;
            preCumulativeReturn = curCumulativeReturn;

            // 超额收益率
            holdingDetailVOS.get(holdingSerial).excessReturn = holdingDetailVOS.get(holdingSerial).strategyReturn - baseReturn;

            holdingSerial++;
        }
        return holdingDetailVOS;
    }

    /**
     * @param traceBackCriteriaVO 回测标准
     * @param stockPool           自选股票池的所有股票的代号
     * @return
     * @throws IOException
     * @throws NoDataWithinException
     * @throws DateNotWithinException
     */
    private List<CumulativeReturnVO> getBase(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockPool) throws IOException, NoDataWithinException, DateNotWithinException {
        LocalDate start = traceBackCriteriaVO.startDate;
        LocalDate end = traceBackCriteriaVO.endDate;

        if (!traceBackCriteriaVO.isCustomized) {
            return getCumulativeReturnOfOneStock(traceBackCriteriaVO.baseStockName, start, end);
        } else {
            baseStockPool = stockPool;
            allDatesWithData = stockDao.getDateWithData();
            baseStockData = stockData;
            return getCustomizedCumulativeReturn(start, end, stockData);
        }
    }

    //TODO gcm 看看自选股和非自选股可否分开两个类，帮忙看

    /**
     * 获取基准累计收益率，自选股
     *
     * @param start 回测区间起始日期
     * @param end   回测区间结束日期
     * @param stockMap
     * @return List<CumulativeReturnVO> 基准累计收益率的列表
     */
    @Override
    public List<CumulativeReturnVO>  getCustomizedCumulativeReturn(LocalDate start, LocalDate end, Map<String, List<StrategyStock>> stockMap) throws IOException, NoDataWithinException, DateNotWithinException {
        List<CumulativeReturnVO> cumulativeReturnVOS = new ArrayList<>();

        LocalDate thisStart = traceBackCriteriaVO.startDate;
        LocalDate thisEnd = traceBackCriteriaVO.endDate;

        while(!allDatesWithData.contains(thisStart) || !allDatesWithData.contains(thisEnd)){
            if(!allDatesWithData.contains(thisStart)){
                thisStart = thisStart.plusDays(1);
            }
            if(!allDatesWithData.contains(thisEnd)){
                thisEnd = thisEnd.minusDays(1);
            }
        }

        LocalDate temp = thisStart;
        while(temp.isBefore(thisEnd) || temp.isEqual(thisEnd)){
            StrategyStock vo = null;
            double totalCumulativeReturn = 0;
            int notSuspend = 0;
            for(int j = 0; j < baseStockPool.size(); j++){
                vo = findStockCertainDay(baseStockPool.get(j), temp);
                //当天有数据
                if(null != vo){
                    totalCumulativeReturn += (vo.close/vo.preClose - 1);
                    notSuspend += 1;
                }
            }

            //即该天有股票开盘
            if(notSuspend != 0){
                baseStockCumulative = baseStockCumulative * (1 + (totalCumulativeReturn / notSuspend));
                cumulativeReturnVOS.add(new CumulativeReturnVO(vo.date, baseStockCumulative-1, false));
            }

            temp = temp.plusDays(1);
        }

        //修改第一天的基准收益率为0
        cumulativeReturnVOS.get(0).cumulativeReturn = 0;

        return cumulativeReturnVOS;
    }


    public TraceBackNumValVO getNumericalVal(TraceBackCriteriaVO traceBackCriteriaVO) {

        TraceBackParameter parameter = null;
//        try {
//            parameter = new TraceBackParameter(traceBackCriteriaVO);
//        } catch (CodeNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (DateNotWithinException e) {
//            e.printStackTrace();
//        } catch (NoDataWithinException e) {
//            e.printStackTrace();
//        } catch (DateShortException e) {
//            e.printStackTrace();
//        }

        return null;
    }

    /**
     * 当不是回测自选股时，计算一只基准股的累计收益率
     * @param stockName 单一股票的信息
     * @param start     起始日期
     * @param end       结束日期
     * @return List<CumulativeReturnVO> 一只基准股的累计收益率
     */
    private List<CumulativeReturnVO> getCumulativeReturnOfOneStock(String stockName, LocalDate start, LocalDate end) throws IOException {

        List<StockVO> list = null;
        try {
            list = stockService.getBaseStockData(stockName, start, end);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoDataWithinException e) {
            e.printStackTrace();
        } catch (DateNotWithinException e) {
            e.printStackTrace();
        }

        List<CumulativeReturnVO> cumulativeReturnVOS = new ArrayList<CumulativeReturnVO>();

        //累计收益率以第一个交易日前一天的收益率来对比计算
        double closeOfFirstDay = list.get(0).preClose;

        for (int i = 0; i < list.size(); i++) {
            double sucClose = list.get(i).close;
            double cumulativeReturn = (sucClose - closeOfFirstDay) / closeOfFirstDay;
            //先将所有的最大回测点设为false
            cumulativeReturnVOS.add(new CumulativeReturnVO(list.get(i).date, cumulativeReturn, false));
        }

        //将第一天的收益率设置为0
        cumulativeReturnVOS.get(0).cumulativeReturn = 0;

        return cumulativeReturnVOS;
    }


    private StrategyStock findStockCertainDay(String stockCode, LocalDate date){
        LocalDate thisDate = date;

        List<StrategyStock> stockVOList = baseStockData.get(stockCode);
        List<LocalDate> dates = new ArrayList<>();
        for(int j = 0; j < stockVOList.size(); j++){
            dates.add(stockVOList.get(j).date);
        }

        int dateIndex = dates.indexOf(thisDate);

        //该股票当天没有数据
        if(dateIndex == -1){
            return null;
        }
        return stockVOList.get(dateIndex);
    }
}
