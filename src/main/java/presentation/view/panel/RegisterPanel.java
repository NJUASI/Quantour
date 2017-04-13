package presentation.view.panel;

import presentation.listener.registerPanelListener.RegisterListener;
import presentation.view.frame.LoginFrame;
import presentation.view.frame.RegisterFrame;
import presentation.view.tools.customizedButton.MyCustomizedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
* Created by Harvey on 2017/3/12.
*/
public class RegisterPanel extends TemplatePanel {

    private static RegisterPanel registerPanel;

    //用户名文本框
    private JTextField admin;

    //密码框
    private JPasswordField password;

    //确认密码框
    private JPasswordField password2;

    //登录按钮
//    private JButton openLogin;

    //注册按钮
    private JButton register;

    MyCustomizedButton close,min;
    /**
     * 构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public RegisterPanel() {

        //the field to write your message to register
        admin=new JTextField("your Phone/Admin");
        admin.setFont(new Font("", Font.CENTER_BASELINE, 18 * width / 1920));
        admin.setBounds(adaptScreen(90, 100, 260, 38));
//        admin.setOpaque(false);
//        admin.setBorder(null);
        admin.setVisible(true);
        add(admin);


        password = new JPasswordField();
        password.setFont(new Font("", Font.CENTER_BASELINE, 18 * width / 1920));
        password.setBounds(adaptScreen(90, 155, 260, 38));
//        password.setBorder(null);
//        password.setOpaque(false);
        password.setVisible(true);
        add(password);

        password2=new JPasswordField();
        password2.setFont(new Font("", Font.CENTER_BASELINE, 18 * width / 1920));
        password2.setBounds(adaptScreen(90, 210, 260, 38));
//        password2.setBorder(null);
//        password2.setOpaque(false);
        password2.setVisible(true);
        add(password2);

        //the Button setting of opening logIn panel
//        openLogin = new JButton("登录");
//        openLogin.setBounds(adaptScreen(470,610,80,40));
//        openLogin.setForeground(Color.BLACK);
//        openLogin.setFont(new Font("",Font.CENTER_BASELINE,15*width/1920));
//        openLogin.setContentAreaFilled(false);
//        openLogin.addMouseListener(new OpenLoginListener());
//        add(openLogin);

        //the Button setting of register
        register =new JButton("注册");
        register.setBounds(adaptScreen(90, 280, 260, 40));
        register.setFont(new Font("",Font.CENTER_BASELINE,20*width/1920));
        register.addMouseListener(new RegisterListener());
        add(register);

        //关闭按钮
        close=new MyCustomizedButton("close",0);
        close.setBounds(adaptScreen(405,0,40,35));
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                RegisterFrame.getInstance().refresh();
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
        min=new MyCustomizedButton("min",0);
        min.setBounds(adaptScreen(350,0,40,35));
        min.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                LoginFrame.getInstance().setExtendedState(JFrame.ICONIFIED);
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

    public static RegisterPanel getInstance(){
        if(registerPanel == null){
            registerPanel = new RegisterPanel();
        }
        return registerPanel;
    }

    public void setUserName(String userName) {
        admin.setText(userName);
    }

    public void setPassword(String password) {
        this.password.setText(password);
    }

    public void setPassword2(String password2) {
        this.password2.setText(password2);
    }

    public String getUserName() {
        return admin.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public String getPassword2() {
        return password2.getText();
    }
}
