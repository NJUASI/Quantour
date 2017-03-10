package service.serviceImpl;

import dao.StockSituationDao;
import dao.daoImpl.StockSituationDaoImpl;
import service.StockSituationService;
import vo.StockSituationVO;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Harvey
 * Update time 2017/3/5
 *
 * 市场温度计信息获取
 */
public class StockSituationServiceImpl implements StockSituationService {

    StockSituationDao stockSituationDao;

    public StockSituationServiceImpl() {
        stockSituationDao = new StockSituationDaoImpl();
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
    public StockSituationVO showStockSituation(LocalDate date) throws IOException {
       return new StockSituationVO(stockSituationDao.getStockSituation(date));
    }
}
