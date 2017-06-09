package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.UserDataHelper;
import com.edu.nju.asi.model.Strategy;
import com.edu.nju.asi.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Byron Dong on 2017/3/5.
 * Last updated by Harvey
 * Update time 2017/3/5
 * <p>
 * 对用户信息进行操作
 */
@Repository
public class UserDataHelperImpl implements UserDataHelper {

    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    /**
     * 添加用户信息
     *
     * @param user 用户信息载体
     * @return 是否成功添加用户
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean add(User user) {
        //用户信息已存在无须添加
        if (get(user.getUserName()) != null) {
            return false;
        }

        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    /**
     * 获取指定用户信息
     *
     * @param username 用户账号
     * @return 用户信息载体
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public User get(String username) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, username);
        transaction.commit();
        session.close();
        return user;
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息载体
     * @return 是否成功修改用户
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean update(User user) {
        //用户不存在，拒绝修改
        if (get(user.getUserName()) == null) {
            return false;
        }

        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
        return true;
    }

    /**
     * 获取已存在的所有用户名称
     *
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<String> getAllUserNames() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select userName from User";
        List<String> result = session.createQuery(hql).list();
        transaction.commit();
        session.close();
        return result;
    }

    /**
     * 更新策略信息
     *
     * @param strategy 策略信息载体
     * @return 是否成功更新策略
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     */
    @Override
    public boolean updateStrategy(Strategy strategy) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, strategy.getCreator());
        if (!isContain(user.getStrategies(), strategy)) {
            transaction.commit();
            session.close();
            return false;
        }

        for (Strategy strategy1 : user.getStrategies()) {
            if (strategy1.getStrategyID().equals(strategy.getStrategyID())) {
                strategy1.setContent(strategy.getContent());
                strategy1.setDescription(strategy.getDescription());
                strategy1.setPrivate(strategy.isPrivate());
                strategy1.setTraceBackInfo(strategy.getTraceBackInfo());
                break;
            }
        }
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    /**
     * 删除策略信息
     *
     * @param userName   用户名
     * @param strategyID 策略ID
     * @return 是否成功删除策略
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     */
    @Override
    public boolean deleteStrategy(String userName, String strategyID) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, userName);
        List<Strategy> strategies = user.getStrategies();
        transaction.commit();
        session.close();

        for (Strategy strategy : strategies) {
            if (strategy.getStrategyID().equals(strategyID)) {
                if (strategy.getCreator().equals(userName)) {
                    return this.deleteStrategyByCreator(strategyID);
                } else {
                    return this.deleteStrategyByChecker(userName, strategyID);
                }
            }
        }
        return false;
    }

    /**
     * 获取策略信息
     *
     * @param userName 用户名
     * @return Strategy 策略信息载体
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/2
     */
    @Override
    public List<Strategy> getStrategy(String userName) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, userName);
        List<Strategy> strategies = user.getStrategies();
        transaction.commit();
        session.close();
        return strategies;
    }

    /**
     * 添加策略信息(创建者)
     *
     * @param strategy 策略信息载体
     * @return 是否成功添加策略
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     */
    public boolean addStrategyByCreator(Strategy strategy) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        if (session.get(Strategy.class, strategy.getStrategyID()) != null) {
            transaction.commit();
            session.close();
            return false;
        }
        session.save(strategy);
        transaction.commit();
        session.close();
        return addStrategyByChecker(strategy.getCreator(), strategy.getStrategyID());
    }

    /**
     * 添加策略信息（订阅者）
     *
     * @param userName 用户名
     * @param strategyID 策略ID
     * @return 是否成功添加策略
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     */
    public boolean addStrategyByChecker(String userName, String strategyID) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, userName);
        Strategy strategy = session.get(Strategy.class,strategyID);

        user.getStrategies().add(strategy);
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    private boolean deleteStrategyByCreator(String strategyID) {
        boolean result = false;
        List<String> userNames = this.getAllUserNames();
        for (String userName : userNames) {
            deleteStrategyByChecker(userName, strategyID);
        }
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Strategy strategy = session.get(Strategy.class, strategyID);
        if (strategy != null) {
            session.delete(strategy);
            result = true;
        }

        transaction.commit();
        session.close();
        return result;
    }

    private boolean deleteStrategyByChecker(String userName, String strategyID) {
        boolean result = false;
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, userName);
        for (Strategy strategy : user.getStrategies()) {
            if (strategy.getStrategyID().equals(strategyID)) {
                user.getStrategies().remove(strategy);
                result = true;
                break;
            }
        }
        session.save(user);
        transaction.commit();
        session.close();
        return result;
    }

    private boolean isContain(List<Strategy> strategies, Strategy strategy) {
        for (Strategy strategy1 : strategies) {
            if (strategy1.getStrategyID().equals(strategy.getStrategyID())) {
                return true;
            }
        }
        return false;
    }
}