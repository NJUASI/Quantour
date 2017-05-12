package com.edu.nju.asi.service;

import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.infoCarrier.traceBack.StockPoolCriteria;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Harvey on 2017/3/5.
 * Last updated by Harvey
 * Update time 2017/3/14
 *
 * 股票信息查看、自选股操作
 */
public interface StockService{

    /**
     * 显示所有股票信息的列表
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @params date 用户选择日期
     * @return 股票信息列表
     * @throws IOException IO
     */
    List<Stock> getAllStocks(LocalDate date) throws IOException;

    /**
     * 用户输入代码或者股票首字母或股票名称，查找符合条件的股票
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/14
     * @param searchString 代码或股票首字母或股票名称
     * @return 符合条件的股票简要信息
     */
    List<StockSearch> searchStock(String searchString) throws IOException;

    /**
     * 根据基准股票名称，起始日期，结束日期，获得该基准股票在此期间的数据
     * @param stockName 股票名称
     * @param start 起始日期
     * @param end 结束日期
     * @return List<StockVO> 基准股票信息的列表
     */
    List<Stock> getBaseStockData(String stockName, LocalDate start, LocalDate end) throws IOException, NoDataWithinException, DateNotWithinException;

    /**
     * 根据股票池的选择标准，选择符合标准的股票池 非自选股调用此方法
     * @param stockPoolVO 股票池的选择标准
     * @return List<String> 符合标准的股票池中所有股票的股票代码
     */
    List<String> getStockPool(StockPoolCriteria stockPoolVO) throws IOException, UnhandleBlockTypeException;
}
