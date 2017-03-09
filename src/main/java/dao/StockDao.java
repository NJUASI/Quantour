package dao;

import po.StockPO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by cuihua
 * Update time 2017/3/9
 *
 * 整理接口，完善注释
 */
public interface StockDao {

    /*
    股票数据
     */
    /**
     * 获取特定时间段内的指定股票所有数据
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     * @param start 时间区域的小值
     * @param end 时间区域的大值
     * @param stockCode 指定股票代码
     * @return 特定时间段内的指定股票所有数据
     */
    List<StockPO> getStockData(LocalDate start, LocalDate end, String stockCode);

    /**
     * 取指定股票的所有数据，没有返回null
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @param stockCode 指定的股票代码
     * @return 此股票的所有数据
     */
    List<StockPO> getStockData(String stockCode);

    /**
     * 获取特定日期的所有股票所有数据
     *
     * @author Harvey
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param date 选定的日期
     * @return 特定日期的保存的所有股票
     */
    List<StockPO> getStockData(LocalDate date) throws IOException;




    /*
    自选股
     */
    /**
     * 获取用户自选股票的数据
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @param date 股票代码
     * @return 指定用户的自选股票
     */
    List<StockPO> getPrivateStocks(String userName, LocalDate date);

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
    boolean addPrivateStock(String userName, String stockCode);

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
    boolean deletePrivateStock(String userName, String stockCode);




    /*
    提供给UserService
     */
    /**
     * 用户注册时，给用户新建一个对应用户名的资源文件
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @param userName 用户名称
     * @return 是否创建成功
     */
    boolean createPrivateDir(String userName);




    /*
    暂定
     */
    /**
     * 获取数据库中股票存在记录的第一天
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param code 股票代码
     * @return 数据库中股票存在记录的第一天
     */
    LocalDate getFirstDay(String code);
}
