package presentation.listener.chartMouseListener;

import org.jfree.chart.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.RectangleEdge;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Byron Dong on 2017/3/22.
 */
public class CandlestickListener implements ChartMouseListener{

    ChartPanel chartPanel;
    Rectangle2D rectangle2D;
    int mouseX;
    int mouseY;
    double yValue;
    double xValue;

    public CandlestickListener(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }

    @Override
    public void chartMouseClicked(ChartMouseEvent event) {
//        JFreeChart chart = event.getChart();
//        XYPlot xyplot = chart.getXYPlot();
//        Point point =event.getTrigger().getPoint();
//        int mouseX =event.getTrigger().getX();
//        int mouseY =event.getTrigger().getY();
//        Point2D point2D = this.chartPanel.translateScreenToJava2D(new Point(mouseX, mouseY));
//        ChartRenderingInfo info = this.chartPanel.getChartRenderingInfo();
//        rectangle2D = chartPanel.getScreenDataArea();
//        double yValue = xyplot.getRangeAxis().java2DToValue(point2D.getY(), info.getPlotInfo().getDataArea(), RectangleEdge.RIGHT);
//        double xValue = xyplot.getDomainAxis().java2DToValue(point2D.getX(), info.getPlotInfo().getDataArea(), RectangleEdge.BOTTOM);
//        System.out.println("MouseX: "+xValue+" "+"MouseY: "+yValue);
    }

    @Override
    public void chartMouseMoved(ChartMouseEvent event) {
        JFreeChart chart = event.getChart();
        XYPlot xyplot = chart.getXYPlot();
        Point point =event.getTrigger().getPoint();
        mouseX =event.getTrigger().getX();
        mouseY =event.getTrigger().getY();
        Point2D point2D = this.chartPanel.translateScreenToJava2D(new Point(mouseX, mouseY));
        ChartRenderingInfo info = this.chartPanel.getChartRenderingInfo();
        rectangle2D = chartPanel.getScreenDataArea();
        yValue = xyplot.getRangeAxis().java2DToValue(point2D.getY(), info.getPlotInfo().getDataArea(), RectangleEdge.RIGHT);
        xValue = xyplot.getDomainAxis().java2DToValue(point2D.getX(), info.getPlotInfo().getDataArea(), RectangleEdge.BOTTOM);
        System.out.println("MouseX: "+xValue+" "+"MouseY: "+yValue);
        refreshAxis(mouseY, mouseX);
    }


    public void refreshAxis(int mouseY, int mouseX) {
        Graphics g = chartPanel.getGraphics();
        update(g);
        int recW = (int) (rectangle2D.getX() + rectangle2D.getWidth());
        int recX = (int) rectangle2D.getX();
        int recY = (int) rectangle2D.getY();
        int recH = (int) (rectangle2D.getY() + rectangle2D.getHeight());
        if (mouseX < recW && mouseX > recX && mouseY < recH && mouseY > recY) {
            g.drawLine(recX, mouseY, recW, mouseY);
            g.drawLine(mouseX, recY, mouseX, recH);
        }
    }
//
    public void update(Graphics g) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (g.getClip() == null) {
            chartPanel.paint(g);
            int recX = (int) rectangle2D.getX();
            int recY = (int) rectangle2D.getY();
            int recH = (int) (rectangle2D.getY() + rectangle2D.getHeight());
            int recW = (int) (rectangle2D.getX() + rectangle2D.getWidth());
            if (mouseX < recW && mouseX > recX && mouseY < recH && mouseY > recY) {
                g.drawString(String.valueOf((int)(yValue)), mouseX + 20, mouseY + 1);
                g.drawString(dateFormat.format(new Date((long) xValue)), mouseX + 20,mouseY + 12);
            }
        }
    }
}
