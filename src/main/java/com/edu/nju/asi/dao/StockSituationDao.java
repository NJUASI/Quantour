package com.edu.nju.asi.dao;

import com.edu.nju.asi.po.StockSituationPO;
import com.edu.nju.asi.utilities.exceptions.NoSituationDataException;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Byron Dong
 * Update time 2017/3/5
 */
public interface StockSituationDao {

    /**
     * 获取指定日期市场温度计数据
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param date 指定日期
     * @return StockSituationPO 指定日期市场温度计数据
     * @throws IOException IO
     */
    StockSituationPO getStockSituation(LocalDate date) throws IOException, NoSituationDataException;

}
