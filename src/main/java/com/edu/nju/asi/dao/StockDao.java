package com.edu.nju.asi.dao;

import com.edu.nju.asi.model.SearchID;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.model.PrivateStock;
import com.edu.nju.asi.utilities.StockList;
import com.edu.nju.asi.utilities.enums.AreaType;
import com.edu.nju.asi.utilities.enums.IndustryType;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.infoCarrier.traceBack.StockPool;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Harvey
 * Update time 2017/3/14
 *
 * 去除为UserService的接口createDir，新增显示用户自选股股票的接口
 * 修改接口getPrivateStocks为获取用户选择的自选股，新增接口getPrivateStockData获取用户自选股数据
 */
public interface StockDao {

    /*
    股票数据
     */
    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/12
     * @param stockCode 指定股票代码
     * @param date 指定日期
     * @return 特定日期指定股票的相关数据，没有返回null
     */
    Stock getStockData(String stockCode, LocalDate date) throws IOException;

    /**
     * 获取特定时间段内的指定股票所有数据
     * 注意：取出来的所有股票数据中，年份小的在链表前端，年份大的在链表后端
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     * @param stockCode 指定股票代码
     * @param start 时间区域的小值
     * @param end 时间区域的大值
     * @return （股票代码相同）特定时间段内的指定股票所有数据
     */
    List<Stock> getStockData(String stockCode, LocalDate start, LocalDate end) throws IOException, DateNotWithinException, NoDataWithinException;

    /**
     * 取指定股票的所有数据，没有返回null
     * 注意：取出来的所有股票数据中，年份小的在链表前端，年份大的在链表后端
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @param stockCode 指定的股票代码
     * @return （股票代码相同）此股票的所有数据
     */
    List<Stock> getStockData(String stockCode) throws IOException;

    /**
     * 获取特定日期的所有股票所有数据
     *
     * @author Harvey
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param date 选定的日期
     * @return （时间相同）特定日期的保存的所有股票
     */
    List<Stock> getStockData(LocalDate date) throws IOException;

    /**
     * 取所有股票的所有数据，没有返回null
     * 注意：取出来的单只股票数据中，年份小的在链表前端，年份大的在链表后端
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/8
     * @return （股票代码相同）此股票的所有数据
     */
    Map<String,List<Stock>> getAllStockData();

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
     * 根据area取StockSearch列表
     */
    List<StockSearch> getByArea(AreaType area);

    /**
     * 根据industry取StockSearch列表
     */
    List<StockSearch> getByIndustry(IndustryType industry);


    /*
    交易日期相关
     */
    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/23
     * @param stockCode 股票代码
     * @return 此股票需要被剔除的所有日期
     */
    List<LocalDate> getDateWithoutData(String stockCode) throws IOException;

    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/23
     * @param stockCode 股票代码
     * @return 在指定时间区段此股票需要被剔除的所有日期
     */
    List<LocalDate> getDateWithoutData(String stockCode, LocalDate start, LocalDate end) throws IOException;

    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/4/6
     * @return 所有的交易日期
     */
    List<LocalDate> getDateWithData() throws IOException;

    /**
     * 增加指定股票的点击量（+1）
     */
    boolean addClickAmount(SearchID searchID);

    /**
     * 获取指定股票的点击率
     */
    double getClickRate(SearchID searchID);

    /**
     * 获取股票排名前N（number）
     */
    List<StockSearch> getTopRankingList(int number);

    /*
    暂定
     */
    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/25
     * @param stockCode 股票代码
     * @return 数据库中股票存在记录的起讫时间，List.get(0)为第一天，List.get(1)为最后一天
     */
    List<LocalDate> getFirstAndLastDay(String stockCode) throws IOException;

    /**
     * 获取所有股票的代码
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/14
     * @return the all stocks code 返回所有股票的代码及其名称，代码作为键值
     */
    Map<String, String> getAllStocksCode();

    /**
     * @author Harvey
     * @lastUpdatedBy cuihua
     * @updateTime 2017/4/18
     * @return the all stocks first letters 返回所有股票的首字母及其名称、代码
     */
    List<StockSearch> getAllStocksFirstLetters() throws IOException;

    /**
     * 获取所有股票的名称
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/14
     * @return the all stocks first letters 返回所有股票的名称及其代码，名称作为键值
     */
    Map<String,String> getAllStocksName();

    /**
     * @return 所有非基准股票的版块有关的信息，即股票池
     */
    List<StockPool> getAllStockPool() throws IOException, UnhandleBlockTypeException;
}
