package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.PrivateStockDao;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.PrivateStockDataHelper;
import com.edu.nju.asi.dataHelper.StockDataHelper;
import com.edu.nju.asi.model.PrivateStock;
import com.edu.nju.asi.model.OptionalStockID;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.PrivateStockExistedException;
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
    public List<Stock> getPrivateStocks(String userName, LocalDate date) throws IOException {
        List<Stock> result = new ArrayList<>();

        List<PrivateStock> privateStockIDList = privateStockDataHelper.getPrivateStock(userName);
        for (PrivateStock temp : privateStockIDList) {
            OptionalStockID id = temp.getOptionalStockID();
            result.add(stockDataHelper.getStockData(id.getStockCode(), date));
        }

        return result;
    }

    @Override
    public boolean addPrivateStock(OptionalStockID privateStockID) throws PrivateStockExistedException {
        return privateStockDataHelper.addPrivateStock(privateStockID);
    }

    @Override
    public boolean addPrivateStockAll(List<OptionalStockID> list) throws PrivateStockExistedException {
        return privateStockDataHelper.addPrivateStockAll(list);
    }

    @Override
    public boolean deletePrivateStock(OptionalStockID privateStockID) {
        return privateStockDataHelper.deletePrivateStock(privateStockID);
    }

    @Override
    public boolean deletePrivateStockAll(List<OptionalStockID> list) {
        return privateStockDataHelper.deletePrivateStockAll(list);
    }
}
