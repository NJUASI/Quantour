package presentation.listener.comparePanelListener;

import presentation.controller.CompareController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/14.
 */
public class CompareListener extends MouseAdapter

    {
    /**
     * 搜索指定日期的市场温度计
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        CompareController.getInstance().compare();
    }
}
