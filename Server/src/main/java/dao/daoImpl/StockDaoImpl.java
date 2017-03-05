package dao.daoImpl;

import dao.StockDao;
import dataHelper.StockDataHelper;
import dataHelper.dataHelperImpl.StockDataHelperImpl;
import po.StockPO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihua on 2017/3/4.
 */
public class StockDaoImpl implements StockDao {

    private StockDataHelper stockHelper;

    public StockDaoImpl() {
        this.stockHelper = new StockDataHelperImpl();
    }

    @Override
    public List<StockPO> getStockData(String code) {
        return this.stockHelper.getStockData(code);
    }

    @Override
    public List<StockPO> getStockData(LocalDate start, LocalDate end, String code) {
        List<StockPO> resultStockList = new ArrayList<StockPO>();
        List<StockPO> tempStockList =  this.stockHelper.getStockData(code);

        for(StockPO stockPO : tempStockList){
            if(this.isTrueDate(start,end,stockPO.getDate())){
                resultStockList.add(stockPO);
            }
        }

        return resultStockList;
    }

    private boolean isTrueDate(LocalDate start, LocalDate end,LocalDate now){

        if(now.isEqual(start)||now.isEqual(end)){
            return true;
        }

        if(now.isAfter(start)&&now.isBefore(end)){
            return true;
        }

        return false;
    }
}
