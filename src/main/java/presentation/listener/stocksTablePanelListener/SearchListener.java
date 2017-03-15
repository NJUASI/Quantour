package presentation.listener.stocksTablePanelListener;

import presentation.controller.StocksTableController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Harvey on 2017/3/15.
 *
 * 股票列表面板的监听器
 */
public class SearchListener extends MouseAdapter {

    /**
     *
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        StocksTableController.getInstance().search();
    }
}
