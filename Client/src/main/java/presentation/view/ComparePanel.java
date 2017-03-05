package presentation.view;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
class ComparePanel extends NavigationBar {
    private static ComparePanel comparePanel;
    public ComparePanel(){
        //the door of function 1
        JButton kString =new JButton("test");
        kString.setBounds(100, 500, 100, 50);
        kString.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainPanel.getCard().show(MainPanel.getCardPanel(), "loginPanel");
            }
        });
        add(kString);
    }
    public static ComparePanel getInstance() {
        if (comparePanel == null) {
            comparePanel = new ComparePanel();
        }
        return comparePanel;
    }
    public void refresh() {
        comparePanel=null;
    }
}
