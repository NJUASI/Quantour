package service;

import utilities.exceptions.DateShortException;
import vo.ChartShowCriteriaVO;
import vo.MovingAverageVO;
import vo.StockVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by cuihua on 2017/3/4.
 *
 * K线图、均线图
 */
public interface ChartService extends Remote{

    /**
     *  获取单支股票的一段日期内的信息
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param chartShowCriteriaVO 股票的选择标准
     * @return 特定股票的所有交易信息
     * @throws RemoteException RMI
     */
    Iterator<StockVO> getSingleStockRecords(ChartShowCriteriaVO chartShowCriteriaVO);

    /**
     * 获取单支股票一段日期内，用户所选天数的均线图的平均值.
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param chartShowCriteriaVO the chart show criteria vo 用户所选股票的信息
     * @return the ave data 用户所选天数的均线图的平均值
     * @throws RemoteException the remote exception
     */
    Map<Integer, Iterator<MovingAverageVO>> getAveData(ChartShowCriteriaVO chartShowCriteriaVO, int[] days) throws DateShortException;
}
