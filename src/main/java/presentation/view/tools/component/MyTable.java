package presentation.view.tools.component;

import presentation.view.panel.StocksTablePanel;
import presentation.view.tools.*;
import presentation.view.tools.ui.MyScrollBarUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;

/**
 *
 * Created by 61990 on 2017/4/17.
 *  建一个table的父类
 */
public class MyTable extends JScrollPane {
    private JTable jTable;
    WindowData windowData;
    int width;
    int height;

    public MyTable(LocalDate date) {
        windowData = WindowData.getInstance();
        width = windowData.getWidth();
        height = windowData.getHeight();
        setSize(1300 * width / 1920, 750 * height / 1030);
        try {
            jTable = new JTable(new StocksTableModel(date));
            jTable.setBounds(0, 0, 1400 * width / 1920, 800 * height / 1030);

            for(int i =4;i<7;i++) {
                jTable.getColumnModel().getColumn(i).setPreferredWidth(80*width/1920);
            }
            for(int i =2;i<4;i++) {
                jTable.getColumnModel().getColumn(i).setPreferredWidth(50*width/1920);
            }


            DefaultTableCellRenderer cellRanderer = new DefaultTableCellRenderer() {
                @Override
                protected void setValue(Object value) {
                    if(value.toString().substring(0,1).equals("-")){
                        setForeground(ColorUtils.downColor());
                        setText(value.toString());
                    }else{
                        setForeground(ColorUtils.upColor());
                        setText(value.toString());
                    }
                }
            };
            jTable.getColumnModel().getColumn(2).setCellRenderer(cellRanderer);
            jTable.getColumnModel().getColumn(3).setCellRenderer(cellRanderer);
            createTable();
            jTable.addMouseListener(new MyMouseListener());
            StocksTablePanel.getInstance().label.setVisible(false);
            jTable.repaint();
        } catch (Exception e) {
            StocksTablePanel.getInstance().label.setVisible(true);
        }

    }



    private void createTable(){
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
        size.height = 30;
        header.setPreferredSize(size);
        setForeground(ColorUtils.fontColor());
        getVerticalScrollBar().setUI(new MyScrollBarUI());
        setBackground(ColorUtils.backgroundColor());
        setBorder(BorderFactory.createEmptyBorder());
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
