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

    DatePickerPanel startDate;
    DatePickerPanel endDate;
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
        startDate = new DatePickerPanel();
        startDate.setLocation(400, 400);
        add(startDate);
        endDate = new DatePickerPanel();
        endDate.setLocation(600, 400);
        add(endDate);
        name1 = new JTextField();
        num1 = new JTextField();
        name2 = new JTextField();
        num2 = new JTextField();
        compare =new JButton("加入比较");
        name1.setBounds(650, 450, 100, 50);
        add(name1);

        num1.setBounds(750, 450, 100, 50);
        add(num1);

        name2.setBounds(650, 500, 100, 50);
        add(name2);

        num2.setBounds(750, 500, 100, 50);
        add(num2);

        //搜索按钮
        compare.setBounds(750, 550, 100, 50);
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
