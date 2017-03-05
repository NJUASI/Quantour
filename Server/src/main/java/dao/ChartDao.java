package dao;

import po.AveragePO;
import po.StockPO;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/3/4.
 */
public interface ChartDao {

    List<StockPO> getCandlestickData();

    List<StockPO> getCandlestickData(LocalDate start, LocalDate end);

    List<AveragePO> getAverageData();


}
