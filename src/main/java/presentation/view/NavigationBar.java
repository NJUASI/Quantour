package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
class NavigationBar extends TempletPanel {
    //K线面板
    private KStringPanel kStringPanel;
    //比较面板
    private ComparePanel comparePanel;
    //温度计面板
    private ThermometerPanel thermometerPanel;
    //自选股面板
    private FavoritesPanel favoritesPanel;
    /**
     * 构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public NavigationBar() {
        setLayout(null);

        //the door of function 1
        JButton kString = new JButton("kString");
        kString.setBounds(adaptScreen(50, 200, 150, 50));
        kString.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
//                kStringPanel = KStringPanel.getInstance();
//                MainPanel.getCardPanel().add(kStringPanel, "kStringPanel");
                MainPanel.getCard().show(MainPanel.getCardPanel(), "kStringPanel");
            }
        });
        add(kString);

        //the door of function 2
        JButton compare = new JButton("compare");
        compare.setBounds(adaptScreen(50, 350, 150, 50));
        compare.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                comparePanel = ComparePanel.getInstance();
                MainPanel.getCardPanel().add(comparePanel, "comparePanel");
                MainPanel.getCard().show(MainPanel.getCardPanel(), "comparePanel");
            }
        });
        add(compare);

        //the door of function 3
        JButton thermometer = new JButton("thermometer");
        thermometer.setBounds(adaptScreen(50, 500, 150, 50));
        thermometer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                thermometerPanel = thermometerPanel.getInstance();
                MainPanel.getCardPanel().add(thermometerPanel, "thermometerPanel");
                MainPanel.getCard().show(MainPanel.getCardPanel(), "thermometerPanel");
            }
        });
        add(thermometer);

        //the door of function 4
        JButton favorites = new JButton("favorites");
        favorites.setBounds(adaptScreen(50, 650, 150, 50));
        favorites.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                favoritesPanel = FavoritesPanel.getInstance();
                MainPanel.getCardPanel().add(favoritesPanel, "favoritesPanel");
                MainPanel.getCard().show(MainPanel.getCardPanel(), "favoritesPanel");
            }
        });
        add(favorites);

        //log out
        JButton logout = new JButton("注销");
        logout.setBounds(adaptScreen(90, 820, 70, 35));
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainPanel.getCard().show(MainPanel.getCardPanel(), "loginPanel");
                KStringPanel.getInstance().refresh();
                ComparePanel.getInstance().refresh();
                ThermometerPanel.getInstance().refresh();
                FavoritesPanel.getInstance().refresh();
            }
        });
        add(logout);

        //return to main panel
//        JButton homePanel = new JButton("主页");
//        homePanel.setBounds(adaptScreen(130, 820, 70, 35));
//        homePanel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                MainPanel.getCard().show(MainPanel.getCardPanel(), "menuPanel");
//                KStringPanel.getInstance().refresh();
//                ComparePanel.getInstance().refresh();
//                ThermometerPanel.getInstance().refresh();
//                FavoritesPanel.getInstance().refresh();
//            }
//        });
//        add(homePanel);
    }
}

