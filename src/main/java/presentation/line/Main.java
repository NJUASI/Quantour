package presentation.line;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.XYPlot;
import presentation.line.aveLine.AveChart;
import presentation.line.kLine.KChart;
import presentation.view.util.ChartUtils;
import presentation.view.util.TestAve;
import vo.ChartShowCriteriaVO;

import java.time.LocalDate;

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

        KChart KChart = new KChart(new ChartShowCriteriaVO("1",LocalDate.of(2014,1,1),LocalDate.of(2014,4,29)));
       KChart.setPlot(LocalDate.of(2014,1,1),LocalDate.of(2014,4,29),1);

        XYPlot plot1 = KChart.getKLinePlot();
        int []tag = {5};
        AveChart aveChart = new AveChart(new ChartShowCriteriaVO("1",LocalDate.of(2014,1,1),LocalDate.of(2014,4,29)),tag);
        plot1 = aveChart.set(plot1);
//        XYPlot plot2 = KChart.getVolumePlot(0.1);

//        LineData data = new LineData("1");
//        data.setData();
//        KChart tool = new KChart();
//
//        final OHLCSeriesCollection seriesCollection = data.seriesCollection;
//        final TimeSeriesCollection timeSeriesCollection = data.timeSeriesCollection;
//        final CandlestickRenderer candlestickRenderer = tool.getRenderer();
//        DateAxis xAxis = tool.getX();
//        NumberAxis yAxis =tool.getY(data.getLow(),data.getHigh(),10);
//        NumberAxis yAxisOfVol = tool.getY(data.getLowVolume(),data.getHighVolume(),4);
//
//        XYBarRenderer xyBarRender=tool.getXYBarRender(seriesCollection,candlestickRenderer);
//
//        XYPlot plot1=new XYPlot(seriesCollection,xAxis,yAxis,candlestickRenderer);//设置画图区域对象
//        XYPlot plot2=new XYPlot(timeSeriesCollection,null,yAxisOfVol,xyBarRender);//建立第二个画图区域对象，主要此时的x轴设为了null值，因为要与第一个画图区域对象共享x轴
//
        CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(KChart.getXAxis());//建立一个恰当的联合图形区域对象，以x轴为共享轴
        combineddomainxyplot.add(plot1);//添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域2/3
//        combineddomainxyplot.add(plot2, 1);//添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域1/3
//        combineddomainxyplot.setGap(10);//设置两个图形区域对象之间的间隔空间
        JFreeChart chartF = new JFreeChart("深发展Ａ", JFreeChart.DEFAULT_TITLE_FONT, combineddomainxyplot, false);

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
