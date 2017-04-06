package presentation.view.panel;

import presentation.view.frame.MainFrame;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.SearchPopupMenu;
import presentation.view.tools.SearchTextField;
import presentation.view.tools.customizedButton.MyButton;
import vo.StockSearchVO;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/17.
 */
public class TitlePanel extends TemplatePanel {

    private static TitlePanel titlePanel;

    JLabel label;

    MyButton close, min;

    //搜索按钮
    JButton searchBt;

    //搜索框
    JTextField searchTextField;

    //自动补全菜单
    SearchPopupMenu popupMenu;

    TitlePanel() {
        setLayout(null);
        setBounds(adaptScreen(90, 0, 1900, 40));
        setBackground(new Color(44, 50, 55));
        setVisible(true);

        label = new JLabel("行情");
        label.setFont(new Font("微软雅黑", Font.BOLD, 23 * width / 1920));
        label.setForeground(ColorUtils.fontColor());
        label.setBounds(adaptScreen(840, 0, 60, 40));
        add(label);

        //关闭按钮
        close = new MyButton("close", 0);
        close.setBounds(adaptScreen(1760, 0, 45, 40));
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                close.moveIn();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                close.moveOut();
            }
        });
        add(close);
        //最小化按钮
        min = new MyButton("min", 0);
        min.setBounds(adaptScreen(1695, 0, 45, 40));
        min.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.getInstance().setExtendedState(JFrame.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                min.moveIn();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                min.moveOut();
            }
        });
        add(min);

        //自动补全菜单
        popupMenu = new SearchPopupMenu();

        //搜索文本框
        searchTextField = new SearchTextField();
        searchTextField.setBounds(40,5,140,20);
//        add(searchTextField);
        searchTextField.setVisible(false);
        searchTextField.setEnabled(false);
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                popupMenu.show(searchTextField,38,30);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //do nothing
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });


        //搜索按钮
        searchBt = new JButton();
        searchBt.setBounds(40,5,20,20);
//        add(searchBt);
        searchBt.addMouseListener(new MouseAdapter() {
            /**
             * 鼠标点击,显示文本搜索框,隐藏搜索按钮
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                searchTextField.setEnabled(true);
                searchTextField.setVisible(true);
                searchBt.setVisible(false);
                searchBt.setEnabled(false);
                //获取焦点
                searchTextField.requestFocus();
            }
        });
    }

    public static TitlePanel getInstance(){
        if(titlePanel==null){
            titlePanel=new TitlePanel();
        }
        return titlePanel;
    }

    public void setTitle(String str){
        label.setText(str);
    }
}
