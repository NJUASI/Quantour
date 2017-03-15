package presentation.listener.associatePanelListener;

import presentation.controller.AssociateController;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Harvey on 2017/3/15.
 */
public class InputListener extends KeyAdapter {
    /**
     * Invoked when a key has been typed.
     * This event occurs when a key press is followed by a key release.
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
        AssociateController.getInstance().search();
    }
}
