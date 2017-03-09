package dao;

import po.StockPO;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/3/4.
 */
public interface StockDao {


    /**
     * 获取特定时间段内的指定股票所有数据
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param start 时间区域的小值
     * @param end 时间区域的大值
     * @param code  指定股票代码
     * @return List<StockPO> 特定时间段内的指定股票所有数据
     */
    List<StockPO> getStockData(LocalDate start, LocalDate end, String code);



    /**
     * 获取所有股票所有数据
     *
     * @author Harvey
     * @updateTime 2017/3/5
     * @return List<StockPO> 保存的所有股票
     */
    List<StockPO> getAllStock(LocalDate date);

    /**
     * 获取用户自选股票的数据
     *
     * @author Harvey
     * @updateTime 2017/3/5
     * @return List<StockPO> 指定用户的自选股票
     * @param userName  用户名称
     * @param date  股票代码
     */
    List<StockPO> getPrivateStocks(String userName, LocalDate date);

    /**
     * 添加用户自选股
     *
     * @author Harvey
     * @updateTime 2017/3/5
     * @return boolean 添加是否成功
     * @param userName  用户名称
     * @param stockCode 股票代码
     */
    boolean addPrivateStock(String userName, String stockCode);

    /**
     * 删除用户自选股
     *
     * @author Harvey
     * @updateTime 2017/3/5
     * @return boolean 删除是否成功
     * @param userName  用户名称
     * @param stockCode 股票代码
     */
    boolean deletePrivateStock(String userName, String stockCode);

    /**
     * 用户注册时，给用户新建一个对应用户名的资源文件.
     *
     * @param userName the user name 用户名称
     * @return the boolean  是否创建成功
     * @author Harvey
     * @updateTime 2017/3/6
     */
    boolean createPrivateDir(String userName);

    /**
     * Gets stock data.
     *
     * @param s the s
     * @return the stock data
     */
    List<StockPO> getStockData(String s);


    /**
     * Gets first day.  获取数据库中股票存在记录的第一天
     *
     * @param code the code 股票代码
     * @return the first day 数据库中股票存在记录的第一天
     */
    LocalDate getFirstDay(String code);
}
