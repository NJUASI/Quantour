package service;

import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.NoDataWithinException;
import vo.CumulativeReturnVO;
import vo.RelativeIndexReturnVO;
import vo.TracebackCriteriaVO;
import vo.TracebackNumValVO;

import java.io.IOException;
import java.util.List;

/**
 * Created by harvey on 17-3-28.
 *
 * 回测功能的接口
 */
public interface TracebackService {

    /**
     * 获取策略累计收益率
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     * @param tracebackCriteriaVO 用户所选回测条件
     * @return List<CumulativeReturnVO> 策略累计收益率的列表
     */
    public List<CumulativeReturnVO> getStrategyCumulativeReturn(TracebackCriteriaVO tracebackCriteriaVO);

    /**
     * 获取基准累计收益率,非自选股
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     * @param tracebackCriteriaVO 用户所选回测条件
     * @return List<CumulativeReturnVO> 基准累计收益率的列表
     */
    public List<CumulativeReturnVO> getBaseCumulativeReturn(TracebackCriteriaVO tracebackCriteriaVO) throws IOException, NoDataWithinException, DateNotWithinException;

    /**
     * 获取基准累计收益率，自选股
     * @param tracebackCriteriaVO 用户所选回测条件
     * @param stockCodes 所有自选股的代码
     * @return List<CumulativeReturnVO> 基准累计收益率的列表
     */
    public List<CumulativeReturnVO> getCustomizedCumulativeReturn(TracebackCriteriaVO tracebackCriteriaVO,List<String> stockCodes);

    /**
     * 计算回测中用列表列出的数值型数据，如阿尔法，beta
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     * @param tracebackCriteriaVO 用户所选回测条件
     * @return TracebackNumValVO 所需的所有数值型数据保存对象
     */
    public TracebackNumValVO getNumericalVal(TracebackCriteriaVO tracebackCriteriaVO);

    /**
     * 计算相对收益指数
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     * @param tracebackCriteriaVO 用户所选回测条件
     * @return RelativeIndexReturnVO 保存表示相对收益指数的对象，包括正周期数，负周期数和赢率
     */
    public RelativeIndexReturnVO getRelativeIndexReturn(TracebackCriteriaVO tracebackCriteriaVO);



}
