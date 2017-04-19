package dataHelper.dataHelperImpl.StockDataHelper;

import dao.daoImpl.StockDaoImpl;
import po.StockPO;
import utilities.DataSourceStateKeeper;
import utilities.enums.DataSourceState;
import utilities.enums.Market;
import vo.StockVO;

import java.io.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cuihua on 2017/4/18.
 */
public class StockDataReaderRunnable implements Runnable {

    String code;

    List<StockPO> result;

    public StockDataReaderRunnable(String code) {
        this.code = code;
    }

    @Override
    public void run() {
        synchronized (StockDataReaderRunnable.class) {


        try {

            result = new StockDaoImpl().getStockData(code);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<StockPO> getResult() {
        return result;
    }
}
