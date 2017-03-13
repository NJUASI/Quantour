package presentation.listener.registerPanelListener;

import presentation.controller.RegisterController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Harvey on 2017/3/12.
 */
public class RegisterListener extends MouseAdapter {

    /**
     * 注册按钮监听鼠标点击
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        RegisterController.getInstance().register();
    }
}
