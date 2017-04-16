package presentation.view.panel;

import org.jfree.chart.ChartPanel;
import presentation.chart.candlestick.LineChart;
import presentation.chart.compare.CompareChart1;
import presentation.chart.compare.CompareChart2;
import presentation.chart.compare.CompareChart3;
import presentation.chart.compare.CompareChart4;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.ui.MyTabUI;
import presentation.view.tools.WindowData;
import vo.StockComparisionVO;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by 61990 on 2017/3/14.
 */
public class CompareChartPanel extends JPanel {
    CompareChart1 chart1;
    CompareChart2 chart2;
    CompareChart3 chart3;
    CompareChart4 chart4, chart5, chart6;
    ChartPanel chartPanel1, chartPanel3, chartPanel4, chartPanel2, chartPanel5, chartPanel6;
    WindowData windowData;
    JPanel panel;
    int width;
    int height;
    public JTextField s;

    public CompareChartPanel(List<StockComparisionVO> vo) {
        windowData = WindowData.getInstance();
        width = windowData.getWidth();
        height = windowData.getHeight();
        setBounds(adaptScreen(0, 130, 1830, 990));
        setLayout(null);
//        panel=new JPanel(null);
//        scrollPane=new JScrollPane();
//        scrollPane.add(panel);
//        panel.setBounds(0,0,1500,800);
//        panel.setVisible(true);
        JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);

        //对象化面板
        JPanel p1 = new JPanel();
        p1.setBackground(WindowData.getInstance().getColor());
        p1.setLayout(null);

        JPanel p2 = new JPanel();
        p2.setBackground(WindowData.getInstance().getColor());
        p2.setLayout(null);

        JPanel p3 = new JPanel();
        p3.setBackground(WindowData.getInstance().getColor());
        p3.setLayout(null);

        tab.add(p1, "涨幅跌幅 对数收益方差");
        tab.add(p2, "收盘价");
        tab.add(p3, "对数收益率");

        tab.setBounds(adaptScreen(-2, 0, 1833, 990));


//        chart1 = new CompareChart1(vo);
//        chartPanel1 = chart1.createChart();
//        chartPanel1.setBounds(adaptScreen(0,0,500,480));
//        chartPanel1.setVisible(true);
//        add(chartPanel1);

        chart2 = new CompareChart2(vo);
        chartPanel2 = chart2.createChart();
        chartPanel2.setBounds(adaptScreen(200, 80, 600, 600));
        chartPanel2.setVisible(true);
        p1.add(chartPanel2);


        chart3 = new CompareChart3(vo);
        chartPanel3 = chart3.createChart();
        chartPanel3.setBounds(adaptScreen(800, 80, 600, 600));
        chartPanel3.setVisible(true);
        p1.add(chartPanel3);

//        LineChart lineChart=new LineChart();
//       ChartPanel chartPanel3=lineChart.createChart();
//        chartPanel3.setBounds(adaptScreen(800,80,600,600));
//        chartPanel3.setVisible(true);
//        p1.add(chartPanel3);

        try {
            chart4 = new CompareChart4(vo.get(0).closes, vo.get(1).closes, vo.get(0).name, vo.get(1).name, "收盘价", "");
            chartPanel4 = chart4.createChart(vo.get(0).code);
            chartPanel4.setBounds(adaptScreen(150, 50, 1400, 660));
            chartPanel4.setVisible(true);
            p2.add(chartPanel4);

        } catch (IOException e) {
            e.printStackTrace();
        }



        try {
            chart6 = new CompareChart4(vo.get(0).logarithmicYield, vo.get(1).logarithmicYield, vo.get(0).name, vo.get(1).name, "对数收益率", "对数收益率");
            chartPanel6 = chart6.createChart(vo.get(0).code);
            chartPanel6.setBounds(adaptScreen(100, 30, 1500, 600));
            chartPanel6.setVisible(true);
            p3.add(chartPanel6);

        } catch (IOException e) {
            e.printStackTrace();
        }


        tab.setBackground(WindowData.getInstance().getColor());
        tab.setForeground(ColorUtils.fontColor());
        tab.setOpaque(true);
        tab.setBorder(BorderFactory.createEmptyBorder());
        tab.setUI(new MyTabUI());
        add(tab);

        setBorder(BorderFactory.createEmptyBorder());

    }

    /**
     * 适应不同大小的屏幕
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/7
     */
    public Rectangle adaptScreen(int x, int y, int width, int height) {
        return new Rectangle(this.width * x / 1920, this.height * y / 1030, this.width * width / 1920, this.height * height / 1030);
    }

    @Override
    public void paintComponent(Graphics g) {

    }
}
