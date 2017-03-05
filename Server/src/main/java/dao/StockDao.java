package dao;

import po.AveragePO;
import po.StockPO;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/3/4.
 */
public interface StockDao {


    List<StockPO> getStockData(LocalDate start, LocalDate end, String code);

    List<StockPO> getStockData(String code);


}
