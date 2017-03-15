package presentation.view.chart;

import org.jfree.chart.ChartPanel;
import presentation.view.panel.TempletPanel;
import vo.StockComparisionVO;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by 61990 on 2017/3/14.
 */
public class CompareChartPanel extends TempletPanel {
    CompareChart1 chart1;
    CompareChart3 chart3;
    CompareChart4 chart2,chart4,chart5,chart6;
    ChartPanel chartPanel1, chartPanel3,chartPanel4,chartPanel2, chartPanel5,chartPanel6;
    public CompareChartPanel(List<StockComparisionVO> vo){
        setLayout(null);
        setBounds(adaptScreen(320,100,1500,850));
        chart1 = new CompareChart1(vo);
        chartPanel1 = chart1.createChart();
        chartPanel1.setBounds(adaptScreen(0,0,350,500));
        add(chartPanel1);
        chartPanel1.repaint();

        chart3 = new CompareChart3(vo);
        chartPanel3 = chart3.createChart();
        chartPanel3.setBounds(adaptScreen(350,0,280,500));
        add(chartPanel3);
        chartPanel3.repaint();

        chart2 = new CompareChart4(vo.get(0).closes,vo.get(0).closes,vo.get(0).name,"","收盘价","");
        chartPanel2= chart2.createChart();
        chartPanel2.setBounds(adaptScreen(630,0,870,250));
        add(chartPanel2);
        chartPanel2.repaint();
        chart4 = new CompareChart4(vo.get(1).closes,vo.get(1).closes,vo.get(1).name,"","收盘价","收盘价");
        chartPanel4= chart4.createChart();
        chartPanel4.setBounds(adaptScreen(630,250,870,250));
        add(chartPanel4);
        chartPanel4.repaint();

        chart5 = new CompareChart4(vo.get(0).increaseMargin,vo.get(1).increaseMargin,"","","对数收益率","对数收益率");
        chartPanel5 = chart5.createChart();
        chartPanel5.setBounds(adaptScreen(0,500,750,255));
        add(chartPanel5);
        chartPanel5.repaint();

        chart6 = new CompareChart4(vo.get(0).logarithmicYield,vo.get(1).logarithmicYield,vo.get(0).name,vo.get(1).name,"涨幅/跌幅","涨幅跌幅");
        chartPanel6 = chart6.createChart();
        chartPanel6.setBounds(adaptScreen(750,500,750,270));
        add(chartPanel6);
        chartPanel6.repaint();


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
