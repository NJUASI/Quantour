package presentation.view;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
class ComparePanel extends NavigationBar {

    //比较面板
    private static ComparePanel comparePanel;

    DoubleDatePickerPanel datePanel;
    JTextField name1;
    JTextField num1;
    JTextField name2;
    JTextField num2;
    JButton compare;

    /**
     * 比较面板构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public ComparePanel() {
        init();
    }

    /**
     * 单件模式
     *
     * @param
     * @return comparePanel 比较面板
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static ComparePanel getInstance() {
        if (comparePanel == null) {
            comparePanel = new ComparePanel();
        }
        return comparePanel;
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
        datePanel.setBounds(width * 400 / 1920, height * 50 / 1030, 370 * width / 1920, 35 * height / 1030);
        add(datePanel);
        name1 = new JTextField();
        num1 = new JTextField();
        name2 = new JTextField();
        num2 = new JTextField();

        name1.setBounds(adaptScreen(900, 30, 150, 35));
        add(name1);

        num1.setBounds(adaptScreen(1100, 30, 150, 35));
        add(num1);

        name2.setBounds(adaptScreen(900, 90, 150, 35));
        add(name2);

        num2.setBounds(adaptScreen(1100, 90, 150, 35));
        add(num2);

        //搜索按钮
        compare = new JButton("比较");
        compare.setBounds(adaptScreen(1300, 50, 70, 35));

        compare.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
        add(compare);
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
        comparePanel = null;
    }
}
