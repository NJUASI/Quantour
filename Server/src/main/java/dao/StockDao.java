package dao;

import po.AveragePO;
import po.StockPO;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/3/4.
 */
public interface StockDao {


    /**
     * 获取特定时间段内的所有指定股票所有数据
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param start 时间区域的小值
     * @param end 时间区域的大值
     * @param code  指定股票代码
     * @return List<StockPO> 特定时间段内的所有指定股票所有数据
     */
    List<StockPO> getStockData(LocalDate start, LocalDate end, String code);

    /**
     * 获取指定股票所有数据
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param code  指定股票代码
     * @return List<StockPO> 特定时间段内的所有指定股票所有数据
     */
    List<StockPO> getStockData(String code);


}
