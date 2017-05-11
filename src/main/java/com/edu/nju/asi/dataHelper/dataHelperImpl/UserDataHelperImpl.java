package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.UserDataHelper;
import com.edu.nju.asi.model.Stock;
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
        if(get(user.getUserName())!=null){
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

        User user = (User)session.get(User.class,username);
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
        if(get(user.getUserName())==null){
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

        String hql = "select userName from User ";
        List<String> result = session.createQuery(hql).list();
        transaction.commit();
        session.close();
        return result;
    }

    /**
     * 获取已存在的所有用户名称
     *
     * @param userName
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<Stock> getPrivateStock(String userName) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user =(User)session.get(User.class,userName);
        List<Stock> stocks = user.getPrivateStock();
        transaction.commit();
        session.close();
        return stocks;
    }

    /**
     * 添加自选股
     *
     * @param userName
     * @param stock
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean addPrivateStock(String userName, Stock stock) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = (User)session.get(User.class,userName);
        List<Stock> privateStocks  = user.getPrivateStock();

        Stock tempStock = isExistStock(privateStocks,stock);
        if(tempStock == null){
            privateStocks.add(stock);
            user.setPrivateStock(privateStocks);
            session.save(user);
        } else{
            return false;
        }

        transaction.commit();
        session.close();
        return true;
    }

    /**
     * 删除自选股
     *
     * @param userName
     * @param stock
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean deletePrivateStock(String userName, Stock stock) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = (User)session.get(User.class,userName);
        List<Stock> privateStocks  = user.getPrivateStock();

        Stock tempStock = isExistStock(privateStocks,stock);
        if(tempStock != null){
            privateStocks.remove(tempStock);
            user.setPrivateStock(privateStocks);
            session.save(user);
        } else{
            return false;
        }

        transaction.commit();
        session.close();
        return true;
    }

    /**
     * 判断自选股是否已经存在
     *
     * @param privateStocks
     * @param stock
     * @return boolean
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    private Stock isExistStock(List<Stock> privateStocks, Stock stock){
        for(Stock privateStock:privateStocks){
            if(privateStock.getStockID().getCode().equals(stock.getStockID().getCode())){
                return privateStock;
            }
        }
        return null;
    }

}