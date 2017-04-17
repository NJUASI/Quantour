package presentation.view.panel.iteration2;

import presentation.view.tools.MyTabelModel;
import presentation.view.tools.WindowData;
import service.StockService;
import service.serviceImpl.StockService.StockServiceImpl;
import utilities.IDReserve;
import utilities.exceptions.PrivateStockNotFoundException;
import vo.StockVO;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by 61990 on 2017/4/17.
 */
public class StockPoolModel extends MyTabelModel {
    private final static int columns = 2;

    private StockService stockService;



    public StockPoolModel() throws IOException, PrivateStockNotFoundException {
        this.stockService = new StockServiceImpl();
        init();
    }

    //初始化列表名称和数据
    private void init() throws IOException, PrivateStockNotFoundException {
        columnNames = new String[]{"股票代码", "股票名称"};
        List<StockVO> stockList = stockService.getPrivateStocks(IDReserve.getInstance().getUserID(), WindowData.getInstance().getDate());

        data = new Object[stockList.size()][columns];

        for (int i = 0; i < stockList.size(); i++) {
            StockVO stockVO = stockList.get(i);
            data[i][0] = stockVO.code;
            data[i][1] = stockVO.name;
        }
    }

}
