package presentation.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.ui.RectangleInsets;
import utilities.exceptions.ColorNotExistException;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Byron Dong on 2017/3/21.
 */
public class ChartTool {

    /**
     * 设置画笔
     *
     * @return CandlestickRenderer 蜡烛图画笔
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     */
    public static CandlestickRenderer getRenderer() {
        CandlestickRenderer render = new CandlestickRenderer();
        render.setUseOutlinePaint(true); //设置是否使用自定义的边框线，程序自带的边框线的颜色不符合中国股票市场的习惯
        render.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_AVERAGE);//设置如何对K线图的宽度进行设定
        render.setAutoWidthGap(0.001);//设置各个K线图之间的间隔
        render.setUpPaint(new Color(255, 61, 61));//设置股票上涨的K线图颜色
        render.setDownPaint(new Color(15, 195, 81));//设置股票下跌的K线图颜色

        return render;
    }

    /**
     * 设置均线的画笔
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     * @return  XYLineAndShapeRenderer 均线画笔
     * @throws ColorNotExistException 均线类型不存在
     */
    public static XYLineAndShapeRenderer getAveragerRender(List<Integer> days) throws ColorNotExistException {
        ColorFactory factory = new ColorFactory();
        XYLineAndShapeRenderer lineAndShapeRenderer = new XYLineAndShapeRenderer();
        lineAndShapeRenderer.setBaseItemLabelsVisible(true);

        for (int i = 0; i < days.size(); i++) {
            lineAndShapeRenderer.setSeriesShapesVisible(i, false);
            if (factory.getColor(days.get(i)) == null) {
                throw new ColorNotExistException();
            }
            lineAndShapeRenderer.setSeriesPaint(i, factory.getColor(days.get(i)));
        }

        return lineAndShapeRenderer;
    }

    /**
     * 设置成交量的画笔
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @param  seriesCollection 数据集合
     * @param  candlestickRenderer K线的画笔
     * @return XYBarRenderer 成交量画笔
     */
    public static XYBarRenderer getXYBarRender(OHLCSeriesCollection seriesCollection, CandlestickRenderer candlestickRenderer){
        XYBarRenderer xyBarRender=new XYBarRenderer(){

            private static final long serialVersionUID = 1L;

            public Paint getItemPaint(int i, int j){//匿名内部类用来处理当日的成交量柱形图的颜色与K线图的颜色保持一致
                if(seriesCollection.getCloseValue(i,j)>seriesCollection.getOpenValue(i,j)){//收盘价高于开盘价，股票上涨，选用股票上涨的颜色
                    return candlestickRenderer.getUpPaint();
                }else{
                    return candlestickRenderer.getDownPaint();
                }
            }};
        xyBarRender.setBarPainter(new StandardXYBarPainter());
        xyBarRender.setDrawBarOutline(false);
        xyBarRender.setMargin(0.1);//设置柱形图之间的间隔
        xyBarRender.setShadowVisible(false);

        return xyBarRender;
    }

    /**
     * 设置X轴
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     * @return  DateAxis
     */
    public static DateAxis getX(LocalDate start, LocalDate end , SegmentedTimeline timeline,int gap) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        DateAxis xAxis = new DateAxis();

        xAxis.setAutoRange(true);//设置不采用自动设置时间范围
        try {
            xAxis.setRange(dateFormat.parse(start.toString()), dateFormat.parse(end.toString()));//设置时间范围，注意时间的最大值要比已有的时间最大值要多一天
        } catch (Exception e) {
            e.printStackTrace();
        }
        xAxis.setTimeline(timeline);//设置时间线显示的规则，用这个方法就摒除掉了周六和周日这些没有交易的日期，使图形看上去连续
        xAxis.setAutoTickUnitSelection(false);//设置不采用自动选择刻度值
        xAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);//设置标记的位置
        xAxis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());//设置标准的时间刻度单位
        xAxis.setTickUnit(new DateTickUnit(DateTickUnit.DAY, gap));//设置时间刻度的间隔，一般以周为单位
        xAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));//设置显示时间的格式
        xAxis.setLabelPaint(new Color(201, 208, 214));
        xAxis.setTickLabelPaint(new Color(201, 208, 214));

        return xAxis;
    }

    /**
     * 设置K线的Y轴
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @param  num 密度（值越大密度越小）
     */
    public static NumberAxis getY(double low, double high,int num){
        NumberAxis yAxis = new NumberAxis();

        yAxis.setAutoRange(false);//不使用自动设定范围
        yAxis.setRange(low*0.9, high*1.1);//设定y轴值的范围，比最低值要低一些，比最大值要大一些，这样图形看起来会美观些
        yAxis.setTickUnit(new NumberTickUnit((high*1.1-low*0.9)/num));//设置刻度显示的密度
        yAxis.setLabelPaint(new Color(201, 208, 214));
        yAxis.setTickLabelPaint(new Color(201, 208, 214));
        yAxis.setAutoRangeIncludesZero(false);

        return yAxis;

    }

    /**
     * 中文主题样式 解决乱码
     */
    public static void setChartTheme() {

        Font FONT = new Font("宋体", Font.PLAIN, 12);

        // 设置中文主题样式 解决乱码
        StandardChartTheme chartTheme = new StandardChartTheme("CN");
        // 设置标题字体
        chartTheme.setExtraLargeFont(FONT);
        // 设置图例的字体
        chartTheme.setRegularFont(FONT);
        // 设置轴向的字体
        chartTheme.setLargeFont(FONT);
        chartTheme.setSmallFont(FONT);
        chartTheme.setTitlePaint(new Color(201, 208, 214));
        chartTheme.setSubtitlePaint(new Color(201, 208, 214));

        chartTheme.setLegendBackgroundPaint(new Color(32,36,39));// 设置标注
        chartTheme.setLegendItemPaint(new Color(201, 208, 214));//
        chartTheme.setChartBackgroundPaint(new Color(32,36,39));


        chartTheme.setPlotBackgroundPaint(new Color(32,36,39));// 绘制区域
        chartTheme.setPlotOutlinePaint(new Color(32,36,39));// 绘制区域外边框
        chartTheme.setLabelLinkPaint(new Color(8, 55, 114));// 链接标签颜色
        chartTheme.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);

        chartTheme.setAxisOffset(new RectangleInsets(5, 12, 5, 12));
        chartTheme.setDomainGridlinePaint(new Color(44, 50, 54));// X坐标轴垂直网格颜色
        chartTheme.setRangeGridlinePaint(new Color(44, 50, 54));// Y坐标轴水平网格颜色

        chartTheme.setBaselinePaint(new Color(44, 50, 54));
        chartTheme.setCrosshairPaint(new Color(44, 50, 54));// 不确定含义
        chartTheme.setAxisLabelPaint(new Color(201, 208, 214));// 坐标轴标题文字颜色
        chartTheme.setTickLabelPaint(new Color(201, 208, 214));// 刻度数字
        chartTheme.setBarPainter(new StandardBarPainter());// 设置柱状图渲染
        chartTheme.setXYBarPainter(new StandardXYBarPainter());// XYBar 渲染

        chartTheme.setItemLabelPaint(Color.black);

        ChartFactory.setChartTheme(chartTheme);
    }
}
