package presentation.listener.stocksTablePanelListener;

import presentation.controller.StocksTableController;
import presentation.view.panel.user.UserPanel;
import presentation.view.tools.PopUpFrame;
import utilities.exceptions.PrivateStockExistedException;
import utilities.exceptions.PrivateStockNotFoundException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/4/13.
 */
public class AddFavoriteListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            StocksTableController.getInstance().addFavorite();
            UserPanel.getInstance().refreshFavorite();
        } catch (PrivateStockExistedException e1) {
            new PopUpFrame(e1.getMessage());
        } catch (PrivateStockNotFoundException e1) {
            new PopUpFrame(e1.getMessage());
        }
    }
}
