package dao.daoImpl;

import dataHelper.StockSituationDataHelper;
import dataHelper.dataHelperImpl.StockSituationDataHelperImpl;
import po.StockSituationPO;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 */
public class StockSituationDaoImpl implements dao.StockSituationDao {

    //市场温度计信息获取的helper对象
    private StockSituationDataHelper stockSituationHelper;

    /**
     * @author Byron Dong
     * @updateTime 2017/3/5 构造函数，初始化成员变量stockSituationHelper
     */
    public StockSituationDaoImpl() {
        this.stockSituationHelper = new StockSituationDataHelperImpl();
    }

    /**
     * 获取指定日期市场温度计数据
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param date 指定日期
     * @return StockSituationPO 指定日期市场温度计数据
     */
    @Override
    public StockSituationPO getStockSituation(LocalDate date) throws IOException {
        return this.stockSituationHelper.getStockSituation(date);
    }
}
