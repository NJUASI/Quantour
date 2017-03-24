package presentation.view.panel;

import presentation.listener.thermometerPanelListener.SearchListner;
import presentation.view.tools.MyLabel;
import presentation.view.tools.SingleDatePickerPanel;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * Created by 61990 on 2017/3/5.
 */
public class ThermometerPanel extends TemplatePanel {
    //温度计面板
    private static ThermometerPanel thermometerPanel;

    //日期选择面板
    SingleDatePickerPanel date;

    //搜索按钮
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

        MyLabel label1=new MyLabel("日期") ;
        label1.setLocation(300*width/1920,50*height/1030);
        add(label1);
        date = new SingleDatePickerPanel();
        date.setBounds(adaptScreen(350, 50,175,35));
        add(date);

        search = new JButton("搜索");
        search.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
        search.setBounds(adaptScreen(600,50,80,35));
        search.addMouseListener(new SearchListner());
        add(search);


//        add(bg);
    }

    /**
     * 单件模式
     *
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
