package dao.daoImpl;

import dao.ChartDao;
import po.AveragePO;
import po.StockPO;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/3/4.
 */
public class ChartDaoImpl implements ChartDao {

    @Override
    public List<StockPO> getCandlestickData() {
        return null;
    }

    @Override
    public List<StockPO> getCandlestickData(LocalDate start, LocalDate end) {
        return null;
    }

    @Override
    public List<AveragePO> getAverageData() {
        return null;
    }
}
