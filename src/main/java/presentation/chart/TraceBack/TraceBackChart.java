package presentation.chart.TraceBack;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import presentation.chart.tools.CandlestickChartTool;
import presentation.chart.tools.TraceBackChartTool;
import service.TracebackService;
import service.serviceImpl.TracebackServiceImpl;
import vo.CumulativeReturnVO;
import vo.TracebackChoiceVO;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/3/29.
 */
public class TraceBackChart {

    private double high = Double.MIN_VALUE;

    private double low = Double.MAX_VALUE;

    private TracebackService tracebackService;

    private List<CumulativeReturnVO> strategyData;

    private List<CumulativeReturnVO> baseData;

    private List<Integer> traceBackPoint;

    public TraceBackChart(TracebackChoiceVO tracebackChoiceVO) {
//        tracebackService = new TracebackServiceImpl();
        tracebackService = new TracebackSeviceStub();
        this.readData(tracebackChoiceVO);
    }

    public JFreeChart createTracebackChart(){
        CandlestickChartTool.setChartTheme();
        JFreeChart traceBackChart = ChartFactory.createTimeSeriesChart("","","",this.getTracebackDataset());

        XYPlot plot = traceBackChart.getXYPlot();
        plot.setRenderer(TraceBackChartTool.getRender(traceBackPoint.get(0),traceBackPoint.get(1)));
        plot.setDomainAxis(TraceBackChartTool.getX(baseData.get(0).currentDate,
                baseData.get(baseData.size()-1).currentDate));
        plot.setRangeAxis(TraceBackChartTool.getY(high,low));

        this.setPlot(plot);
        this.setTraceBackChart(traceBackChart);

        return traceBackChart;
    }

    private void setTraceBackChart(JFreeChart chart){
        chart.setBackgroundPaint(new Color(32,36,39));
        chart.getLegend().setItemPaint(new Color(201, 208, 214));
        chart.getLegend().setBackgroundPaint(new Color(32,36,39));
        chart.getLegend().setFrame(new BlockBorder(new Color(32,36,39)));
        chart.getXYPlot().getDomainAxis().setVisible(true);
        chart.getTitle().setPaint(new Color(201, 208, 214));
        chart.setTextAntiAlias(false);
    }

    private void setPlot(XYPlot plot){
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinePaint(new Color(44, 50, 54));
        plot.setRangeGridlinePaint(new Color(44, 50, 54));
        plot.setDomainGridlineStroke(new BasicStroke());
        plot.setRangeGridlineStroke(new BasicStroke());
    }

    private TimeSeriesCollection getTracebackDataset(){

        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
        TimeSeries strategy =  new TimeSeries("策略");
        TimeSeries base = new TimeSeries("基准");
        traceBackPoint = new ArrayList<>();

        for(int i=0;i<this.strategyData.size();i++){
            CumulativeReturnVO strategyVO = strategyData.get(i);
            CumulativeReturnVO baseVO = baseData.get(i);
            LocalDate strategyDate = strategyVO.currentDate;
            LocalDate baseDate = baseVO.currentDate;

            if(strategyVO.isTraceBack){
                traceBackPoint.add(i);
            }

            strategy.add(new Day(strategyDate.getDayOfMonth(),strategyDate.getMonthValue(),strategyDate.getYear()),
                    strategyVO.cumulativeReturn);
            base.add(new Day(baseDate.getDayOfMonth(),baseDate.getMonthValue(),baseDate.getYear()),
                    baseVO.cumulativeReturn);
        }

        timeSeriesCollection.addSeries(strategy);
        timeSeriesCollection.addSeries(base);
        this.setHighAndLow(timeSeriesCollection);

        return timeSeriesCollection;
    }

    private void setHighAndLow(TimeSeriesCollection timeSeriesCollection){
        int seriesCount = timeSeriesCollection.getSeriesCount();//一共有多少个序列，目前为一个
        for (int i = 0; i < seriesCount; i++) {
            int itemCount = timeSeriesCollection.getItemCount(i);//每一个序列有多少个数据项
            for (int j = 0; j < itemCount; j++) {
                if (high < timeSeriesCollection.getYValue(i,j)) {//取第i个序列中的第j个数据项的最大值
                    high = timeSeriesCollection.getYValue(i, j);
                }
                if (low > timeSeriesCollection.getYValue(i, j)) {
                    low = timeSeriesCollection.getYValue(i, j);
                }
            }
        }
    }


    private void readData(TracebackChoiceVO tracebackChoiceVO){
        this.strategyData = tracebackService.getStrategyCumulativeReturn(tracebackChoiceVO);
        this.baseData = tracebackService.getBaseCumulativeReturn(tracebackChoiceVO);
    }



}
