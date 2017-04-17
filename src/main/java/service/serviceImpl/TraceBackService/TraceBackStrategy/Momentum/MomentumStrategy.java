package service.serviceImpl.TraceBackService.TraceBackStrategy.Momentum;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import service.StockService;
import service.StockTradingDayService;
import service.TraceBackService;
import service.serviceImpl.StockService.StockServiceImpl;
import service.serviceImpl.StockTradingDayServiceImpl;
import service.serviceImpl.TraceBackService.AllTraceBackStrategy;
import service.serviceImpl.TraceBackService.TraceBackServiceImpl;
import utilities.exceptions.CodeNotFoundException;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.DateShortException;
import utilities.exceptions.NoDataWithinException;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by harvey on 17-3-31.
 */
public class MomentumStrategy extends AllTraceBackStrategy {

    StockService stockService;
    TraceBackService traceBackService;
    StockDao stockDao;
    StockTradingDayService stockTradingDayService;

    //初始投资
    double initInvestment;

    //剩余投资
    double remainInvestment;

    //所有持有期有数据的日期
    List<LocalDate> holdingDates = new ArrayList<>();

    //所有形成期有数据的日期
    List<LocalDate> formativeDates = new ArrayList<>();

    //所选股票池的代码和其在一定期间内的映射(包括开始日期前的形成期)
    Map<String, List<StockVO>> stockMap = new TreeMap<>();


    public MomentumStrategy(List<String> stockPoolCodes, TraceBackCriteriaVO traceBackCriteriaVO) throws IOException, DateNotWithinException, NoDataWithinException {
        super(stockPoolCodes,traceBackCriteriaVO);

        stockService = new StockServiceImpl();
        stockDao = new StockDaoImpl();
        traceBackService = new TraceBackServiceImpl();
        stockTradingDayService = new StockTradingDayServiceImpl();

        //初始为千元投资
        initInvestment = 1000;
        remainInvestment = initInvestment;

        //设置持有期和形成期有数据的日期
        setDates();

        setStockMap();
    }

    private void setStockMap() throws IOException, NoDataWithinException, DateNotWithinException {
        for(int i = 0; i < stockPoolCodes.size(); i++){
            stockMap.put(stockPoolCodes.get(i), stockService.getOneStockData(stockPoolCodes.get(i), formativeDates.get(0), formativeDates.get(formativeDates.size()-1)));
        }
    }

    /**
     * 设置持有其和形成期有数据的日期
     */
    private void setDates() throws IOException {
        holdingDates = stockDao.getDateWithData(traceBackCriteriaVO.startDate,traceBackCriteriaVO.endDate);

        LocalDate startOfFormative = stockTradingDayService.getTradingDayMinus(traceBackCriteriaVO.startDate, 20, stockPoolCodes);
        LocalDate endOfFormative = stockTradingDayService.getLastTradingDay(traceBackCriteriaVO.startDate, stockPoolCodes);
        formativeDates = stockDao.getDateWithData(startOfFormative,endOfFormative);
        formativeDates.addAll(holdingDates);
    }

    /**
     * 根据目标股票池及所给的标准，返回策略的累计收益率
     *
     * @return List<CumulativeReturnVO> 策略的累计收益率
     */
    @Override
    public TraceBackStrategyVO traceBack() throws IOException, NoDataWithinException, DateNotWithinException {

        List<CumulativeReturnVO> cumulativeReturnVOS = new ArrayList<CumulativeReturnVO>();

        //当前所持有的股票
        List<String> holdingStocks = new ArrayList<String>();

        //回测区间,从用户所选区间里再选出第一个交易日和最后一个交易日
        LocalDate end = holdingDates.get(holdingDates.size()-1);

        int tradingDays = holdingDates.size();

        //形成期
        int formativePeriod = traceBackCriteriaVO.formativePeriod;

        //持有期
        int holdingPeriod = traceBackCriteriaVO.holdingPeriod;

        //周期数
        int periodNum = 0;

        //刚好能够均分
        if(tradingDays%holdingPeriod == 0){
            periodNum = tradingDays/holdingPeriod;
        }
        //不能均分，则周期数+1
        else{
            periodNum = tradingDays/holdingPeriod + 1;
        }

        for (int i = 0; i < periodNum; i++ ){

            //持有期的开始日期
            LocalDate startOfHolding = holdingDates.get(i*holdingPeriod);

            //形成期的结束日期
            LocalDate endOfFormative = formativeDates.get(formativeDates.indexOf(startOfHolding)-1);
            //形成期的起始日期
            LocalDate startOfFormative = formativeDates.get(formativeDates.indexOf(endOfFormative)-formativePeriod+1);


            //持有期所持有的股票
            holdingStocks = pickStocks(formate(stockPoolCodes,startOfFormative,endOfFormative));

            //持有期的结束日期
            LocalDate endOfHolding = null;
            //最后一个周期，直接以最后一个交易日为最后一天
            if(i == periodNum-1){
                endOfHolding = end;
            }
            else {
                endOfHolding = holdingDates.get((i+1)*holdingPeriod-1);
            }

            //多往前计算一天
            LocalDate startOfHoldingMinusOne = formativeDates.get(formativeDates.indexOf(startOfHolding)-1);

            //保存每个持有期中，所持有股票的相对于持有期起始日期的累计收益率

            Map<String, List<StockVO>> holdingStockVOs = new TreeMap<>();
            for(int j = 0; j < holdingStocks.size(); j++){
                holdingStockVOs.put(holdingStocks.get(j), findStockVOsWithinDay(holdingStocks.get(j), startOfHoldingMinusOne, endOfHolding));
            }

            List<CumulativeReturnVO> dailyTotalCumulativeReturn = traceBackService.getCustomizedCumulativeReturn(startOfHoldingMinusOne,endOfHolding, holdingStockVOs);

            List<CumulativeReturnVO> cumulatives = computeHoldingPeriod(dailyTotalCumulativeReturn);

            double lastRate = cumulatives.get(cumulatives.size()-1).cumulativeReturn;

            HoldingDetailVO holdingDetailVO = computeHoldingDetailVO(lastRate, startOfHolding, endOfHolding);
            holdingDetailVOS.add(holdingDetailVO);

            //第一个周期，第一天的累计收益率置为0
            if(i == 0){
                cumulatives.get(0).cumulativeReturn = 0;
            }

            cumulativeReturnVOS.addAll(cumulatives);
        }

        TraceBackStrategyVO traceBackStrategyVO = new TraceBackStrategyVO(maxRetracement(cumulativeReturnVOS),holdingDetailVOS);

        return traceBackStrategyVO;
    }

    /**
     * 形成期／N日均值，用于后续策略筛选
     *
     * @param stockCodes      股票列表
     * @param periodStart     持有期起始日期;
     * @param formativePeriod 形成期长度（MS）／N日均值偏离度（MR）
     * @return 形成的
     */
    @Override
    protected List<FormativePeriodRateVO> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException {
        return null;
    }

    /**
     * 计算当前持有期所持有的股票在当前的持有期内每一天相对于回测区间起始日期的总的累计收益率
     * @param dailyTotalCumulativeReturn 当前持有期所持有股票每天的累计收益率
     * @return
     */
    private List<CumulativeReturnVO> computeHoldingPeriod(List<CumulativeReturnVO> dailyTotalCumulativeReturn) throws DateNotWithinException, NoDataWithinException, IOException {

        List<CumulativeReturnVO> cumulativeReturn = new ArrayList<CumulativeReturnVO>();

        //从1开始计数，因为日期多往前算了一天
        for(int i = 1; i < dailyTotalCumulativeReturn.size(); i++){
            LocalDate date = dailyTotalCumulativeReturn.get(i).currentDate;
            double rate = dailyTotalCumulativeReturn.get(i).cumulativeReturn;

            double remainInvestment = this.remainInvestment;
            remainInvestment = remainInvestment *(1+rate);
            cumulativeReturn.add(new CumulativeReturnVO(date, (remainInvestment - initInvestment) / initInvestment, false));
        }

        return cumulativeReturn;
    }

    /**
     * 计算一个持仓期的持仓详情
     * @param lastRate 当前持仓周期最后一天相对于该持仓周期第一天的累计收益率
     * @param start 持有期的起始日期的前一个交易日,由于第一天相对第一天为0,则应多往前计算一天
     * @param end 持有期的结束日期，也为交易日
     * @return HoldingDetailVO  持仓期的持仓详情
     */
    private HoldingDetailVO computeHoldingDetailVO(double lastRate, LocalDate start, LocalDate end){

        //保存当前持仓期详情信息
        HoldingDetailVO curHoldingPeriod = new HoldingDetailVO();
        curHoldingPeriod.periodSerial = holdingDetailVOS.size()+1;
        curHoldingPeriod.startDate = start;
        curHoldingPeriod.endDate = end;

        //保存之前的投资资金
        double preRemainInvestment = remainInvestment;

        //更新剩余资金
        remainInvestment = initInvestment * (1+lastRate);

        //当前持仓期的策略收益
        curHoldingPeriod.strategyReturn = (remainInvestment - preRemainInvestment) / preRemainInvestment;

        return curHoldingPeriod;
    }

    /**
     * 对股票排序并挑选股票购买
     * @param formativePeriodRate 形成期内，目标股票池所有股票的代码与累计收益率的列表
     * @return 选取前20%的股票购买 //TODO 目前挑选股票的参数，暂定为取前20%，不知后期是否要做活一点儿
     */
    @Override
    protected List<String> pickStocks(List<FormativePeriodRateVO> formativePeriodRate) {

       List<String> sortedStockPool = sortStocks(formativePeriodRate);

       int size = formativePeriodRate.size();
       int topTwentyPercent = 0;

       //将所有的股票分为5组
       if(size%5 == 0){
           topTwentyPercent = size/5;
       }
       else{
           topTwentyPercent = size/5+1;
       }

       //取前1/5，即前20%
       return  sortedStockPool.subList(0,topTwentyPercent);
    }

    @Override
    protected void calculate(LocalDate periodStart, LocalDate periodEnd, int periodSerial) {

    }

    /**
     * 对形成期的目标股票池进行排序
     * @param formativePeriodRate 目标股票池的股票代码与形成期的累计收益率的键值对
     * @return 经排序后的目标股票池的所有股票的代码 //TODO gcm 排序策略？这里直接按降序排序
     */
    private List<String> sortStocks(List<FormativePeriodRateVO> formativePeriodRate) {

        List<String> sortedStockCodes = new ArrayList<String>();

        //按默认方式排序
        formativePeriodRate.sort(null);

        for(int i = 0; i < formativePeriodRate.size(); i++){
            sortedStockCodes.add(formativePeriodRate.get(i).stockCode);
        }

        return sortedStockCodes;
    }

    /**
     * 计算某一时期的所选股票列表内的所有股票的累计收益率，股票代码与累计收益率键值对
     * @param stockCodes 股票列表
     * @param start 形成期起始日期
     * @param end 形成期结束日期
     * @return
     */
    private List<FormativePeriodRateVO> formate(List<String> stockCodes, LocalDate start, LocalDate end) throws IOException, NoDataWithinException, DateNotWithinException {

        List<FormativePeriodRateVO> formativePeriodRate = new ArrayList<>();

        for(int i = 0; i < stockCodes.size(); i++){

            List<StockVO> stockVOList = findStockVOsWithinDay(stockCodes.get(i), start, end);

            double rate = (stockVOList.get(stockVOList.size()-1).close - stockVOList.get(0).close) / stockVOList.get(0).close;

            formativePeriodRate.add(new FormativePeriodRateVO(stockVOList.get(0).code, rate));
        }

        return formativePeriodRate;
    }

    private List<StockVO> findStockVOsWithinDay(String stockCode, LocalDate start, LocalDate end){

        LocalDate thisStart = start;
        LocalDate thisEnd = end;

        List<StockVO> stockVOList = stockMap.get(stockCode);
        List<LocalDate> dates = new ArrayList<>();
        for(int j = 0; j < stockVOList.size(); j++){
            dates.add(stockVOList.get(j).date);
        }

        while(!dates.contains(thisStart) || !dates.contains(thisEnd)){
            if(!dates.contains(thisStart)){
                thisStart = thisStart.plusDays(1);
            }
            if(!dates.contains(thisEnd)){
                thisEnd = thisEnd.minusDays(1);
            }
        }
        int startIndex = dates.indexOf(thisStart);
        int endIndex = dates.indexOf(thisEnd);

        return stockVOList.subList(startIndex, endIndex+1);

    }
}
