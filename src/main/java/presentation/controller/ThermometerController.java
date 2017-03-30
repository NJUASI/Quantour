package presentation.controller;

import presentation.chart.compare.StockSituationBarChart;
import presentation.view.panel.ThermometerPanel;
import presentation.view.tools.MyLabel;
import presentation.view.tools.WindowData;
import service.StockSituationService;
import service.serviceImpl.StockSituationServiceImpl;
import utilities.exceptions.NoSituationDataException;
import vo.PriceRiseOrFallVO;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Harvey on 2017/3/13.
 */
public class ThermometerController {

    /**
     * The constant thermometerController.
     */
    private static ThermometerController thermometerController = new ThermometerController();

    private ThermometerPanel thermometerPanel;

    private JPanel barPanel1;
    private JPanel barPanel2;
    private JPanel barPanel3;
    private MyLabel volumeName;
    private JLabel volume;

    private StockSituationService stockSituationService;

    /**
     * Instantiates a new Thermometer controller.
     */
    private ThermometerController(){
        thermometerPanel = ThermometerPanel.getInstance();
        stockSituationService = new StockSituationServiceImpl();
    }

    /**
     * Get instance thermometer controller.
     *
     * @return the thermometer controller
     */
    public static ThermometerController getInstance(){
        return thermometerController;
    }

    /**
     * 搜索操作,获取指定日期的市场温度计的数据
     */
    public void search() {

        LocalDate date = thermometerPanel.getDate();
        List<PriceRiseOrFallVO> list = null;
        try {
            list = stockSituationService.getStockStituationData(date);
        } catch (NoSituationDataException e) {
            JOptionPane.showMessageDialog(thermometerPanel,e.getMessage());
            thermometerPanel.remove(barPanel1);
            thermometerPanel.remove(barPanel2);
            thermometerPanel.remove(barPanel3);
            thermometerPanel.remove(volumeName);
            thermometerPanel.remove(volume);
//            e.printStackTrace();
        }

//        StockSituationPieChart pieChart = new StockSituationPieChart(list.iterator());
//        JPanel piePanel=pieChart.createChart();
//        piePanel.setBounds(thermometerPanel.adaptScreen(230,200,800,600));
//        piePanel.setBackground(new Color(55,60,56));
//        thermometerPanel.add(piePanel);
//        piePanel.repaint();


        StockSituationBarChart barChart1 = new StockSituationBarChart(list,0," 涨停 VS 跌停","涨停/跌停");
        StockSituationBarChart barChart2 = new StockSituationBarChart(list,2,"涨幅超过5% VS 跌幅超过5%","涨幅/跌幅");
        StockSituationBarChart barChart3 = new StockSituationBarChart(list,4,"开盘‐收盘大于5% * 上一个交易日收盘价 \nVS\n 开盘‐收盘小于‐5% * 上一个交易日收盘价","大于5%/小于5%");

        if (barPanel1 != null) {
            thermometerPanel.remove(barPanel1);
            thermometerPanel.remove(barPanel2);
            thermometerPanel.remove(barPanel3);
            thermometerPanel.remove(volumeName);
            thermometerPanel.remove(volume);
        }
        barPanel1 = barChart1.createChart();
        barPanel2 = barChart2.createChart();
        barPanel3 = barChart3.createChart();
        volumeName=new MyLabel(list.get(6).name);

        volumeName.setLocation(900* WindowData.getInstance().getWidth()/1920,69*WindowData.getInstance().getHeight()/1030);
        volumeName.setVisible(true);

        volume=new JLabel(list.get(6).num+"");

        volume.setBounds(thermometerPanel.adaptScreen(1000,65,180,40));
        volume.setVisible(true);

        barPanel1.setBounds(thermometerPanel.adaptScreen(30,150,540,720));
        barPanel2.setBounds(thermometerPanel.adaptScreen(650,150,540,720));
        barPanel3.setBounds(thermometerPanel.adaptScreen(1270,150,540,720));

        barPanel1.setBackground(new Color(32, 36, 39));
        barPanel2.setBackground(new Color(32, 36, 39));
        barPanel3.setBackground(new Color(32, 36, 39));
        volume.setForeground(new Color(255,255,255));
        volume.setFont(new Font("微软雅黑" ,Font.CENTER_BASELINE,16*WindowData.getInstance().getWidth()/1920));

        thermometerPanel.add(volumeName);
        thermometerPanel.add(volume);
        thermometerPanel.add(barPanel1);
        thermometerPanel.add(barPanel2);
        thermometerPanel.add(barPanel3);
        thermometerPanel.setBackground(new Color(32, 36, 39));
        barPanel1.repaint();
        barPanel2.repaint();
        barPanel3.repaint();
        volumeName.repaint();
        volume.repaint();
    }
}
