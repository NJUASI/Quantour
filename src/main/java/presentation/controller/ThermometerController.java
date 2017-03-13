package presentation.controller;

import presentation.view.chart.StockSituationBarChart;
import presentation.view.chart.StockSituationPieChart;
import presentation.view.panel.ThermometerPanel;
import service.ChartService;
import service.StockSituationService;
import service.serviceImpl.ChartServiceImpl;
import service.serviceImpl.StockSituationServiceImpl;
import vo.PriceRiseOrFallVO;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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
        Iterator<PriceRiseOrFallVO> itr = stockSituationService.getStockStituationData(date);

        StockSituationPieChart pieChart = new StockSituationPieChart(itr);
        StockSituationBarChart barChart = new StockSituationBarChart(itr);

        JPanel piePanel=pieChart.createChart();
        piePanel.setBounds(thermometerPanel.adaptScreen(230,200,800,600));
        piePanel.setBackground(new Color(55,60,56));
        thermometerPanel.add(piePanel);
        piePanel.repaint();

        JPanel barPanel=barChart.createChart();
        barPanel.setBounds(thermometerPanel.adaptScreen(1040,230,870,550));
        barPanel.setBackground(new Color(55,60,56));
        thermometerPanel.add(barPanel);
        barPanel.repaint();
    }
}
