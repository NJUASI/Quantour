package presentation.controller;

import presentation.view.chart.StockSituationBarChart;
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

    private ThermometerPanel thermometerPanel;

    private JPanel barPanel1;
    private JPanel barPanel2;
    private JPanel barPanel3;

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

        StockSituationBarChart barChart1 = new StockSituationBarChart(list,0," 涨停 VS 跌停","涨停/跌停");
        StockSituationBarChart barChart2 = new StockSituationBarChart(list,2,"涨幅超过5% VS 跌幅超过5%","涨幅/跌幅");
        StockSituationBarChart barChart3 = new StockSituationBarChart(list,4,"开盘‐收盘大于5% * 上一个交易日收盘价 \nVS 开盘‐收盘小于‐5% * 上一个交易日收盘价","大于5%/小于5%");

        if (barPanel1 != null) {
            thermometerPanel.remove(barPanel1);
            thermometerPanel.remove(barPanel2);
            thermometerPanel.remove(barPanel3);
        }
        barPanel1 = barChart1.createChart();
        barPanel2 = barChart2.createChart();
        barPanel3 = barChart3.createChart();

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
