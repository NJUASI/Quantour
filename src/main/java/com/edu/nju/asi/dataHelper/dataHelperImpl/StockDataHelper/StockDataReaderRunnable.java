package com.edu.nju.asi.dataHelper.dataHelperImpl.StockDataHelper;

import com.edu.nju.asi.dao.daoImpl.StockDaoImpl;
import com.edu.nju.asi.po.StockPO;

import java.io.*;
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

//            result = new StockDaoImpl().getStockData(code);
            result = null;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<StockPO> getResult(){
        return result;
    }
}
