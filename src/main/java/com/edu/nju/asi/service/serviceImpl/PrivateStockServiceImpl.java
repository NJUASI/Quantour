package com.edu.nju.asi.service.serviceImpl;

import com.edu.nju.asi.dao.PrivateStockDao;
import com.edu.nju.asi.model.OptionalStockID;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.service.PrivateStockService;
import com.edu.nju.asi.utilities.exceptions.PrivateStockExistedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/5/11.
 */
@Service("PrivateStockService")
public class PrivateStockServiceImpl implements PrivateStockService {

    @Autowired
    PrivateStockDao privateStockDao;

    @Override
    public List<Stock> getPrivateStocks(String userName, LocalDate date) throws IOException {
        return privateStockDao.getPrivateStocks(userName, date);
    }

    @Override
    public boolean addPrivateStock(OptionalStockID privateStockID) throws PrivateStockExistedException {
        return privateStockDao.addPrivateStock(privateStockID);
    }

    @Override
    public boolean addPrivateStockAll(List<OptionalStockID> list) throws PrivateStockExistedException {
        return privateStockDao.addPrivateStockAll(list);
    }

    @Override
    public boolean deletePrivateStock(OptionalStockID privateStockID){
        return privateStockDao.deletePrivateStock(privateStockID);
    }

    @Override
    public boolean deletePrivateStockAll(List<OptionalStockID> list) {
        return privateStockDao.deletePrivateStockAll(list);
    }
}
