package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.StockSituationDataHelper;
import com.edu.nju.asi.model.StockSituation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


/**
 * Created by Byron Dong on 2017/3/5.
 * Last updated by cuihua
 * Update time 2017/3/9
 *
 * 具体实现其功能
 */
@Repository
public class StockSituationDataHelperImpl implements StockSituationDataHelper {

    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    /**
     * 获取指定日期市场温度计数据
     *
     * @param date 指定日期
     * @return StockSituation 指定日期市场温度计数据
     * @author Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public StockSituation getStockSituation(LocalDate date) {
        return null;
    }
}
