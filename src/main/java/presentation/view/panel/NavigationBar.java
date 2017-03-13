package presentation.view.panel;

import presentation.listener.navigationBarListener.CompareListener;
import presentation.listener.navigationBarListener.FavoritesListener;
import presentation.listener.navigationBarListener.KStringListener;
import presentation.listener.navigationBarListener.ThermometerListener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
public class NavigationBar extends TempletPanel {

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
        kString.addMouseListener(new KStringListener());
        add(kString);

        //the door of function 2
        JButton compare = new JButton("compare");
        compare.setBounds(adaptScreen(50, 350, 150, 50));
        compare.addMouseListener(new CompareListener());
        add(compare);

        //the door of function 3
        JButton thermometer = new JButton("thermometer");
        thermometer.setBounds(adaptScreen(50, 500, 150, 50));
        thermometer.addMouseListener(new ThermometerListener());
        add(thermometer);

        //the door of function 4
        JButton favorites = new JButton("favorites");
        favorites.setBounds(adaptScreen(50, 650, 150, 50));
        favorites.addMouseListener(new FavoritesListener());
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

