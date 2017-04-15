package service.serviceImpl.TraceBackService.TraceBackStrategy.Momentum;

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
    StockTradingDayService stockTradingDayService;

    //初始投资
    double initInvestment;

    //剩余投资
    double remainInvestment;


    public MomentumStrategy(List<String> stockPoolCodes, TraceBackCriteriaVO traceBackCriteriaVO) {
        super(stockPoolCodes,traceBackCriteriaVO);

        stockService = new StockServiceImpl();
        traceBackService = new TraceBackServiceImpl();
        stockTradingDayService = new StockTradingDayServiceImpl();

        //初始为千元投资
        initInvestment = 1000;
        remainInvestment = initInvestment;
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
        LocalDate start = stockTradingDayService.getNextTradingDay(traceBackCriteriaVO.startDate,stockPoolCodes);
        LocalDate end = stockTradingDayService.getLastTradingDay(traceBackCriteriaVO.endDate,stockPoolCodes);

        int tradingDays = stockTradingDayService.getTradingDays(start,end,stockPoolCodes);

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

        //第一个持有期的起始日期
        LocalDate startOfHolding = start;

        for (int i = 0; i < periodNum; i++ ){

            //形成期的起始日期
            LocalDate startOfFormative = stockTradingDayService.getTradingDayMinus(startOfHolding, formativePeriod, stockPoolCodes);
            //形成期的结束日期
            LocalDate endOfFormative = stockTradingDayService.getLastTradingDay(startOfHolding.minusDays(1),stockPoolCodes);

            //持有期所持有的股票
            holdingStocks = pickStocks(formate(stockPoolCodes,startOfFormative,endOfFormative));

            //持有期的结束日期，根据持有期的起始日期进行计算
            LocalDate endOfHolding = stockTradingDayService.getTradingDayPlus(startOfHolding,holdingPeriod-1,holdingStocks);

            //最后一个周期，直接以最后一个交易日为最后一天
            if(i == periodNum-1){
                endOfHolding = end;
            }

            //多往前计算一天
            LocalDate startOfHoldingMinusOne = stockTradingDayService.getLastTradingDay(startOfHolding.minusDays(1), holdingStocks);

            //保存每个持有期中，所持有股票的相对于持有期起始日期的累计收益率
            List<CumulativeReturnVO> dailyTotalCumulativeReturn = traceBackService.getCustomizedCumulativeReturn(startOfHoldingMinusOne,endOfHolding,holdingStocks);

            List<CumulativeReturnVO> cumulatives = computeHoldingPeriod(dailyTotalCumulativeReturn);

            double lastRate = dailyTotalCumulativeReturn.get(dailyTotalCumulativeReturn.size()-1).cumulativeReturn;

            HoldingDetailVO holdingDetailVO = computeHoldingDetailVO(lastRate, startOfHolding, endOfHolding);
            holdingDetailVOS.add(holdingDetailVO);

            //第一个周期，第一天的累计收益率置为0
            if(i == 0){
                cumulatives.get(0).cumulativeReturn = 0;
            }

            cumulativeReturnVOS.addAll(cumulatives);

            //更新持有期的开始日期
            startOfHolding = stockTradingDayService.getNextTradingDay(endOfHolding.plusDays(1),holdingStocks);
        }

        TraceBackStrategyVO traceBackStrategyVO = new TraceBackStrategyVO(maxRetracement(cumulativeReturnVOS),holdingDetailVOS);

        return traceBackStrategyVO;
    }

    /**
     * 形成期／N日均值，用于后续策略筛选
     *
     * @param stockCodes      股票列表
     * @param periodStart     持有期起始日期
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
        remainInvestment = remainInvestment * (1+lastRate);
        //当前持仓期剩余投资资金
        curHoldingPeriod.remainInvestment = remainInvestment;

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

        List<FormativePeriodRateVO> formativePeriodRate = new ArrayList<FormativePeriodRateVO>();

        for(int i = 0; i < stockCodes.size(); i++){

            //TODO gcm 这里是否以形成期的前一天为基准，还是以形成期的第一天为基准
            List<StockVO> stockVOS = stockService.getOneStockData(stockCodes.get(i), start, end);

            double rate = (stockVOS.get(stockVOS.size()-1).close - stockVOS.get(0).close) / stockVOS.get(0).close;

            formativePeriodRate.add(new FormativePeriodRateVO(stockVOS.get(0).code, rate));
        }

        return formativePeriodRate;
    }
}
