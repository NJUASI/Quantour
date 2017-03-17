package presentation.view.chart;

import org.jfree.chart.ChartPanel;
import presentation.view.tools.MyScrollBarUI;
import presentation.view.tools.WindowData;
import vo.StockComparisionVO;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.List;

/**
 * Created by 61990 on 2017/3/14.
 */
public class CompareChartPanel extends JPanel {
    CompareChart1 chart1;
    CompareChart2 chart2;
    CompareChart3 chart3;
    CompareChart4 chart4,chart5,chart6;
    ChartPanel chartPanel1, chartPanel3,chartPanel4,chartPanel2, chartPanel5,chartPanel6;
    JScrollPane scrollPane;
    WindowData windowData;
    JPanel panel;
    int width;
    int height;
    public JTextField s;

    public CompareChartPanel(List<StockComparisionVO> vo){
        windowData =WindowData.getInstance();
        width = windowData.getWidth();
        height =windowData.getHeight();

        panel=new JPanel(null);
        panel.setBackground(new Color(32, 36, 39));
        chart1 = new CompareChart1(vo);
        chartPanel1 = chart1.createChart();
        chartPanel1.setBounds(adaptScreen(0,0,500,480));
        panel.add(chartPanel1);

        chart2 = new CompareChart2(vo);
        chartPanel2 = chart2.createChart();
        chartPanel2.setBounds(adaptScreen(500,0,500,480));
        panel.add(chartPanel2);


        chart3 = new CompareChart3(vo);
        chartPanel3 = chart3.createChart();
        chartPanel3.setBounds(adaptScreen(1000,0,500,480));
        panel.add(chartPanel3);


        chart4 = new CompareChart4(vo.get(0).closes,null,vo.get(0).name,"","收盘价","收盘价");
        chartPanel4= chart4.createChart();
        chartPanel4.setBounds(adaptScreen(250,550,1000,500));
        panel.add(chartPanel4);


        chart5 = new CompareChart4(vo.get(1).closes,null,vo.get(1).name,"","收盘价","");
        chartPanel5= chart5.createChart();
        chartPanel5.setBounds(adaptScreen(250,1100,1000,500));
        panel.add(chartPanel5);


        chart6 = new CompareChart4(vo.get(0).logarithmicYield,vo.get(1).logarithmicYield,vo.get(0).name,vo.get(1).name,"对数收益率","对数收益率");
        chartPanel6 = chart6.createChart();
        chartPanel6.setBounds(adaptScreen(250,1650,1000,500));
        panel.add(chartPanel6);

        setBounds(adaptScreen(250,100,1500,850));
        setPreferredSize(new Dimension(1500,850));

        panel.setPreferredSize(new Dimension(1500*getWidth()/1920,2800*getHeight()/1030));
        scrollPane=new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(1500*width/1920,800*height/1030));
        scrollPane.setBounds(adaptScreen(0,0,1500,800));
        scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
        scrollPane.add(panel);

        scrollPane.setViewportView(panel);
        scrollPane.setVisible(true);
        panel.setVisible(true);
        setVisible(true);
        scrollPane.repaint();
        panel.repaint();
        panel.requestFocus();
        add(scrollPane);

    }

    /**
     * 适应不同大小的屏幕
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/7
     */
    public Rectangle adaptScreen(int x,int y,int width,int height){
        return new Rectangle(this.width*x/1920,this.height*y/1030,this.width*width/1920,this.height*height/1030);
    }
    @Override
    public void paintComponent(Graphics g) {

    }
}
