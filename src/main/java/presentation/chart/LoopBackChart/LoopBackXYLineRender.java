package presentation.chart.LoopBackChart;

import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;


import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Byron Dong on 2017/3/28.
 */
public class LoopBackXYLineRender extends XYLineAndShapeRenderer {



    @Override
    public Shape getItemShape(int row, int column) {
        Shape shape = new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0);
        if(row==0&&(column==4||column==3)){
            return shape;
        }
        return new Rectangle(0,0);
    }

    @Override
    public Paint getItemPaint(int row, int column) {
        if(row==0&&column==4){
            return Color.GREEN;
        }
        return lookupSeriesPaint(row);
    }

}
