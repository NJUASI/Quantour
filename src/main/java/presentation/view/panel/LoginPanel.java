package presentation.view.panel;

import presentation.controller.LoginController;
import presentation.listener.loginPanelListener.LoginListener;
import presentation.listener.loginPanelListener.OpenRegisterListener;

import javax.swing.*;
import java.awt.*;


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
    private LoginPanel() {

        //to write your message
        admin = new JTextField("Enter your admin");
        admin.setFont(new Font("微软雅黑", Font.BOLD, 25 * width / 1920));
        admin.setBounds(adaptScreen(300, 400, 300, 50));
//        admin.setOpaque(false);
//        admin.setBorder(null);
//        admin.setForeground(new Color(122,2,2));
        admin.setVisible(true);
        add(admin);

        password = new JPasswordField();
        password.setFont(new Font("微软雅黑", Font.BOLD, 25 * width / 1920));
        password.setBounds(adaptScreen(300, 500, 300, 50));

//        password.setBorder(null);
//        password.setOpaque(false);
        password.setVisible(true);
        add(password);

        //the Button setting of logIn to mainPanel
        JButton login = new JButton("登录");
        login.setBounds(adaptScreen(360, 600, 80, 40));
        login.setFont(new Font("", Font.CENTER_BASELINE, 20 * width / 1920));
        loginController = LoginController.getInstance();
        login.addMouseListener(new LoginListener(admin.getText(),password.getText()));
        add(login);

        //the Button setting of opening register panel
        openRegister = new JButton("注册");
        openRegister.setForeground(Color.BLACK);
        openRegister.setFont(new Font("", Font.CENTER_BASELINE, 15 * width / 1920));
        openRegister.setBounds(adaptScreen(470, 610, 80, 40));
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