package presentation.view.tools;

import presentation.view.panel.StocksTablePanel;
import presentation.view.tools.UI.MyScrollBarUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
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


        try {
            jTable = new JTable(new StocksTableModel(date));
            jTable.setBounds(0, 0, 1400 * width / 1920, 800 * height / 1030);

//            jTable.setRowHeight (30);//设置每行的高度为30
//            jTable.setRowMargin (5);//设置相邻两行单元格的距离
//        table.removeColumn(table.getColumnModel().getColumn(columnIndex));// columnIndex是要删除的列序号


            for(int i =4;i<7;i++) {
                jTable.getColumnModel().getColumn(i).setPreferredWidth(80*width/1920);
            }
            for(int i =2;i<4;i++) {
                jTable.getColumnModel().getColumn(i).setPreferredWidth(50*width/1920);
            }
            jTable.setRowSelectionAllowed(true);//设置可否被选择
            jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jTable.setSelectionBackground(new Color(37,58,84));//设置所选择行的背景色
            jTable.setSelectionForeground(new Color(201,208,214));//设置所选择行的前景色

            jTable.setShowVerticalLines(true);//是否显示垂直的网格线
            jTable.setBackground(new Color(32, 36, 39));
            jTable.setForeground(new Color(201,208,214));
            jTable.setRowSelectionInterval(0, 0);
            jTable.setGridColor(new Color(19, 22, 24));
            jTable.setBorder(BorderFactory.createEmptyBorder());

            JTableHeader header = jTable.getTableHeader();
            header.setDefaultRenderer(new MyTableHeaderRender(header.getDefaultRenderer()));
            header.setReorderingAllowed(false);
            header.setResizingAllowed(false);
            Dimension size = header.getPreferredSize();
            size.height = 30;
            header.setPreferredSize(size);
            setForeground(new Color(201,208,214));

            DefaultTableCellRenderer cellRanderer = new DefaultTableCellRenderer() {
                @Override
                protected void setValue(Object value) {
                        if(value.toString().substring(0,1).equals("-")){
                            setForeground(new Color(15,195,81));
                            setText(value.toString());
                        }else{
                            setForeground(new Color(255,61,61));
                            setText(value.toString());
                        }
                }
            };
//            TableColumn tc = jTable.getColumn("开盘指数");

//            tc.setCellRenderer(cellRanderer);
            //初始化table的渲染器
//            DefaultTableCellRenderer cellRanderer = new DefaultTableCellRenderer();
//            cellRanderer.setForeground(new Color(255,61,61));
            jTable.getColumnModel().getColumn(2).setCellRenderer(cellRanderer);
//            DefaultTableCellRenderer cellRanderer1 = new DefaultTableCellRenderer();
//            cellRanderer1.setForeground(new Color(15,195,81));
            jTable.getColumnModel().getColumn(3).setCellRenderer(cellRanderer);

            getVerticalScrollBar().setUI(new MyScrollBarUI());
            setBackground(new Color(32,36,39));
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