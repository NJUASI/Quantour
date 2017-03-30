package presentation.chart;


import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import presentation.chart.TraceBack.TraceBackChart;
import presentation.chart.tools.TraceBackChartTool;
import presentation.chart.TraceBack.TraceBackListener;
import vo.TracebackChoiceVO;

import javax.swing.*;

/**
 * Created by Byron Dong on 2017/3/21.
 */
public class Main {

    public static void main(String []args){
        TraceBackChart traceBackChart = new TraceBackChart(new TracebackChoiceVO());
        JFreeChart chart = traceBackChart.createTracebackChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.addChartMouseListener(new TraceBackListener(chartPanel));


        JFrame ee=new JFrame();
        ee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ee.setSize(750*2,400*2);
        ee.getContentPane().add(chartPanel);
        ee.setVisible(true);

    }
}
