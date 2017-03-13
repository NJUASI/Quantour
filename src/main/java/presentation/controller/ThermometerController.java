package presentation.controller;

import presentation.view.chart.StockSituationBarChart;
import presentation.view.chart.StockSituationPieChart;
import presentation.view.panel.ThermometerPanel;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import vo.PriceRiseOrFallVO;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harvey on 2017/3/13.
 */
public class ThermometerController {
    private static ThermometerController thermometerController = new ThermometerController();
    private ThermometerPanel thermometerPanel;
    private ChartService chartService;

    private ThermometerController(){
        thermometerPanel = ThermometerPanel.getInstance();
        chartService = new ChartServiceImpl();
    }

    public static ThermometerController getInstance(){
        return thermometerController;
    }

    public void search() {

        //TODO 获取这一天的市场温度计
        List<PriceRiseOrFallVO> list = new ArrayList<PriceRiseOrFallVO>();
        LocalDate date = thermometerPanel.getDate();

        list.add(new PriceRiseOrFallVO("涨停",45,date));
        list.add(new PriceRiseOrFallVO("跌停",50,date));
        list.add(new PriceRiseOrFallVO("涨幅超过5%",20,date));
        list.add(new PriceRiseOrFallVO("跌幅超过5%",10,date));
        list.add(new PriceRiseOrFallVO("开盘-收盘小于-5%*上一个交易日收盘价",40,date));
        list.add(new PriceRiseOrFallVO("开盘-收盘大于+5%*上一个交易日收盘价",40,date));

        StockSituationPieChart pieChart = new StockSituationPieChart(list.iterator());
        StockSituationBarChart barChart = new StockSituationBarChart(list.iterator());

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
