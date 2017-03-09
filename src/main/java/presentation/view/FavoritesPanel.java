package presentation.view;

import utilities.IDReserve;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by 61990 on 2017/3/5.
 */
public class FavoritesPanel extends NavigationBar {
    //自选股面板
    private static FavoritesPanel favoritesPanel;
        ListModel jListModel;
        JList list;
    /**
     * 自选股票面板构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public FavoritesPanel(){
        updateText();
        WindowData windowData = WindowData.getInstance();
        int width = windowData.getWidth();
        int height =windowData.getHeight();
        list.setBounds(adaptScreen(400,200,700,700));

        list.setForeground(Color.red);
        list.setBackground(new Color(55,60,56));
        add(list);

        JButton search = new JButton("查看");
        search.setBounds(adaptScreen(1150,400,80,40));
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainPanel.getCard().show(MainPanel.getCardPanel(), "kStringPanel");
            }
        });
        add(search);
//        add(bg);
    }
    void updateText(){
        list = new JList();
        jListModel =  new DefaultComboBoxModel(getList());
        list.setModel(jListModel);
    }
    public Vector<String> getList(){
        //TODO 返回自选股列表
        IDReserve.getInstance().getUserID();

        Vector<String> vector=new Vector<>();
            vector.add("2331");
        vector.add("2332");
        vector.add("2333");
        return vector;
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
