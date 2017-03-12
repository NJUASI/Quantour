package presentation.listener.loginPanelListener;

import presentation.controller.LoginController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
* Created by Harvey on 2017/3/12.
*/
public class LoginListener extends MouseAdapter {

    //登录界面控制器
    LoginController loginController;

    public LoginListener(String userName,String password) {
        loginController = LoginController.getInstance();
    }

    /**
     * 登录按钮监听鼠标点击
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        loginController.login();
    }
}
