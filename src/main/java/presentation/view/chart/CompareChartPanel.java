package presentation.view.chart;

import org.jfree.chart.ChartPanel;
import presentation.view.panel.TempletPanel;
import vo.StockComparisionVO;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

/**
 * Created by 61990 on 2017/3/14.
 */
public class CompareChartPanel extends TempletPanel {
    CompareChart1 chart1;
    CompareChart2 chart2;
    CompareChart3 chart3;
    CompareChart4 chart4,chart5,chart6;
    ChartPanel chartPanel1, chartPanel3,chartPanel4,chartPanel2, chartPanel5,chartPanel6;
    JScrollPane scrollPane;
    public CompareChartPanel(List<StockComparisionVO> vo){
        setBounds(adaptScreen(250,100,1500,850));

        scrollPane=new JScrollPane();
        scrollPane.setBounds(adaptScreen(0,0,1500,850));

        JPanel panel=new JPanel(new GridLayout(5,1));
        JPanel panel2 =new JPanel(new GridLayout(1,2));
        panel.setPreferredSize(new Dimension(1500,2500));
        panel2.setPreferredSize(new Dimension(1500,500));

        chart1 = new CompareChart1(vo);
        chartPanel1 = chart1.createChart();
        chartPanel1.setBounds(adaptScreen(0,0,600,500));
        panel2.add(chartPanel1);
        chartPanel1.repaint();

        chart2 = new CompareChart2(vo);
        chartPanel2 = chart2.createChart();
        chartPanel2.setBounds(adaptScreen(100,1500,750,400));
        panel.add(chartPanel2);
        chartPanel2.repaint();

        chart3 = new CompareChart3(vo);
        chartPanel3 = chart3.createChart();
        chartPanel3.setBounds(adaptScreen(730,0,400,500));
        panel2.add(chartPanel3);
        chartPanel3.repaint();

        panel.add(panel2);

        chart4 = new CompareChart4(vo.get(0).closes,null,vo.get(0).name,"","收盘价","");
        chartPanel4= chart4.createChart();
        chartPanel4.setBounds(adaptScreen(100,550,1000,400));
        panel.add(chartPanel4);
        chartPanel4.repaint();

        chart5 = new CompareChart4(vo.get(1).closes,null,vo.get(1).name,"","收盘价","收盘价");
        chartPanel5= chart5.createChart();
        chartPanel5.setBounds(adaptScreen(100,1000,870,400));
        panel.add(chartPanel5);
        chartPanel5.repaint();



        chart6 = new CompareChart4(vo.get(0).logarithmicYield,vo.get(1).logarithmicYield,vo.get(0).name,vo.get(1).name,"对数收益率","对数收益率");
        chartPanel6 = chart6.createChart();
        chartPanel6.setBounds(adaptScreen(100,2000,750,400));
        panel.add(chartPanel6);
        chartPanel6.repaint();
        panel.repaint();
        scrollPane.setVisible(true);
        scrollPane.add(panel);
        add(scrollPane);
//        chart4 = new CompareChart4(vo.l,vo.logarithmicYieldVariance2);
//        chartPanel4 = chart4.createChart();
//        chartPanel4.setBounds(adaptScreen(0,400,1000,500));
//        add(chartPanel4);
//        chartPanel4.repaint();
//        Map<LocalDate, Double> map= vo.closes1;
//        Set set = map.keySet();
//        System.out.println("sss");
//        for(Iterator iter = set.iterator(); iter.hasNext();)
//        {
//            String key = (String)iter.next();
//            Double value = map.get(key);
//            System.out.println(key+"===="+value);
//        }
//        System.out.println("eeee");
    }

}
