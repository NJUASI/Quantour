package service;

import vo.PriceRiseOrFallVO;

import java.time.LocalDate;
import java.util.List;

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
    List<PriceRiseOrFallVO> getStockStituationData(LocalDate date);
}
