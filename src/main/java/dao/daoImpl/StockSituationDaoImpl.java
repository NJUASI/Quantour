package dao.daoImpl;

import dataHelper.StockSituationDataHelper;
import dataHelper.dataHelperImpl.StockSituationDataHelperImpl;
import po.StockSituationPO;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by cuihua
 * Update time 2017/3/9
 */
public class StockSituationDaoImpl implements dao.StockSituationDao {

    //市场温度计信息获取的helper对象
    private StockSituationDataHelper stockSituationHelper;

    /**
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     */
    public StockSituationDaoImpl() {
        this.stockSituationHelper = new StockSituationDataHelperImpl();
    }

    /**
     * 获取指定日期市场温度计数据
     *
     * @author Byron Dong
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param date 指定日期
     * @return 指定日期市场温度计数据
     * @throws IOException IO
     */
    @Override
    public StockSituationPO getStockSituation(LocalDate date) throws IOException {
        return this.stockSituationHelper.getStockSituation(date);
    }
}
