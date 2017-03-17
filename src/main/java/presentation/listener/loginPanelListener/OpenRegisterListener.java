package presentation.listener.loginPanelListener;

import presentation.view.frame.RegisterFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Harvey on 2017/3/12.
 */
public class OpenRegisterListener extends MouseAdapter{

    /**
     * 由登录界面跳转到注册界面
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
//        ViewSwitchController.getInstance().viewSwitch("registerPanel");
          RegisterFrame.getInstance();
            RegisterFrame.getInstance().setVisible(true);

    }
}
