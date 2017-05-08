package com.edu.nju.asi.dataHelperVersion2.dataHelperImpl;

import com.edu.nju.asi.dataHelperVersion2.DataSourceDataHelper;
import com.edu.nju.asi.model.DataSourceInfo;
import com.edu.nju.asi.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

/**
 * Created by Byron Dong on 2017/5/8.
 */
public class DataSourceDataHelperImpl implements DataSourceDataHelper {

    /**
     * @return 上传成功
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/30
     */
    @Override
    public boolean upload(String userName, String fileSize, LocalDateTime localDateTime){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        DataSourceInfo dataSourceInfo = new DataSourceInfo();
        dataSourceInfo.setUserName(userName);
        dataSourceInfo.setFileSize(fileSize);
        dataSourceInfo.setUploadTime(localDateTime.toString());
        session.save(dataSourceInfo);
        transaction.commit();
        HibernateUtil.closeSession();
        return true;
    }

    /**
     * @return 用户自己上传的数据源格式信息，没有返回null
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/4/13
     */
    @Override
    public DataSourceInfo getMyDataSource(String userName){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        DataSourceInfo dataSourceInfo = (DataSourceInfo)session.get(DataSourceInfo.class,userName);
        transaction.commit();
        HibernateUtil.closeSession();
        return dataSourceInfo;
    }
}
