package presentation.view.tools;

import service.StockService;
import service.serviceImpl.StockServiceImpl;
import vo.StockVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.Iterator;

/**
 * Created by 61990 on 2017/3/10.
 */
public class FavoritesTable extends JScrollPane {
    private JTable jTable;
    WindowData windowData;
    int width;
    int height;
    StockService stockService;
    JLabel label ;
    public FavoritesTable(LocalDate date) {
        stockService = new StockServiceImpl();
        windowData = WindowData.getInstance();
        width = windowData.getWidth();
        height = windowData.getHeight();
        setSize(1400 * width / 1920, 800 * height / 1030);
       //加提示语言
        label= new JLabel("当日无股票信息");
        label.setBounds(600* width / 1920,370* height / 1030,200* width / 1920* height / 1030,60);

//        jTable = new JTable();
//        add(jTable);
        add(label);
        label.setVisible(false);
        try {
            Iterator<StockVO> stockList = stockService.getAllStocks(date);
            int index = 0;
            while (stockList.hasNext()) {
                index++;
                stockList.next();
            }
            Object[]  columnNames = { "股票代码", "股票名称","记录编号", "开盘指数", "最高指数",
                    "最低指数", "收盘指数", "成交量", "复权后的收盘指数", "昨日收盘指数", "昨日复权收盘指数"};
            Object[][] rowDate = new Object[index][11];
            stockList = stockService.getAllStocks(date);
            for (int j = 0; stockList.hasNext(); j++) {
                StockVO stockVO=stockList.next();
                rowDate[j][0] = stockVO.code;
                rowDate[j][1] = stockVO.name;
                rowDate[j][2] = stockVO.serial;
                rowDate[j][3] = stockVO.open;
                rowDate[j][4] = stockVO.high;
                rowDate[j][5] = stockVO.low;
                rowDate[j][6] = stockVO.close;
                rowDate[j][7] = stockVO.volume;
                rowDate[j][8] = stockVO.adjClose;
                rowDate[j][9] = stockVO.preClose;
                rowDate[j][10] = stockVO.preAdjClose;
            }
            //设置不可编辑
            DefaultTableModel model = new DefaultTableModel(rowDate, columnNames) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            jTable = new JTable(model);
            jTable.setBounds(0, 0, 1400 * width / 1920, 800 * height / 1030);

//            jTable.setRowHeight (30);//设置每行的高度为30
//            jTable.setRowMargin (5);//设置相邻两行单元格的距离
//        table.removeColumn(table.getColumnModel().getColumn(columnIndex));// columnIndex是要删除的列序号
            jTable.setRowSelectionAllowed(true);//设置可否被选择
            jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jTable.setSelectionBackground(Color.lightGray);//设置所选择行的背景色
            jTable.setSelectionForeground(Color.white);//设置所选择行的前景色
            jTable.setShowVerticalLines(true);//是否显示垂直的网格线
            jTable.setBackground(new Color(55, 60, 56));
            jTable.setRowSelectionInterval(0, 0);
            jTable.setGridColor(Color.GRAY);
            jTable.setBackground(Color.white);
            setViewportView(jTable);
            label.setVisible(false);
            jTable.repaint();
        } catch (Exception e) {
            setViewportView(null);
            label.setVisible(true);
        }



    }

    public String getCode() {
        String cellValue = (String) jTable.getValueAt(jTable.getSelectedRow(), 1);// 取单元格数据,row是行号,column是列号
        return cellValue;
    }
    public String getName() {
        String cellValue = (String) jTable.getValueAt(jTable.getSelectedRow(), 2);// 取单元格数据,row是行号,column是列号
        return cellValue;
    }
}