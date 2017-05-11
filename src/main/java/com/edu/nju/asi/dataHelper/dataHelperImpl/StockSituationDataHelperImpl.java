package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.StockSituationDataHelper;
import com.edu.nju.asi.model.StockSituation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        StockSituation stockSituation = (StockSituation)session.get(StockSituation.class,date);
        transaction.commit();
        session.close();
        return stockSituation;
    }

    /**
     * 修改指定日期市场温度计数据
     *
     * @param stockSituation 指指定日期市场温度计数据
     * @return StockSituation 指定日期市场温度计数据
     * @author Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean updateStockSituation(StockSituation stockSituation) {
        if(getStockSituation(stockSituation.getDate())==null){
            return false;
        }

        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(stockSituation);
        transaction.commit();
        session.close();
        return true;
    }

    /**
     * 添加指定日期市场温度计数据
     *
     * @param stockSituation 指指定日期市场温度计数据
     * @return StockSituation 指定日期市场温度计数据
     * @author Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean addStockSituation(StockSituation stockSituation) {
        if(getStockSituation(stockSituation.getDate())!=null){
            return false;
        }

        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(stockSituation);
        transaction.commit();
        session.close();
        return true;
    }
}
