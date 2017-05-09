package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.StockSituationDao;
import com.edu.nju.asi.model.StockSituation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by cuihua
 * Update time 2017/3/9
 */
@Repository
public class StockSituationDaoImpl implements StockSituationDao {

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
