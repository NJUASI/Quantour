package dataHelper;

import po.StockSituationPO;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 */
public interface StockSituationDataHelper {

    /**
     * 获取指定日期所有数据
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param date  指定股票代码
     * @return List<StockPO> 指定股票所有数据
     */
    StockSituationPO getStockSituation(LocalDate date) throws IOException;
}
