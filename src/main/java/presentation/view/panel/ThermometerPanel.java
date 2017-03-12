package presentation.view.panel;

import presentation.StockSituationChart.StockSituationP;
import presentation.view.chart.StockSituationBarChart;
import presentation.view.chart.StockSituationPieChart;
import presentation.view.toos.SingleDatePickerPanel;
import vo.PriceRiseOrFallVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        piePanel=new JPanel();
        barPanel=new JPanel();

        search = new JButton("搜索");
        search.setBounds(adaptScreen(900, 50, 150, 35));
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO 获取这一天的市场温度计
                List<PriceRiseOrFallVO> list = new ArrayList<PriceRiseOrFallVO>();
                LocalDate date = LocalDate.now();
                list.add(new PriceRiseOrFallVO("涨停",45,date));
                list.add(new PriceRiseOrFallVO("跌停",50,date));
                list.add(new PriceRiseOrFallVO("涨幅超过5%",20,date));
                list.add(new PriceRiseOrFallVO("跌幅超过5%",10,date));
                list.add(new PriceRiseOrFallVO("开盘-收盘小于-5%*上一个交易日收盘价",40,date));
                list.add(new PriceRiseOrFallVO("开盘-收盘大于+5%*上一个交易日收盘价",40,date));

                StockSituationPieChart pieChart = new StockSituationPieChart(list.iterator());
                StockSituationBarChart barChart = new StockSituationBarChart(list.iterator());

                piePanel=pieChart.createChart();
                piePanel.setBounds(adaptScreen(230,200,800,600));
                piePanel.setBackground(new Color(55,60,56));
                add(piePanel);
                piePanel.repaint();

                barPanel=barChart.createChart();
                barPanel.setBounds(adaptScreen(1040,230,870,550));
                barPanel.setBackground(new Color(55,60,56));
                add(barPanel);
                barPanel.repaint();
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
