package service;

import vo.ChartShowCriteriaVO;
import vo.StockVO;

import java.rmi.RemoteException;
import java.util.Iterator;

/**
 * Created by cuihua on 2017/3/4.
 *
 * K线图、均线图
 * TODO 因为对界面需要显示的信息不确定，故暂统定为StockVO
 */
public interface ChartService {

    /**
     *
     * @param chartShowCriteriaVO 股票的选择标准
     * @return 特定股票的所有交易信息
     * @throws RemoteException RMI
     */
    Iterator<StockVO> getSingleStockRecords(ChartShowCriteriaVO chartShowCriteriaVO) throws RemoteException;
}
