package presentation.view.panel;

import presentation.listener.registerPanelListener.OpenLoginListener;
import presentation.listener.registerPanelListener.RegisterListener;

import javax.swing.*;
import java.awt.*;

/**
* Created by Harvey on 2017/3/12.
*/
public class RegisterPanel extends TempletPanel{

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
