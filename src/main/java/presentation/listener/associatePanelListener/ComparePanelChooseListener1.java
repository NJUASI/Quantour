package presentation.listener.associatePanelListener;

import presentation.controller.CompareController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Harvey on 2017/3/26.
 *
 * 用于监听comparePanel里面的associatePanel1中JList的选择
 */
public class ComparePanelChooseListener1 extends MouseAdapter{
    /**
     * 选择一个item
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        CompareController.getInstance().updateTextField();
    }
}
