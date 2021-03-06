package com.edu.nju.asi.service;

import com.edu.nju.asi.infoCarrier.traceBack.StockPoolCriteria;
import com.edu.nju.asi.model.BaseStock;
import com.edu.nju.asi.model.SearchID;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.utilities.enums.AreaType;
import com.edu.nju.asi.utilities.enums.IndustryType;
import com.edu.nju.asi.utilities.enums.StocksSortCriteria;
import com.edu.nju.asi.utilities.exceptions.DateNotWithinException;
import com.edu.nju.asi.utilities.exceptions.NoDataWithinException;
import com.edu.nju.asi.utilities.exceptions.UnhandleBlockTypeException;

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
     * @param sortCriteria 对所有股票的比较排序条件
     * @param industryType 限制股票的行业信息
     * @param areaType 慈安之股票的地域信息
     * @return 股票信息列表
     * @throws IOException IO
     */
    List<Stock> getAllStocks(LocalDate date, StocksSortCriteria sortCriteria, IndustryType industryType, AreaType areaType) throws IOException, UnhandleBlockTypeException;

    /**
     * 用户输入代码或者股票首字母或股票名称，查找符合条件的股票
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/14
     * @param searchString 代码或股票首字母或股票名称
     * @return 符合条件的股票简要信息
     */
    List<StockSearch> searchStock(String searchString);

    /**
     * 根据需要查看的日期，获得所有基准股票在此日的数据
     * @param thisDate 需要查看的日期
     * @return 基准股票信息的列表
     */
    List<BaseStock> getBaseStockDataOfOneDay(LocalDate thisDate);

    /**
     * 根据股票池的选择标准，选择符合标准的股票池 非自选股调用此方法
     * @param stockPoolVO 股票池的选择标准
     * @return List<String> 符合标准的股票池中所有股票的股票代码
     */
    List<String> getStockPool(StockPoolCriteria stockPoolVO) throws IOException, UnhandleBlockTypeException;

    /**
     * 增加指定股票的点击量（+1）
     * @param  searchID
     * @return boolean
     */
    boolean addClickAmount(SearchID searchID);

    /**
     * 获取指定股票的点击率
     * @param  searchID
     * @return double
     */
    double getClickRate(SearchID searchID);

    /**
     * 获取股票排名前N（number）
     * @param number
     * @return List<StockSearch>
     */
    List<StockSearch> getTopRankingList(int number);
}
