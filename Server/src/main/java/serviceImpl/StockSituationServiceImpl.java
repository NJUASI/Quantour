package serviceImpl;

import dao.StockSituationDao;
import dao.daoImpl.StockSituationDaoImpl;
import service.StockSituationService;
import vo.StockSituationVO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 */
public class StockSituationServiceImpl extends UnicastRemoteObject implements StockSituationService {

    StockSituationDao stockSituationDao;

    protected StockSituationServiceImpl() throws RemoteException {
        stockSituationDao = new StockSituationDaoImpl();
    }

    /**
     * 显示市场情况温度计.
     *
     * @param date the date
     * @return the stock situation vo
     * @throws RemoteException the remote exception
     */
    @Override
    public StockSituationVO showStockSituation(LocalDate date) throws RemoteException {
       return new StockSituationVO(stockSituationDao.getStockSituation(date));
    }
}
