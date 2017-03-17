package presentation.view.panel;

import presentation.view.frame.LoginFrame;
import presentation.view.frame.MainFrame;
import presentation.view.tools.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/17.
 */
public class TitlePanel extends TemplatePanel {
    private static TitlePanel titlePanel;
    JLabel label;
    MyButton close,min;
    TitlePanel(){
        setLayout(null);
        setBounds(adaptScreen(100,0,1900,40));
        setBackground(new Color(44,50,55));
        setVisible(true);

        label= new JLabel("行情");
        label.setFont(new Font("微软雅黑",Font.BOLD,23*width/1920));
        label.setForeground(new Color(201, 208, 214));
        label.setBounds(adaptScreen(840,0,60,40));
        add(label);

        //关闭按钮
        close=new MyButton("close",0);
        close.setBounds(adaptScreen(1760,0,40,35));
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
        min=new MyButton("min",0);
        min.setBounds(adaptScreen(1700,0,40,35));
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
