package service.serviceImpl;

import dao.StockSituationDao;
import dao.daoImpl.StockSituationDaoImpl;
import service.StockSituationService;
import vo.StockSituationVO;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 *
 * 市场温度计信息获取
 */
public class StockSituationServiceImpl implements StockSituationService {

    StockSituationDao stockSituationDao;

    public StockSituationServiceImpl() {
        stockSituationDao = new StockSituationDaoImpl();
    }

    /**
     * 显示市场情况温度计.
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param date the date 用户选择日期
     * @return the stock situation vo   市场温度计的情况
     */
    @Override
    public StockSituationVO showStockSituation(LocalDate date) throws IOException {
       return new StockSituationVO(stockSituationDao.getStockSituation(date));
    }
}
