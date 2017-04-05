package presentation.chart;


import presentation.view.tools.SingleDatePickerPanel;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.NoDataWithinException;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Byron Dong on 2017/3/21.
 */
public class Main {

    public static void main(String []args) throws DateNotWithinException, NoDataWithinException, IOException {
//        TraceBackChart traceBackChart = new TraceBackChart(new TraceBackCriteriaVO());
//        JFreeChart chart = traceBackChart.createTracebackChart();
//        ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.addChartMouseListener(new TraceBackListener(chartPanel));
        SingleDatePickerPanel singleDatePickerPanel = new SingleDatePickerPanel();
        singleDatePickerPanel.setBounds(350, 50,175,35);
        singleDatePickerPanel.setVisible(true);



        JFrame ee=new JFrame();
        ee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ee.setSize(750*2,400*2);
        ee.getContentPane().add(singleDatePickerPanel);
        ee.setVisible(true);

    }
}
