package com.edu.nju.asi.dao;

import com.edu.nju.asi.model.BaseStock;

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
     * 获取特定日期指定股票的相关数据
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @param stockCode 指定股票代码
     * @param start 指定开始日期（如果存在，包含start）
     * @param  end 指定结束日期（如果存在，包含end）
     * @return 特定日期指定股票的相关数据
     */
    List<BaseStock> getStockData(String stockCode, LocalDate start, LocalDate end);

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
