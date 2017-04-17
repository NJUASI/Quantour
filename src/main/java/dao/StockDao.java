package dao;

import po.PrivateStockPO;
import po.StockPO;
import po.StockSearchPO;
import utilities.exceptions.*;
import vo.StockPoolVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Harvey
 * Update time 2017/3/14
 *
 * 去除为UserService的接口createDir，新增显示用户自选股股票的接口
 * 修改接口getPrivateStocks为获取用户选择的自选股，新增接口getPrivateStockData获取用户自选股数据
 */
public interface StockDao {

    /*
    股票数据
     */
    /**
     * 获取特定日期指定股票的相关数据
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/12
     * @param stockCode 指定股票代码
     * @param date 指定日期
     * @return 特定日期指定股票的相关数据
     */
    StockPO getStockData(String stockCode, LocalDate date) throws IOException;

    /**
     * 获取特定时间段内的指定股票所有数据
     * 注意：取出来的所有股票数据中，年份小的在链表前端，年份大的在链表后端
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     * @param stockCode 指定股票代码
     * @param start 时间区域的小值
     * @param end 时间区域的大值
     * @return （股票代码相同）特定时间段内的指定股票所有数据
     */
    List<StockPO> getStockData(String stockCode, LocalDate start, LocalDate end) throws IOException, DateNotWithinException, NoDataWithinException;

    /**
     * 取指定股票的所有数据，没有返回null
     * 注意：取出来的所有股票数据中，年份小的在链表前端，年份大的在链表后端
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @param stockCode 指定的股票代码
     * @return （股票代码相同）此股票的所有数据
     */
    List<StockPO> getStockData(String stockCode) throws IOException;

    /**
     * 获取特定日期的所有股票所有数据
     *
     * @author Harvey
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param date 选定的日期
     * @return （时间相同）特定日期的保存的所有股票
     */
    List<StockPO> getStockData(LocalDate date) throws IOException;


    /*
    交易日期相关
     */
    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/23
     * @param stockCode 股票代码
     * @return 此股票需要被剔除的所有日期
     */
    List<LocalDate> getDateWithoutData(String stockCode) throws IOException;

    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/23
     * @param stockCode 股票代码
     * @return 在指定时间区段此股票需要被剔除的所有日期
     */
    List<LocalDate> getDateWithoutData(String stockCode, LocalDate start, LocalDate end) throws IOException;

    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/4/6
     * @return 所有的交易日期
     */
    List<LocalDate> getDateWithData() throws IOException;

    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/4/6
     * @param start 指定区间开始日期
     * @param end 指定区间结束日期
     * @return 指定区间内所有的交易日期
     */
    List<LocalDate> getDateWithData(LocalDate start, LocalDate end) throws IOException;

    /*
    自选股操作
     */
    /**
     * 获取用户自选股票的数据
     *
     * @author Harvey
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/12
     * @param userName 用户名称
     * @param date 股票代码
     * @return （时间相同）指定用户指定日期的自选股票数据
     */
    List<StockPO> getPrivateStockData(String userName, LocalDate date) throws IOException, PrivateStockNotFoundException;

    /**
     * 获取用户的自选股票
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/12
     * @param userName 用户名称
     * @return 指定用户的自选股
     */
    PrivateStockPO getPrivateStocks(String userName) throws PrivateStockNotFoundException;

    /**
     * 获取用户的自选股票池
     *
     * @author  Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/17
     * @param userName 用户名称
     * @return 指定用户的自选股
     */
    List<String> getPrivateStockCodes(String userName) throws PrivateStockNotFoundException;

    /**
     * 添加用户自选股
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @param stockCode 股票代码
     * @return 添加是否成功
     */
    boolean addPrivateStock(String userName, String stockCode) throws PrivateStockExistedException, PrivateStockNotFoundException;

    /**
     * 删除用户自选股
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @param stockCode 股票代码
     * @return 删除是否成功
     */
    boolean deletePrivateStock(String userName, String stockCode) throws PrivateStockNotExistException, PrivateStockNotFoundException;




    /*
    暂定
     */
    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/25
     * @param stockCode 股票代码
     * @return 数据库中股票存在记录的起讫时间，List.get(0)为第一天，List.get(1)为最后一天
     */
    List<LocalDate> getFirstAndLastDay(String stockCode) throws IOException;

    /**
     * 获取所有股票的代码
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/14
     * @return the all stocks code 返回所有股票的代码及其名称，代码作为键值
     */
    Map<String, String> getAllStocksCode();

    /**
     * @author Harvey
     * @lastUpdatedBy cuihua
     * @updateTime 2017/4/18
     * @return the all stocks first letters 返回所有股票的首字母及其名称、代码
     */
    List<StockSearchPO> getAllStocksFirstLetters() throws IOException;

    /**
     * 获取所有股票的名称
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/14
     * @return the all stocks first letters 返回所有股票的名称及其，名称作为键值
     */
    Map<String,String> getAllStocksName();

    /**
     * 若参照日期为交易日，则返回参照日期;否则返回参照日期前的一个交易日
     * @param date 参照日期
     * @param stockCode
     * @return LocalDate
     */
    LocalDate getLastTradingDay(LocalDate date, String stockCode) throws IOException;

    /**
     * 获取所有股票的版块有关的信息
     * @return 所有股票的版块有关的信息
     */
    List<StockPoolVO> getAllStockPool() throws IOException, UnhandleBlockTypeException;
}
