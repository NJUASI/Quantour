package presentation.chart;


import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import presentation.listener.chartMouseListener.CandlestickListener;
import presentation.view.util.ChartUtils;
import utilities.exceptions.CodeNotFoundException;
import utilities.exceptions.ColorNotExistException;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.NoDataWithinException;
import vo.ChartShowCriteriaVO;

import javax.swing.*;
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


        JFreeChart chartF = null;

        try {
            CandlestickChart candlestickChart = new CandlestickChart(new ChartShowCriteriaVO("1", LocalDate.of(2014,1,1),LocalDate.of(2014,4,29)),tag);
            chartF = candlestickChart.createAllPlot(2,1,5);

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
        }



        ChartUtils.setAntiAlias(chartF);// 抗锯齿
        XYPlot xyplot = (XYPlot) chartF.getPlot();
        ChartUtils.setXY_XAixs(xyplot);

        ChartPanel ss=new ChartPanel(chartF);
        ss.addChartMouseListener(new CandlestickListener(ss));
        JFrame ee=new JFrame();
        ee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ee.setSize(750*2,400*2);
        ee.getContentPane().add(ss);
        ee.setVisible(true);

    }
}
