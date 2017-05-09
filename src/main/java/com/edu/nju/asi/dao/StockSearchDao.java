package com.edu.nju.asi.dao;

import com.edu.nju.asi.po.StockSearchPO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Byron Dong on 2017/5/9.
 */
public interface StockSearchDao {

    /**
     * @return 所有股票简化代码
     */
    List<String> getAllStockCodes();

    /**
     * @return 所有股票指数简化代码
     */
    List<String> getAllBaseStockCodes();

    /**
     * @return 所有股票名称的首字母缩写及其名称、代码
     */
    List<StockSearchPO> getAllStocksFirstLetters();

    /**
     * @return 返回所有股票的代码及其名称，代码作为键值
     */
    Map<String,String> getAllStocksCode();

    /**
     * @return 返回所有股票的汉语名称及其代码，名称作为键值
     */
    Map<String,String> getAllStocksName();

}
