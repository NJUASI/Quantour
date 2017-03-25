package service;

import utilities.exceptions.*;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by cuihua
 * Update time 2017/3/12
 * 修改股票比较接口
 *
 * K线图、均线图、股票比较
 *
 */
public interface ChartService {

    /**
     * 获取单支股票的所有数据
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/9
     * @param code 股票代码
     * @return 特定股票的所有交易信息
     */
    List<StockVO> getSingleStockRecords(String code) throws IOException, CodeNotFoundException;

    /**
     * 获取单支股票的一段日期内的信息
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param chartShowCriteriaVO 股票的选择标准
     * @return 特定股票的所有交易信息
     */
    List<StockVO> getSingleStockRecords(ChartShowCriteriaVO chartShowCriteriaVO) throws IOException, DateNotWithinException, CodeNotFoundException, NoDataWithinException;

    /**
     * 获取单支股票一段日期内，用户所选天数的均线图的平均值.
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param chartShowCriteriaVO the chart show criteria vo 用户所选股票的信息
     * @param days  用户指定需要查看的几日均线图：如5、10日均线图，则传入包含5、10的list
     * @return 用户所选天数的均线图的平均值
     * @throws DateShortException 类型不匹配
     */
    Map<Integer, List<MovingAverageVO>> getAveData(ChartShowCriteriaVO chartShowCriteriaVO, List<Integer> days) throws DateShortException, IOException, DateNotWithinException, CodeNotFoundException, NoDataWithinException;

    /**
     * 获取单支股票所有数据均线图的平均值.
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/10
     * @param code  用户所选股票的代号
     * @param days  用户指定需要查看的几日均线图：如5、10日均线图，则传入包含5、10的list
     * @return 用户所选天数的均线图的平均值
     * @throws DateShortException 类型不匹配
     */
    Map<Integer, List<MovingAverageVO>> getAveData(String code, List<Integer> days) throws DateShortException, DateNotWithinException;

    /**
     * 获取两只股票的比较信息
     *
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/12
     * @param stockComparsionCriteriaVO 要比较的两只股票标准，包括分别的代码，要比较的起讫时间
     * @return 界面上需要的两只股票的比较信息
     */
    List<StockComparisionVO> getComparision(StockComparsionCriteriaVO stockComparsionCriteriaVO) throws IOException, DataSourceFirstDayException, DateNotWithinException, NoDataWithinException;

    /**
     * 获取单支股票被剔除的日期
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     * @param stockCode 股票代码
     * @return 被剔除的日期
     */
    List<LocalDate> getDateWithoutData(String stockCode) throws IOException;

    /**
     * 获取指定时间段单支股票被剔除的日期
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     * @param chartShowCriteriaVO 股票的选择标准
     * @return 被剔除的日期
     */
    List<LocalDate> getDateWithoutData(ChartShowCriteriaVO chartShowCriteriaVO) throws IOException;

}
