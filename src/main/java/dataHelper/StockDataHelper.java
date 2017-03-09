package dataHelper;

import po.StockPO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Byron Dong on 2017/3/5.
 * Last updated by cuihua
 * Update time 2017/3/9
 *
 * 去除多余接口
 */
public interface StockDataHelper {

    /**
     * 获取指定日期的所有股票数据
     *
     * @author Byron Dong
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/5
     * @param stockCode  指定日期
     * @return List<StockPO> 指定日期的所有股票数据
     * @throws IOException IO
     */
    List<StockPO> getStockRecords(String stockCode) throws IOException;

    /**
     * 获取指定日期的所有股票数据
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/6
     * @param date  指定日期
     * @return List<StockPO> 指定日期的所有股票数据
     * @throws IOException IO
     */
    List<StockPO> getStockRecords(LocalDate date) throws IOException;

    /**
     * 获取数据库中股票存在记录的第一天
     *
     * @author Byron Dong
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/8
     * @param code 股票代码
     * @return 数据库中股票存在记录的第一天
     * @throws IOException IO
     */
    LocalDate getFirstDay(String code);
}
