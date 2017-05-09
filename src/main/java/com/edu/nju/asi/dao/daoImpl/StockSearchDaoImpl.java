package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.StockSearchDao;
import com.edu.nju.asi.po.StockSearchPO;

import java.util.List;
import java.util.Map;

/**
 * Created by Byron Dong on 2017/5/9.
 */
public class StockSearchDaoImpl implements StockSearchDao {
    /**
     * @return 所有股票简化代码
     */
    @Override
    public List<String> getAllStockCodes() {
        return null;
    }

    /**
     * @return 所有股票指数简化代码
     */
    @Override
    public List<String> getAllBaseStockCodes() {
        return null;
    }

    /**
     * @return 所有股票名称的首字母缩写及其名称、代码
     */
    @Override
    public List<StockSearchPO> getAllStocksFirstLetters() {
        return null;
    }

    /**
     * @return 返回所有股票的代码及其名称，代码作为键值
     */
    @Override
    public Map<String, String> getAllStocksCode() {
        return null;
    }

    /**
     * @return 返回所有股票的汉语名称及其代码，名称作为键值
     */
    @Override
    public Map<String, String> getAllStocksName() {
        return null;
    }
}
