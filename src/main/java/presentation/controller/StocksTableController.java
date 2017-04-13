package presentation.controller;

import presentation.view.panel.*;
import presentation.view.tools.component.ProgressBar;
import presentation.view.tools.StocksTablePane;
import presentation.view.tools.WindowData;
import service.StockSituationService;
import service.serviceImpl.StockSituationServiceImpl;
import utilities.exceptions.NoSituationDataException;
import vo.PriceRiseOrFallVO;

import javax.swing.*;
import java.util.List;

/**
 * Created by Harvey on 2017/3/15.
 */
public class StocksTableController {

    /**
     * The constant stocksTableController.
     */
    private static StocksTableController stocksTableController = new StocksTableController();

    /**
     * The Stocks table panel.
     */
    private StocksTablePanel stocksTablePanel;

    /**
     * The K string panel.
     */
    private KStringPanel kStringPanel;

    /**
     * The First.
     */
    boolean first = true;

    /**
     * The Stocks table.
     */
    StocksTablePane stocksTablePane;
    ThermometerPanel thermometerPanel;
    JProgressBar progressBar;
    StockSituationService stockSituationService;
    /**
     * Instantiates a new Stocks table controller.
     */
    private StocksTableController(){
        stocksTablePanel = StocksTablePanel.getInstance();
    }

    /**
     * Get instance stocks table controller.
     *
     * @return the stocks table controller
     */
    public static StocksTableController getInstance(){
        return stocksTableController;
    }
    /**
     * TODO 导入数据
     * filePath 路径
     */

    public void importDate(String filePath) {

        if(progressBar!=null){
            stocksTablePanel.remove(progressBar);
        }
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);  //显示提示信息
        progressBar.setIndeterminate(false);
        progressBar.setBounds(1300,50,200,35);
        stocksTablePanel.add(progressBar);
        new ProgressBar(progressBar,stocksTablePanel.search).start();
    }

    /**
     * Search. 选定日期，搜索列表
     */

    public void search() {

        WindowData windowData = WindowData.getInstance();
        int width = windowData.getWidth();
        int height = windowData.getHeight();


        if (stocksTablePane != null) {
            stocksTablePanel.remove(stocksTablePane);
        }


        if (thermometerPanel != null) {
            stocksTablePanel.remove(thermometerPanel);
        }

        List<PriceRiseOrFallVO> list=null;
        stockSituationService=new StockSituationServiceImpl();
        int num[]=null;
        try {
            stocksTablePane = new StocksTablePane(stocksTablePanel.getChooseDate());
            stocksTablePane.setLocation(50 * width / 1920, 120 * height / 1030);
            if(stocksTablePane!=null) {
                stocksTablePanel.add(stocksTablePane);
                stocksTablePane.repaint();
            }
            list  = stockSituationService.getStockStituationData(stocksTablePanel.getChooseDate());
             num=new int[]{ list.get(0).num,list.get(1).num,list.get(2).num,list.get(3).num,list.get(4).num,list.get(5).num};
            thermometerPanel=new ThermometerPanel(num,list.get(6).num);
            thermometerPanel.setLocation(1460 * width / 1920, 40 * height / 1030);
            stocksTablePanel.add(thermometerPanel);
            thermometerPanel.repaint();

        }catch (NoSituationDataException e) {
            stocksTablePanel.label.setVisible(true);
            stocksTablePanel.remove(stocksTablePane);
            stocksTablePane.repaint();
        }


    }

    /**
     * Check detail.选择单支股票，查看详情
     */
    public void checkDetail() {

        try {
            KStringPanel.getInstance().count=1;
            kStringPanel = KStringPanel.getInstance();
            kStringPanel.addMessage("", "");
            kStringPanel.datePanel.setDate(stocksTablePanel.getChooseDate());
            NavigationBar.getInstance().whileClicked(2);
            kStringPanel.addMessage(stocksTablePane.getName(), stocksTablePane.getCode());
            KStringPanel.getInstance().count=0;
            kStringPanel.removeChartPanel();
        }catch (Exception e){

        }
    }
}
