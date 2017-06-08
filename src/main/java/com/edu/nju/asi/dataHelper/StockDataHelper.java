package com.edu.nju.asi.dataHelper;

import com.edu.nju.asi.infoCarrier.FirstAndLastDay;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.StockList;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Byron Dong on 2017/3/5.
 * Last updated by cuihua
 * Update time 2017/3/9
 *
 * 去除多余接口
 */
public interface StockDataHelper {


    /*
    股票数据
     */
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
    Stock getStockData(String stockCode, LocalDate date);

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
    List<Stock> getStockData(String stockCode, LocalDate start,LocalDate end);

    /**
     * 取指定股票的所有数据，没有返回null
     * 注意：取出来的所有股票数据中，年份小的在链表前端，年份大的在链表后端
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/6
     * @param stockCode 指定的股票代码
     * @return （股票代码相同）此股票的所有数据
     */
    List<Stock> getStockData(String stockCode);

    /**
     * 取所有股票的所有数据，没有返回null
     * 注意：取出来的单只股票数据中，年份小的在链表前端，年份大的在链表后端
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/8
     * @param codes 指定的股票代码
     * @return （股票代码相同）此股票的所有数据
     */
    Map<String,List<Stock>> getAllStockData(List<String> codes);

    /**
     * 获取特定日期的所有股票所有数据
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @param date 选定的日期
     * @return （时间相同）特定日期的保存的所有股票
     */
    List<Stock> getStockData(LocalDate date);

    /**
     * 添加股票列表
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @param stocks 需要添加的股票列表
     * @return boolean
     */
    boolean addStockAll(List<Stock> stocks);


    /*
    交易时间相关
     */
    /**
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 所有的交易日期
     */
    List<LocalDate> getDateWithData();

    /**
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @param stockCode 股票代码
     * @return 数据库中股票存在记录的起讫时间，List.get(0)为第一天，List.get(1)为最后一天
     */
    List<LocalDate> getFirstAndLastDay(String stockCode);
}
