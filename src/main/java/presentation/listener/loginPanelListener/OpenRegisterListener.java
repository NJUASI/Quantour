package presentation.listener.loginPanelListener;

import presentation.controller.LoginController;
import presentation.view.panel.MainPanel;
import presentation.view.panel.RegisterPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Harvey on 2017/3/12.
 */
public class OpenRegisterListener extends MouseAdapter{

    private LoginController loginController;

    public OpenRegisterListener() {
        this.loginController = LoginController.getInstance();
    }

    /**
     * {@inheritDoc}
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        loginController.openRegister();
    }
}
