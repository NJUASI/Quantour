package service;

import utilities.exceptions.DateShortException;
import vo.ChartShowCriteriaVO;
import vo.MovingAverageVO;
import vo.StockVO;

import java.io.IOException;
import java.rmi.Remote;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Byron Dong
 * Update time 2017/3/10
 *
 * K线图、均线图
 *
 * TODO getAveData参数days的含义
 */
public interface ChartService extends Remote{

    /**
     * 获取单支股票的所有数据
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/9
     * @param code 股票代码
     * @return 特定股票的所有交易信息
     */
    Iterator<StockVO> getSingleStockRecords(String code) throws IOException;

    /**
     * 获取单支股票的一段日期内的信息
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param chartShowCriteriaVO 股票的选择标准
     * @return 特定股票的所有交易信息
     */
    Iterator<StockVO> getSingleStockRecords(ChartShowCriteriaVO chartShowCriteriaVO) throws IOException;

    /**
     * 获取单支股票一段日期内，用户所选天数的均线图的平均值.
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param chartShowCriteriaVO the chart show criteria vo 用户所选股票的信息
     * @param days
     * @return 用户所选天数的均线图的平均值
     * @throws DateShortException 类型不匹配
     */
    Map<Integer, Iterator<MovingAverageVO>> getAveData(ChartShowCriteriaVO chartShowCriteriaVO, List<Integer> days) throws DateShortException, IOException;

    /**
     * 获取单支股票所有数据均线图的平均值.
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/10
     * @param code  用户所选股票的代号
     * @param days
     * @return 用户所选天数的均线图的平均值
     * @throws DateShortException 类型不匹配
     */
    Map<Integer, Iterator<MovingAverageVO>> getAveData(String code, List<Integer> days) throws DateShortException;
}
