package presentation.chart;


import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import presentation.listener.chartMouseListener.CandlestickListener;
import presentation.view.tools.ChartUtils;
import utilities.exceptions.*;
import vo.ChartShowCriteriaVO;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/3/21.
 */
public class Main {

    public static void main(String []args){
        List<Integer> tag = new ArrayList<Integer>();
        tag.add(10);
        tag.add(20);
        tag.add(30);
        tag.add(60);


            Panel ss = null;

        try {
            CandlestickChart candlestickChart = new CandlestickChart(new ChartShowCriteriaVO("1", LocalDate.of(2014,1,1),LocalDate.of(2014,4,29)),tag);
            ss = candlestickChart.createAllPanel();

        } catch (DateNotWithinException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CodeNotFoundException e) {
            e.printStackTrace();
        } catch (NoDataWithinException e) {
            e.printStackTrace();
        } catch (ColorNotExistException e) {
            e.printStackTrace();
        } catch (DateShortException e) {
            e.printStackTrace();
        }

//        ss.setLayout(new BorderLayout());
//
//        ss.add(new ChartPanel(null),BorderLayout.NORTH);
        JFrame ee=new JFrame();
        ee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ee.setSize(750*2,400*2);
        ee.getContentPane().add(ss);
        ee.setVisible(true);

    }
}
