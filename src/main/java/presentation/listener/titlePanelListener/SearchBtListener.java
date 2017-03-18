package presentation.listener.titlePanelListener;

import presentation.controller.TitleController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Harvey on 2017/3/18.
 */
public class SearchBtListener extends MouseAdapter {
    /**
     * {@inheritDoc}
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        TitleController.getInstance().search();
    }
}
