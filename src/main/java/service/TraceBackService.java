package service;

import utilities.exceptions.CodeNotFoundException;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.DateShortException;
import utilities.exceptions.NoDataWithinException;
import vo.CumulativeReturnVO;
import vo.RelativeIndexReturnVO;
import vo.TraceBackCriteriaVO;
import vo.TraceBackNumValVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by harvey on 17-3-28.
 *
 * 回测功能的接口
 */
public interface TraceBackService {

    /**
     * 获取策略累计收益率，非自选股票池
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     * @param traceBackCriteriaVO 用户所选回测条件
     * @return List<CumulativeReturnVO> 策略累计收益率的列表
     */
    List<CumulativeReturnVO> getStrategyCumulativeReturn(TraceBackCriteriaVO traceBackCriteriaVO) throws DateNotWithinException, NoDataWithinException, IOException, DateShortException, CodeNotFoundException;


    /**
     * 获取策略累计收益率，自选股票池
     * @param traceBackCriteriaVO 用户所选回测条件
     * @param stockCodes 策略累计收益率的列表
     * @return List<CumulativeReturnVO> 策略累计收益率的列表
     */
    List<CumulativeReturnVO> getStrategyCumulativeReturnOfCustomized(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockCodes) throws DateNotWithinException, NoDataWithinException, IOException, DateShortException, CodeNotFoundException;

    /**
     * 获取基准累计收益率,非自选股票池
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     * @param start 回测区间起始日期
     * @param end 回测区间结束日期
     * @param stockName 基准股票的名称
     * @return List<CumulativeReturnVO> 基准累计收益率的列表
     */
    List<CumulativeReturnVO> getBaseCumulativeReturn(LocalDate start, LocalDate end, String stockName) throws IOException, NoDataWithinException, DateNotWithinException;

    /**
     * 获取基准累计收益率，自选股票池
     * @param start 回测区间起始日期
     * @param end 回测区间结束日期
     * @param stockCodes 所有自选股的代码
     * @return List<CumulativeReturnVO> 基准累计收益率的列表
     */
    List<CumulativeReturnVO> getCustomizedCumulativeReturn(LocalDate start, LocalDate end, List<String> stockCodes) throws IOException, NoDataWithinException, DateNotWithinException;

    /**
     * 计算回测中用列表列出的数值型数据，如阿尔法，beta
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     * @param traceBackCriteriaVO 用户所选回测条件
     * @return TraceBackNumValVO 所需的所有数值型数据保存对象
     */
    TraceBackNumValVO getNumericalVal(TraceBackCriteriaVO traceBackCriteriaVO);

    /**
     * 计算相对收益指数
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     * @param traceBackCriteriaVO 用户所选回测条件
     * @return RelativeIndexReturnVO 保存表示相对收益指数的对象，包括正周期数，负周期数和赢率
     */
    RelativeIndexReturnVO getRelativeIndexReturn(TraceBackCriteriaVO traceBackCriteriaVO);



}
