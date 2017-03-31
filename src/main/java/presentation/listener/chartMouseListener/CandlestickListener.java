package presentation.listener.chartMouseListener;

import org.jfree.chart.*;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Byron Dong on 2017/3/22.
 */
public class CandlestickListener implements ChartMouseListener{

    ChartPanel chartPanel;

    Rectangle2D rectangle2D;

    ValueMarker markerX =null;

    ValueMarker markerY = null;

    List<LocalDate> date;

    public CandlestickListener(ChartPanel chartPanel, List<LocalDate> date) {
        this.chartPanel = chartPanel;
        this.date = date;
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

        int mouseX =event.getTrigger().getX();
        int mouseY =event.getTrigger().getY();
        Point2D point2D = this.chartPanel.translateScreenToJava2D(new Point(mouseX, mouseY));
        ChartRenderingInfo info = this.chartPanel.getChartRenderingInfo();
        rectangle2D = chartPanel.getScreenDataArea();
        double yValue = xyplot.getRangeAxis().java2DToValue(point2D.getY(), info.getPlotInfo().getDataArea(), RectangleEdge.RIGHT);
        double xValue = xyplot.getDomainAxis().java2DToValue(point2D.getX(), info.getPlotInfo().getDataArea(), RectangleEdge.BOTTOM);

        markerX = new ValueMarker(xValue);  // position is the value on the axis
        markerY = new ValueMarker(yValue);  // position is the value on the axis

        this.setXMaker(markerX,mouseX);
        this.setYMaker(markerY,yValue);

        this.setMyStoke();
        xyplot.addDomainMarker(markerX);
        xyplot.addRangeMarker(markerY);

    }

    private void setMyStoke(){
        float dashes[] = {21,9,3,9};
        markerX.setPaint(new Color(82,98,113));
        markerX.setStroke(new BasicStroke(0.9f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,8.f,dashes,10));
        markerY.setPaint(new Color(82,98,113));
        markerY.setStroke(new BasicStroke(0.001f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,8.f,dashes,10));

    }

    private void setXMaker(ValueMarker valueMarker,int value){
       this.setTheme(valueMarker);
       valueMarker.setLabel(this.calculate(value,chartPanel.getSize().width).toString());
       valueMarker.setLabelAnchor(RectangleAnchor.CENTER);
    }

    private void setYMaker(ValueMarker valueMarker,double value){
        this.setTheme(valueMarker);
        DecimalFormat df=new DecimalFormat("#.000");
        valueMarker.setLabel(df.format(value));
        valueMarker.setLabelAnchor(RectangleAnchor.CENTER);

    }

    private void setTheme(ValueMarker valueMarker){
        valueMarker.setLabelPaint(new Color(201, 208, 214));
        valueMarker.setOutlinePaint(new Color(201, 208, 214));
        valueMarker.setLabelBackgroundColor(new Color(87,107,131));
        valueMarker.setLabelFont(new Font("宋体", Font.PLAIN, 12));
    }

    private LocalDate calculate(double mousex, double size){
        int dateSize = date.size();
        double times = size/dateSize;

        for(int i=0;i<date.size();i++){
            if(mousex>=i*times&&mousex<=(i+1)*times){
                return date.get(i);
            }
        }
        return null;
    }
}
