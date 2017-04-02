package presentation.chart;


import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import presentation.chart.TraceBack.TraceBackChart;
import presentation.listener.chartMouseListener.TraceBackListener;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.NoDataWithinException;
import vo.TracebackCriteriaVO;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Byron Dong on 2017/3/21.
 */
public class Main {

    public static void main(String []args) throws DateNotWithinException, NoDataWithinException, IOException {
        TraceBackChart traceBackChart = new TraceBackChart(new TracebackCriteriaVO());
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
