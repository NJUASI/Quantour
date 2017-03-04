package serviceImpl;

import service.StockSituationService;
import vo.StockSituationVO;

import java.rmi.RemoteException;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 */
public class StockSituationServiceImpl implements StockSituationService {

    @Override
    public StockSituationVO show(LocalDate date) throws RemoteException {
        return null;
    }
}
