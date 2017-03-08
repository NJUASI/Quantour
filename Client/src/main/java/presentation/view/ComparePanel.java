package presentation.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
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
    AssociatePanel associatePanel;
    AssociatePanel associatePanel2;

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



        //搜索按钮
        compare = new JButton("比较");
        compare.setBounds(adaptScreen(1300, 50, 70, 35));

        //提示框面板
        associatePanel = new AssociatePanel();
        associatePanel.setVisible(false);
        add(associatePanel);
        associatePanel2 = new AssociatePanel();
        associatePanel2.setVisible(false);
        add(associatePanel2);

        name1.setBounds(adaptScreen(900, 30, 150, 35));
        add(name1);

        num1.setBounds(adaptScreen(1100, 30, 50, 35));
        add(num1);


        name2.setBounds(adaptScreen(900, 70, 150, 35));
        add(name2);

        num2.setBounds(adaptScreen(1100, 70, 50, 35));
        add(num2);

        compare.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
        add(compare);
        add(bg);
        addFunction();
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

    /**
     * 增加提醒性的监听
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/8
     */
    void addFunction() {
        associatePanel.list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                name1.setText(associatePanel.getMessage());
                associatePanel.setVisible(false);
            }
        });
        associatePanel2.list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                name2.setText(associatePanel2.getMessage());
                associatePanel2.setVisible(false);
            }
        });
        Document dt1 = num1.getDocument();
        dt1.addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                associatePanel.setVisible(true);
                associatePanel.setBounds(adaptScreen(900, 66, 250, 200));
                associatePanel.updateText(num1.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                associatePanel.setVisible(false);
            }
        });
        Document dt2 = name1.getDocument();
        dt2.addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {

            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                associatePanel.setVisible(true);
                associatePanel.setBounds(adaptScreen(900, 66, 250, 200));
                associatePanel.updateText(num1.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                associatePanel.setVisible(false);
            }
        });
        Document dt3 = name2.getDocument();
        dt3.addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                associatePanel2.setVisible(true);
                associatePanel2.setBounds(adaptScreen(900, 105, 250, 200));
                associatePanel2.updateText(name2.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                associatePanel2.setVisible(true);
                associatePanel2.setBounds(adaptScreen(900, 105, 250, 200));
                associatePanel2.updateText(name2.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                associatePanel2.setVisible(false);
            }
        });
        Document dt4 = num2.getDocument();
        dt4.addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                associatePanel2.setVisible(true);
                associatePanel2.setBounds(adaptScreen(900, 105, 250, 200));
                associatePanel2.updateText(num2.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                associatePanel2.setVisible(false);
            }
        });
        setMouseClick(name1);
        setMouseClick(name2);
        setMouseClick(num1);
        setMouseClick(num2);
    }
    void setMouseClick(JTextField jTextField){
        jTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                associatePanel.setVisible(false);
                associatePanel2.setVisible(false);
            }
        });
    }
}
