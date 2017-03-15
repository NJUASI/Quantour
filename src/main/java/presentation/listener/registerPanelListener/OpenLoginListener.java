package presentation.listener.registerPanelListener;

import presentation.controller.RegisterController;
import presentation.listener.ViewSwitchController;
import presentation.view.panel.RegisterPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Harvey on 2017/3/12.
 */
public class OpenLoginListener extends MouseAdapter{

    /**
     * 由注册界面跳转到登录界面
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        ViewSwitchController.getInstance().viewSwitch("loginPanel");
    }
}
