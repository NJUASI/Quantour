package presentation.chart.traceBack;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import presentation.chart.tools.CandlestickChartTool;
import presentation.chart.tools.TraceBackChartTool;
import presentation.view.tools.ColorUtils;
import service.TraceBackService;
import service.serviceImpl.TraceBackService.TraceBackServiceImpl;
import utilities.enums.BlockType;
import utilities.enums.StType;
import utilities.enums.TraceBackStrategy;
import utilities.exceptions.*;
import vo.CumulativeReturnVO;
import vo.StockPoolCriteriaVO;
import vo.TraceBackCriteriaVO;
import vo.TraceBackVO;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/3/29.
 */
public class TraceBackChart {

    //最高比例
    private double high = Double.MIN_VALUE;

    //最低比例
    private double low = Double.MAX_VALUE;


    //策略的数据集合
    private List<CumulativeReturnVO> strategyData;

    //基准的数据集合
    private List<CumulativeReturnVO> baseData;

    //最大回测点的位置
    private List<Integer> traceBackPoint;

    /**
     * 根据回测的信息载体
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/30
     */
    public TraceBackChart(List<CumulativeReturnVO> strategyData, List<CumulativeReturnVO> baseData) {
        this.baseData = baseData;
        this.strategyData = strategyData;

//        this.readData();
    }

    /**
     * 创建回测图对象
     *
     * @return JFreeChart TraceBackChart对象
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/30
     */
    public JFreeChart createTracebackChart() {
        CandlestickChartTool.setChartTheme();
        TraceBackChartTool tool = new TraceBackChartTool();
        JFreeChart traceBackChart = ChartFactory.createTimeSeriesChart("", "", "", this.getTracebackDataset());

        XYPlot plot = traceBackChart.getXYPlot();
        plot.setRenderer(tool.getRender(traceBackPoint.get(0), traceBackPoint.get(1)));
        plot.setDomainAxis(tool.getX(baseData.get(0).currentDate,
                baseData.get(baseData.size() - 1).currentDate));
        plot.setRangeAxis(tool.getY(high, low));

        this.setPlot(plot);
        this.setTraceBackChart(traceBackChart);

        return traceBackChart;
    }

    /**
     * 修饰h回测图图
     *
     * @param chart 图表对象
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/30
     */
    private void setTraceBackChart(JFreeChart chart) {
        chart.setBackgroundPaint(ColorUtils.backgroundColor());
        chart.getLegend().setItemPaint(ColorUtils.fontColor());
        chart.getLegend().setBackgroundPaint(ColorUtils.backgroundColor());
        chart.getLegend().setFrame(new BlockBorder(ColorUtils.backgroundColor()));
        chart.getXYPlot().getDomainAxis().setVisible(true);
        chart.getTitle().setPaint(ColorUtils.fontColor());
        chart.setTextAntiAlias(false);
    }

    /**
     * 修饰画板
     *
     * @param plot 画板对象
     * @return XYPlot 修饰后的对象
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/30
     */
    private void setPlot(XYPlot plot) {
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinePaint(ColorUtils.lineColor());
        plot.setRangeGridlinePaint(ColorUtils.lineColor());
        plot.setDomainGridlineStroke(new BasicStroke());
        plot.setRangeGridlineStroke(new BasicStroke());
    }

    /**
     * 获取回测数据集合
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/30
     * @Return TimeSeriesCollection 数据集合
     */
    private TimeSeriesCollection getTracebackDataset() {

        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
        TimeSeries strategy = new TimeSeries("策略");
        TimeSeries base = new TimeSeries("基准");
        traceBackPoint = new ArrayList<>();

        System.out.println(strategyData.size());
        System.out.println(baseData.size());
        for (int i = 0; i < this.strategyData.size(); i++) {
            CumulativeReturnVO strategyVO = strategyData.get(i);
            CumulativeReturnVO baseVO = baseData.get(i);
            LocalDate strategyDate = strategyVO.currentDate;
            LocalDate baseDate = baseVO.currentDate;

            if (strategyVO.isTraceBack) {
                traceBackPoint.add(i);
            }

            strategy.add(new Day(strategyDate.getDayOfMonth(), strategyDate.getMonthValue(), strategyDate.getYear()),
                    strategyVO.cumulativeReturn);
            base.add(new Day(baseDate.getDayOfMonth(), baseDate.getMonthValue(), baseDate.getYear()),
                    baseVO.cumulativeReturn);
        }

        timeSeriesCollection.addSeries(strategy);
        timeSeriesCollection.addSeries(base);
        this.setHighAndLow(timeSeriesCollection);

        return timeSeriesCollection;
    }

    /**
     * 获取数据的最高值和最低值(历史)
     *
     * @param timeSeriesCollection 数据集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/30
     */
    private void setHighAndLow(TimeSeriesCollection timeSeriesCollection) {
        int seriesCount = timeSeriesCollection.getSeriesCount();//一共有多少个序列，目前为一个
        for (int i = 0; i < seriesCount; i++) {
            int itemCount = timeSeriesCollection.getItemCount(i);//每一个序列有多少个数据项
            for (int j = 0; j < itemCount; j++) {
                if (high < timeSeriesCollection.getYValue(i, j)) {//取第i个序列中的第j个数据项的最大值
                    high = timeSeriesCollection.getYValue(i, j);
                }
                if (low > timeSeriesCollection.getYValue(i, j)) {
                    low = timeSeriesCollection.getYValue(i, j);
                }
            }
        }
    }

    /**
     * 读取数据
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    private void readData() throws DateNotWithinException, NoDataWithinException, IOException, DateShortException, CodeNotFoundException, NoMatchEnumException, UnhandleBlockTypeException, DataSourceFirstDayException {
        TraceBackService traceBackService = new TraceBackServiceImpl();
        TraceBackCriteriaVO traceBackCriteriaVO1 = new TraceBackCriteriaVO();
        //设置TraceBackCriteriaVO
        traceBackCriteriaVO1.baseStockName = "沪深300";
        traceBackCriteriaVO1.startDate = LocalDate.of(2013, 5, 1);
        traceBackCriteriaVO1.endDate = LocalDate.of(2014, 4, 29);
        traceBackCriteriaVO1.strategyType = TraceBackStrategy.MR;
        traceBackCriteriaVO1.formativePeriod = 5;
        traceBackCriteriaVO1.holdingNum = 1;
        traceBackCriteriaVO1.holdingPeriod = 5;
        traceBackCriteriaVO1.isCustomized = false;

        List<BlockType> list = new ArrayList<>();
        list.add(BlockType.ZB);
        list.add(BlockType.CYB);
        StockPoolCriteriaVO stockPoolCriteriaVO = new StockPoolCriteriaVO(StType.EXCLUDE, list);
        traceBackCriteriaVO1.stockPoolVO = stockPoolCriteriaVO;


        List<String> stockPool = new ArrayList<>();
        stockPool.add("000001");
        stockPool.add("000022");
        stockPool.add("000010");
        stockPool.add("000004");

        long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        TraceBackVO traceBackVO = null;
        try {
            traceBackVO = traceBackService.traceBack(traceBackCriteriaVO1, stockPool);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println("enter");
        this.strategyData = traceBackVO.strategyCumulativeReturn;
        this.baseData = traceBackVO.baseCumulativeReturn;
    }
}
