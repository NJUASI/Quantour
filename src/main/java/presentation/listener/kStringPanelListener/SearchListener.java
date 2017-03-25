package presentation.listener.kStringPanelListener;

import presentation.controller.KStringPanelController;
import presentation.view.panel.KStringPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Harvey on 2017/3/25.
 */
public class SearchListener extends MouseAdapter {

    /**
     * 局部信息
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        KStringPanelController.getInstance().search();
    }
}
