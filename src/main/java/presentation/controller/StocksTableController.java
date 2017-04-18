package presentation.controller;

import presentation.view.panel.*;
import presentation.view.tools.StocksTablePane;
import presentation.view.tools.WindowData;
import presentation.view.tools.component.MyTable;
import service.StockService;
import service.StockSituationService;
import service.serviceImpl.StockService.StockServiceImpl;
import service.serviceImpl.StockSituationServiceImpl;
import utilities.CodeReserve;
import utilities.IDReserve;
import utilities.exceptions.PrivateStockExistedException;
import utilities.exceptions.PrivateStockNotFoundException;
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
    public MyTable stocksTablePane;
    ThermometerPanel thermometerPanel;
    StockSituationService stockSituationService;
    StockService stockService;
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
            stocksTablePane = new MyTable(stocksTablePanel.getChooseDate());
            stocksTablePane.setLocation(60 * width / 1920, 155 * height / 1030);
            if(stocksTablePane!=null) {
                stocksTablePanel.add(stocksTablePane);
                stocksTablePane.repaint();
            }
            list  = stockSituationService.getStockStituationData(stocksTablePanel.getChooseDate());
             num=new int[]{ list.get(0).num,list.get(1).num,list.get(2).num,list.get(3).num,list.get(4).num,list.get(5).num};
            thermometerPanel=new ThermometerPanel(num,list.get(6).num);
            thermometerPanel.setLocation(1400 * width / 1920, 80 * height / 1030);
            stocksTablePanel.add(thermometerPanel);
            stocksTablePanel.logo.setVisible(false);
            thermometerPanel.repaint();

        }catch (Exception e) {
            stocksTablePanel.remove(stocksTablePane);
            stocksTablePane.repaint();
            stocksTablePanel.label.setVisible(true);
            stocksTablePanel.label.repaint();
        }


    }

    /**
     * Check detail.选择单支股票，查看详情
     */
    public void checkDetail() {

        try {
            KStringPanel.getInstance().count=1;
            kStringPanel = KStringPanel.getInstance();
//            kStringPanel.addMessage("", "");
            ViewSwitchController.getInstance().viewSwitch("kStringPanel");
            kStringPanel.datePanel.setDate(stocksTablePanel.getChooseDate());
            NavigationBar.getInstance().whileClicked(2);
            kStringPanel.addMessage(CodeReserve.getInstance().getName(), CodeReserve.getInstance().getCode());
            KStringPanel.getInstance().count=0;
            kStringPanel.removeChartPanel();
        }catch (Exception e){

        }
    }

    /**
     * 将选择的股票加入自选股
     */
    public void addFavorite() throws PrivateStockExistedException, PrivateStockNotFoundException {
        stockService = new StockServiceImpl();
        stockService.addPrivateStock(IDReserve.getInstance().getUserID(),stocksTablePane.getCode());
    }
}
