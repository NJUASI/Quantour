package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.BaseStockDao;
import com.edu.nju.asi.dataHelper.BaseStockDataHelper;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.model.BaseStock;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Byron Dong on 2017/5/15.
 */
@Component("BaseStockDao")
public class BaseStockDaoImpl implements BaseStockDao {

    private BaseStockDataHelper baseStockDataHelper;

    /**
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     */
    public BaseStockDaoImpl() {
        baseStockDataHelper = HelperManager.baseStockDataHelper;
    }

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
    public BaseStock getStockData(String stockCode, LocalDate date) {
        return baseStockDataHelper.getStockData(stockCode,date);
    }

    /**
     * 获取特定日期指定股票的相关数据
     *
     * @param stockCode 指定股票代码
     * @param start     指定开始日期（如果存在，包含start）
     * @param end       指定结束日期（如果存在，包含end）
     * @return 特定日期指定股票的相关数据
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<BaseStock> getStockData(String stockCode, LocalDate start, LocalDate end) {
        return baseStockDataHelper.getStockData(stockCode,start,end);
    }

    /**
     * 添加股票列表
     *
     * @param baseStocks 需要添加的股票列表
     * @return boolean
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean addBaseStockAll(List<BaseStock> baseStocks) {
        return baseStockDataHelper.addBaseStockAll(baseStocks);
    }
}
