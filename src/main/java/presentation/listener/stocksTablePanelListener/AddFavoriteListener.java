package presentation.listener.stocksTablePanelListener;

import presentation.controller.StocksTableController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/4/13.
 */
public class AddFavoriteListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        StocksTableController.getInstance().addFavorite();
    }
}
