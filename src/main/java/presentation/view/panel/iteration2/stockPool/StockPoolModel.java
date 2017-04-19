package presentation.view.panel.iteration2.stockPool;

import presentation.view.tools.MyTabelModel;
import presentation.view.tools.WindowData;
import service.StockService;
import service.serviceImpl.StockService.StockServiceImpl;
import utilities.IDReserve;
import utilities.exceptions.PrivatePoolIsNullException;
import utilities.exceptions.PrivateStockNotFoundException;
import vo.StockVO;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by 61990 on 2017/4/17.
 */
public class StockPoolModel extends MyTabelModel {
    private final static int columns = 2;

    private StockService stockService;


    public StockPoolModel() {
        this.stockService = new StockServiceImpl();
        init();
    }

    //初始化列表名称和数据
    private void init() {
        columnNames = new String[]{"股票代码", "股票名称"};
        Map<String,String> stockList = PrivateStockPool.getInstance().getPrivatePool();
        data = new Object[stockList.size()][columns];

        int count=0;
        for(String code:stockList.keySet()){
            data[count][0] = code;
            data[count][1] = stockList.get(code);
            count++;
        }
    }

}
