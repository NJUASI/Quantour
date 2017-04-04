package service.serviceImpl.TracebackService;

import service.StockService;
import service.TracebackService;
import service.serviceImpl.StockService.StockPoolFilter;
import service.serviceImpl.StockService.StockServiceImpl;
import utilities.enums.TracebackStrategy;
import utilities.exceptions.DateNotWithinException;
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
public class TracebackServiceImpl implements TracebackService {

    private StockService stockService;

    public TracebackServiceImpl() {
        stockService = new StockServiceImpl();
    }


    //TODO gcm 看看自选股和非自选股可否分开两个类，帮忙看
    /**
     * 获取策略累计收益率
     *
     * @param tracebackCriteriaVO 用户所选回测条件
     * @return List<CumulativeReturnVO> 策略累计收益率的列表
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public List<CumulativeReturnVO> getStrategyCumulativeReturn(TracebackCriteriaVO tracebackCriteriaVO) throws DateNotWithinException, NoDataWithinException, IOException {
        //TODO gcm 需要下面的一个接口 还未实现

        //获取目标股票池
        List<String> stockPool = stockService.getStockPool(tracebackCriteriaVO.stockPoolVO);

        //确定策略
        AllTracebackStrategy tracebackStrategy = TracebackStrategyFactory.createTracebackStrategy(tracebackCriteriaVO.strategyType);

        //回测
        return tracebackStrategy.traceback(stockPool,tracebackCriteriaVO);
    }

    /**
     * 获取策略累计收益率，自选股票池
     *
     * @param tracebackCriteriaVO 用户所选回测条件
     * @param stockCodes 自选股票池所有股票的代码
     * @return List<CumulativeReturnVO> 策略累计收益率的列表
     */
    @Override
    public List<CumulativeReturnVO> getStrategyCumulativeReturnOfCustomized(TracebackCriteriaVO tracebackCriteriaVO, List<String> stockCodes) throws DateNotWithinException, NoDataWithinException, IOException {

        //确定策略
        AllTracebackStrategy tracebackStrategy = TracebackStrategyFactory.createTracebackStrategy(tracebackCriteriaVO.strategyType);

        //回测
        return tracebackStrategy.traceback(stockCodes,tracebackCriteriaVO);

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

        return maxRetracement(getCumulativeReturnOfOneStock(baseStock,start));
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

        return maxRetracement(cumulativeReturnVOS);
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
     * @param tracebackCriteriaVO 用户所选回测条件
     * @return TracebackNumValVO 所需的所有数值型数据保存对象
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public TracebackNumValVO getNumericalVal(TracebackCriteriaVO tracebackCriteriaVO) {
        return null;
    }

    /**
     * 计算相对收益指数
     *
     * @param tracebackCriteriaVO 用户所选回测条件
     * @return RelativeIndexReturnVO 保存表示相对收益指数的对象，包括正周期数，负周期数和赢率
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public RelativeIndexReturnVO getRelativeIndexReturn(TracebackCriteriaVO tracebackCriteriaVO) {
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
     * 计算最大回撤点
     * @param cumulativeReturnVOS 未计算最大回测的累计收益率
     * @return List<CumulativeReturnVO> 标记了两个最大回撤点的累计收益率，标记点的isTraceBack为true
     */
    private List<CumulativeReturnVO> maxRetracement(List<CumulativeReturnVO> cumulativeReturnVOS){

        //TODO gcm 用了两个循环，不知道怎么改进算法，你们可以帮下忙

        //回撤点的峰值在list中的位置
        int top = 0;
        //回撤点的谷值在list中的位置
        int down = 0;

        //将第一个位置默认为最大回撤值点
        cumulativeReturnVOS.get(0).isTraceBack = true;

        double max = 0;

        for(int i = 0; i < cumulativeReturnVOS.size(); i++){
            for (int j = 0; j < cumulativeReturnVOS.size(); j++){
                double diff = cumulativeReturnVOS.get(i).cumulativeReturn - cumulativeReturnVOS.get(j).cumulativeReturn;
                if(max < diff){
                    //重新设置最大回撤点
                    cumulativeReturnVOS.get(top).isTraceBack = false;
                    cumulativeReturnVOS.get(down).isTraceBack = false;
                    top = i;
                    down = j;
                    cumulativeReturnVOS.get(top).isTraceBack = true;
                    cumulativeReturnVOS.get(down).isTraceBack = true;
                }
            }
        }

        return cumulativeReturnVOS;
    }
}
