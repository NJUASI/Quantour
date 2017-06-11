package com.edu.nju.asi.task.crawler;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockSearchDataHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/6/2.
 */
public class AdjStockHelper {

    public void handle(){
        this.crawing(LocalDate.of(2016,1,1),LocalDate.of(2017,6,1));
    }

    public void crawing(LocalDate start,LocalDate end){
        StockSearchDataHelper stockSearchDataHelper = HelperManager.stockSearchDataHelper;
        List<String> list= new ArrayList<>();
        for(String code:stockSearchDataHelper.getAllStocksCode().keySet()){
            list.add(code);
        }
        String path = "F:/stock/";
        String basePath = "F:/baseStock/";
        CrawingHistory crawer= new CrawingHistory();
        crawer.crawHistoryFront(list,start,end,path+"front/");
        crawer.crawHistoryFrontBase(list,start,end,basePath+"front/");
        crawer.crawHistoryAfter(list,start,end,path+"after/");
        crawer.crawHistoryAfterBase(list,start,end,basePath+"after/");
        crawer.close();
    }
}
