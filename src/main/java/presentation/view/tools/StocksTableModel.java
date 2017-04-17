package presentation.view.tools;

import service.StockService;
import service.serviceImpl.StockService.StockServiceImpl;
import vo.StockVO;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Harvey on 2017/3/15.
 *
 * 股票列表的模型
 */
public class StocksTableModel extends MyTabelModel {

    private final static int columns = 12;



    //查询的日期
    private LocalDate date;
    private StockService stockService;

    public StocksTableModel(LocalDate date) throws IOException {
        this.date = date;
        this.stockService = new StockServiceImpl();
        init();
    }

    //初始化列表名称和数据
    private void init() throws IOException {
        columnNames = new String[]{"股票代码", "股票名称", "涨/跌额","涨/跌幅","开盘指数", "最高指数",
                "最低指数", "收盘指数", "成交量", "复权后的收盘指数", "昨日收盘指数", "昨日复权收盘指数"};
        List<StockVO> stockList = stockService.getAllStocks(date);

        data = new Object[stockList.size()][columns];

        for (int i = 0; i < stockList.size(); i++) {
            StockVO stockVO = stockList.get(i);
            NumberFormat ddf= NumberFormat.getNumberInstance() ;
            ddf.setMaximumFractionDigits(4);

            data[i][0] = stockVO.code;
            data[i][1] = stockVO.name;
            data[i][2] = ddf.format(stockVO.increase);
            data[i][3] = ddf.format(stockVO.increaseMargin);
            data[i][4] = stockVO.open;
            data[i][5] = stockVO.high;
            data[i][6] = stockVO.low;
            data[i][7] = stockVO.close;
            data[i][8] = stockVO.volume;
            data[i][9] = stockVO.adjClose;
            data[i][10] = stockVO.preClose;
            data[i][11] = stockVO.preAdjClose;
        }
    }


}
