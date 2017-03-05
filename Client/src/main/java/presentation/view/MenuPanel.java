package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
class MenuPanel extends JPanel {
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
        setLayout(null);

        //the door of function 1
        JLabel message =new JLabel("welcome Mr.gao");
        message.setForeground(Color.GREEN);
        message.setFont(new Font("微软雅黑",Font.BOLD,25));
        message.setBounds(900,20,300,50);


        add(message);

        //the door of function 1
        JButton kString =new JButton("kString");
        kString.setBounds(400, 400, 200, 200);
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
        compare.setBounds(600, 400, 200, 200);
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
        thermometer.setBounds(400, 600, 200, 200);
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
        favorites.setBounds(600, 600, 200, 200);
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
        logout.setBounds(100, 900, 20, 20);
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

    }
}
