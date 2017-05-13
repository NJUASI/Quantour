package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.PrivateStockDao;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.PrivateStockDataHelper;
import com.edu.nju.asi.dataHelper.StockDataHelper;
import com.edu.nju.asi.model.PrivateStock;
import com.edu.nju.asi.model.PrivateStockID;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.PrivateStockExistedException;
import com.edu.nju.asi.utilities.exceptions.PrivateStockNotExistException;
import com.edu.nju.asi.utilities.exceptions.PrivateStockNotFoundException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihua on 2017/5/11.
 */
@Component("PrivateStockDao")
public class PrivateStockDaoImpl implements PrivateStockDao {

    StockDataHelper stockDataHelper;
    PrivateStockDataHelper privateStockDataHelper;

    public PrivateStockDaoImpl() {
        stockDataHelper = HelperManager.stockDataHelper;
        this.privateStockDataHelper = HelperManager.privateStockDataHelper;
    }

    @Override
    public List<Stock> getPrivateStocks(String userName, LocalDate date) throws IOException, PrivateStockNotFoundException {
        List<Stock> result = new ArrayList<>();

        List<PrivateStock> privateStockIDList = privateStockDataHelper.getPrivateStock(userName);
        for (PrivateStock temp : privateStockIDList) {
            PrivateStockID id = temp.getPrivateStockID();
            result.add(stockDataHelper.getStockData(id.getStockCode(), date));
        }

        return result;
    }

    @Override
    public boolean addPrivateStock(PrivateStockID privateStockID) throws PrivateStockExistedException, PrivateStockNotFoundException {
        return privateStockDataHelper.addPrivateStock(privateStockID);
    }

    @Override
    public boolean addPrivateStockAll(List<PrivateStockID> list) throws PrivateStockExistedException {
        return privateStockDataHelper.addPrivateStockAll(list);
    }

    @Override
    public boolean deletePrivateStock(PrivateStockID privateStockID) throws PrivateStockNotExistException, PrivateStockNotFoundException {
        return deletePrivateStock(privateStockID);
    }

    @Override
    public boolean deletePrivateStockAll(List<PrivateStockID> list) {
        return deletePrivateStockAll(list);
    }
}
