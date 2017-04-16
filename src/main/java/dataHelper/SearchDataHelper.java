package dataHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/3/14.
 */
public interface SearchDataHelper {

    /**
     * @return 所有股票简化代码
     */
    List<String> getAllStockCodes();

    /**
     * @return 所有股票指数简化代码
     */
    List<String> getAllBaseStockCodes();

    /**
     * @return 所有股票名称的首字母缩写及其名称，名称作为键值
     */
    Map<String,String> getAllStocksFirstLetters();

    /**
     * @return 返回所有股票的代码及其名称，代码作为键值
     */
    Map<String,String> getAllStocksCode();

    /**
     * @return 返回所有股票的汉语名称及其代码，名称作为键值
     */
    Map<String,String> getAllStocksName();
}
