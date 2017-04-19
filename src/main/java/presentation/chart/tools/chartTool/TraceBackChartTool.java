package presentation.chart.tools.chartTool;

import org.jfree.chart.axis.*;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;

import org.jfree.ui.TextAnchor;
import presentation.chart.tools.renderer.TraceBackXYLineRenderer;
import presentation.view.tools.ColorUtils;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/3/28.
 */
public class TraceBackChartTool {

    /**
     * 设置画笔
     *
     * @param  high 最大回测点的高值位置
     * @param  low 最大会测点的低值位置
     * @return TraceBackXYLineRenderer 画笔
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/30
     */
    public TraceBackXYLineRenderer getRender(int high, int low) {
        TraceBackXYLineRenderer lineAndShapeRenderer = new TraceBackXYLineRenderer(high,low);
        lineAndShapeRenderer.setBaseItemLabelsVisible(true);

        lineAndShapeRenderer.setSeriesShapesVisible(0, false);
        lineAndShapeRenderer.setSeriesShapesVisible(1, false);

        lineAndShapeRenderer.setSeriesPaint(0, ColorUtils.upColor());
        lineAndShapeRenderer.setSeriesPaint(1, ColorUtils.downColor());

        lineAndShapeRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE1, TextAnchor.BOTTOM_CENTER));
        lineAndShapeRenderer.setBaseShapesVisible(false);
        lineAndShapeRenderer.setSeriesShapesVisible(0,true);

        return lineAndShapeRenderer;
    }

    /**
     * 设置X轴
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/30
     * @return  DateAxis
     */
    public DateAxis getX (LocalDate start,LocalDate end) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        DateAxis xAxis = new DateAxis();

        xAxis.setAutoRange(true);//设置不采用自动设置时间范围
        try {
            xAxis.setRange(dateFormat.parse(start.toString()), dateFormat.parse(end.toString()));//设置时间范围，注意时间的最大值要比已有的时间最大值要多一天
        } catch (Exception e) {
            e.printStackTrace();
        }
//        xAxis.setTimeline(timeline);//设置时间线显示的规则，用这个方法就摒除掉了周六和周日这些没有交易的日期，使图形看上去连续
        xAxis.setAutoTickUnitSelection(false);//设置不采用自动选择刻度值
        xAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);//设置标记的位置
        xAxis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());//设置标准的时间刻度单位
        xAxis.setTickUnit(new DateTickUnit(DateTickUnit.DAY,3));//设置时间刻度的间隔，一般以周为单位
        xAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));//设置显示时间的格式
        xAxis.setVerticalTickLabels(true);
        xAxis.setLabelPaint(ColorUtils.fontColor());
        xAxis.setTickLabelPaint(ColorUtils.fontColor());
        xAxis.setAxisLineVisible(false);
        xAxis.setUpperMargin(0.06);
        xAxis.setLowerMargin(0.01);

        return xAxis;
    }

    /**
     * 设置Y轴
     *
     * @param  high 最大回测点的高值位置
     * @param  low 最大会测点的低值位置
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/30
     */
    public NumberAxis getY(double high , double low){
        NumberAxis yAxis = new NumberAxis();

        yAxis.setAutoRange(false);//不使用自动设定范围
        yAxis.setRange(low-0.1, high+0.1);
        yAxis.setTickUnit(new NumberTickUnit((0.025)));//设置刻度显示的密度
        yAxis.setLabelPaint(ColorUtils.fontColor());
        yAxis.setTickLabelPaint(ColorUtils.fontColor());
        yAxis.setAutoRangeIncludesZero(false);
        yAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());
        yAxis.setLowerMargin(0.01);
        yAxis.setUpperMargin(0.06);
        return yAxis;

    }
}
