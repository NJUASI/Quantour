package presentation.view.tools;

import presentation.controller.StocksTableController;
import presentation.view.panel.StocksTablePanel;
import presentation.view.panel.iteration2.stockPool.PrivateStockPool;
import presentation.view.tools.ui.MyScrollBarUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by 61990 on 2017/3/10.
 */
public class StocksTablePane extends JScrollPane {

    public JTable jTable;
    WindowData windowData;
    int width;
    int height;

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
//            jTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
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

            getVerticalScrollBar().setUI(new MyScrollBarUI());
            setBackground(ColorUtils.backgroundColor());
            setBorder(BorderFactory.createEmptyBorder());
            setViewportView(jTable);

            jTable.addMouseListener(new MyMouseListener());
            //源
            DragSource dragSource = DragSource.getDefaultDragSource();
            dragSource.createDefaultDragGestureRecognizer(jTable, DnDConstants.ACTION_COPY_OR_MOVE, new DragGestureListener() {
                public void dragGestureRecognized(DragGestureEvent event)
                {
                    //当前选择中单元格的内容
                    TableModel tableModel = jTable.getModel();
                    int[] rows = jTable.getSelectedRows();
                    Map<String,String> map = new TreeMap<>();
                    for(int i = 0; i < rows.length; i++){
                        map.put((String)tableModel.getValueAt(rows[i],0),(String) tableModel.getValueAt(rows[i],1));
                    }
                    DraggedTrasferable transferable = new DraggedTrasferable(map);
                    event.startDrag(
                            DragSource.DefaultCopyDrop,
                            transferable);

                }
            });
            StocksTablePanel.getInstance().label.setVisible(false);
            jTable.repaint();
        } catch (Exception e) {
            StocksTablePanel.getInstance().label.setVisible(true);
        }

    }

    public String[] getCode() {
        int code[] =jTable.getSelectedRows();
        String cellValue[]=new String[code.length];
        for(int i = 0 ; i <code.length;i++) {
            cellValue[i] = "" + jTable.getValueAt(code[i], 0);// 取单元格数据,row是行号,column是列号
        }
            return cellValue;
    }

    public String getName() {
        String cellValue = "" + jTable.getValueAt(jTable.getSelectedRow(), 1);// 取单元格数据,row是行号,column是列号
        return cellValue;
    }

    public String getCode(int index){
        String cellValue = "" + jTable.getValueAt(index, 0);// 取单元格数据,row是行号,column是列号
        return cellValue;
    }

    public String getName(int index){
        String cellValue = "" + jTable.getValueAt(index, 1);// 取单元格数据,row是行号,column是列号
        return cellValue;
    }
}