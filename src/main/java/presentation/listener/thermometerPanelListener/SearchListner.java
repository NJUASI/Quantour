package presentation.listener.thermometerPanelListener;

import presentation.controller.ThermometerController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Harvey on 2017/3/13.
 */
public class SearchListner extends MouseAdapter {
    /**
     * 搜索指定日期的市场温度计
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        ThermometerController.getInstance().search();
    }

}
