package presentation.listener.userPanelListener;

import presentation.controller.UserController;
import utilities.enums.DataSourceState;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/4/17.
 */
public class ChangeNetPath extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e){
        UserController.getInstance().changePath(DataSourceState.ORIGINAL);
    }
}
