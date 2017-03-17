package presentation.controller;

import presentation.view.chart.StockSituationBarChart;
import presentation.view.chart.StockSituationPieChart;
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

        StockSituationPieChart pieChart = new StockSituationPieChart(list.iterator());
        StockSituationBarChart barChart = new StockSituationBarChart(list.iterator());

//        JPanel piePanel=pieChart.createChart();
//        piePanel.setBounds(thermometerPanel.adaptScreen(230,200,800,600));
//        piePanel.setBackground(new Color(55,60,56));
//        thermometerPanel.add(piePanel);
//        piePanel.repaint();

        JPanel barPanel=barChart.createChart();
        barPanel.setBounds(thermometerPanel.adaptScreen(230,200,800,600));
        barPanel.setBackground(new Color(32, 36, 39));
        thermometerPanel.add(barPanel);
        thermometerPanel.setBackground(new Color(32, 36, 39));
        barPanel.repaint();
    }
}
