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
//    private static ThermometerPanel thermometerPanel;

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

        MyLabel label1=new MyLabel("涨停") ;
//        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setBounds(adaptScreen(0,0,40,35));
        add(label1);

        MyLabel label2=new MyLabel("跌停") ;
        label2.setBounds(adaptScreen(0,0,100,35));
        add(label2);
        MyLabel label3=new MyLabel("涨幅超过5%") ;
        label3.setBounds(adaptScreen(0,0,100,35));
        add(label3);
        MyLabel label4=new MyLabel("开盘‐收盘大于5%*" +
                "上一个交易日收盘价") ;
        label4.setBounds(adaptScreen(0,0,100,35));
        add(label4);
        MyLabel label5=new MyLabel("日期") ;
        label5.setBounds(adaptScreen(0,0,100,35));
        add(label5);


//        search = new JButton("搜索");
//        search.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
//        search.setBounds(adaptScreen(600,50,80,35));
//        search.addMouseListener(new SearchListner());
//        add(search);

    }




    public void setData() {
        date.getDate();
    }
}
