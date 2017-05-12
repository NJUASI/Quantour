package com.edu.nju.asi.service.serviceImpl;

import com.edu.nju.asi.dao.PrivateStockDao;
import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.dao.daoImpl.PrivateStockDaoImpl;
import com.edu.nju.asi.dao.daoImpl.StockDaoImpl;
import com.edu.nju.asi.model.PrivateStockID;
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

    PrivateStockDao privateStockDao;

    public PrivateStockServiceImpl(StockDao stockDao) {
        this.privateStockDao = new PrivateStockDaoImpl();
    }

    @Override
    public List<Stock> getPrivateStocks(String userName, LocalDate date) throws IOException, PrivateStockNotFoundException {
        return privateStockDao.getPrivateStocks(userName, date);
    }
    @Override
    public List<String> getPrivateStockCodes(String userName) throws PrivateStockNotFoundException {
        return privateStockDao.getPrivateStockCodes(userName);
    }

    @Override
    public boolean addPrivateStock(PrivateStockID privateStockID) throws PrivateStockExistedException, PrivateStockNotFoundException {
        return privateStockDao.addPrivateStock(privateStockID);
    }

    @Override
    public boolean addPrivateStockAll(List<PrivateStockID> list) throws PrivateStockExistedException {
        return privateStockDao.addPrivateStockAll(list);
    }

    @Override
    public boolean deletePrivateStock(PrivateStockID privateStockID) throws PrivateStockNotExistException, PrivateStockNotFoundException {
        return privateStockDao.deletePrivateStock(privateStockID);
    }

    @Override
    public boolean deletePrivateStockAll(List<PrivateStockID> list) {
        return privateStockDao.deletePrivateStockAll(list);
    }
}
