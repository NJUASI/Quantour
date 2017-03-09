package dao;

import po.StockSituationPO;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 */
public interface StockSituationDao {

    /**
     * 获取指定日期市场温度计数据
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param date 指定日期
     * @return StockSituationPO 指定日期市场温度计数据
     */
    StockSituationPO getStockSituation(LocalDate date) throws IOException;

}
