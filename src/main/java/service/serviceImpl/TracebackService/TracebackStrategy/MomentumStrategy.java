package service.serviceImpl.TracebackService.TracebackStrategy;

import service.StockService;
import service.StockTradingDayService;
import service.TracebackService;
import service.serviceImpl.StockService.StockServiceImpl;
import service.serviceImpl.StockTradingDayServiceImpl;
import service.serviceImpl.TracebackService.AllTracebackStrategy;
import service.serviceImpl.TracebackService.TracebackServiceImpl;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.NoDataWithinException;
import vo.CumulativeReturnVO;
import vo.StockVO;
import vo.TracebackCriteriaVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;

/**
 * Created by harvey on 17-3-31.
 */
public class MomentumStrategy implements AllTracebackStrategy {

    StockService stockService;
    TracebackService tracebackService;
    StockTradingDayService stockTradingDayService;

    //千元投资
    final double initInvestment = 1000;

    //当前的剩余投资
    double cumulativeReturn;

    public MomentumStrategy() {
        stockService = new StockServiceImpl();
        tracebackService = new TracebackServiceImpl();
        stockTradingDayService = new StockTradingDayServiceImpl();
        cumulativeReturn = initInvestment;
    }

    /**
     * 根据目标股票池及所给的标准，返回策略的累计收益率
     *
     * @param stockPoolCodes      目标股票池所有股票的代码
     * @param tracebackCriteriaVO 回测的所有标准
     * @return List<CumulativeReturnVO> 策略的累计收益率
     */
    @Override
    public List<CumulativeReturnVO> traceback(List<String> stockPoolCodes, TracebackCriteriaVO tracebackCriteriaVO) throws IOException, NoDataWithinException, DateNotWithinException {

        List<CumulativeReturnVO> cumulativeReturnVOS = new ArrayList<CumulativeReturnVO>();

        //当前所持有的股票
        List<String> holdingStocks = new ArrayList<String>();

        //回测区间,从用户所选区间里再选出第一个交易日和最后一个交易日
        LocalDate start = stockTradingDayService.getNextTradingDay(tracebackCriteriaVO.startDate,stockPoolCodes);
        LocalDate end = stockTradingDayService.getNextTradingDay(tracebackCriteriaVO.endDate,stockPoolCodes);

        int tradingDays = stockTradingDayService.getTradingDays(start,end,stockPoolCodes);

        //形成期
        int formativePeriod = tracebackCriteriaVO.formativePeriod;
        //持有期
        int holdingPeriod = tracebackCriteriaVO.holdingPeriod;

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



        //第一个形成期的起始日期
        LocalDate startOfFormative = stockTradingDayService.getTradingDayMinus(start, formativePeriod, stockPoolCodes);
        //第一个形成期的结束日期
        LocalDate endOfFormative = stockTradingDayService.getLastTradingDay(start.minusDays(1),stockPoolCodes);

        //第一个持有期的起始日期
        LocalDate startOfHolding = start;
        for (int i = 0; i < periodNum; i++ ){

            //持有期所持有的股票
            holdingStocks = pickStocks(formate(stockPoolCodes,startOfFormative,endOfFormative));

            //持有期的结束日期，根据持有期的起始日期进行计算
            LocalDate endOfHolding = stockTradingDayService.getTradingDayPlus(startOfHolding,holdingPeriod,holdingStocks);

            //最后一个周期，直接以最后一个交易日为最后一天
            if(i == periodNum-1){
                endOfHolding = end;
            }


            List<CumulativeReturnVO> cumulatives = computeHoldingPeriod(holdingStocks, start, end);
            //第一个周期，第一天的累计收益率置为0
            if(i == 0){
                cumulatives.get(0).cumulativeReturn = 0;
            }

            cumulativeReturnVOS.addAll(computeHoldingPeriod(holdingStocks, start, endOfHolding));
        }

        return cumulativeReturnVOS;
    }

    /**
     * 计算当前持有期所持有的股票在当前的持有期内每一天相对于回测区间起始日期的总的累计收益率
     * @param holdingStocks 当前持有期所持有的股票
     * @param start 持有期的起始日期的前一个交易日,由于第一天相对第一天为0,则应多往前计算一天,
     * @param end 持有期的结束日期，也为交易日
     * @return
     */
    private List<CumulativeReturnVO> computeHoldingPeriod(List<String> holdingStocks, LocalDate start, LocalDate end) throws DateNotWithinException, NoDataWithinException, IOException {

        //保存每个持有期中，所持有股票的相对于持有期起始日期的累计收益率
        List<CumulativeReturnVO> dailyTotalCumulativeReturn = tracebackService.getCustomizedCumulativeReturn(start,end,holdingStocks);

        List<CumulativeReturnVO> cumulativeReturn = new ArrayList<CumulativeReturnVO>();

        //从1开始计数，因为日期多往前算了一天
        for(int i = 1; i < dailyTotalCumulativeReturn.size(); i++){
            LocalDate date = dailyTotalCumulativeReturn.get(i).currentDate;
            double rate = dailyTotalCumulativeReturn.get(i).cumulativeReturn;

            this.cumulativeReturn = this.cumulativeReturn *(1+rate);
            cumulativeReturn.add(new CumulativeReturnVO(date, (rate - initInvestment) / initInvestment, false));
        }

        return cumulativeReturn;
    }

    /**
     * 对股票排序并挑选股票购买
     * @param formativePeriodRate 形成期内，目标股票池所有股票的代码与累计收益率的键值对
     * @return 选取前20%的股票购买 //TODO 目前挑选股票的参数，暂定为取前20%，不知后期是否要做活一点儿
     */
    private List<String> pickStocks(Map<String, Double> formativePeriodRate) {

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

    /**
     * 对形成期的目标股票池进行排序
     * @param formativePeriodRate 目标股票池的股票代码与形成期的累计收益率的键值对
     * @return 经排序后的目标股票池的所有股票的代码 //TODO gcm 排序策略？这里直接按降序排序
     */
    private List<String> sortStocks(Map<String, Double> formativePeriodRate) {
        List<String> sortedStockCodes = new ArrayList<String>();

        for(Map.Entry<String, Double> entry: formativePeriodRate.entrySet()){

            //判断是否已经插入
            boolean isInsert = false;

            for(int i = 0; i < sortedStockCodes.size(); i++){
                if(sortedStockCodes.size() == 0){
                    sortedStockCodes.add(entry.getKey());
                }
                else {
                    if(entry.getValue() > formativePeriodRate.get(sortedStockCodes.get(i))){
                        sortedStockCodes.add(i,entry.getKey());
                        isInsert = true;
                    }
                }
            }

            //未插入，说明最小，则加在最后
            if(!isInsert){
                sortedStockCodes.add(entry.getKey());
            }
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
    private Map<String,Double> formate(List<String> stockCodes, LocalDate start, LocalDate end){

        Map<String,Double> formativePeriodRate = new TreeMap<String, Double>();

        for(int i = 0; i < stockCodes.size(); i++){
            formativePeriodRate.computeIfAbsent(stockCodes.get(i), new Function<String, Double>() {
                /**
                 * 根据股票代码及形成期的起始时间，计算该股票的累计收益率
                 * @param stockCode 股票代码
                 * @return Double 该股票形成期内的累计收益率
                 */
                @Override
                public Double apply(String stockCode) {
                    //因为该股票可能在形成期起始或者结束日期停牌，故寻找此区间内该股票的起始和停牌日期
                    LocalDate thisStockStartDay = null;
                    try {
                        thisStockStartDay = stockTradingDayService.getNextTradingDay(start,stockCode);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    LocalDate thisStockEndDay = null;
                    try {
                         thisStockEndDay = stockTradingDayService.getLastTradingDay(end,stockCode);
                    } catch (IOException e) {
                    }

                    StockVO startVO =  stockService.getOneStockDataOneDay(stockCode, thisStockStartDay);
                    StockVO endVO = stockService.getOneStockDataOneDay(stockCode, thisStockEndDay);

                    return (endVO.close - startVO.close) / startVO.close;
                }
            });
        }

        return formativePeriodRate;
    }
}
