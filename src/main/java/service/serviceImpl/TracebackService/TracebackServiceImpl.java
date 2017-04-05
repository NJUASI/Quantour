package service.serviceImpl.TracebackService;

import service.StockService;
import service.TracebackService;
import service.serviceImpl.StockServiceImpl;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.NoDataWithinException;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by harvey on 17-3-28.
 */
public class TracebackServiceImpl implements TracebackService {

    private StockService stockService;

    public TracebackServiceImpl() {
        stockService = new StockServiceImpl();
    }

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
    public List<CumulativeReturnVO> getStrategyCumulativeReturn(TracebackCriteriaVO tracebackCriteriaVO) {
        return null;
    }

    /**
     * 获取基准累计收益率,非自选股
     *
     * @param tracebackCriteriaVO 用户所选回测条件
     * @return List<CumulativeReturnVO> 基准累计收益率的列表
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public List<CumulativeReturnVO> getBaseCumulativeReturn(TracebackCriteriaVO tracebackCriteriaVO) throws IOException, NoDataWithinException, DateNotWithinException {

        LocalDate start = tracebackCriteriaVO.startDate;
        LocalDate end = tracebackCriteriaVO.endDate;
        int span = start.until(end).getDays();

        String stockName = tracebackCriteriaVO.baseStockName;
        List<StockVO> baseStock = stockService.getBaseStockData(stockName,start,end);

        return getCumulativeReturnOfOneStock(baseStock,span);
    }

    /**
     * 获取基准累计收益率，自选股
     *
     * @param tracebackCriteriaVO 用户所选回测条件
     * @param stockCodes          所有自选股的代码
     * @return List<CumulativeReturnVO> 基准累计收益率的列表
     */
    @Override
    public List<CumulativeReturnVO> getCustomizedCumulativeReturn(TracebackCriteriaVO tracebackCriteriaVO, List<String> stockCodes) throws IOException, NoDataWithinException, DateNotWithinException {

        List<CumulativeReturnVO> cumulativeReturnVOS = new ArrayList<CumulativeReturnVO>();
        List<List<StockVO>> customizedStockVOs = new ArrayList<List<StockVO>>();

        LocalDate start = tracebackCriteriaVO.startDate;
        LocalDate end = tracebackCriteriaVO.endDate;
        int span = start.until(end).getDays();

        //添加第一天的数据，为0;
        cumulativeReturnVOS.add(new CumulativeReturnVO(start,0,false));

        //将每一支股票的信息添加进列表
        for(int i = 0; i < stockCodes.size(); i++){
            //每一支股票在日期范围内的信息
            List<StockVO> list = stockService.getOneStockData(stockCodes.get(i),start,end);
            customizedStockVOs.add(list);
        }

        //TODO gcm 还没有完成，nnd

        return cumulativeReturnVOS;
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
     * @param span 时间区间
     * @return List<CumulativeReturnVO> 单一股票在时间区间内的累计收益率
     */
    private List<CumulativeReturnVO> getCumulativeReturnOfOneStock(List<StockVO> list,int span){

        List<CumulativeReturnVO> cumulativeReturnVOS = new ArrayList<CumulativeReturnVO>();

        //TODO gcm 将第一天的数据加入进去,查询果仁网，看第一天的日期是以交易日为准，还是以用户的选择为准
        CumulativeReturnVO firstDay = new CumulativeReturnVO(list.get(0).date,0,false);
        cumulativeReturnVOS.add(firstDay);

        //累计收益率以第一个交易日的收益率来对比计算
        double closeOfFirstDay = list.get(0).close;

        for(int i = 1; i < list.size(); i++) {
            double sucClose = list.get(i).close;
            double cumulativeReturn = (sucClose - closeOfFirstDay) / closeOfFirstDay;
            //TODO gcm 先将所有的最大回测点设为false
            cumulativeReturnVOS.add(new CumulativeReturnVO(list.get(i).date, cumulativeReturn, false));
        }

        return cumulativeReturnVOS;
    }
}
