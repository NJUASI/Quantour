package presentation.chart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;
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
        chart.setBackgroundPaint(Color.white);

        // 4. Set a few custom plot features
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE); // light yellow = new Color(0xffffe0)
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);
        ((NumberAxis) plot.getRangeAxis()).setAutoRangeIncludesZero(false);

        // 5. Skip week-ends on the date axis
        ((DateAxis) plot.getDomainAxis()).setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());

        // 6. No volume drawn
        ((CandlestickRenderer) plot.getRenderer()).setDrawVolume(true);

        ((CandlestickRenderer) plot.getRenderer()).setVolumePaint(Color.blue);

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