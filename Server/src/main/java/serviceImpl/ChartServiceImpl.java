package serviceImpl;

import service.ChartService;
import vo.ChartShowCriteriaVO;
import vo.StockVO;

import java.rmi.RemoteException;
import java.util.Iterator;

/**
 * Created by cuihua on 2017/3/4.
 */
public class ChartServiceImpl implements ChartService {

    @Override
    public Iterator<StockVO> getSingleStockRecords(ChartShowCriteriaVO chartShowCriteriaVO) throws RemoteException {
        return null;
    }
}
