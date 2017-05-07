package com.edu.nju.asi.dataHelper;

import com.edu.nju.asi.po.StockSituationPO;
import com.edu.nju.asi.utilities.exceptions.NoSituationDataException;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Byron Dong
 * Update time 2017/3/5
 *
 * 确定接口
 */
public interface StockSituationDataHelper {

    /**
     * 获取指定日期所有数据
     *
     * @author Byron Dong
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/5
     * @param date  指定股票代码
     * @return 指定股票所有数据
     * @throws IOException IO
     */
    StockSituationPO getStockSituation(LocalDate date) throws IOException, NoSituationDataException;
}
