package presentation.view.chart;

import org.jfree.chart.ChartPanel;
import presentation.view.panel.TempletPanel;
import vo.StockComparisionVO;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by 61990 on 2017/3/14.
 */
public class CompareChartPanel extends TempletPanel {
    CompareChart1 chart1;
    CompareChart2 chart2;
    CompareChart3 chart3;
    CompareChart3 chart4;
    ChartPanel chartPanel1,chartPanel2, chartPanel3,chartPanel4;
    public CompareChartPanel(StockComparisionVO vo){
        setLayout(null);
        setBounds(adaptScreen(320,100,1500,850));
        chart1 = new CompareChart1(vo);
        chartPanel1 = chart1.createChart();
        chartPanel1.setBounds(adaptScreen(0,0,400,400));
        add(chartPanel1);
        chartPanel1.repaint();
        chart2 = new CompareChart2(vo.increaseMargin1,vo.increaseMargin2);
        chartPanel2 = chart2.createChart();
        chartPanel2.setBounds(adaptScreen(400,0,300,400));
        add(chartPanel2);
        chartPanel2.repaint();
        chart3 = new CompareChart3(vo.logarithmicYieldVariance1,vo.logarithmicYieldVariance2);
        chartPanel3 = chart3.createChart();
        chartPanel3.setBounds(adaptScreen(700,0,300,400));
        add(chartPanel3);
        chartPanel3.repaint();
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
