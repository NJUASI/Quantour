package presentation.listener.strategyPanelListener;

import presentation.controller.KStringPanelController;
import presentation.controller.StrategyPanelController;
import presentation.view.panel.iteration2.StrategyPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/4/9.
 */
public class SearchListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        StrategyPanelController.getInstance().search();
    }
}
