package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
public class FavoritesPanel extends NavigationBar {
    private static FavoritesPanel favoritesPanel;
    public FavoritesPanel(){
        //the door of function 1
        JButton kString =new JButton("test");
        kString.setBounds(500, 500, 300, 50);
        kString.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainPanel.getCard().show(MainPanel.getCardPanel(), "loginPanel");
            }
        });
        add(kString);
        JTextField admin=new JTextField("Enter your admin");
        admin.setForeground(Color.GREEN);
        admin.setFont(new Font("微软雅黑",Font.BOLD,25));
        admin.setBounds(190,203,300,50);
//        admin.setOpaque(false);
//        admin.setBorder(null);
        admin.setVisible(true);
        add(admin);
    }
    public static FavoritesPanel getInstance() {
        if (favoritesPanel == null) {
            favoritesPanel = new FavoritesPanel();
        }
        return favoritesPanel;
    }
    public void refresh() {
        favoritesPanel=null;
    }
}
