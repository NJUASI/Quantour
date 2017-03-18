package presentation.controller;

import presentation.view.chart.StockSituationBar5PercentChart;
import presentation.view.chart.StockSituationBarLimitChart;
import presentation.view.chart.StockSituationBarOpenCloseChart;
import presentation.view.panel.ThermometerPanel;
import service.StockSituationService;
import service.serviceImpl.StockSituationServiceImpl;
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
    /**
     * The Thermometer panel.
     */
    private ThermometerPanel thermometerPanel;
    /**
     * The Stock situation service.
     */
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
        List<PriceRiseOrFallVO> list = stockSituationService.getStockStituationData(date);

//        StockSituationPieChart pieChart = new StockSituationPieChart(list.iterator());
//        JPanel piePanel=pieChart.createChart();
//        piePanel.setBounds(thermometerPanel.adaptScreen(230,200,800,600));
//        piePanel.setBackground(new Color(55,60,56));
//        thermometerPanel.add(piePanel);
//        piePanel.repaint();

        for (PriceRiseOrFallVO vo : list) {
            System.out.print(vo.num + " ");
        }

        StockSituationBarLimitChart barChart1 = new StockSituationBarLimitChart(list);
        StockSituationBar5PercentChart barChart2 = new StockSituationBar5PercentChart(list);
        StockSituationBarOpenCloseChart barChart3 = new StockSituationBarOpenCloseChart(list);

        JPanel barPanel1 = barChart1.createChart();
        JPanel barPanel2 = barChart2.createChart();
        JPanel barPanel3 = barChart3.createChart();

        barPanel1.setBounds(thermometerPanel.adaptScreen(30,150,540,720));
        barPanel2.setBounds(thermometerPanel.adaptScreen(650,150,540,720));
        barPanel3.setBounds(thermometerPanel.adaptScreen(1270,150,540,720));

        barPanel1.setBackground(new Color(32, 36, 39));
        barPanel2.setBackground(new Color(32, 36, 39));
        barPanel3.setBackground(new Color(32, 36, 39));

        thermometerPanel.add(barPanel1);
        thermometerPanel.add(barPanel2);
        thermometerPanel.add(barPanel3);
        thermometerPanel.setBackground(new Color(32, 36, 39));
        barPanel1.repaint();
        barPanel2.repaint();
        barPanel3.repaint();
    }
}
