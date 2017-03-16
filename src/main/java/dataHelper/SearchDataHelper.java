package dataHelper;

import java.util.Map;

/**
 * Created by Harvey on 2017/3/14.
 */
public interface SearchDataHelper {

    /**
     * Gets all stocks code. 获取所有股票的代码
     *
     * @return the all stocks code 返回所有股票的代码及其名称，代码作为键值
     */
    Map<String,String> getAllStocksFirstLetters();

    /**
     * Gets all stocks first letters.获取所有股票的首字母
     *
     * @return the all stocks first letters 返回所有股票的首字母及其名称，首字母作为键值
     */
    Map<String,String> getAllStocksCode();

    /**
     * Gets all stocks first letters.获取所有股票的名称
     *
     * @return the all stocks first letters 返回所有股票的名称及其代码，名称作为键值
     */
    Map<String,String> getAllStocksName();
}
