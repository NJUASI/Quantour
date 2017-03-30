package presentation.chart;


import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import presentation.chart.LoopBackChart.LoopBackChartTool;
import presentation.chart.LoopBackChart.ToolMouseListen;

import javax.swing.*;

/**
 * Created by Byron Dong on 2017/3/21.
 */
public class Main {

    public static void main(String []args){
        JFreeChart chart = LoopBackChartTool.createLoopBackChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.addChartMouseListener(new ToolMouseListen(chartPanel));

//        ss.setLayout(new BorderLayout());
//
//        ss.add(new ChartPanel(null),BorderLayout.NORTH);
        JFrame ee=new JFrame();
        ee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ee.setSize(750*2,400*2);
        ee.getContentPane().add(chartPanel);
        ee.setVisible(true);

    }
}
