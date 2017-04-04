package service;

import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.NoDataWithinException;
import vo.StockPoolCriteriaVO;
import vo.StockSearchVO;
import vo.StockVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/3/5.
 * Last updated by Harvey
 * Update time 2017/3/14
 *
 * 股票信息查看、自选股操作
 */
public interface StockService{

    /**
     * 显示所有股票信息的列表
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @params date 用户选择日期
     * @return 股票信息列表
     * @throws IOException IO
     */
    List<StockVO> getAllStocks(LocalDate date) throws IOException;

    /**
     * 显示用户的自选股信息列表
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName the user name 用户名称
     * @param date 用户选择日期
     * @return the iterator 自选股信息列表
     */
    Iterator<StockVO> getPrivateStocks(String userName, LocalDate date) throws IOException;

    /**
     * 用户添加自选股
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @param stockCode 股票代码
     * @return 是否添加成功
     */
    public boolean addPrivateStock(String userName, String stockCode);

    /**
     * 用户删除自选股
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @param stockCode 股票代码
     * @return 是否删除成功
     */
    public boolean deletePrivateStock(String userName, String stockCode);

    /**
     * 用户输入代码或者股票首字母或股票名称，查找符合条件的股票
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/14
     * @param searchString 代码或股票首字母或股票名称
     * @return 符合条件的股票简要信息
     */
    public List<StockSearchVO> searchStock(String searchString);

    /**
     * 根据股票代码，起始日期，结束日期，获得该股票在此期间的数据
     * @param stockCode 股票代码
     * @param start 起始日期
     * @param end 结束日期
     * @return Map<LocalDate, StockVO> 该股票信息和对应日期的键值对
     */
    Map<LocalDate, StockVO> getOneStockDateAndData(String stockCode, LocalDate start, LocalDate end) throws DateNotWithinException, NoDataWithinException, IOException;

    /**
     * 根据股票代码，起始日期，结束日期，获得该股票在此期间的数据
     * @param stockCode 股票代码
     * @param start 起始日期
     * @param end 结束日期
     * @return List<StockVO> 该股票信息的列表
     */
    List<StockVO> getOneStockData(String stockCode, LocalDate start, LocalDate end) throws DateNotWithinException, NoDataWithinException, IOException;

    /**
     * 根据基准股票名称，起始日期，结束日期，获得该基准股票在此期间的数据
     * @param stockName 股票名称
     * @param start 起始日期
     * @param end 结束日期
     * @return List<StockVO> 基准股票信息的列表
     */
    List<StockVO> getBaseStockData(String stockName, LocalDate start, LocalDate end) throws IOException, NoDataWithinException, DateNotWithinException;

    /**
     * 若参照日期为交易日，则返回参照日期;否则，返回参照日期的前一个交易日
     * @param date 参照日期
     * @param stockCode 股票代码
     * @return LocalDate
     */
    LocalDate getLastTradingDay(LocalDate date, String stockCode) throws IOException;

    /**
     * 根据传入的股票代码，找出传入股票代码中相对于date最晚的交易日期。若参照日期date为交易日，则返回参照日期;否则，返回参照日期的前一个交易日
     * @param date 参照日期
     * @param stockCodes 传入的股票代码列表
     * @return
     */
    LocalDate getLastTradingDay(LocalDate date, List<String> stockCodes);

    /**
     * 根据股票池的选择标准，选择符合标准的股票池 非自选股调用此方法
     * @param stockPoolVO 股票池的选择标准
     * @return List<String> 符合标准的股票池中所有股票的股票代码
     */
    List<String> getStockPool(StockPoolCriteriaVO stockPoolVO);

    /**
     * 以参照日期为基准，减去minusDay的交易日天数，获取到那天的日期
     * 例： start = 4.21.2014 minusDay = 2 则返回值为4.17.2014
     * @param start 作为参照的日期
     * @param minusDay 减去的交易日的天数
     * @param stockPoolCodes 传入的股票代码列表
     * @return
     */
    LocalDate getTradingDayMinus(LocalDate start, int minusDay, List<String> stockPoolCodes);

    /**
     * 根据传入的股票代码，找出传入股票代码中相对于date最晚的交易日期。若参照日期date为交易日，则返回参照日期;否则，返回参照日期的前一个交易日
     * @param date 参照日期
     * @param stockCodes 传入的股票代码列表
     * @return 参照日期的下一个交易日
     */
    LocalDate getNextTradingDay(LocalDate date, List<String> stockCodes);

    /**
     * 若参照日期date为交易日，则返回参照日期;否则，返回参照日期的前一个交易日
     * @param date 参照日期
     * @param stockCode 传入的股票代码
     * @return 参照日期的下一个交易日
     */
    LocalDate getNextTradingDay(LocalDate date, String stockCode);

    /**
     * 以参照日期为基准，加上plusDay的交易日天数，获取到那天的日期
     * 例： start = 4.17.2014 plusDay = 2 则返回值为4.21.2014
     * @param start 作为参照的日期
     * @param plusDay 加上的交易日的天数
     * @param stockPoolCodes 传入的股票代码列表
     * @return
     */
    LocalDate getTradingDayPlus(LocalDate start, int plusDay, List<String> stockPoolCodes);

    /**
     * 查找当前日期，股票的信息
     * @param stockCode 股票代码
     * @param date 当前日期
     * @return
     */
    StockVO getOneStockDataOneDay(String stockCode, LocalDate date);

    /**
     * 计算从起始日期到结束日期之间总共有多少天的交易日，包括起始日期和结束日期
     * @param start 起始日期
     * @param end 结束日期
     * @return 起始日期到结束日期之间总共有多少天的交易日
     */
    int getTradingDays(LocalDate start, LocalDate end);
}
