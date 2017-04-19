package presentation.listener.stocksTablePanelListener;

import presentation.controller.StocksTableController;
import presentation.controller.ViewSwitchController;
import presentation.view.panel.NavigationBar;
import presentation.view.panel.StocksTablePanel;
import presentation.view.panel.user.UserPanel;
import presentation.view.tools.StocksTablePane;
import utilities.CodeReserve;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Harvey on 2017/3/15.
 *
 * 股票列表面板查看详情的按钮监听器
 */
public class DetailOfCodeListener extends MouseAdapter {
    /**
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        CodeReserve.getInstance().setCode(StocksTableController.getInstance().stocksTablePane.getCode()[0],StocksTableController.getInstance().stocksTablePane.getName());
        ViewSwitchController.getInstance().viewSwitch("kStringPanel");
        StocksTableController.getInstance().checkDetail();
    }
}
