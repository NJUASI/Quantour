package presentation.listener.thermometerPanelListener;

import presentation.controller.ThermometerController;
import presentation.view.chart.StockSituationBarChart;
import presentation.view.chart.StockSituationPieChart;
import vo.PriceRiseOrFallVO;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
