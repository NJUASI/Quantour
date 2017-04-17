package service.serviceImpl.TraceBackService.TraceBackStrategy.Momentum;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
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

    //累计收益率
    double curCumulativeReturn;

    //所有有数据的日期
    List<LocalDate> allDatesWithData = new ArrayList<>();

    //所选股票池的代码和它的所有信息的映射
    Map<String, List<StockVO>> stockMap = new TreeMap<>();


    public MomentumStrategy(List<String> stockPoolCodes, TraceBackCriteriaVO traceBackCriteriaVO) throws IOException, DateNotWithinException, NoDataWithinException {
        super(stockPoolCodes,traceBackCriteriaVO);

        stockService = new StockServiceImpl();
        stockDao = new StockDaoImpl();
        traceBackService = new TraceBackServiceImpl();
        stockTradingDayService = new StockTradingDayServiceImpl();

        //初始为千元投资
        initInvestment = 1000;

        //累计收益率初始化为1
        curCumulativeReturn = 1;


        setStockMap();
        //设置持有期和形成期有数据的日期
        setDates();

    }

    private void setStockMap() throws IOException, NoDataWithinException, DateNotWithinException {
        for(int i = 0; i < stockPoolCodes.size(); i++){
            stockMap.put(stockPoolCodes.get(i), stockService.getStockData(stockPoolCodes.get(i)));
        }
    }

    /**
     * 设置持有其和形成期有数据的日期
     */
    private void setDates() throws IOException {

        //获取所有有数据的日期
        allDatesWithData = stockDao.getDateWithData();
    }

    /**
     * 根据目标股票池及所给的标准，返回策略的累计收益率
     *
     * @return List<CumulativeReturnVO> 策略的累计收益率
     */
    @Override
    public TraceBackStrategyVO traceBack() throws IOException, NoDataWithinException, DateNotWithinException {

        List<CumulativeReturnVO> cumulativeReturnVOS = new ArrayList<CumulativeReturnVO>();

        //区间第一个交易日在allDatesWithData中的位置
        int startIndex = -1;
        //区间最后一个交易日在allDatesWithData中的位置
        int endIndex = -1;

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
        startIndex = allDatesWithData.indexOf(thisStart);
        endIndex = allDatesWithData.indexOf(thisEnd);

        //当前所持有的股票
        List<String> holdingStocks = new ArrayList<String>();

        //回测区间,从用户所选区间里再选出第一个交易日和最后一个交易日
        LocalDate end = allDatesWithData.get(endIndex);

        //区间内交易日的天数，包含两头
        int tradingDays = endIndex - startIndex + 1;

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
            LocalDate startOfHolding = allDatesWithData.get(startIndex+i*holdingPeriod);

            //形成期的结束日期
            LocalDate endOfFormative = allDatesWithData.get(startIndex+i*holdingPeriod-1);
            //形成期的起始日期
            LocalDate startOfFormative = allDatesWithData.get(startIndex+i*holdingPeriod-formativePeriod);

            System.out.println("periodNum:  "+i);

            //持有期所持有的股票
            holdingStocks = pickStocks(formate(stockPoolCodes,startOfFormative,endOfFormative));

            //持有期的结束日期
            LocalDate endOfHolding = null;
            //最后一个周期，直接以最后一个交易日为最后一天
            if(i == periodNum-1){
                endOfHolding = end;
            }
            else {
                endOfHolding = allDatesWithData.get(startIndex+(i+1)*holdingPeriod-1);
            }

            //计算一个持仓期中每天相对于回测区间第一天的累计收益率 以及每个持仓期详情

            //保存先前一个周期最后一天的累计收益率
            double preCumulativeReturn = curCumulativeReturn;
            LocalDate temp = startOfHolding;
            while(temp.isBefore(endOfHolding) || temp.isEqual(endOfHolding)){
                double totalCumulativeReturn = 0;
                int notSuspend = 0;
                for(int j = 0; j < holdingStocks.size(); j++){
                    StockVO vo = findStockCertainDay(holdingStocks.get(j), temp);
                    //当天有数据
                    if(vo != null){
                        totalCumulativeReturn += (vo.close/vo.preClose - 1);
                        notSuspend += 1;
                    }
                }

                //即该天相对于前一天的总累计收益率不为0
                if(totalCumulativeReturn != 0){
                    curCumulativeReturn = curCumulativeReturn * (1 + (totalCumulativeReturn / notSuspend));
                }

                cumulativeReturnVOS.add(new CumulativeReturnVO(temp, curCumulativeReturn-1, false));

                temp = temp.plusDays(1);
            }

            double thisPeriodReturn = curCumulativeReturn / preCumulativeReturn - 1;
            holdingDetailVOS.add(new HoldingDetailVO(periodNum+1, startOfHolding, endOfHolding, holdingStocks.size(), thisPeriodReturn, curCumulativeReturn*initInvestment));

        }

        //将第一天的累计收益率初始化为0
        cumulativeReturnVOS.get(0).cumulativeReturn = 0;
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
            //说明为该形成期没有数据
            if(stockVOList.size() == 0){
                continue;
            }

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

    private StockVO findStockCertainDay(String stockCode, LocalDate date){
        LocalDate thisDate = date;

        List<StockVO> stockVOList = stockMap.get(stockCode);
        List<LocalDate> dates = new ArrayList<>();
        for(int j = 0; j < stockVOList.size(); j++){
            dates.add(stockVOList.get(j).date);
        }

        while(!dates.contains(thisDate)){
            thisDate = thisDate.plusDays(1);
        }
        int dateIndex = dates.indexOf(thisDate);

        //该股票当天没有数据
        if(dateIndex == -1){
            return null;
        }
        return stockVOList.get(dateIndex);
    }
}
