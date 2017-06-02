package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.StrategyDataHelper;
import com.edu.nju.asi.model.Strategy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Byron Dong on 2017/6/2.
 */
@Repository
public class SrategyDataHelperImpl implements StrategyDataHelper {

    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    /**
     * 获取所有策略
     *
     * @return List<Strategy> 是否成功添加用户
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/2
     */
    @Override
    public List<Strategy> getAllStrategy() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Strategy";
        Query query = session.createQuery(hql);
        List<Strategy> result = query.list();
        if(result == null || result.isEmpty()){
            transaction.commit();
            session.close();
            return null;
        }
        transaction.commit();
        session.close();
        return result;
    }

    /**
     * 获取单个策略
     *
     * @param strategyID
     * @return 指定策略
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/2
     */
    @Override
    public Strategy getStrategy(String strategyID) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Strategy strategy = (Strategy)session.get(Strategy.class,strategyID);
        transaction.commit();
        session.close();
        return strategy;
    }

    /**
     * 判断策略是否已经创建
     *
     * @param strategyID
     * @return boolean true则已存在
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/2
     */
    @Override
    public boolean isExist(String strategyID) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Strategy strategy = session.get(Strategy.class,strategyID);
        if(strategy == null){
            return false;
        }
        return true;
    }
}
