package presentation.chart.tools;

import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;


import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Byron Dong on 2017/3/28.
 */
public class TraceBackXYLineRenderer extends XYLineAndShapeRenderer {

    private int high;

    private int low;

    /**
     * Creates a new renderer with both lines and shapes visible.
     */
    public TraceBackXYLineRenderer(int high, int low) {
        this.high = high;
        this.low = low;
    }

    @Override
    public Shape getItemShape(int row, int column) {
        Shape shape = new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0);

        if(row==0&&this.isTraceBack(column)){
            return shape;
        }

        return new Rectangle(0,0);
    }

    @Override
    public Paint getItemPaint(int row, int column) {
        if(row==0&&this.isTraceBack(column)){
            return Color.GREEN;
        }

        return lookupSeriesPaint(row);
    }

    private boolean isTraceBack(int column){
        if(column==low||column==high){
            return true;
        }
        return false;
    }

}
