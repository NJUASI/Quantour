package dao;

import po.StockSituationPO;

import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 */
public interface StockSituationDao {

    StockSituationPO getStockSituation(LocalDate date);

}
