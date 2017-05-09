package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.model.DataSourceInfo;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.edu.nju.asi.dao.DataSourceDao;
import com.edu.nju.asi.dataHelper.DataSourceDataHelper;
import com.edu.nju.asi.dataHelper.dataHelperImpl.DataSourceDataHelperImpl;
import com.edu.nju.asi.po.DataSourceInfoPO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;

/**
 * Created by cuihua on 2017/3/30.
 */
@Repository
public class DataSourceDaoImpl implements DataSourceDao {

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
    public boolean addDataSource(DataSourceInfo dataSourceInfo) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        return false;
    }

    /**
     * @param dataSourceInfo 要修改的信息
     * @return 上传成功
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean updateDataSource(DataSourceInfo dataSourceInfo) {
        return false;
    }

    /**
     * @param userName 用户名
     * @return 上传成功
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean deleteDataSource(String userName) {
        return false;
    }

    /**
     * @return 用户自己上传的数据源格式信息，没有返回null
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public DataSourceInfo getDataSource() {
        return null;
    }
}