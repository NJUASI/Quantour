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

    ViewSwitchController viewSwitchController;

    public OpenLoginListener() {
        this.viewSwitchController = ViewSwitchController.getInstance();
    }

    /**
     * {@inheritDoc}
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        viewSwitchController.viewSwitch("loginPanel");
    }
}
