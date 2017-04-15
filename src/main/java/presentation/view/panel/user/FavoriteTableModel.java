package presentation.view.panel.user;

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
public class FavoriteTableModel extends AbstractTableModel {

    private final static int columns = 12;

    private String[] columnNames;
    private Object[][] data;

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
        columnNames = new String[]{"股票代码", "股票名称", "涨/跌额","涨/跌幅","开盘指数", "最高指数",
                "最低指数", "收盘指数", "成交量", "复权后的收盘指数", "昨日收盘指数", "昨日复权收盘指数"};
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

    /**
     * Returns the number of rows in the model. A
     * <code>JTable</code> uses this method to determine how many rows it
     * should display.  This method should be quick, as it
     * is called frequently during rendering.
     *
     * @return the number of rows in the model
     * @see #getColumnCount
     */
    @Override
    public int getRowCount() {
        return data.length;
    }

    /**
     * Returns the number of columns in the model. A
     * <code>JTable</code> uses this method to determine how many columns it
     * should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param rowIndex    the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     * @return the value Object at the specified cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    /**
     * Returns a default name for the column using spreadsheet conventions:
     * A, B, C, ... Z, AA, AB, etc.  If <code>column</code> cannot be found,
     * returns an empty string.
     *
     * @param column the column being queried
     * @return a string containing the default name of <code>column</code>
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Returns false.  This is the default implementation for all cells.
     *
     * @param rowIndex    the row being queried
     * @param columnIndex the column being queried
     * @return false
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return super.isCellEditable(rowIndex, columnIndex);
    }
}
