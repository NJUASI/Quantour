package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
class NavigationBar extends JPanel {

    private KStringPanel kStringPanel;
    private ComparePanel comparePanel;
    private ThermometerPanel thermometerPanel;
    private FavoritesPanel favoritesPanel;

    public NavigationBar(){
        setLayout(null);

        //the door of function 1
        JLabel message =new JLabel("Mr.gao");
        message.setForeground(Color.GREEN);
        message.setFont(new Font("微软雅黑",Font.BOLD,25));
        message.setBounds(50,50,300,50);


        add(message);

        //the door of function 1
        JButton kString =new JButton("kString");
        kString.setBounds(50, 200, 100, 50);
        kString.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                kStringPanel=KStringPanel.getInstance();
                MainPanel.getCardPanel().add(kStringPanel, "kStringPanel");
                MainPanel.getCard().show(MainPanel.getCardPanel(), "kStringPanel");
            }
        });
        add(kString);

        //the door of function 2
        JButton compare =new JButton("compare");
        compare.setBounds(50, 300, 100, 50);
        compare.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                comparePanel=ComparePanel.getInstance();
                MainPanel.getCardPanel().add(comparePanel, "comparePanel");
                MainPanel.getCard().show(MainPanel.getCardPanel(), "comparePanel");
            }
        });
        add(compare);

        //the door of function 3
        JButton thermometer =new JButton("thermometer");
        thermometer.setBounds(50, 400, 100, 50);
        thermometer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                thermometerPanel=thermometerPanel.getInstance();
                MainPanel.getCardPanel().add(thermometerPanel, "thermometerPanel");
                MainPanel.getCard().show(MainPanel.getCardPanel(), "thermometerPanel");
            }
        });
        add(thermometer);

        //the door of function 4
        JButton favorites =new JButton("favorites");
        favorites.setBounds(50, 500, 100, 50);
        favorites.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                favoritesPanel=FavoritesPanel.getInstance();
                MainPanel.getCardPanel().add(favoritesPanel, "favoritesPanel");
                MainPanel.getCard().show(MainPanel.getCardPanel(), "favoritesPanel");
            }
        });
        add(favorites);

        //log out
        JButton logout =new JButton("logout");
        logout.setBounds(50, 600, 70, 70);
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainPanel.getCard().show(MainPanel.getCardPanel(), "loginPanel");
            }
        });
        add(logout);

        //return to main panel
        JButton homePanel =new JButton("homePanel");
        homePanel.setBounds(150, 600, 70, 70);
        homePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainPanel.getCard().show(MainPanel.getCardPanel(), "menuPanel");
                KStringPanel.getInstance().refresh();
                ComparePanel.getInstance().refresh();
                ThermometerPanel.getInstance().refresh();
                FavoritesPanel.getInstance().refresh();
            }
        });
        add(homePanel);
    }
}

