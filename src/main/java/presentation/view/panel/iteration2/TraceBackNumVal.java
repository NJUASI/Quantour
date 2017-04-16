package presentation.view.panel.iteration2;

import presentation.view.panel.StocksTablePanel;
import presentation.view.panel.user.FavoriteTableModel;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.MyMouseListener;
import presentation.view.tools.MyTableHeaderRender;
import presentation.view.tools.WindowData;
import presentation.view.tools.ui.MyScrollBarUI;
import vo.TraceBackCriteriaVO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;

/**
 * Created by 61990 on 2017/4/15.
 */
public class TraceBackNumVal  extends JScrollPane {
    LocalDate date;
    private JTable jTable;

    WindowData windowData;

    int width;

    int height;

    public TraceBackNumVal(){
        date = WindowData.getInstance().getDate();
        windowData = WindowData.getInstance();
        width = windowData.getWidth();
        height = windowData.getHeight();

        setSize(1000 * width / 1920, 130 * height / 1030);

        try {
            jTable = new JTable(new TraceBackNumModel(ChooseStrategyPanel.getInstance().getInfo()));
            jTable.setBounds(0, 0, 1400 * width / 1920, 800 * height / 1030);

            jTable.setRowSelectionAllowed(true);//设置可否被选择
            jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jTable.setSelectionBackground(ColorUtils.markColor());//设置所选择行的背景色
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

//            StocksTablePanel.getInstance().label.setVisible(false);
//            jTable.repaint();
        } catch (Exception e) {
//            StocksTablePanel.getInstance().label.setVisible(true);
        }
    }
}
