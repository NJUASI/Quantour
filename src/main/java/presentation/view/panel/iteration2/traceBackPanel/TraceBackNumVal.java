package presentation.view.panel.iteration2.traceBackPanel;

import presentation.view.tools.ColorUtils;
import presentation.view.tools.MyTableHeaderRender;
import presentation.view.tools.WindowData;
import presentation.view.tools.ui.MyScrollBarUI;
import vo.TraceBackNumValVO;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * Created by 61990 on 2017/4/15.
 */
public class TraceBackNumVal  extends JScrollPane {

    private JTable jTable;

    WindowData windowData;

    int width;

    int height;

    public TraceBackNumVal(TraceBackNumValVO traceBackNumValVO){
        windowData = WindowData.getInstance();
        width = windowData.getWidth();
        height = windowData.getHeight();

        setSize(1000 * width / 1920, 120 * height / 1030);

        try {
            jTable = new JTable(new TraceBackNumModel(traceBackNumValVO));
            jTable.setBounds(0, 0, 1000 * width / 1920, 120 * height / 1030);

            jTable.setRowSelectionAllowed(true);//设置可否被选择
            jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jTable.setSelectionBackground(ColorUtils.markColor());//设置所选择行的背景色
            jTable.setSelectionForeground(ColorUtils.fontColor());//设置所选择行的前景色

            jTable.setRowHeight(30);
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

//            StocksTablePanel.getInstance().label.setVisible(false);
//            jTable.repaint();
        } catch (Exception e) {
//            StocksTablePanel.getInstance().label.setVisible(true);
        }
    }
}

