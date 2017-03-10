package service;

import vo.StockSituationVO;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Harvey
 * Update time 2017/3/5
 *
 * 市场情况温度计
 */
public interface StockSituationService extends Remote{

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
    public StockSituationVO showStockSituation(LocalDate date) throws IOException;
}
