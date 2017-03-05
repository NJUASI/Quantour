package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
public class FavoritesPanel extends NavigationBar {
    //自选股面板
    private static FavoritesPanel favoritesPanel;

    /**
     * 自选股票面板构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
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

    /**
     * 单件模式
     *
     * @param
     * @return FavoritesPanel 自选股票
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static FavoritesPanel getInstance() {
        if (favoritesPanel == null) {
            favoritesPanel = new FavoritesPanel();
        }
        return favoritesPanel;
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
        favoritesPanel=null;
    }
}
