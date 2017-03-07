package presentation.view;

import javafx.scene.control.DatePicker;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by 61990 on 2017/3/5.
 */
public class KStringPanel extends NavigationBar {
    //k线面板
    private static KStringPanel kStringPanel;
    DoubleDatePickerPanel datePanel;
    JTextField name;
    JTextField num;
    JButton search;
    JButton compare;
    JButton favorite;
    /**
     * k线面板构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public KStringPanel() {
        //初始化datePicker
        name = new JTextField();
        num = new JTextField();
        search =new JButton("搜索");
        favorite =new JButton("收藏");
        compare =new JButton("加入比较");
        init();
    }

    /**
     * 添加日期选择器等各种原件
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/6
     */
    private void init() {
        datePanel = new DoubleDatePickerPanel();
        datePanel.setBounds(width*400/1920, height*50/1030,370*width/1920,35*height/1030);
        add(datePanel);

        name.setBounds(adaptScreen(900, 50, 150, 35));
        add(name);

        num.setBounds(adaptScreen(1100, 50, 50, 35));
        add(num);
        //搜索按钮
        search.setBounds(adaptScreen(1300, 50, 70, 35));
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
        add(search);
        //加入比较按钮
        compare.setBounds(adaptScreen(1400, 50, 120, 35));
        compare.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
        add(compare);

        favorite.setBounds(adaptScreen(1550, 50, 70, 35));
        favorite.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
        add(favorite);
        add(bg);
        // 获取日期的方法
        //getDate
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
        kStringPanel = null;
    }
}
