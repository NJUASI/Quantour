package presentation.view;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
public class ThermometerPanel extends NavigationBar {
    //温度计面板
    private static ThermometerPanel thermometerPanel;

    /**
     * 温度计面板构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public ThermometerPanel() {
        //the door of function 1
        JButton kString = new JButton("test");
        kString.setBounds(500, 400, 100, 50);
        kString.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainPanel.getCard().show(MainPanel.getCardPanel(), "loginPanel");
            }
        });
        add(kString);
    }

    /**
     * 单件模式
     *
     * @param
     * @return thermometerPanel 温度计面板
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static ThermometerPanel getInstance() {
        if (thermometerPanel == null) {
            thermometerPanel = new ThermometerPanel();
        }
        return thermometerPanel;
    }

    /**
     * 清除单件
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public void refresh() {
        thermometerPanel = null;
    }
}
