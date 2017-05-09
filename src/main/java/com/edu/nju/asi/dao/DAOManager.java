package com.edu.nju.asi.dao;

import com.edu.nju.asi.utilities.util.ApplicationContextHelper;
import org.springframework.context.ApplicationContext;

/**
 * Created by Byron Dong on 2017/5/9.
 */
public class DAOManager {

    public final static DataSourceInfoDao dataSourceInfoDao;

    public final static  StockDao stockDao;

    public final static  StockSearchDao stockSearchDao;

    public final static  StockSituationDao stockSituationDao;

    public final static UserDao userDao;

    static {
        ApplicationContext applicationContext = ApplicationContextHelper.getApplicationContext();
        dataSourceInfoDao = applicationContext.getBean(DataSourceInfoDao.class);
        stockDao = applicationContext.getBean(StockDao.class);
        stockSearchDao = applicationContext.getBean(StockSearchDao.class);
        stockSituationDao = applicationContext.getBean(StockSituationDao.class);
        userDao = applicationContext.getBean(UserDao.class);
    }
}
