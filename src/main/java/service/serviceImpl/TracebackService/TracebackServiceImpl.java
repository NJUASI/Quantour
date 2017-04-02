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

/**
 * Created by harvey on 17-3-28.
 */
public class TracebackServiceImpl implements TracebackService {

    private StockService stockService;

    private List<StockVO> customizedStockPool;

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

        List<CumulativeReturnVO> cumulativeReturnVOS = new ArrayList<CumulativeReturnVO>();
        LocalDate start = tracebackCriteriaVO.startDate;
        LocalDate end = tracebackCriteriaVO.endDate;

        CumulativeReturnVO firstDay = new CumulativeReturnVO(start,0.0,false);
        //加入第一天的数据
        cumulativeReturnVOS.add(firstDay);

        String stockName = tracebackCriteriaVO.baseStockName;
        List<StockVO> baseStock = stockService.getBaseStock(stockName,start,end);
        for(int i = 1; i < baseStock.size(); i++) {
            double sucClose = baseStock.get(i).close;
            double preClose = baseStock.get(i - 1).close;
            double cumulativeReturn = (sucClose - preClose) / preClose;
            //TODO gcm 先将所有的最大回测点设为false
            cumulativeReturnVOS.add(new CumulativeReturnVO(baseStock.get(i).date, cumulativeReturn, false));
        }

        return cumulativeReturnVOS;
    }

    /**
     * 获取基准累计收益率，自选股
     *
     * @param tracebackCriteriaVO 用户所选回测条件
     * @param stockCodes          所有自选股的代码
     * @return List<CumulativeReturnVO> 基准累计收益率的列表
     */
    @Override
    public List<CumulativeReturnVO> getCustomizedCumulativeReturn(TracebackCriteriaVO tracebackCriteriaVO, List<String> stockCodes) {
        List<CumulativeReturnVO> cumulativeReturnVOS = new ArrayList<CumulativeReturnVO>();
        //TODO gcm,等做
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
}
