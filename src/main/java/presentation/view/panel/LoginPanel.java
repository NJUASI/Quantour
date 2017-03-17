package presentation.view.panel;

import presentation.controller.LoginController;
import presentation.listener.loginPanelListener.LoginListener;
import presentation.listener.loginPanelListener.OpenRegisterListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by 61990 on 2017/3/5.
 */
public class LoginPanel extends TempletPanel {

    private static LoginPanel loginPanel;

    //登录控制器
    LoginController loginController;

    //用户名文本框
    private JTextField admin;

    //密码框
    private JPasswordField password;

    //注册按钮
    JButton openRegister;

    /**
     * 构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public LoginPanel() {
        setVisible(true);
        setBounds(adaptScreen(0,0,450,500));

        //to write your message
        admin = new JTextField("Enter your admin");
        admin.setFont(new Font("", Font.CENTER_BASELINE, 18 * width / 1920));
        admin.setBounds(adaptScreen(90, 200, 260, 38));
        admin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                admin.setText("");
            }
        });
//        admin.setOpaque(false);
//        admin.setBorder(null);
//        admin.setForeground(new Color(122,2,2));
        admin.setVisible(true);
        add(admin);

        password = new JPasswordField("");
        password.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                password.setText("");
            }
        });
        password.setFont(new Font("", Font.CENTER_BASELINE, 18 * width / 1920));
        password.setBounds(adaptScreen(90, 255, 260, 38));

        password.setVisible(true);
        add(password);

        //the Button setting of logIn to mainPanel
        JButton login = new JButton("登录");
        login.setBounds(adaptScreen(90, 340, 260, 40));
        login.setFont(new Font("", Font.CENTER_BASELINE, 20 * width / 1920));
        loginController = LoginController.getInstance();
        login.addMouseListener(new LoginListener());
        add(login);

        //the Button setting of opening register panel
        openRegister = new JButton("注册");
        openRegister.setFont(new Font("", Font.CENTER_BASELINE, 15 * width / 1920));
        openRegister.setBounds(adaptScreen(350, 350, 60, 35));
        openRegister.setContentAreaFilled(false);
        openRegister.addMouseListener(new OpenRegisterListener());
        add(openRegister);
    }

    public static LoginPanel getInstance() {
        if(loginPanel == null){
            loginPanel = new LoginPanel();
        }
        return loginPanel;
    }

    public String getUsername() {
    return admin.getText();
}

    public String getPassword() {
        return password.getText();
    }

    public void setUsername(String userName) {
        admin.setText(userName);
    }

    public void setPassword(String password) {
        this.password.setText(password);
    }
}