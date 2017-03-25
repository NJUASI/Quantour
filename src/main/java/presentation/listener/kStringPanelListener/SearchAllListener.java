package presentation.listener.kStringPanelListener;

import presentation.controller.KStringPanelController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Harvey on 2017/3/25.
 */
public class SearchAllListener extends MouseAdapter{
    /**
     * 总体信息
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        KStringPanelController.getInstance().searchAll();
    }
}
