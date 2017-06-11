package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.BaseStockDao;
import com.edu.nju.asi.dataHelper.BaseStockDataHelper;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockSearchDataHelper;
import com.edu.nju.asi.model.BaseStock;
import com.edu.nju.asi.utilities.StockCodeHelper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Byron Dong on 2017/5/15.
 */
@Component("BaseStockDao")
public class BaseStockDaoImpl implements BaseStockDao {

    private BaseStockDataHelper baseStockDataHelper;
    private StockSearchDataHelper stockSearchDataHelper;

    /**
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     */
    public BaseStockDaoImpl() {
        baseStockDataHelper = HelperManager.baseStockDataHelper;
        stockSearchDataHelper = HelperManager.stockSearchDataHelper;
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
        return baseStockDataHelper.getStockData(stockCode, date);
    }

    /**
     * 获取特定日期指定股票的相关数据
     *
     * @param stockCode 指定股票代码
     * @return 特定日期指定股票的相关数据
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<BaseStock> getStockData(String stockCode) {
        return baseStockDataHelper.getStockData(stockCode, null, null);
    }

    /**
     * 根据基准股票名称，起始日期，结束日期，获得该基准股票在此期间的数据
     *
     * @param stockName 股票名称
     * @return List<StockVO> 基准股票信息的列表
     */
    @Override
    public List<BaseStock> getBaseStockData(String stockName) {
        System.out.println("-----------In base----------");
        System.out.println("getBaseStockData" + this);
        Map<String, String> map = stockSearchDataHelper.getAllStocksCode();
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + "   " + entry.getValue());
        }

        System.out.println("baseCode: " + map.get(stockName));

        String baseStockCode = StockCodeHelper.format(map.get(stockName));
        System.out.println("finished getBaseStockData--------------" + stockName + "--------------------------");
        return getStockData(baseStockCode);
    }

    @Override
    public List<String> getAllBaseStocksCode() {
        // 数据库中只有这五只基准股票，因为无baseStockSearch的相关表，故写死
        List<String> baseStocksCode = new ArrayList<>();
        baseStocksCode.add("000300");
        baseStocksCode.add("000001");
        baseStocksCode.add("399001");
        baseStocksCode.add("399005");
        baseStocksCode.add("399006");
        return baseStocksCode;
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
