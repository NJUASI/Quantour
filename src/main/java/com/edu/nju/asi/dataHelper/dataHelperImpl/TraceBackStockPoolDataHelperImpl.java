package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.TraceBackStockPoolDataHelper;
import com.edu.nju.asi.model.TraceBackStockID;
import com.edu.nju.asi.model.TraceBackStockPool;
import com.edu.nju.asi.utilities.exceptions.TraceBackStockExistedException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/5/14.
 */
@Repository
public class TraceBackStockPoolDataHelperImpl implements TraceBackStockPoolDataHelper {

    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    /**
     * 获取指定用户名的回测股池
     *
     * @param userName
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<TraceBackStockPool> getTraceBackStockPool(String userName) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from TraceBackStockPool where traceBackStockID.userName =:userName";
        Query query = session.createQuery(hql);
        query.setParameter("userName", userName);
        List<TraceBackStockPool> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    /**
     * 添加单只回测股
     *
     * @param traceBackStockID
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean addTraceBackStock(TraceBackStockID traceBackStockID) {
        if (isExist(traceBackStockID)) {
            return false;
        }

        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TraceBackStockPool traceBackStockPool = new TraceBackStockPool();
        traceBackStockPool.setTraceBackStockID(traceBackStockID);
        session.save(traceBackStockPool);
        transaction.commit();
        session.close();
        return true;
    }

    /**
     * 添加回测股列表
     *
     * @param list
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean addTraceBackStockAll(List<TraceBackStockID> list) throws TraceBackStockExistedException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<TraceBackStockPool> exceptionStock = new ArrayList<>();

        try {
            for (int i = 0; i < list.size(); i++) {
                TraceBackStockPool traceBackStockPool = (TraceBackStockPool) session.get(TraceBackStockPool.class, list.get(i));
                if (traceBackStockPool == null) {
                    traceBackStockPool = new TraceBackStockPool();
                    traceBackStockPool.setTraceBackStockID(list.get(i));
                    session.save(traceBackStockPool);
                } else {
                    traceBackStockPool = new TraceBackStockPool();
                    traceBackStockPool.setTraceBackStockID(list.get(i));
                    exceptionStock.add(traceBackStockPool);
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
            throw new TraceBackStockExistedException(exceptionStock);
        }
    }

    /**
     * 删除单只回测股
     *
     * @param traceBackStockID
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean deleteTraceBackStock(TraceBackStockID traceBackStockID) {
        if (!isExist(traceBackStockID)) {
            return false;
        }

        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TraceBackStockPool traceBackStockPool = new TraceBackStockPool();
        traceBackStockPool.setTraceBackStockID(traceBackStockID);
        session.delete(traceBackStockPool);
        transaction.commit();
        session.close();
        return true;
    }

    /**
     * 删除回测股列表
     *
     * @param list
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean deleteTraceBackStockAll(List<TraceBackStockID> list) {
        String hql = "delete from TraceBackStockPool where traceBackStockID =:id";
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        boolean result = true;

        try {
            for (TraceBackStockID traceBackStockID : list) {
                Query query = session.createQuery(hql);
                query.setParameter("id", traceBackStockID);
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
     * 用于判断指定回测股是否存在
     *
     * @param traceBackStockID
     * @return boolean
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     */
    private boolean isExist(TraceBackStockID traceBackStockID) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        boolean result = false;

        TraceBackStockPool traceBackStockPool = (TraceBackStockPool) session.get(TraceBackStockPool.class, traceBackStockID);
        if (traceBackStockPool != null) {
            result = true;
        }
        transaction.commit();
        session.close();
        return result;
    }
}
