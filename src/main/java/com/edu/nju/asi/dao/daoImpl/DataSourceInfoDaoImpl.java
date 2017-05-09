package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.model.DataSourceInfo;
import com.edu.nju.asi.dao.DataSourceInfoDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by cuihua on 2017/3/30.
 */
@Repository
public class DataSourceInfoDaoImpl implements DataSourceInfoDao {

    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    /**
     * @param dataSourceInfo 要上传的信息
     * @return 上传成功
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean addDataSourceInfo(DataSourceInfo dataSourceInfo) {

        //如果getDataSource返回不为null，说明该用户的信息不能添加
        if(getDataSourceInfo(dataSourceInfo.getUserName())!=null){
            return false;
        }

        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(dataSourceInfo);

        transaction.commit();
        session.close();
        return true;
    }

    /**
     * @param dataSourceInfo 要修改的信息
     * @return 上传成功
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean updateDataSourceInfo(DataSourceInfo dataSourceInfo) {

        //如果getDataSource返回为null，说明该用户的信息尚未添加
        if(getDataSourceInfo(dataSourceInfo.getUserName())==null){
            return false;
        }

        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(dataSourceInfo);
        transaction.commit();
        session.close();
        return true;
    }

    /**
     * @param userName 用户名
     * @return 上传成功
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean deleteDataSourceInfo(String userName) {

        DataSourceInfo sourceInfo = getDataSourceInfo(userName);

        //如果getDataSource返回为null，说明该用户的信息不能删除
        if(sourceInfo==null){
            return false;
        }
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(sourceInfo);
        transaction.commit();
        session.close();
        return false;
    }

    /**
     * @return 用户自己上传的数据源格式信息，没有返回null
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public DataSourceInfo getDataSourceInfo(String userName) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        DataSourceInfo dataSourceInfo = (DataSourceInfo)session.get(DataSourceInfo.class,userName);
        transaction.commit();
        session.close();
        return dataSourceInfo;
    }
}