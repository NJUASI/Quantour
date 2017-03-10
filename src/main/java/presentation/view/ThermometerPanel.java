package presentation.view;

import service.StockService;
import service.StockSituationService;
import service.serviceImpl.StockSituationServiceImpl;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
public class ThermometerPanel extends NavigationBar {
    //温度计面板
    private static ThermometerPanel thermometerPanel;
    SingleDatePickerPanel date;
    JButton search;

    /**
     * 温度计面板构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public ThermometerPanel() {
        date = new SingleDatePickerPanel();
        date.setBounds(width * 600 / 1920, height * 50 / 1030, width * 150 / 1920, 35 * height / 1030);
        add(date);

        search = new JButton("搜索");
        search.setBounds(adaptScreen(900, 50, 150, 35));
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO 获取这一天的市场温度计
                StockSituationService stockSituationService = new StockSituationServiceImpl();
                try {
                    stockSituationService.showStockSituation(date.getDate());
                } catch (Exception e1) {

                }
            }
        });
        add(search);
//        add(bg);
    }

    /**
     * 单件模式
     *
     * @param
     * @return thermometerPanel 温度计面板
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static ThermometerPanel getInstance() {
        if (thermometerPanel == null) {
            thermometerPanel = new ThermometerPanel();
        }
        return thermometerPanel;
    }

    /**
     * 清除单件
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public void refresh() {
        thermometerPanel = null;
    }
}
