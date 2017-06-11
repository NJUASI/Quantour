package com.edu.nju.asi.dao;

import com.edu.nju.asi.model.BaseStock;
import com.edu.nju.asi.utilities.exceptions.DateNotWithinException;
import com.edu.nju.asi.utilities.exceptions.NoDataWithinException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Byron Dong on 2017/5/15.
 */
public interface BaseStockDao {

    /**
     * 获取特定日期指定股票的相关数据
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @param stockCode 指定股票代码
     * @param date 指定日期
     * @return 特定日期指定股票的相关数据
     */
    BaseStock getStockData(String stockCode, LocalDate date);

    /**
     * 获取指定股票的相关数据
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @param stockCode 指定股票代码
     * @return 特定日期指定股票的相关数据
     */
    List<BaseStock> getStockData(String stockCode);

    /**
     * 根据基准股票名称，起始日期，结束日期，获得该基准股票在此期间的数据
     * @param stockName 股票名称
     * @return 基准股票信息的列表
     */
    List<BaseStock> getBaseStockData(String stockName);

    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/5/30
     * @return 所有基准股票的代码
     */
    List<String> getAllBaseStocksCode();

    /**
     * 添加股票列表
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @param baseStocks 需要添加的股票列表
     * @return boolean
     */
    boolean addBaseStockAll(List<BaseStock> baseStocks);
}
