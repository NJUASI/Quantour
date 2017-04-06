package presentation.view.tools;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by Byron Dong on 2017/3/18.
 */
public class MyTableHeaderRender implements TableCellRenderer {
    private static final Font labelFont = new Font("宋体",Font.PLAIN,12);
    private TableCellRenderer delegate;

    public MyTableHeaderRender(TableCellRenderer delegate) {
        this.delegate = delegate;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = delegate.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (c instanceof JLabel) {
            JLabel label = (JLabel) c;
            label.setBackground(new Color(41,46,51));
            label.setForeground(ColorUtils.fontColor());
            label.setFont(labelFont);
            label.setBorder(null);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBorder(BorderFactory.createEmptyBorder());
        }
        return c;
    }
}