package presentation.listener.userPanelListener;

import presentation.controller.UserController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/4/15.
 */
public class DeleteFavoriteListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e){
        UserController.getInstance().deleteFavorite();
    }
}
