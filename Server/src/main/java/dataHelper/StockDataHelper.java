package dataHelper;

import po.StockPO;

import java.io.IOException;
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
    List<StockPO> getStock(String stockCode) throws IOException;
}
