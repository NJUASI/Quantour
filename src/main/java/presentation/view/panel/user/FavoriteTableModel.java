package presentation.view.panel.user;

import presentation.view.tools.MyTabelModel;
import service.StockService;
import service.serviceImpl.StockService.StockServiceImpl;
import utilities.IDReserve;
import utilities.exceptions.PrivateStockNotFoundException;
import vo.StockVO;

import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Harvey on 2017/3/15.
 *
 * 股票列表的模型
 */
public class FavoriteTableModel extends MyTabelModel {

    private final static int columns = 7;

    //查询的日期
    private LocalDate date;
    private StockService stockService;



    public FavoriteTableModel(LocalDate date) throws IOException, PrivateStockNotFoundException {
        this.date = date;
        this.stockService = new StockServiceImpl();
        init();
    }

    //初始化列表名称和数据
    private void init() throws IOException, PrivateStockNotFoundException {
        columnNames = new String[]{"股票代码", "股票名称", "涨/跌额","涨/跌幅", "最高指数",
                "最低指数", "成交量"};
        List<StockVO> stockList = stockService.getPrivateStocks(IDReserve.getInstance().getUserID(),date);

        data = new Object[stockList.size()][columns];

        for (int i = 0; i < stockList.size(); i++) {
            StockVO stockVO = stockList.get(i);
            NumberFormat ddf= NumberFormat.getNumberInstance() ;
            ddf.setMaximumFractionDigits(4);

            data[i][0] = stockVO.code;
            data[i][1] = stockVO.name;
            data[i][2] = ddf.format(stockVO.increase);
            data[i][3] = ddf.format(stockVO.increaseMargin);
            data[i][4] = stockVO.high;
            data[i][5] = stockVO.low;
            data[i][6] = stockVO.volume;

        }
    }


}
