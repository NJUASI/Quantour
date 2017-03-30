package presentation.chart.LoopBackChart;


;import org.jfree.chart.*;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.RectangleEdge;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Byron Dong on 2017/3/28.
 */
public class ToolMouseListen implements ChartMouseListener {

    ChartPanel chartPanel;
    Rectangle2D rectangle2D;
    ValueMarker markerX = null;


    public ToolMouseListen(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }

    @Override
    public void chartMouseClicked(ChartMouseEvent event) {

    }

    @Override
    public void chartMouseMoved(ChartMouseEvent event) {
        JFreeChart chart = event.getChart();
        XYPlot xyplot = chart.getXYPlot();


        if (markerX != null) {
            xyplot.clearDomainMarkers();
        }

        int mouseX = event.getTrigger().getX();
        int mouseY = event.getTrigger().getY();
        Point2D point2D = this.chartPanel.translateScreenToJava2D(new java.awt.Point(mouseX, mouseY));
        ChartRenderingInfo info = this.chartPanel.getChartRenderingInfo();
        rectangle2D = chartPanel.getScreenDataArea();
        double yValue = xyplot.getRangeAxis().java2DToValue(point2D.getY(), info.getPlotInfo().getDataArea(), RectangleEdge.RIGHT);
        double xValue = xyplot.getDomainAxis().java2DToValue(point2D.getX(), info.getPlotInfo().getDataArea(), RectangleEdge.BOTTOM);

        System.out.println("X: "+mouseX+" "+"Y: "+mouseY);
        System.out.println("xValue: "+xValue+" "+"yValue: "+yValue);
        markerX = new ValueMarker(xValue);  // position is the value on the axis


        this.setMyStoke();
        xyplot.addDomainMarker(markerX);

    }

    private void setMyStoke() {
        float dashes[] = {4.5f};
        markerX.setPaint(new Color(82, 98, 113));
        markerX.setStroke(new BasicStroke(0.9f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 8.f, dashes, 0.0f));

    }
}
