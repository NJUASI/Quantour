package service;

import vo.StockSituationVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 *
 * 市场情况温度计
 */
public interface StockSituationService extends Remote{

    /**
     * 显示市场情况温度计.
     *
     * @param date the date
     * @return the stock situation vo
     * @throws RemoteException the remote exception
     */
    public StockSituationVO showStockSituation(LocalDate date) throws RemoteException;
}
