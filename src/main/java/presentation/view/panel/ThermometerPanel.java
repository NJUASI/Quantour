package presentation.view.panel;

import presentation.listener.thermometerPanelListener.SearchListner;
import presentation.view.tools.SingleDatePickerPanel;

import javax.swing.*;
import java.time.LocalDate;

/**
 * Created by 61990 on 2017/3/5.
 */
public class ThermometerPanel extends NavigationBar {
    //温度计面板
    private static ThermometerPanel thermometerPanel;

    //日期选择面板
    SingleDatePickerPanel date;

    //搜索按钮
    JButton search;

    //饼图面板
    JPanel piePanel;

    //柱状图面板
    JPanel barPanel;
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
        search.addMouseListener(new SearchListner());
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

    public LocalDate getDate() {
        return date.getDate();
    }
}
