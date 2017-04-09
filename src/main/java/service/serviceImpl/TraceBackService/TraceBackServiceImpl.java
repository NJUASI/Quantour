package service.serviceImpl.TraceBackService;

import service.StockService;
import service.StockTradingDayService;
import service.TraceBackService;
import service.serviceImpl.StockService.StockServiceImpl;
import service.serviceImpl.StockTradingDayServiceImpl;
import utilities.exceptions.CodeNotFoundException;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.DateShortException;
import utilities.exceptions.NoDataWithinException;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by harvey on 17-3-28.
 */
public class TraceBackServiceImpl implements TraceBackService {

    private StockService stockService;

    private StockTradingDayService stockTradingDayService;

    //保存基准股票列表
    private List<String> baseStockCodes;

    //保存基准累计收益率
    private List<CumulativeReturnVO> baseCumulativeReturn;

    //保存策略累计收益率
    private List<CumulativeReturnVO> strategyCumulativeReturn;

    //保存历史持仓详情
    private List<HoldingDetailVO> holdingDetailVOS;

    //起始投资
    double initInvestment;

    //剩余投资（基准）
    double remainInvestmentBase;

    //剩余投资(策略)
    double remainInvestmentStrategy;


    public TraceBackServiceImpl() {
        stockService = new StockServiceImpl();
        stockTradingDayService = new StockTradingDayServiceImpl();

        strategyCumulativeReturn = new ArrayList<CumulativeReturnVO>();
        baseCumulativeReturn = new ArrayList<CumulativeReturnVO>();

        holdingDetailVOS = new ArrayList<HoldingDetailVO>();

        //初始为1000
        initInvestment = 1000;
        remainInvestmentBase = initInvestment;
        remainInvestmentStrategy = initInvestment;

    }

    //TODO gcm 看看自选股和非自选股可否分开两个类，帮忙看
    /**
     * 获取策略累计收益率
     *
     * @param traceBackCriteriaVO 用户所选回测条件
     * @return List<CumulativeReturnVO> 策略累计收益率的列表
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public List<CumulativeReturnVO> getStrategyCumulativeReturn(TraceBackCriteriaVO traceBackCriteriaVO) throws DateNotWithinException, NoDataWithinException, IOException, DateShortException, CodeNotFoundException {
        //TODO gcm 需要下面的一个接口 还未实现

        //获取目标股票池
        List<String> stockPool = stockService.getStockPool(traceBackCriteriaVO.stockPoolVO);

        return strategyCumulativeReturn;
    }

    /**
     * 获取基准累计收益率,非自选股
     *
     * @param start 回测区间起始日期
     * @param end 回测区间结束日期
     * @param stockName 基准股票的名称
     * @return List<CumulativeReturnVO> 基准累计收益率的列表
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public List<CumulativeReturnVO> getBaseCumulativeReturn(LocalDate start, LocalDate end, String stockName) throws IOException, NoDataWithinException, DateNotWithinException {

        List<StockVO> baseStock = stockService.getBaseStockData(stockName,start,end);

        //TODO gcm 方法搬家
        baseStockCodes.add(baseStock.get(0).code);

        return baseCumulativeReturn;
    }

    /**
     * 获取基准累计收益率，自选股
     *
     * @param start 回测区间起始日期
     * @param end 回测区间结束日期
     * @param stockCodes 所有自选股的代码
     * @return List<CumulativeReturnVO> 基准累计收益率的列表
     */
    @Override
    public List<CumulativeReturnVO> getCustomizedCumulativeReturn(LocalDate start, LocalDate end, List<String> stockCodes) throws IOException, NoDataWithinException, DateNotWithinException {

        List<CumulativeReturnVO> cumulativeReturnVOS = new ArrayList<CumulativeReturnVO>();
        List<Map<LocalDate,CumulativeReturnVO>> everyCumulativeReturnVOs = new ArrayList<Map<LocalDate,CumulativeReturnVO>>();

        int span = start.until(end).getDays()+1;

        //添加第一天的数据，为0;
        cumulativeReturnVOS.add(new CumulativeReturnVO(start,0,false));

        //将每一支股票的信息添加进列表
        for(int i = 0; i < stockCodes.size(); i++){
            //每一支股票在日期范围内的累计收益率
            List<StockVO> list = stockService.getOneStockData(stockCodes.get(i),start,end);
            everyCumulativeReturnVOs.add(getCumulativeReturnOfOneStockMap(list,span));
        }


        for(int i = 1; i < span; i++){

            double totalCumulativeReturn = 0;
            int notSuspended = 0;

            for (int j = 0; j < everyCumulativeReturnVOs.size(); j++){
                if(everyCumulativeReturnVOs.get(j).containsKey(start.plusDays(i))){
                    totalCumulativeReturn += everyCumulativeReturnVOs.get(j).get(start.plusDays(i)).cumulativeReturn;
                    notSuspended += 1;
                }
            }
            //未停牌的股票支数不为0,则说明当天有数据
            if(notSuspended != 0){
                cumulativeReturnVOS.add(new CumulativeReturnVO(start.plusDays(i),totalCumulativeReturn/notSuspended,false));
            }
        }

        return cumulativeReturnVOS;
    }

    /**
     * 获取每一支股票的日期与累计收益率的map，日期作为键值
     * @param list 日期范围内的一支股票的信息
     * @param span 日期范围
     * @return 每天日期所对应的股票的累计收益率
     */
    private Map<LocalDate,CumulativeReturnVO> getCumulativeReturnOfOneStockMap(List<StockVO> list, int span) {
        Map<LocalDate,CumulativeReturnVO> map = new TreeMap<LocalDate,CumulativeReturnVO>();

        //TODO gcm 将第一天的数据加入进去,查询果仁网，看第一天的日期是以交易日为准，还是以用户的选择为准
        CumulativeReturnVO firstDay = new CumulativeReturnVO(list.get(0).date,0,false);
        map.put(list.get(0).date,firstDay);

        //累计收益率以第一个交易日的收益率来对比计算
        double closeOfFirstDay = list.get(0).close;

        for(int i = 1; i < list.size(); i++) {
            double sucClose = list.get(i).close;
            double cumulativeReturn = (sucClose - closeOfFirstDay) / closeOfFirstDay;
            //先将所有的最大回测点设为false
            map.put(list.get(i).date,new CumulativeReturnVO(list.get(i).date, cumulativeReturn, false));
        }

        return map;
    }

    /**
     * 计算回测中用列表列出的数值型数据，如阿尔法，beta
     *
     * @param traceBackCriteriaVO 用户所选回测条件
     * @return TraceBackNumValVO 所需的所有数值型数据保存对象
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public TraceBackNumValVO getNumericalVal(TraceBackCriteriaVO traceBackCriteriaVO) {
        return null;
    }

    /**
     *
     * @param list 单一股票的信息
     * @param start 因为起始日期可能不是交易日，但是还是以起始日期为准 //TODO 在日期上，先把周末的日期挖掉,不让用户可以选择周末的日期
     * @return List<CumulativeReturnVO> 单一股票在时间区间内的累计收益率
     */
    private List<CumulativeReturnVO> getCumulativeReturnOfOneStock(List<StockVO> list,LocalDate start){

        List<CumulativeReturnVO> cumulativeReturnVOS = new ArrayList<CumulativeReturnVO>();

        //TODO gcm 将第一天的数据加入进去,查询果仁网，看第一天的日期是以交易日为准，还是以用户的选择为准
        CumulativeReturnVO firstDay = new CumulativeReturnVO(start,0,false);
        cumulativeReturnVOS.add(firstDay);

        //累计收益率以第一个交易日的收益率来对比计算
        double closeOfFirstDay = list.get(0).close;

        for(int i = 1; i < list.size(); i++) {
            double sucClose = list.get(i).close;
            double cumulativeReturn = (sucClose - closeOfFirstDay) / closeOfFirstDay;
            //先将所有的最大回测点设为false
            cumulativeReturnVOS.add(new CumulativeReturnVO(list.get(i).date, cumulativeReturn, false));
        }

        return cumulativeReturnVOS;
    }

    /**
     *
     * @param stockPoolCodes
     * @param traceBackCriteriaVO
     * @return
     * @throws IOException
     * @throws NoDataWithinException
     * @throws DateNotWithinException
     */
    public TraceBackVO computeCumulativeReturn(List<String> stockPoolCodes, TraceBackCriteriaVO traceBackCriteriaVO) throws IOException, NoDataWithinException, DateNotWithinException {

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
            startOfHolding = stockTradingDayService.getLastTradingDay(startOfHolding.minusDays(1), holdingStocks);

            //持有期中，所有基准股票的相对于持有期起始日期的累计收益率
            List<CumulativeReturnVO> periodBaseCumulativeReturn = getCustomizedCumulativeReturn(startOfHolding,endOfHolding,baseStockCodes);

            //持有期中，所持有股票的相对于持有期起始日期的累计收益率
            List<CumulativeReturnVO> periodStrategyCumulativeReturn = getCustomizedCumulativeReturn(startOfHolding,endOfHolding,holdingStocks);

            List<CumulativeReturnVO> strategyCumulatives = computeHoldingPeriod(periodStrategyCumulativeReturn, startOfHolding, endOfHolding);

            //计算一次持仓周期详情
            HoldingDetailVO holdingDetailVO = computeHoldingDetailVO(periodBaseCumulativeReturn, periodBaseCumulativeReturn, startOfHolding, endOfHolding, i+1);

            //第一个周期，第一天的累计收益率置为0
            if(i == 0){
                strategyCumulatives.get(0).cumulativeReturn = 0;
            }

            //将计算结果添加进成员变量
            holdingDetailVOS.add(holdingDetailVO);
            strategyCumulativeReturn.addAll(strategyCumulatives);

            //更新持有期的开始日期
            startOfHolding = stockTradingDayService.getNextTradingDay(endOfHolding.plusDays(1),holdingStocks);
        }


        return null;
    }

    /**
     * 计算一个持仓期的持仓详情
     * @param periodBaseCumulativeReturn 当前持有期所持有股票每天的累计收益率
     * @param start 持有期的起始日期的前一个交易日,由于第一天相对第一天为0,则应多往前计算一天
     * @param end 持有期的结束日期，也为交易日
     * @return HoldingDetailVO  持仓期的持仓详情
     */
    private HoldingDetailVO computeHoldingDetailVO(List<CumulativeReturnVO> periodBaseCumulativeReturn, List<CumulativeReturnVO> periodStrategyCumulativeReturn, LocalDate start, LocalDate end, int serial){

        //保存当前持仓期详情信息
        HoldingDetailVO curHoldingPeriod = new HoldingDetailVO();
        curHoldingPeriod.periodSerial = serial;
        curHoldingPeriod.startDate = start;
        curHoldingPeriod.endDate = end;

        //保存之前的投资资金
        double preRemainInvestment = remainInvestmentStrategy;
        //持仓期最后一天的累计收益率
        double lastRate = periodStrategyCumulativeReturn.get(periodStrategyCumulativeReturn.size()-1).cumulativeReturn;
        //更新剩余资金
        remainInvestmentStrategy = remainInvestmentStrategy * (1+lastRate);
        //当前持仓期剩余投资资金
        curHoldingPeriod.remainInvestment = remainInvestmentStrategy;

        //当前持仓期的策略收益
        curHoldingPeriod.strategyReturn = (preRemainInvestment - remainInvestmentStrategy) / remainInvestmentStrategy;

        return curHoldingPeriod;
    }

    /**
     * 对股票排序并挑选股票购买
     * @param formativePeriodRate 形成期内，目标股票池所有股票的代码与累计收益率的列表
     * @return 选取前20%的股票购买 //TODO 目前挑选股票的参数，暂定为取前20%，不知后期是否要做活一点儿
     */
    private List<String> pickStocks(List<FormativePeriodRateVO> formativePeriodRate) {

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

    /**
     * 计算当前持有期所持有的股票在当前的持有期内每一天相对于回测区间起始日期的总的累计收益率
     * @param dailyTotalCumulativeReturn 当前持有期所持有股票每天的累计收益率
     * @param start 持有期的起始日期的前一个交易日,由于第一天相对第一天为0,则应多往前计算一天,
     * @param end 持有期的结束日期，也为交易日
     * @return
     */
    private List<CumulativeReturnVO> computeHoldingPeriod(List<CumulativeReturnVO> dailyTotalCumulativeReturn, LocalDate start, LocalDate end) throws DateNotWithinException, NoDataWithinException, IOException {

        List<CumulativeReturnVO> cumulativeReturn = new ArrayList<CumulativeReturnVO>();

        //从1开始计数，因为日期多往前算了一天
        for(int i = 1; i < dailyTotalCumulativeReturn.size(); i++){
            LocalDate date = dailyTotalCumulativeReturn.get(i).currentDate;
            double rate = dailyTotalCumulativeReturn.get(i).cumulativeReturn;

            double remainInvestment = this.remainInvestmentStrategy;
            remainInvestment = remainInvestment *(1+rate);
            cumulativeReturn.add(new CumulativeReturnVO(date, (remainInvestment - initInvestment) / initInvestment, false));
        }

        return cumulativeReturn;
    }

}
