package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.StockDataHelper;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.vo.StockPoolVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by Byron Dong on 2017/3/5.
 * Last updated by cuihua
 * Update time 2017/3/18
 */
@Repository
public class StockDataHelperImpl implements StockDataHelper {

    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

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
     * 获取所有股票的版块有关的信息
     *
     * @return 所有股票的版块有关的信息
     */
    @Override
    public List<StockPoolVO> getAllStockPool() {
        return null;
    }
}
