package com.edu.nju.asi.dataHelper;

import com.edu.nju.asi.model.StockSituation;
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
     * 获取指定日期市场温度计数据
     *
     * @author Byron Dong
     * @updateTime 2017/5/9
     * @param date 指定日期
     * @return StockSituation 指定日期市场温度计数据
     */
    StockSituation getStockSituation(LocalDate date);
}
