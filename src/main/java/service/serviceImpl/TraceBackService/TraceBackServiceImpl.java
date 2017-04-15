package service.serviceImpl.TraceBackService;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import service.StockService;
import service.TraceBackService;
import service.serviceImpl.StockService.StockServiceImpl;
import utilities.enums.TraceBackStrategy;
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


    public TraceBackServiceImpl() {
        stockService = new StockServiceImpl();
    }

    @Override
    public TraceBackVO traceBack(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockPool) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException {

        TraceBackVO traceBackVO = new TraceBackVO();

        //累计基准收益率
        traceBackVO.baseCumulativeReturn = getBase(traceBackCriteriaVO, stockPool);

        //选择策略
        AllTraceBackStrategy traceBackStrategy = TraceBackStrategyFactory.createTraceBackStrategy(stockPool,traceBackCriteriaVO);
        //策略回测
        TraceBackStrategyVO traceBackStrategyVO = traceBackStrategy.traceBack();
        traceBackVO.strategyCumulativeReturn = traceBackStrategyVO.strategyCumulativeReturn;

        //计算持仓详情的基准收益率和超额收益率
        traceBackVO.holdingDetailVOS = calHoldingDetail(traceBackStrategyVO.holdingDetailVOS, traceBackVO.baseCumulativeReturn, traceBackCriteriaVO.holdingPeriod);
        //计算绝对收益周期
        traceBackVO.absoluteReturnPeriodVO = countAbsoluteReturnPeriod(traceBackVO.holdingDetailVOS);
        //计算相对收益周期
        traceBackVO.relativeReturnPeriodVO = countRelativeReturnPeriod(traceBackVO.holdingDetailVOS);

        traceBackVO.certainFormates = findHoldingWithCertainFormate(traceBackCriteriaVO, stockPool);
        traceBackVO.certainHoldings = findFormateWithCertainHolding(traceBackCriteriaVO, stockPool);

        return traceBackVO;
    }

    /**
     * 给定持有期，计算不同形成期下，超额收益和策略胜率的分布信息
     * @param traceBackCriteriaVO 用户所选的回测标准
     * @param stockPool 自选股票池（可能有）
     * @return
     * @throws DateNotWithinException
     * @throws NoDataWithinException
     * @throws IOException
     * @throws DateShortException
     * @throws CodeNotFoundException
     */
    private List<ExcessAndWinRateDistVO> findFormateWithCertainHolding(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockPool) throws DateNotWithinException, NoDataWithinException, IOException, DateShortException, CodeNotFoundException {
        return findBestFormateOrHolding(traceBackCriteriaVO, stockPool, false);
    }

    /**
     * 给定形成期，计算不同持有期下，超额收益和策略胜率的分布信息
     * @param traceBackCriteriaVO 用户所选的回测标准
     * @param stockPool 自选股票池（可能有）
     * @return
     * @throws CodeNotFoundException
     * @throws DateShortException
     * @throws DateNotWithinException
     * @throws NoDataWithinException
     * @throws IOException
     */
    private List<ExcessAndWinRateDistVO>  findHoldingWithCertainFormate(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockPool) throws CodeNotFoundException, DateShortException, DateNotWithinException, NoDataWithinException, IOException {
        return findBestFormateOrHolding(traceBackCriteriaVO, stockPool, true);
    }

    private List<ExcessAndWinRateDistVO> findBestFormateOrHolding(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockPool, boolean certainFormate) throws DateNotWithinException, NoDataWithinException, IOException, DateShortException, CodeNotFoundException {

        List<ExcessAndWinRateDistVO> certainHoldings = new ArrayList<>();

        for(int i = 2; i <= 50; i = i+2){
            ExcessAndWinRateDistVO excessAndWinRateDistVO = new ExcessAndWinRateDistVO();
            //给定形成期
            if(certainFormate){
                //新的持有期
                traceBackCriteriaVO.holdingPeriod = i;
            }
            //给定持有期
            else{
                //新的形成期
                traceBackCriteriaVO.formativePeriod = i;
            }

            TraceBackVO traceBackVO = new TraceBackVO();

            //累计基准收益率
            traceBackVO.baseCumulativeReturn = getBase(traceBackCriteriaVO, stockPool);

            //选择策略
            AllTraceBackStrategy traceBackStrategy = TraceBackStrategyFactory.createTraceBackStrategy(stockPool,traceBackCriteriaVO);
            //策略回测
            TraceBackStrategyVO traceBackStrategyVO = traceBackStrategy.traceBack();
            traceBackVO.strategyCumulativeReturn = traceBackStrategyVO.strategyCumulativeReturn;

            //计算持仓详情的基准收益率和超额收益率
            traceBackVO.holdingDetailVOS = calHoldingDetail(traceBackStrategyVO.holdingDetailVOS, traceBackVO.baseCumulativeReturn, traceBackCriteriaVO.holdingPeriod);
            //计算相对收益周期
            traceBackVO.relativeReturnPeriodVO = countRelativeReturnPeriod(traceBackVO.holdingDetailVOS);

            //相对强弱计算周期
            excessAndWinRateDistVO.relativeCycle = i;
            //总超额收益
            double strategyLastRate = traceBackVO.strategyCumulativeReturn.get(traceBackVO.strategyCumulativeReturn.size()-1).cumulativeReturn;
            double baseLastRate = traceBackVO.baseCumulativeReturn.get(traceBackVO.baseCumulativeReturn.size()-1).cumulativeReturn;
            excessAndWinRateDistVO.excessRate = strategyLastRate - baseLastRate;
            //策略胜率
            excessAndWinRateDistVO.winRate = traceBackVO.relativeReturnPeriodVO.winRate;

            certainHoldings.add(excessAndWinRateDistVO);
        }

        return certainHoldings;
    }

    /**
     * 计算绝对收益周期
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

        for(int i = 0; i < holdingDetailVOS.size(); i++){
            //为正
            if (holdingDetailVOS.get(i).strategyReturn > 0){
                //向上取整
                double rate = Math.ceil(holdingDetailVOS.get(i).strategyReturn*100);
                if(positiveNums.containsKey(rate)){
                    positiveNums.put(rate,positiveNums.get(rate)+1);
                }
                else {
                    positiveNums.put(rate,1);
                }

                //正周期数+1
                positivePeriodsNum++;
            }
            //为负
            else{
                if(holdingDetailVOS.get(i).strategyReturn < 0){
                    //向下去整
                    double rate = Math.floor(holdingDetailVOS.get(i).strategyReturn*100);
                    if(negativeNums.containsKey(rate)){
                        negativeNums.put(rate,negativeNums.get(rate)+1);
                    }
                    else {
                        negativeNums.put(rate,1);
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

        for(int i = 0; i < holdingDetailVOS.size(); i++){

            //相对收益率为正
            if (holdingDetailVOS.get(i).excessReturn > 0){
                //向上取整
                double rate = Math.ceil(holdingDetailVOS.get(i).excessReturn*100);
                if(positiveNums.containsKey(rate)){
                    positiveNums.put(rate,positiveNums.get(rate)+1);
                }
                else {
                    positiveNums.put(rate,1);
                }

                //正周期数+1
                positivePeriodsNum++;
            }
            //为负
            else{
                if(holdingDetailVOS.get(i).excessReturn < 0){
                    //向下去整
                    double rate = Math.floor(holdingDetailVOS.get(i).excessReturn*100);
                    if(negativeNums.containsKey(rate)){
                        negativeNums.put(rate,negativeNums.get(rate)+1);
                    }
                    else {
                        negativeNums.put(rate,1);
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

    /**
     * 计算基准收益率和超额收益率
     * @param holdingDetailVOS     只有策略收益率的持仓详情
     * @param baseCumulativeReturn 累计基准收益率
     * @param holdPeriod
     * @return
     */
    private List<HoldingDetailVO> calHoldingDetail(List<HoldingDetailVO> holdingDetailVOS, List<CumulativeReturnVO> baseCumulativeReturn, int holdPeriod) {

        double initInvestment = 1000;
        double remainInvestment = initInvestment;

        int holdingSerial = 0;

        for(int i = 0; i < baseCumulativeReturn.size();){

            double lastRate = 0;

            if ((i + holdPeriod -1) < baseCumulativeReturn.size()){
                // 一个持仓周期最后一天的累计收益率
                lastRate = baseCumulativeReturn.get(i).cumulativeReturn;
            }
            //最后一个周期可能不满持仓周期数
            else {
                lastRate = baseCumulativeReturn.get(baseCumulativeReturn.size()-1).cumulativeReturn;
            }
            //保存之前的投资资金
            double preRemainInvestment = remainInvestment;

            //更新剩余资金
            remainInvestment = remainInvestment * (1+lastRate);

            //当前持仓起的基准收益率
            double baseReturn = (remainInvestment - preRemainInvestment) / preRemainInvestment;
            holdingDetailVOS.get(holdingSerial).baseReturn = baseReturn;
            //超额收益率
            holdingDetailVOS.get(holdingSerial).excessReturn = holdingDetailVOS.get(holdingSerial).strategyReturn - baseReturn;

            i = i+holdPeriod;
        }

        return holdingDetailVOS;
    }

    /**
     *
     * @param traceBackCriteriaVO 回测标准
     * @param stockPool   自选股票池的所有股票的代号
     * @return
     * @throws IOException
     * @throws NoDataWithinException
     * @throws DateNotWithinException
     */
    private List<CumulativeReturnVO> getBase(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockPool) throws IOException, NoDataWithinException, DateNotWithinException {

        LocalDate start = traceBackCriteriaVO.startDate;
        LocalDate end = traceBackCriteriaVO.endDate;

        if(traceBackCriteriaVO.isCustomized){
            return getCumulativeReturnOfOneStock(traceBackCriteriaVO.baseStockName, start, end);
        }
        else{
            return getCustomizedCumulativeReturn(start, end, stockPool);
        }
    }

    @Override
    public List<CumulativeReturnVO> getStrategyCumulativeReturn(TraceBackCriteriaVO traceBackCriteriaVO) throws DateNotWithinException, NoDataWithinException, IOException, DateShortException, CodeNotFoundException {
        return null;
    }

    @Override
    public List<CumulativeReturnVO> getBaseCumulativeReturn(LocalDate start, LocalDate end, String stockName) throws IOException, NoDataWithinException, DateNotWithinException {
        return null;
    }


    //TODO gcm 看看自选股和非自选股可否分开两个类，帮忙看
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

    @Override
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
     *
     * @param stockName 单一股票的信息
     * @param start 起始日期
     * @param end   结束日期
     * @return List<CumulativeReturnVO> 单一股票在时间区间内的累计收益率
     */
    private List<CumulativeReturnVO> getCumulativeReturnOfOneStock(String stockName, LocalDate start, LocalDate end) throws DateNotWithinException, NoDataWithinException, IOException {

        List<StockVO> list = stockService.getBaseStockData(stockName,start,end);

        List<CumulativeReturnVO> cumulativeReturnVOS = new ArrayList<CumulativeReturnVO>();

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


}
