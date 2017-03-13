package presentation.line;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.XYPlot;
import presentation.line.aveLine.AveChart;
import presentation.line.kLine.KChart;
import presentation.view.util.ChartUtils;
import utilities.exceptions.ColorNotExistException;
import vo.ChartShowCriteriaVO;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CombinedDomainXYPlot;

import javax.swing.*;

//
///**
// * Created by Byron Dong on 2017/3/8.
// */
public class Main {
//
    public static void main(String []args){

//        KChart KChart = new KChart(new ChartShowCriteriaVO("1",LocalDate.of(2014,1,1),LocalDate.of(2014,4,29)));
//       KChart.setPlot(LocalDate.of(2014,1,1),LocalDate.of(2014,4,29),7);
//
//        XYPlot plot1 = KChart.getKLinePlot();
        List<Integer> tag = new ArrayList<Integer>();
        tag.add(10);
        tag.add(20);
        tag.add(30);
        tag.add(60);
//
//        AveChart aveChart = new AveChart(new ChartShowCriteriaVO("1",LocalDate.of(2014,1,1),LocalDate.of(2014,4,29)),tag);
//     try {
//      plot1 = aveChart.set(plot1);
//     } catch (ColorNotExistException e) {
//      e.printStackTrace();
//     }
//     XYPlot plot2 = KChart.getVolumePlot(0.1);
//
//        CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(KChart.getXAxis());//建立一个恰当的联合图形区域对象，以x轴为共享轴
//        combineddomainxyplot.add(plot1,2);//添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域2/3
//        combineddomainxyplot.add(plot2, 1);//添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域1/3
//        combineddomainxyplot.setGap(10);//设置两个图形区域对象之间的间隔空间

     LineChart chart = new LineChart(new ChartShowCriteriaVO("1",LocalDate.of(2014,1,1),LocalDate.of(2014,4,29)),tag,
             new Font("宋体",Font.BOLD,14));

        JFreeChart chartF = null;
        try {
            chartF = chart.getAll(0.1,10,2,1);
        } catch (ColorNotExistException e) {
            e.printStackTrace();
        }
//     try {
//      chartF = new JFreeChart("深发展Ａ", JFreeChart.DEFAULT_TITLE_FONT,
//              /**chart.getKLineChart(),chart.getKLineAndVolumeChart(0.1,10,2,1),chart.getKLineAndAverageChart()*/
//              chart.getAll(0.1,10,2,1), true);
//     } catch (ColorNotExistException e) {
//      e.printStackTrace();
//     }

     ChartUtils.setAntiAlias(chartF);// 抗锯齿
        // 4:对柱子进行渲染[创建不同图形]
//        ChartUtils.setTimeSeriesRender(chartF.getPlot(), true, false);
        // 5:对其他部分进行渲染
        XYPlot xyplot = (XYPlot) chartF.getPlot();
        ChartUtils.setXY_XAixs(xyplot);
//        ChartUtils.setXY_YAixs(xyplot);


        ChartPanel ss=new ChartPanel(chartF);
        JFrame ee=new JFrame();
        ee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ee.setSize(750*2,400*2);
        ee.getContentPane().add(ss);
        ee.setVisible(true);

    }
//
}
