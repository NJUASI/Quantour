package dataHelper;

import po.StockPO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Byron Dong on 2017/3/5.
 */
public interface StockDataHelper {

    /**
     * 获取指定股票所有数据
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param stockCode  指定股票代码
     * @return List<StockPO> 指定股票所有数据
     */
    List<StockPO> getStockRecords(String stockCode) throws IOException;

    /**
     * 获取指定日期的所有股票数据
     *
     * @author cuihua
     * @updateTime 2017/3/6
     * @param date  指定日期
     * @return List<StockPO> 指定日期的所有股票数据
     */
    List<StockPO> getStockRecords(LocalDate date) throws IOException;

    /**
     * Gets first day.  获取数据库中股票存在记录的第一天
     *
     * @param code the code 股票代码
     * @return the first day 数据库中股票存在记录的第一天
     */
    LocalDate getFirstDay(String code);
}
