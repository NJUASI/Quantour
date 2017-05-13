package com.edu.nju.asi.service.serviceImpl;

import com.edu.nju.asi.dao.StockSituationDao;
import com.edu.nju.asi.dao.daoImpl.StockSituationDaoImpl;
import com.edu.nju.asi.model.StockSituation;
import com.edu.nju.asi.service.StockSituationService;
import com.edu.nju.asi.utilities.exceptions.NoSituationDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Harvey
 * Update time 2017/3/5
 *
 * 市场温度计信息获取
 */
@Service("StockSituationService")
public class StockSituationServiceImpl implements StockSituationService {

    @Autowired
    StockSituationDao stockSituationDao;

    public StockSituationServiceImpl() {
//        stockSituationDao = new StockSituationDaoImpl();
    }

    /**
     * 显示市场情况温度计
     *
     * @auther cuihua
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param date 用户选择的日期
     * @return 所选当日的市场情况温度计的信息
     * @throws IOException IO
     */
    @Override
    public StockSituation getStockStituation(LocalDate date) throws NoSituationDataException, IOException {
        return stockSituationDao.getStockSituation(date);
    }
}
