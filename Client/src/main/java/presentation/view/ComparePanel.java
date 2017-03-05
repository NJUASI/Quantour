package presentation.view;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
class ComparePanel extends NavigationBar {

    //比较面板
    private static ComparePanel comparePanel;

    /**
     * 比较面板构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public ComparePanel() {
        //the door of function 1
        JButton kString = new JButton("test");
        kString.setBounds(100, 500, 100, 50);
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
     * @return comparePanel 比较面板
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static ComparePanel getInstance() {
        if (comparePanel == null) {
            comparePanel = new ComparePanel();
        }
        return comparePanel;
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
        comparePanel = null;
    }
}
