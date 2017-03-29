package presentation.chart;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;
import presentation.chart.Candlestick.CandlestickChartTool;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import utilities.exceptions.CodeNotFoundException;
import vo.StockVO;

public class MainTest {

    public static void main(String args[]) throws IOException, CodeNotFoundException, ParseException {
        List<StockVO> data1;
        ChartService service = new ChartServiceImpl();
        data1 = service.getSingleStockRecords("1");

        // 1. Download MSFT quotes from Yahoo Finance and store them as OHLCDataItem
        List<OHLCDataItem> dataItems = new ArrayList<OHLCDataItem>();
        DateFormat df = new SimpleDateFormat("y-M-d");

        for (StockVO st : data1) {

            Date date = df.parse(st.date.toString());
            double open = st.open;
            double high = st.high;
            double low = st.low;
            double close = st.close;
            double volume = Double.parseDouble(st.volume);
            double adjClose = st.adjClose;

            // adjust data:
            open = open * adjClose / close;
            high = high * adjClose / close;
            low = low * adjClose / close;

            OHLCDataItem item = new OHLCDataItem(date, open, high, low, adjClose, volume);
            dataItems.add(item);
        }
        Collections.reverse(dataItems); // Data from Yahoo is from newest to oldest. Reverse so it is oldest to newest.
        //Convert the list into an array
        OHLCDataItem[] data = dataItems.toArray(new OHLCDataItem[dataItems.size()]);
        OHLCDataset dataset = new DefaultOHLCDataset("MSFT", data);

        // 2. Create chart
        JFreeChart chart = ChartFactory.createCandlestickChart("MSFT", "Time", "Price", dataset, false);

        // 3. Set chart background
        chart.setBackgroundPaint(new Color(32,36,39));
        chart.getTitle().setPaint(new Color(201, 208, 214));
        chart.setTextAntiAlias(false);

        // 4. Set a few custom plot features
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setRenderer(CandlestickChartTool.getRenderer());
        plot.setBackgroundPaint(new Color(32,36,39));
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinePaint(new Color(44, 50, 54));
        plot.setRangeGridlinePaint(new Color(44, 50, 54));
        plot.setDomainGridlineStroke(new BasicStroke());
        plot.setRangeGridlineStroke(new BasicStroke());
        ((NumberAxis) plot.getRangeAxis()).setAutoRangeIncludesZero(false);

        // 5. Skip week-ends on the date axis
        ((DateAxis) plot.getDomainAxis()).setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());

        // 6. No volume drawn
        ((CandlestickRenderer) plot.getRenderer()).setDrawVolume(true);

        ((CandlestickRenderer) plot.getRenderer()).setVolumePaint(new Paint() {
            @Override
            public PaintContext createContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
                return null;
            }

            @Override
            public int getTransparency() {
                return 0;
            }
        });

        // 7. Create and display full-screen JFrame
        JFrame myFrame = new JFrame();
        myFrame.setResizable(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.add(new ChartPanel(chart), BorderLayout.CENTER);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Insets insets = kit.getScreenInsets(myFrame.getGraphicsConfiguration());
        Dimension screen = kit.getScreenSize();
        myFrame.setSize((int) (screen.getWidth() - insets.left - insets.right), (int) (screen.getHeight() - insets.top - insets.bottom));
        myFrame.setLocation((int) (insets.left), (int) (insets.top));
        myFrame.setVisible(true);
    }
}