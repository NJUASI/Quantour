package presentation.listener.loginPanelListener;

import presentation.controller.LoginController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
* Created by Harvey on 2017/3/12.
*/
public class LoginListener extends MouseAdapter {

    /**
     * 登录按钮监听鼠标点击
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        LoginController.getInstance().login();
    }
}
