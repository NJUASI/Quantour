package service;

import vo.StockSituationVO;

import java.rmi.RemoteException;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 *
 * 市场情况温度计
 */
public interface StockSituationService {

    /**
     *
     * @param date 特定的日期
     * @return 单日的股票市场情况温度计
     * @throws RemoteException
     */
    StockSituationVO show(LocalDate date) throws RemoteException;
}
