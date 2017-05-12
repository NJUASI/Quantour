package com.edu.nju.asi.service;

import com.edu.nju.asi.model.StockSituation;
import com.edu.nju.asi.utilities.exceptions.NoSituationDataException;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Harvey
 * Update time 2017/3/5
 *
 * 市场情况温度计
 */
public interface StockSituationService{


    /**
     * Gets stock stituation data. 获取市场温度计的情况
     *
     * @param date the date
     * @return the stock stituation data
     */
    StockSituation getStockStituation(LocalDate date) throws NoSituationDataException, IOException;
}
