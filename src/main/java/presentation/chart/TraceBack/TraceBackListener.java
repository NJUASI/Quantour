package presentation.chart.TraceBack;


;import org.jfree.chart.*;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;


import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Byron Dong on 2017/3/28.
 */
public class TraceBackListener implements ChartMouseListener {

    ChartPanel chartPanel;
    Rectangle2D rectangle2D;
    ValueMarker markerX = null;
    List<LocalDate> date;


    public TraceBackListener(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
        this.setDate();
    }

    @Override
    public void chartMouseClicked(ChartMouseEvent event) {}

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
        LocalDate localDate = this.calculate(mouseX,1500);
        markerX.setLabel(localDate.toString());
        markerX.setLabelPaint(new Color(201, 208, 214));
        markerX.setLabelBackgroundColor(new Color(87,107,131));
        markerX.setLabelFont(new Font("宋体", Font.PLAIN, 12));
        markerX.setLabelAnchor(RectangleAnchor.BOTTOM);


        this.setMyStoke();
        xyplot.addDomainMarker(markerX);

    }

    private void setMyStoke() {
        float dashes[] = {4.5f};
        markerX.setPaint(new Color(82, 98, 113));
        markerX.setStroke(new BasicStroke(0.9f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 8.f, dashes, 0.0f));

    }

    private LocalDate calculate(int mousex,int size){
        int dateSize = this.date.size();
        double times = size/dateSize;

        for(int i=0;i<date.size();i++){
            if(mousex>=i*times&&mousex<=(i+1)*times){
                return this.date.get(i);
            }
        }
        return null;
    }

    private void setDate(){
        this.date =  new ArrayList<>();
        for(int i=1;i<32;i++){
            this.date.add(LocalDate.of(2014,7,i));
        }
    }
}
