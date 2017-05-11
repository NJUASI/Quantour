package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dataHelper.StockSituationDataHelper;
import com.edu.nju.asi.dataHelper.dataHelperImpl.StockSituationDataHelperImpl;
import com.edu.nju.asi.model.StockSituation;
import com.edu.nju.asi.utilities.exceptions.NoSituationDataException;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by cuihua
 * Update time 2017/3/9
 */
public class StockSituationDaoImpl implements com.edu.nju.asi.dao.StockSituationDao {

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
    public StockSituation getStockSituation(LocalDate date) throws NoSituationDataException, IOException {
//        return this.stockSituationHelper.getStockSituation(date);
        return null;
    }
}
