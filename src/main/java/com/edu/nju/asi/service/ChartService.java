package com.edu.nju.asi.service;

import com.edu.nju.asi.infoCarrier.*;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.enums.MovingAverageType;
import com.edu.nju.asi.utilities.exceptions.*;

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
    List<Stock> getSingleStockRecords(String code) throws IOException, CodeNotFoundException;

    /**
     * 获取单支股票的一段日期内的信息
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param chartShowCriteria 股票的选择标准
     * @return 特定股票的所有交易信息
     */
    List<Stock> getSingleStockRecords(ChartShowCriteria chartShowCriteria) throws IOException, DateNotWithinException, CodeNotFoundException, NoDataWithinException;

    /**
     * 获取单支股票一段日期内，用户所选天数的均线图的平均值.
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param chartShowCriteria the chart show criteria com.edu.nju.asi.vo 用户所选股票的信息
     * @param MATypes  用户指定需要查看的几日均线图：如5、10日均线图，则传入包含5、10的list
     * @return 用户所选天数的均线图的平均值
     * @throws DateShortException 类型不匹配
     */
    Map<MovingAverageType, List<MovingAverage>> getAveData(ChartShowCriteria chartShowCriteria, List<MovingAverageType> MATypes) throws IOException, DateNotWithinException, CodeNotFoundException, NoDataWithinException, NoMatchEnumException;

    /**
     * 获取单支股票所有数据均线图的平均值.
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/10
     * @param code  用户所选股票的代号
     * @param MATypes  用户指定需要查看的几日均线图：如5、10日均线图，则传入包含5、10的list
     * @return 用户所选天数的均线图的平均值
     * @throws DateShortException 类型不匹配
     */
    Map<MovingAverageType, List<MovingAverage>> getAveData(String code, List<MovingAverageType> MATypes);

    /**
     * 获取两只股票的比较信息
     *
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/12
     * @param stockComparisionCriteria 要比较的两只股票标准，包括分别的代码，要比较的起讫时间
     * @return 界面上需要的两只股票的比较信息
     */
    List<StockComparision> getComparision(StockComparisionCriteria stockComparisionCriteria) throws IOException, DataSourceFirstDayException, DateNotWithinException, NoDataWithinException;

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
     * @param chartShowCriteria 股票的选择标准
     * @return 被剔除的日期
     */
    List<LocalDate> getDateWithoutData(ChartShowCriteria chartShowCriteria) throws IOException;

    /**
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/25
     * @param stockCode 股票代码
     * @return 股票在数据源中的起讫时间
     */
    FirstAndLastDay getFirstAndLastDay(String stockCode) throws IOException;

}
