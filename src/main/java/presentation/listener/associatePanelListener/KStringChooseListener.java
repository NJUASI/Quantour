package presentation.listener.associatePanelListener;

import presentation.controller.AssociateController;
import presentation.controller.KStringPanelController;
import presentation.view.panel.AssociatePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Harvey on 2017/3/25.
 */
public class KStringChooseListener extends MouseAdapter {

    /**
     * 选择一个item
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        KStringPanelController.getInstance().updateTextField();
    }
}
