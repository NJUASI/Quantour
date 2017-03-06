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
    DatePickerPanel startDate;
    DatePickerPanel endDate;
    JTextField name;
    JTextField num;
    JButton search;
    JButton compare;

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
        startDate = new DatePickerPanel();
        startDate.setLocation(400, 400);
        add(startDate);
        endDate = new DatePickerPanel();
        endDate.setLocation(600, 400);
        add(endDate);
        name.setBounds(650, 450, 100, 50);
        add(name);

        num.setBounds(750, 450, 100, 50);
        add(num);
        //搜索按钮
        search.setBounds(750, 550, 100, 50);
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
        add(search);
        //加入比较按钮
        compare.setBounds(750, 550, 100, 50);
        compare.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
        add(compare);

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
