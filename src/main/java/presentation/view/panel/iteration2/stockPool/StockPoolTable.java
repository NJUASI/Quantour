package presentation.view.panel.iteration2.stockPool;

import presentation.view.tools.ColorUtils;
import presentation.view.tools.MyTableHeaderRender;
import presentation.view.tools.WindowData;
import presentation.view.tools.ui.MyScrollBarUI;
import utilities.exceptions.PrivatePoolIsNullException;
import utilities.exceptions.PrivateStockNotFoundException;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.IOException;

/**
 * Created by 61990 on 2017/4/17.
 */
public class StockPoolTable extends JScrollPane {

    public JTable jTable;
    WindowData windowData;

    int width;
    int height;



    public StockPoolTable() throws IOException, PrivateStockNotFoundException, PrivatePoolIsNullException {

        windowData = WindowData.getInstance();
        width = windowData.getWidth();
        height = windowData.getHeight();


        setSize(200 * width / 1920, 150 * height / 1030);

        jTable = new JTable(new StockPoolModel());
        jTable.setBounds(0, 0, 200 * width / 1920, 150 * height / 1030);
        jTable.setRowHeight (30* height / 1030);//设置每行的高度为30


        jTable.setRowSelectionAllowed(true);//设置可否被选择
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable.setSelectionBackground(ColorUtils.tableSelectedColor());//设置所选择行的背景色
        jTable.setSelectionForeground(ColorUtils.fontColor());//设置所选择行的前景色

        jTable.setShowVerticalLines(true);//是否显示垂直的网格线
        jTable.setBackground(ColorUtils.backgroundColor());
        jTable.setForeground(ColorUtils.fontColor());
        jTable.setRowSelectionInterval(0, 0);
        jTable.setGridColor(ColorUtils.divideColor());
        jTable.setBorder(BorderFactory.createEmptyBorder());

        JTableHeader header = jTable.getTableHeader();
        header.setDefaultRenderer(new MyTableHeaderRender(header.getDefaultRenderer()));
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);
        Dimension size = header.getPreferredSize();
        size.height = 33* height / 1030;
        header.setPreferredSize(size);
        setForeground(ColorUtils.fontColor());

        getVerticalScrollBar().setUI(new MyScrollBarUI());
        setBackground(ColorUtils.backgroundColor());
        setBorder(BorderFactory.createEmptyBorder());
        jTable.setBackground(WindowData.getInstance().getColor());

        setViewportView(jTable);


    }

    public String getCode() {
        String cellValue = "" + jTable.getValueAt(jTable.getSelectedRow(), 0);// 取单元格数据,row是行号,column是列号
        return cellValue;
    }
    public String getName() {
        String cellValue = "" + jTable.getValueAt(jTable.getSelectedRow(), 1);// 取单元格数据,row是行号,column是列号
        return cellValue;
    }
}
