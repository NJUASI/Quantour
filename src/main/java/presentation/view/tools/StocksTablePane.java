package presentation.view.tools;

import presentation.controller.StocksTableController;
import presentation.controller.ViewSwitchController;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

/**
 * Created by 61990 on 2017/3/10.
 */
public class StocksTablePane extends JScrollPane {

    private JTable jTable;

    WindowData windowData;

    int width;

    int height;

    JLabel label;
    int count=0;
    public StocksTablePane(LocalDate date) {

        windowData = WindowData.getInstance();
        width = windowData.getWidth();
        height = windowData.getHeight();
        setSize(1400 * width / 1920, 800 * height / 1030);

        //加提示语言
        label = new JLabel("当日无股票信息");
        label.setBounds(600 * width / 1920, 370 * height / 1030, 200 * width / 1920 * height / 1030, 60);
        label.setBackground(new Color(32,36,39));
        add(label);
        label.setVisible(false);

        try {

            jTable = new JTable(new StocksTableModel(date));
            jTable.setBounds(0, 0, 1400 * width / 1920, 800 * height / 1030);

//            jTable.setRowHeight (30);//设置每行的高度为30
//            jTable.setRowMargin (5);//设置相邻两行单元格的距离
//        table.removeColumn(table.getColumnModel().getColumn(columnIndex));// columnIndex是要删除的列序号


            jTable.setRowSelectionAllowed(true);//设置可否被选择
            jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jTable.setSelectionBackground(new Color(37,58,84));//设置所选择行的背景色
            jTable.setSelectionForeground(new Color(201,208,214));//设置所选择行的前景色

            jTable.setShowVerticalLines(true);//是否显示垂直的网格线
            jTable.setBackground(new Color(32, 36, 39));
            jTable.setRowSelectionInterval(0, 0);
            jTable.setGridColor(new Color(19, 22, 24));

            JTableHeader header = jTable.getTableHeader();
            header.setDefaultRenderer(new MyTableHeaderRender(header.getDefaultRenderer()));
            header.setReorderingAllowed(false);
            header.setResizingAllowed(false);
            Dimension size = header.getPreferredSize();
            size.height = 30;
            header.setPreferredSize(size);


            getVerticalScrollBar().setUI(new MyScrollBarUI());
            setBackground(new Color(32,36,39));
            setViewportView(jTable);
            count=0;
            jTable.addMouseListener(new MyMouseListener());

            jTable.repaint();
        } catch (Exception e) {
            setBackground(new Color(32,36,39));
            setForeground(new Color(201,208,214));
            label.setBackground(new Color(32,36,39));
            label.setForeground(new Color(201,208,214));
            label.setVisible(true);
        }

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