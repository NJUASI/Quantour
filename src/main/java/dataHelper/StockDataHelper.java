package dataHelper;

import po.StockPO;
import utilities.exceptions.UnhandleBlockTypeException;
import vo.StockPoolVO;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param stockCode 股票代码
     * @return 数据库中股票存在记录的起讫时间，List.get(0)为第一天，List.get(1)为最后一天
     * @throws IOException IO
     */
    List<LocalDate> getFirstAndLastDay(String stockCode) throws IOException;

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
     * @updateTime 2017/4/6
     * @return 所有交易日期
     */
    List<LocalDate> getDateWithData() throws IOException;


    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/4/15
     * @return 所有股票的版块有关的信息
     */
    List<StockPoolVO> getAllStockPool() throws IOException, UnhandleBlockTypeException;

}
