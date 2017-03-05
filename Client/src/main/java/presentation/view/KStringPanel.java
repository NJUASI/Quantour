package presentation.view;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
public class KStringPanel extends NavigationBar {
    private static KStringPanel kStringPanel;
    public KStringPanel(){
        //the door of function 1
        JButton kString =new JButton("test");
        kString.setBounds(500, 530, 100, 50);
        kString.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainPanel.getCard().show(MainPanel.getCardPanel(), "loginPanel");
            }
        });
        add(kString);
    }
    public static KStringPanel getInstance() {
        if (kStringPanel == null) {
            kStringPanel = new KStringPanel();
        }
        return kStringPanel;
    }
    public void refresh() {
        kStringPanel=null;
    }
}
