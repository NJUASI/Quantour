package com.edu.nju.asi.task.storer;

import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/6/12.
 */
public class StoreController {

    //存储stock信息的对象
    private StoreStockHelper storeStockHelper;

    //存储复权数据的对象
    private StoreAdjStockHelper storeAdjStockHelper;

    //存储stockSearch的对象
    private StoreStockSearchHelper storeStockSearchHelper;

    //初始化成员信息
    public StoreController(String root, LocalDate start, LocalDate end) {
        storeStockHelper = new StoreStockHelper(root);
        storeAdjStockHelper = new StoreAdjStockHelper(root);
        storeStockSearchHelper = new StoreStockSearchHelper(root,start,end);
    }

    /**
     * 存储股票和基准
     *
     * @author ByronDong
     * @updateTime 2017/6/12
     */
    public void storeStock(){
        storeStockHelper.handle();
    }

    /**
     * 存储复权数据
     *
     * @author ByronDong
     * @updateTime 2017/6/12
     */
    public void storeAdjData(){
        storeAdjStockHelper.handle();
    }

    /**
     * 存储StockSearch
     *
     * @author ByronDong
     * @updateTime 2017/6/12
     */
    public void storeStockSearch(){
        storeStockSearchHelper.handle();
    }
}
