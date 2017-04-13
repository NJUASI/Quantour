package presentation.listener.userPanelListener;

import presentation.controller.UserController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/4/13.
 */
public class ModifyPasswordListener  extends MouseAdapter {
    /**
     * {@inheritDoc}
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e){
        UserController.getInstance().modifyPassword();
    }
}