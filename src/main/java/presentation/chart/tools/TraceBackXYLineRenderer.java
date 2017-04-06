package presentation.chart.tools;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRendererState;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.util.LineUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.ShapeUtilities;
import presentation.view.tools.ColorUtils;


import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Byron Dong on 2017/3/28.
 */
public class TraceBackXYLineRenderer extends XYLineAndShapeRenderer {

    //最大会测点的高值位置
    private int high;

    //最大会测点的低值位置
    private int low;

    /**
     * Creates a new renderer with both lines and shapes visible.
     */
    public TraceBackXYLineRenderer(int high, int low) {
        this.high = high;
        this.low = low;
    }

    /**
     * 重写getItemShape方法
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/30
     */
    @Override
    public Shape getItemShape(int row, int column) {
        Shape shape = new Ellipse2D.Double(-2.0, -2.0, 10.0, 10.0);

        if(row==0&&this.isTraceBack(column)){
            return shape;
        }

        return new Rectangle(0,0);
    }

    /**
     * 重写getItemPaint方法
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/30
     */
    @Override
    public Paint getItemPaint(int row, int column) {
        if(row==0&&this.isTraceBack(column)){
            return ColorUtils.fontColor();
        }

        return lookupSeriesPaint(row);
    }

    protected void drawFirstPassShape(Graphics2D g2, int pass, int series,
                                      int item, Shape shape) {
        g2.setStroke(getItemStroke(series, item));
        if(series==0&&isTraceBack(item)){
            g2.setPaint(ColorUtils.upColor());

        }
        else {
            g2.setPaint(getItemPaint(series, item));
        }
        g2.draw(shape);
    }


    /**
     * 判断是否是最大回测点
     *
     * @param  column 需要判断的位置坐标
     * @return boolean 判断结果
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/30
     */
    private boolean isTraceBack(int column){
        if(column==low||column==high){
            return true;
        }
        return false;
    }

}
