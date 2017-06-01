package com.edu.nju.asi.dataHelper;

import com.edu.nju.asi.utilities.util.ApplicationContextHelper;
import org.springframework.context.ApplicationContext;

/**
 * Created by Byron Dong on 2017/5/10.
 */
public class HelperManager {

    public final static  StockDataHelper stockDataHelper;

    public final static  StockSearchDataHelper stockSearchDataHelper;

    public final static UserDataHelper userDataHelper;

    public final static PrivateStockDataHelper privateStockDataHelper;

    public final static BaseStockDataHelper baseStockDataHelper;

    static {
        ApplicationContext applicationContext = ApplicationContextHelper.getApplicationContext();
        stockDataHelper = applicationContext.getBean(StockDataHelper.class);
        stockSearchDataHelper = applicationContext.getBean(StockSearchDataHelper.class);
        userDataHelper = applicationContext.getBean(UserDataHelper.class);
        privateStockDataHelper = applicationContext.getBean(PrivateStockDataHelper.class);
        baseStockDataHelper = applicationContext.getBean(BaseStockDataHelper.class);
    }

}
