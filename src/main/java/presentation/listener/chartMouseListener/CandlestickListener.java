package presentation.listener.chartMouseListener;

import org.jfree.chart.*;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.RectangleEdge;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Byron Dong on 2017/3/22.
 */
public class CandlestickListener implements ChartMouseListener{

    ChartPanel chartPanel;
    Rectangle2D rectangle2D;
    ValueMarker markerX =null;
    ValueMarker markerY = null;

    public CandlestickListener(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }

    @Override
    public void chartMouseClicked(ChartMouseEvent event) {
    }

    @Override
    public void chartMouseMoved(ChartMouseEvent event) {
        JFreeChart chart = event.getChart();
        XYPlot xyplot = chart.getXYPlot();

        if(markerX!=null&&markerY!=null){
            xyplot.clearDomainMarkers();
            xyplot.clearRangeMarkers();
        }

        Point point =event.getTrigger().getPoint();
        int mouseX =event.getTrigger().getX();
        int mouseY =event.getTrigger().getY();
        Point2D point2D = this.chartPanel.translateScreenToJava2D(new Point(mouseX, mouseY));
        ChartRenderingInfo info = this.chartPanel.getChartRenderingInfo();
        rectangle2D = chartPanel.getScreenDataArea();
        double yValue = xyplot.getRangeAxis().java2DToValue(point2D.getY(), info.getPlotInfo().getDataArea(), RectangleEdge.RIGHT);
        double xValue = xyplot.getDomainAxis().java2DToValue(point2D.getX(), info.getPlotInfo().getDataArea(), RectangleEdge.BOTTOM);

        markerX = new ValueMarker(xValue);  // position is the value on the axis
        markerX.setPaint(Color.BLUE);
        markerY = new ValueMarker(yValue);  // position is the value on the axis
        markerY.setPaint(Color.BLUE);
        xyplot.addDomainMarker(markerX);
        xyplot.addRangeMarker(markerY);
    }
}
