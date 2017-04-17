package presentation.listener.chartMouseListener;


;import org.jfree.chart.*;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import presentation.view.tools.ColorUtils;


import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Byron Dong on 2017/3/28.
 */
public class TraceBackListener implements ChartMouseListener {

    ChartPanel chartPanel;

    Rectangle2D rectangle2D;

    ValueMarker markerY = null;


    public TraceBackListener(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }

    @Override
    public void chartMouseClicked(ChartMouseEvent event) {

    }

    @Override
    public void chartMouseMoved(ChartMouseEvent event) {
        JFreeChart chart = event.getChart();
        XYPlot xyplot = chart.getXYPlot();


        if(markerY!=null){
            xyplot.clearDomainMarkers();
            xyplot.clearRangeMarkers();
        }

        int mouseX =event.getTrigger().getX();
        int mouseY =event.getTrigger().getY();
        Point2D point2D = this.chartPanel.translateScreenToJava2D(new Point(mouseX, mouseY));
        ChartRenderingInfo info = this.chartPanel.getChartRenderingInfo();
        rectangle2D = chartPanel.getScreenDataArea();
        double yValue = xyplot.getRangeAxis().java2DToValue(point2D.getY(), info.getPlotInfo().getDataArea(), RectangleEdge.RIGHT);
        double xValue = xyplot.getDomainAxis().java2DToValue(point2D.getX(), info.getPlotInfo().getDataArea(), RectangleEdge.BOTTOM);

        markerY = new ValueMarker(yValue);  // position is the value on the axis

        this.setYMaker(markerY,yValue);

        this.setMyStoke();
        xyplot.addRangeMarker(markerY);

    }

    private void setMyStoke(){
        float dashes[] = {21,9,3,9};
        markerY.setPaint(ColorUtils.markLineColor());
        markerY.setStroke(new BasicStroke(0.001f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,8.f,dashes,10));

    }

    private void setYMaker(ValueMarker valueMarker,double value){
        this.setTheme(valueMarker);
        DecimalFormat df=new DecimalFormat("##.00%");
        valueMarker.setLabel(df.format(value));
        valueMarker.setLabelAnchor(RectangleAnchor.CENTER);

    }

    private void setTheme(ValueMarker valueMarker){
        valueMarker.setLabelPaint(ColorUtils.fontColor());
        valueMarker.setOutlinePaint(ColorUtils.fontColor());
        valueMarker.setLabelBackgroundColor(ColorUtils.markLabelColor());
        valueMarker.setLabelFont(new Font("宋体", Font.PLAIN, 12));
    }

}
