package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.PrivateStockDataHelper;
import com.edu.nju.asi.model.PrivateStock;
import com.edu.nju.asi.model.PrivateStockID;
import com.edu.nju.asi.utilities.exceptions.PrivateStockExistedException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/5/11.
 */
@Repository
public class PrivateStockDataHelperImpl implements PrivateStockDataHelper {

    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    /**
     * 获取自选股
     *
     * @param userName
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<PrivateStock> getPrivateStock(String userName) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from PrivateStock where privateStockID.userName =:userName";
        Query query = session.createQuery(hql);
        query.setParameter("userName", userName);
        List<PrivateStock> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    /**
     * 添加自选股
     *
     * @param privateStockID
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean addPrivateStock(PrivateStockID privateStockID) {
        if (isExist(privateStockID)) {
            return false;
        }

        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PrivateStock privateStock = new PrivateStock();
        privateStock.setPrivateStockID(privateStockID);
        session.save(privateStock);
        transaction.commit();
        session.close();
        return true;
    }

    /**
     * 添加自选股列表
     *
     * @param list
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean addPrivateStockAll(List<PrivateStockID> list) throws PrivateStockExistedException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<PrivateStock> exceptionStock = new ArrayList<>();

        try {
            for (int i = 0; i < list.size(); i++) {
                PrivateStock privateStock = (PrivateStock) session.get(PrivateStock.class, list.get(i));
                if (privateStock == null) {
                    privateStock = new PrivateStock();
                    privateStock.setPrivateStockID(list.get(i));
                    session.save(privateStock);
                } else {
                    privateStock = new PrivateStock();
                    privateStock.setPrivateStockID(list.get(i));
                    exceptionStock.add(privateStock);
                }

                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }

        if (exceptionStock.isEmpty()) {
            return true;
        } else {
            throw new PrivateStockExistedException(exceptionStock);
        }
    }

    /**
     * 删除自选股
     *
     * @param privateStockID
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean deletePrivateStock(PrivateStockID privateStockID) {
        if (!isExist(privateStockID)) {
            return false;
        }

        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        PrivateStock privateStock = new PrivateStock();
        privateStock.setPrivateStockID(privateStockID);
        session.delete(privateStock);
        transaction.commit();
        session.close();
        return true;
    }

    /**
     * 删除自选股列表
     *
     * @param list
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean deletePrivateStockAll(List<PrivateStockID> list) {
        String hql = "delete from PrivateStock where privateStockID =:id";
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        boolean result = true;

        try {
            for (PrivateStockID privateStockID : list) {
                Query query = session.createQuery(hql);
                query.setParameter("id", privateStockID);
                query.executeUpdate();
            }
            transaction.commit();
        } catch (Exception e) {
            result = false;
            transaction.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * 用于判断指定自选股是否存在
     *
     * @param privateStockID
     * @return boolean
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     */
    private boolean isExist(PrivateStockID privateStockID) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        boolean result = false;

        PrivateStock privateStock = (PrivateStock) session.get(PrivateStock.class, privateStockID);
        if (privateStock != null) {
            result = true;
        }
        transaction.commit();
        session.close();
        return result;
    }
}
