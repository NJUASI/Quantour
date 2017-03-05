package dataHelper;

import po.StockPO;

import java.util.List;

/**
 * Created by Byron Dong on 2017/3/5.
 */
public interface StockDataHelper {

    List<StockPO> getStockData(String stockCode);
}
