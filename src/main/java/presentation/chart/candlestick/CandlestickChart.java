package presentation.chart.candlestick;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.ui.RectangleEdge;
import presentation.chart.tools.CandlestickChartTool;
import presentation.listener.chartMouseListener.CandlestickListener;
import presentation.view.tools.ChartUtils;
import presentation.view.tools.WindowData;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import utilities.exceptions.*;
import vo.ChartShowCriteriaVO;
import vo.FirstLastDayVO;
import vo.StockVO;

import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Byron Dong on 2017/3/21.
 */
public class CandlestickChart {

    //K线的数据最高价
    private double high = Double.MIN_VALUE;

    //K线的数据最低价
    private double low = Double.MAX_VALUE;

    //根据要求取出的数据
    private List<StockVO> data;

    //需要剔除的日期
    private List<LocalDate> dateException;

    //逻辑层对象
    private ChartService service;

    //X轴起始日期
    private LocalDate start ;

    //Y轴的结束日期
    private LocalDate end ;

    private AverageChart averageChart;

    /**
     * 根据股票代号进行初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    public CandlestickChart(String code,List<Integer> days) throws IOException, CodeNotFoundException {
        data = new ArrayList<StockVO>();
        this.service = new ChartServiceImpl();
        dateException = this.service.getDateWithoutData(code);
        averageChart = new AverageChart(code,days);
        this.readData(code);
    }

    /**
     * 根据股票代号和起始日期进行初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    public CandlestickChart(ChartShowCriteriaVO chartShowCriteriaVO,List<Integer> days) throws DateNotWithinException, IOException, CodeNotFoundException, NoDataWithinException, DateShortException {
        data = new ArrayList<StockVO>();
        this.service = new ChartServiceImpl();
        dateException = this.service.getDateWithoutData(chartShowCriteriaVO);
        averageChart = new AverageChart(chartShowCriteriaVO,days);
        start = chartShowCriteriaVO.start;
        end = chartShowCriteriaVO.end;
        this.readData(chartShowCriteriaVO);
    }

    public ChartPanel createCandlestickChartPanel() throws ColorNotExistException {
        JFreeChart candlestickChart = this.createCandlestickChart();
        ChartUtils.setAntiAlias(candlestickChart);

        ChartPanel chartPanel = new ChartPanel(candlestickChart);
        chartPanel.addChartMouseListener(new CandlestickListener(chartPanel));
        chartPanel.setPopupMenu(null);
        chartPanel.setMouseZoomable(true,false);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setZoomAroundAnchor(true);
        chartPanel.setVisible(true);

        return chartPanel;
    }

    public Panel createAllPanel() throws ColorNotExistException {

        Panel chartPanel =  new Panel(null);
        OHLCSeriesCollection ohlcSeriesCollection = this.getCandlestickData();

        VolumeChart chart = new VolumeChart(this.data,ohlcSeriesCollection, CandlestickChartTool.getX(this.start,this.end,this.getTimeLine(),this.getGap()));
        ChartPanel volumePanel =  chart.createVolumePanel();
        ChartPanel candlestickPanel = this.createCandlestickChartPanel();
        candlestickPanel.setBounds(0,0,1620* WindowData.getInstance().getWidth()/1920,600* WindowData.getInstance().getHeight()/1030);
        volumePanel.setBounds(48,600*WindowData.getInstance().getHeight()/1030,1600* WindowData.getInstance().getWidth()/1920,250* WindowData.getInstance().getHeight()/1030);
        candlestickPanel.setVisible(true);
        volumePanel.setVisible(true);

        chartPanel.add(candlestickPanel);
        chartPanel.add(volumePanel);
        chartPanel.setVisible(true);

        return chartPanel;
    }

    private JFreeChart createCandlestickChart() throws ColorNotExistException {
        CandlestickChartTool.setChartTheme();

        JFreeChart candlestickChart = ChartFactory.createCandlestickChart(this.data.get(0).name, "", "",
                null, true);
        candlestickChart.setAntiAlias(false);
        candlestickChart.setTextAntiAlias(false);
        XYPlot plot = candlestickChart.getXYPlot();
        OHLCSeriesCollection ohlcSeriesCollection = this.getCandlestickData();
        plot.setDataset(0, ohlcSeriesCollection);
        plot.setRenderer(0, CandlestickChartTool.getRenderer(ohlcSeriesCollection));
        plot = averageChart.set(plot);

        plot = this.setPlot(plot);
        plot.setDomainAxis(CandlestickChartTool.getX(this.start,this.end,this.getTimeLine(),this.getGap()));
        plot.setRangeAxis(CandlestickChartTool.getY(this.low,this.high,30));//y轴的密度

        candlestickChart = this.setChart(candlestickChart);

        return candlestickChart;
    }

    private JFreeChart setChart(JFreeChart chart){
        chart.setBackgroundPaint(new Color(32,36,39));
        chart.getLegend().setItemPaint(new Color(201, 208, 214));
        chart.getLegend().setBackgroundPaint(new Color(32,36,39));
        chart.getLegend().setFrame(new BlockBorder(new Color(32,36,39)));
        chart.getLegend().setPosition(RectangleEdge.LEFT);
        chart.getTitle().setPaint(new Color(201, 208, 214));
        chart.setTextAntiAlias(false);
        chart.getXYPlot().getDomainAxis().setVisible(false);
        return chart;
    }

    private XYPlot setPlot(XYPlot plot){
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinePaint(new Color(44, 50, 54));
        plot.setRangeGridlinePaint(new Color(44, 50, 54));
        plot.setDomainGridlineStroke(new BasicStroke());
        plot.setRangeGridlineStroke(new BasicStroke());

        return plot;
    }

    /**
     * 获取合适的日期间隔
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     * @return  int 间隔
     */
    private int getGap(){
        int days = this.getTimeDifference();
        int gap = days/120;
        return (gap+1)*7;//以两周的时间作为基础（7）
    }

    /**
     * 获取指定日期的时间差
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     * @return  int 差值
     */
    private int getTimeDifference(){
        int years= end.getYear()-start.getYear();
        int startDay = start.getDayOfYear();
        int endDay = end.getDayOfYear();
        int result=0;
        LocalDate startYear = this.start;

        for(int i=0;i<years;i++){
            result = result + startYear.plusYears(i).lengthOfYear();
        }
        return (result-startDay+endDay);
    }

    /**
     * 获取自定义的时间线规则
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @return   SegmentedTimeline
     */
    private SegmentedTimeline getTimeLine() {
        SegmentedTimeline timeline = SegmentedTimeline.newMondayThroughFridayTimeline();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

        try {
            for (LocalDate date : dateException) {
                timeline.addException(dateFormat.parse(date.toString()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeline;
    }

    /**
     * 获取K线数据集合
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @Return OHLCSeriesCollection 数据集合
     */
    private OHLCSeriesCollection getCandlestickData() {
        OHLCSeriesCollection ohlcSeriesCollection = new OHLCSeriesCollection();
        OHLCSeries series = new OHLCSeries("");//高开低收数据序列，股票K线图的四个数据，依次是开，高，低，收

        for (StockVO stockVO : this.data) {
            series.add(new Day(stockVO.date.getDayOfMonth(), stockVO.date.getMonth().getValue(), stockVO.date.getYear())
                    , stockVO.open, stockVO.high, stockVO.low, stockVO.close);
        }
        ohlcSeriesCollection.addSeries(series);

        //设置最高值和最低值
        this.setHighAndLow(ohlcSeriesCollection);

        return ohlcSeriesCollection;
    }

    /**
     * 获取K线数据集合
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @Return OHLCSeriesCollection 数据集合
     */
    private OHLCDataset getCandlestickDataset() {
        List<OHLCDataItem> dataItems = new ArrayList<OHLCDataItem>();
        DateFormat df = new SimpleDateFormat("y-M-d");

        for (StockVO stockVO : this.data) {
            Date date = null;
            try {
                date = df.parse(stockVO.date.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            double open = stockVO.open;
            double high = stockVO.high;
            double low = stockVO.low;
            double close = stockVO.close;
            double volume = Double.parseDouble(stockVO.volume);
            double adjClose = stockVO.adjClose;

            // adjust data:
            open = open * adjClose / close;
            high = high * adjClose / close;
            low = low * adjClose / close;

            OHLCDataItem item = new OHLCDataItem(date, open, high, low, adjClose, volume);
            dataItems.add(item);
        }
        Collections.reverse(dataItems);
        OHLCDataItem[] dataSet = dataItems.toArray(new OHLCDataItem[dataItems.size()]);
        OHLCDataset dataset = new DefaultOHLCDataset("", dataSet);

       this.getCandlestickData();

        return dataset;
    }

    /**
     * 获取K线数据的最高值和最低值(历史)
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    private void setHighAndLow(OHLCSeriesCollection seriesCollection) {
        int seriesCount = seriesCollection.getSeriesCount();//一共有多少个序列，目前为一个
        for (int i = 0; i < seriesCount; i++) {
            int itemCount = seriesCollection.getItemCount(i);//每一个序列有多少个数据项
            for (int j = 0; j < itemCount; j++) {
                if (high < seriesCollection.getHighValue(i, j)) {//取第i个序列中的第j个数据项的最大值
                    high = seriesCollection.getHighValue(i, j);
                }
                if (low > seriesCollection.getLowValue(i, j) && seriesCollection.getLowValue(i, j) > 0) {//取第i个序列中的第j个数据项的最小值
                    low = seriesCollection.getLowValue(i, j);
                }
            }

        }
    }

    /**
     * 根据股票代号获取股票数据
     *
     * @param code 股票代号
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    private void readData(String code) throws IOException, CodeNotFoundException {
        FirstLastDayVO firstLastDayVO = this.service.getFirstAndLastDay(code);
        start = firstLastDayVO.first;
        end = firstLastDayVO.last;
        data = this.service.getSingleStockRecords(String.valueOf(Integer.parseInt(code)));
    }

    /**
     * 根据股票代号和日期获取股票数据
     *
     * @param chartShowCriteriaVO 股票信息载体
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    private void readData(ChartShowCriteriaVO chartShowCriteriaVO) throws DateNotWithinException, IOException, CodeNotFoundException, NoDataWithinException {
        data = this.service.getSingleStockRecords(chartShowCriteriaVO);
    }
}
