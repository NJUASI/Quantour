package presentation.view;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
public class ThermometerPanel extends NavigationBar {
    //温度计面板
    private static ThermometerPanel thermometerPanel;
    DatePickerPanel date;
    /**
     * 温度计面板构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public ThermometerPanel() {
        date = new DatePickerPanel();
        date.setLocation(400, 400);
        add(date);
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
