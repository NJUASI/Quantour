package dao.daoImpl;

import dao.ChartDao;
import dataHelper.StockDataHelper;
import dataHelper.dataHelperImpl.StockDataHelperImpl;
import po.AveragePO;
import po.StockPO;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/3/4.
 */
public class ChartDaoImpl implements ChartDao {

    private StockDataHelper stockHelper;

    public ChartDaoImpl() {
        this.stockHelper = new StockDataHelperImpl();
    }

    @Override
    public List<StockPO> getCandlestickData(String code) {
        return this.stockHelper.getStockData(code);
    }

    @Override
    public List<StockPO> getCandlestickData(LocalDate start, LocalDate end, String code) {
        List<StockPO> tempStockList = this.stockHelper.getStockData(code);

        for(StockPO stockPO : tempStockList){

        }
        return null;
    }

    @Override
    public List<AveragePO> getAverageData(String code) {
        return null;
    }

    private boolean isTrueDate(LocalDate start, LocalDate end,LocalDate now){
        if(now.isAfter(start)&&now.isBefore(end)){
            return true;
        }
        return false;
    }
}
