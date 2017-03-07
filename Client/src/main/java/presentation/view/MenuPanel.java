package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
class MenuPanel extends TempletPanel{
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
    public MenuPanel() {

        //the door of function 1
        JButton kString =new JButton("kString");
        kString.setBounds(width/2-200*width/1920, height/2-200*height/1030, 200*width/1920, 200*height/1030);
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
        compare.setBounds(adaptScreen(width/2, height/2-200, 200, 200));
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
        thermometer.setBounds(adaptScreen(width/2-200, height/2, 200, 200));
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
        favorites.setBounds(adaptScreen(width/2, height/2, 200, 200));
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
        JButton logout =new JButton("注销");
        logout.setBounds(100, 900, 60, 30);
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
        add(bg);
    }
}
