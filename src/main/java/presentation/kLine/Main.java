package presentation.kLine;

import org.jfree.chart.plot.XYPlot;
import vo.ChartShowCriteriaVO;

import java.time.LocalDate;

//
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CombinedDomainXYPlot;
//
///**
// * Created by Byron Dong on 2017/3/8.
// */
public class Main {
//
    public static void main(String []args){

        Chart chart = new Chart(new ChartShowCriteriaVO("1",LocalDate.of(2014,1,1),LocalDate.of(2014,4,29)));

        chart.setRenderer(0.001);
        chart.setX(LocalDate.of(2014,1,1),LocalDate.of(2014,4,29),7);
        chart.setY(50);
        chart.setYOfVol(4);

        XYPlot plot1 = chart.getKLinePlot();
        XYPlot plot2 = chart.getVolumePlot(0.1);

//        LineData data = new LineData("1");
//        data.setData();
//        Chart tool = new Chart();
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
        CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(chart.getXAxis());//建立一个恰当的联合图形区域对象，以x轴为共享轴
        combineddomainxyplot.add(plot1, 2);//添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域2/3
        combineddomainxyplot.add(plot2, 1);//添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域1/3
        combineddomainxyplot.setGap(10);//设置两个图形区域对象之间的间隔空间
        JFreeChart chartF = new JFreeChart("深发展Ａ", JFreeChart.DEFAULT_TITLE_FONT, combineddomainxyplot, false);
        ChartFrame frame = new ChartFrame("SZ", chartF);
        frame.pack();
        frame.setVisible(true);
//
    }
//
}
