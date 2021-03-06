package com.edu.nju.asi.dataHelper;

import com.edu.nju.asi.model.SearchID;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.utilities.enums.AreaType;
import com.edu.nju.asi.utilities.enums.IndustryType;

import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/3/14.
 */
public interface StockSearchDataHelper {

    /**
     * @return 所有股票名称的首字母缩写及其名称、代码
     */
    List<StockSearch> getAllStockSearch();

    /**
     * @return 所有股票(除去基准股)名称的首字母缩写及其名称、代码
     */
    List<StockSearch> getAllStockSearchWithoutBase();

    /**
     * @return 返回所有股票的代码及其名称，代码作为键值
     */
    Map<String,String> getAllStocksCode();

    /**
     * @return 返回所有股票的汉语名称及其代码，名称作为键值
     */
    Map<String,String> getAllStocksName();

    /**
     * 添加StockSearch列表
     */
    boolean addStockSearchAll(List<StockSearch> list);

    /**
     * 添加StockSearch列表
     */
    List<StockSearch> search(String info);

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
    List<StockSearch> getRankingList(int number);
}
