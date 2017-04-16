package presentation.listener.userPanelListener;

import presentation.controller.UserController;
import presentation.view.tools.PopUpFrame;
import utilities.exceptions.PasswordInputException;

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
        try {
            UserController.getInstance().modifyPassword();
        } catch (PasswordInputException e1) {
            new PopUpFrame(e1.getMessage());
        }
    }
}