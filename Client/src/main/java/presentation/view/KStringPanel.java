package presentation.view;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by 61990 on 2017/3/5.
 */
public class KStringPanel extends NavigationBar {
    //k线面板
    private static KStringPanel kStringPanel;

    /**
     * k线面板构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public KStringPanel(){
        //开始日期

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

    /**
     * 单间模式
     *
     * @param
     * @return KStringPanel K线面板
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static KStringPanel getInstance() {
        if (kStringPanel == null) {
            kStringPanel = new KStringPanel();
        }
        return kStringPanel;
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
        kStringPanel=null;
    }
}
