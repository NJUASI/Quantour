package presentation.listener.navigationBarListener;

import presentation.listener.ViewSwitchController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Harvey on 2017/3/13.
 */
public class CompareListener extends MouseAdapter {
    /**
     * {@inheritDoc}
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        ViewSwitchController.getInstance().viewSwitch("comparePanel");
    }
}
