package presentation.listener.userPanelListener;

import presentation.controller.UserController;
import presentation.view.panel.iteration2.ChooseStrategyPanel;
import presentation.view.tools.PopUpFrame;
import utilities.exceptions.PrivateStockNotExistException;
import utilities.exceptions.PrivateStockNotFoundException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/4/15.
 */
public class DeleteFavoriteListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e){
        try {
            UserController.getInstance().deleteFavorite();
            ChooseStrategyPanel.getInstance().refreshTabel();
        } catch (PrivateStockNotExistException e1) {
            new PopUpFrame(e1.getMessage());
        } catch (PrivateStockNotFoundException e1) {
            new PopUpFrame(e1.getMessage());
        }  catch (Exception e2){
//            new PopUpFrame("没有自选股");
        }
    }
}
