package presentation.listener.loginPanelListener;

import presentation.listener.ViewSwitchController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Harvey on 2017/3/12.
 */
public class OpenRegisterListener extends MouseAdapter{

    private ViewSwitchController viewSwitchController;

    public OpenRegisterListener() {
        this.viewSwitchController = ViewSwitchController.getInstance();
    }

    /**
     * {@inheritDoc}
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        viewSwitchController.viewSwitch("registerPanel");
    }
}
