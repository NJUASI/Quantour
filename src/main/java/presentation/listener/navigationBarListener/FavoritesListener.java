package presentation.listener.navigationBarListener;

import presentation.listener.ViewSwitchController;
import presentation.view.panel.ComparePanel;
import presentation.view.panel.KStringPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Harvey on 2017/3/13.
 */
public class FavoritesListener extends MouseAdapter {
    /**
     * {@inheritDoc}
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        ViewSwitchController.getInstance().viewSwitch("favoritesPanel");
        KStringPanel.getInstance().associatePanel.setVisible(false);
        ComparePanel.getInstance().associatePanel.setVisible(false);
        ComparePanel.getInstance().associatePanel2.setVisible(false);
    }
}
