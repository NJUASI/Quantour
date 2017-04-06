package presentation.view.tools;

import presentation.controller.StocksTableController;
import presentation.controller.ViewSwitchController;
import presentation.view.panel.StocksTablePanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Locale;

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


        try {
            jTable = new JTable(new StocksTableModel(date));
            jTable.setBounds(0, 0, 1400 * width / 1920, 800 * height / 1030);

//            jTable.setRowHeight (30);//设置每行的高度为30
//            jTable.setRowMargin (5);//设置相邻两行单元格的距离
//        table.removeColumn(table.getColumnModel().getColumn(columnIndex));// columnIndex是要删除的列序号
            for(int i =2;i<5;i++) {
                jTable.getColumnModel().getColumn(i).setPreferredWidth(80*width/1920);
            }
            jTable.setRowSelectionAllowed(true);//设置可否被选择
            jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jTable.setSelectionBackground(new Color(37,58,84));//设置所选择行的背景色
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
            size.height = 30;
            header.setPreferredSize(size);
            setForeground(ColorUtils.fontColor());

            getVerticalScrollBar().setUI(new MyScrollBarUI());
            setBackground(ColorUtils.backgroundColor());
            setBorder(BorderFactory.createEmptyBorder());
            setViewportView(jTable);
            count=0;
            jTable.addMouseListener(new MyMouseListener());
            StocksTablePanel.getInstance().label.setVisible(false);
            jTable.repaint();
        } catch (Exception e) {
            StocksTablePanel.getInstance().label.setVisible(true);
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