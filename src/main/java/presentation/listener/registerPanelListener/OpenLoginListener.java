package presentation.listener.registerPanelListener;

import presentation.controller.RegisterController;
import presentation.view.panel.RegisterPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Harvey on 2017/3/12.
 */
public class OpenLoginListener extends MouseAdapter{

    RegisterController registerController;

    public OpenLoginListener() {
        this.registerController = RegisterController.getInstance();
    }

    /**
     * {@inheritDoc}
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        registerController.openLogin();
    }
}
