package presentation.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import utilities.exceptions.CodeNotFoundException;
import utilities.exceptions.ColorNotExistException;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.NoDataWithinException;
import vo.ChartShowCriteriaVO;
import vo.StockVO;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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

    //默认的X轴起始日期（可修改）
    private LocalDate start = LocalDate.of(2005,2,1);

    //默认的X轴的结束日期（可修改）
    private LocalDate end = LocalDate.of(2014,4,29);

    private AverageChart averageChart;

    private VolumeChart volumeChart;

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
        dateException = this.service.getDateException(code);
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
    public CandlestickChart(ChartShowCriteriaVO chartShowCriteriaVO,List<Integer> days) throws DateNotWithinException, IOException, CodeNotFoundException, NoDataWithinException {
        data = new ArrayList<StockVO>();
        this.service = new ChartServiceImpl();
        dateException = this.service.getDateException(chartShowCriteriaVO);
        averageChart = new AverageChart(chartShowCriteriaVO,days);
        start = chartShowCriteriaVO.start;
        end = chartShowCriteriaVO.end;
        this.readData(chartShowCriteriaVO);
    }

    public JFreeChart createCandlestickChart() throws ColorNotExistException {
        ChartTool.setChartTheme();

        JFreeChart candlestickChart = ChartFactory.createCandlestickChart(this.data.get(0).name, "", "",
                null, true);
        candlestickChart.setAntiAlias(false);
        candlestickChart.setTextAntiAlias(false);
        XYPlot plot = candlestickChart.getXYPlot();
        plot.setDataset(0, this.getCandlestickData());
        plot.setRenderer(0, ChartTool.getRenderer());
        plot = averageChart.set(plot);
        plot.setDomainAxis(ChartTool.getX(this.start,this.end,this.getTimeLine(),this.getGap()));
        plot.setRangeAxis(ChartTool.getY(this.low,this.high,30));//y轴的密度

        candlestickChart = this.setChart(candlestickChart);

        return candlestickChart;
    }

    public JFreeChart createAllPlot(int candlestick ,int volume,int gap) throws ColorNotExistException {

        XYPlot plot = this.createCandlestickChart().getXYPlot();
        VolumeChart chart = new VolumeChart(this.data,this.getCandlestickData(),ChartTool.getRenderer());
        XYPlot volumePlot = chart.getVolumePlot();

        CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(ChartTool.
                getX(this.start,this.end,this.getTimeLine(),this.getGap()));//建立一个恰当的联合图形区域对象，以x轴为共享轴
        combineddomainxyplot.add(plot,candlestick);//添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域2/3
        combineddomainxyplot.add(volumePlot, volume);//添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域1/3
        combineddomainxyplot.setGap(gap);//设置两个图形区域对象之间的间隔空间

        JFreeChart chartAll = new JFreeChart("",new Font("宋体", Font.PLAIN, 12),combineddomainxyplot,true);

        chartAll = this.setChart(chartAll);

        return chartAll;
    }

    private JFreeChart setChart(JFreeChart chart){
        chart.setBackgroundPaint(new Color(32,36,39));
        chart.getLegend().setItemPaint(new Color(201, 208, 214));
        chart.getLegend().setBackgroundPaint(new Color(32,36,39));
        chart.getLegend().setFrame(new BlockBorder(new Color(32,36,39)));
        chart.getTitle().setPaint(new Color(201, 208, 214));
        chart.setTextAntiAlias(false);

        return chart;
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
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//
//        try {
//            for (LocalDate date : dateException) {
//                timeline.addException(dateFormat.parse(date.toString()));
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
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
        //series.add(new Day(28, 9, 2007), 9.2, 9.58, 9.16, 9.34);

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
