package com.edu.nju.asi.service.serviceImpl;

import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.dao.daoImpl.StockDaoImpl;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.service.PrivateStockService;
import com.edu.nju.asi.utilities.exceptions.PrivateStockExistedException;
import com.edu.nju.asi.utilities.exceptions.PrivateStockNotExistException;
import com.edu.nju.asi.utilities.exceptions.PrivateStockNotFoundException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/5/11.
 */
public class PrivateStockServiceImpl implements PrivateStockService {
    StockDao stockDao;

    public PrivateStockServiceImpl(StockDao stockDao) {
        this.stockDao = new StockDaoImpl();
    }

    /**
     * 显示用户的自选股信息列表
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName the user name 用户名称
     * @param date 用户选择日期
     * @return the iterator 自选股信息列表
     */
    @Override
    public List<Stock> getPrivateStocks(String userName, LocalDate date) throws IOException, PrivateStockNotFoundException {
        return stockDao.getPrivateStockData(userName,date);
    }

    /**
     * 获取用户的自选股票池
     *
     * @param userName 用户名称
     * @return 指定用户的自选股
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/17
     */
    @Override
    public List<String> getPrivateStockCodes(String userName) throws PrivateStockNotFoundException {
        return stockDao.getPrivateStockCodes(userName);
    }

    /**
     * 用户添加自选股
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @param stockCode 股票代码
     * @return 是否添加成功
     */
    @Override
    public boolean addPrivateStock(String userName, String stockCode) throws PrivateStockExistedException, PrivateStockNotFoundException {
        return stockDao.addPrivateStock(userName, stockCode);
    }

    /**
     * 用户删除自选股
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @param stockCode 股票代码
     * @return 是否删除成功
     */
    @Override
    public boolean deletePrivateStock(String userName, String stockCode) throws PrivateStockNotExistException, PrivateStockNotFoundException {
        return stockDao.deletePrivateStock(userName, stockCode);
    }
}
