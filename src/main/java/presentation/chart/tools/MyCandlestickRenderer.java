package presentation.chart.tools;

import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import presentation.view.tools.ColorUtils;
import sun.plugin2.util.ColorUtil;

import java.awt.*;

/**
 * Created by Byron Dong on 2017/3/29.
 */
public class MyCandlestickRenderer extends CandlestickRenderer {

    //k数据集合
    private OHLCSeriesCollection ohlcSeriesCollection;

    /**
     * Creates a new renderer for candlestick charts.
     */
    public MyCandlestickRenderer(OHLCSeriesCollection ohlcSeriesCollection) {
        this.ohlcSeriesCollection = ohlcSeriesCollection;
    }

    /**
     * Creates a new renderer for candlestick charts.
     * <p>
     * Use -1 for the candle width if you prefer the width to be calculated
     * automatically.
     *
     * @param candleWidth The candle width.
     */
    public MyCandlestickRenderer(double candleWidth, OHLCSeriesCollection ohlcSeriesCollection) {
        super(candleWidth);
        this.ohlcSeriesCollection = ohlcSeriesCollection;
    }

    /**
     * Creates a new renderer for candlestick charts.
     * <p>
     * Use -1 for the candle width if you prefer the width to be calculated
     * automatically.
     *
     * @param candleWidth      the candle width.
     * @param drawVolume       a flag indicating whether or not volume bars should
     *                         be drawn.
     * @param toolTipGenerator the tool tip generator. <code>null</code> is
     */
    public MyCandlestickRenderer(double candleWidth, boolean drawVolume, XYToolTipGenerator toolTipGenerator, OHLCSeriesCollection ohlcSeriesCollection) {
        super(candleWidth, drawVolume, toolTipGenerator);
        this.ohlcSeriesCollection = ohlcSeriesCollection;
    }

    /**
     * 重写getItemOutLinePaint方法
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/30
     */
    public Paint getItemOutlinePaint(int row, int column) {

        if(ohlcSeriesCollection.getCloseValue(row,column)>ohlcSeriesCollection.getOpenValue(row,column)){
            return ColorUtils.upColor();
        }else{
            return ColorUtils.downColor();
        }
    }
}
