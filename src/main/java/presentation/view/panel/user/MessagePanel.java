package presentation.view.panel.user;

import presentation.listener.navigationBarListener.UserListener;
import presentation.listener.userPanelListener.ModifyPasswordListener;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.WindowData;
import presentation.view.tools.component.MyButton;
import presentation.view.tools.component.MyLabel;
import utilities.IDReserve;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/4/13.
 */
public class MessagePanel extends TemplatePanel{
    MyLabel userNum, password;
    MyLabel key,message1,message2;
    JPasswordField password1,password2;
    JButton modify, sure,cancel;
    int width;
    int height;
    public MessagePanel() {
        width= WindowData.getInstance().getWidth();
        height= WindowData.getInstance().getHeight();

        setBackground(new Color(32,36,39));

        JLabel logo =new JLabel();
        ImageIcon bgPicture= new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("picture/logo3.png"));
        bgPicture.setImage(bgPicture.getImage().getScaledInstance(80*width/1920, 80*height/1130, Image.SCALE_DEFAULT ));
        logo.setIcon(bgPicture);
        logo.addMouseListener(new UserListener());
        logo.setBounds(adaptScreen(120,50,80,80));
        add(logo);

        MyLabel name = new MyLabel("账户",17);
        name.setBounds(adaptScreen(30, 150, 50, 35));
        add(name);

        key = new MyLabel("密码",17);
        key.setBounds(adaptScreen(30, 200, 50, 35));
        add(key);

        setMessage();

        modify = new MyButton("修改密码");
        modify.setBounds(adaptScreen(170,255,100 ,25));
        modify.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                modifyPassword();
            }
        });
        add(modify);



        message1 = new MyLabel("新密码",17);
        message1.setBounds(adaptScreen(30, 200, 80, 35));
        add(message1);

        message2 = new MyLabel("确认密码",17);
        message2.setBounds(adaptScreen(30, 250, 80, 35));
        add(message2);

        password1 =new JPasswordField();
        password1.setFont(new Font("微软雅黑" ,Font.CENTER_BASELINE,14* WindowData.getInstance().getWidth()/1920));
        password1.setBounds(adaptScreen(120,200,160,35));
        add(password1);

        password2=new JPasswordField();
        password2.setFont(new Font("微软雅黑" ,Font.CENTER_BASELINE,14* WindowData.getInstance().getWidth()/1920));
        password2.setBounds(adaptScreen(120,250,160,35));
        add(password2);

        sure = new MyButton("确认");
        sure.setBounds(adaptScreen(160,305,60 ,30));
        sure.addMouseListener(new ModifyPasswordListener());
        add(sure);

        cancel= new MyButton("取消");
        cancel.setBounds(adaptScreen(230,305,60 ,30));
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cancelModify();
            }
        });
        add(cancel);

        cancelModify();
    }
    public  void setMessage(){
        userNum = new MyLabel(IDReserve.getInstance().getUserID(),17);
        userNum.setBounds(adaptScreen(100,150,100,35));
        add(userNum);

        password = new MyLabel("********",17);
        password.setBounds(adaptScreen(100,200,100,35));
        add(password);
    }
    public void modifyPassword(){
        key.setVisible(false);
        password.setVisible(false);
        modify.setVisible(false);
        password1.setVisible(true);
        password1.setText("");
        password2.setVisible(true);
        password2.setText("");
        message1.setVisible(true);
        message2.setVisible(true);
        sure.setVisible(true);
        cancel.setVisible(true);
    }
    public void cancelModify(){
        key.setVisible(true);
        password.setVisible(true);
        modify.setVisible(true);
        password1.setVisible(false);
        password2.setVisible(false);
        message1.setVisible(false);
        message2.setVisible(false);
        sure.setVisible(false);
        cancel.setVisible(false);
    }
    public String getPassword(){
        if (password2.getText().equals(password1.getText())&&password1.getText()!=""){
            return password1.getText();
        }else{
            JOptionPane.showMessageDialog(null,"请确认输入密码");
        }
        return null;
    }
}
