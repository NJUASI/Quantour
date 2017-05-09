package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.dataHelper.PrivateStockDataHelper;
import com.edu.nju.asi.dataHelper.SearchDataHelper;
import com.edu.nju.asi.dataHelper.StockDataHelper;
import com.edu.nju.asi.dataHelper.dataHelperImpl.PrivateStockDataHelperImpl;
import com.edu.nju.asi.dataHelper.dataHelperImpl.SearchDataHelperImpl;
import com.edu.nju.asi.dataHelper.dataHelperImpl.StockDataHelperImpl;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.po.PrivateStockPO;
import com.edu.nju.asi.po.StockPO;
import com.edu.nju.asi.po.StockSearchPO;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.vo.StockPoolVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by cuihua
 * Update time 2017/3/15
 * 新增异常
 */
public class StockDaoImpl implements StockDao {

    /**
     * 获取特定日期指定股票的相关数据
     *
     * @param stockCode 指定股票代码
     * @param date      指定日期
     * @return 特定日期指定股票的相关数据
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public Stock getStockData(String stockCode, LocalDate date) {
        return null;
    }

    /**
     * 获取特定时间段内的指定股票所有数据
     * 注意：取出来的所有股票数据中，年份小的在链表前端，年份大的在链表后端
     *
     * @param stockCode 指定股票代码
     * @param start     时间区域的小值
     * @param end       时间区域的大值
     * @return （股票代码相同）特定时间段内的指定股票所有数据
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<Stock> getStockData(String stockCode, LocalDate start, LocalDate end) {
        return null;
    }

    /**
     * 取指定股票的所有数据，没有返回null
     * 注意：取出来的所有股票数据中，年份小的在链表前端，年份大的在链表后端
     *
     * @param stockCode 指定的股票代码
     * @return （股票代码相同）此股票的所有数据
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/6
     */
    @Override
    public List<Stock> getStockData(String stockCode) {
        return null;
    }

    /**
     * 获取特定日期的所有股票所有数据
     *
     * @param date 选定的日期
     * @return （时间相同）特定日期的保存的所有股票
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<Stock> getStockData(LocalDate date) {
        return null;
    }

    /**
     * @param stockCode 股票代码
     * @return 此股票需要被剔除的所有日期
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<LocalDate> getDateWithoutData(String stockCode) {
        return null;
    }

    /**
     * @param stockCode 股票代码
     * @param start
     * @param end       @return 在指定时间区段此股票需要被剔除的所有日期
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<LocalDate> getDateWithoutData(String stockCode, LocalDate start, LocalDate end) {
        return null;
    }

    /**
     * @return 所有的交易日期
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<LocalDate> getDateWithData() {
        return null;
    }

    /**
     * @param stockCode 股票代码
     * @return 数据库中股票存在记录的起讫时间，List.get(0)为第一天，List.get(1)为最后一天
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<LocalDate> getFirstAndLastDay(String stockCode) {
        return null;
    }

    /**
     * 获取所有股票的代码
     *
     * @return the all stocks code 返回所有股票的代码及其名称，代码作为键值
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/14
     */
    @Override
    public Map<String, String> getAllStocksCode() {
        return null;
    }

    /**
     * @return the all stocks first letters 返回所有股票的首字母及其名称、代码
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<StockSearch> getAllStocksFirstLetters() {
        return null;
    }

    /**
     * 获取所有股票的名称
     *
     * @return the all stocks first letters 返回所有股票的名称及其代码，名称作为键值
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/14
     */
    @Override
    public Map<String, String> getAllStocksName() {
        return null;
    }

    /**
     * 获取所有股票的版块有关的信息
     *
     * @return 所有股票的版块有关的信息
     */
    @Override
    public List<StockPoolVO> getAllStockPool() {
        return null;
    }
}
