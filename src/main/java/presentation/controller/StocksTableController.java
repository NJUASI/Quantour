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
import java.awt.*;
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
    public StocksTablePane stocksTablePane;
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

    int width ;
    int height ;
    /**
     * Search. 选定日期，搜索列表
     */

    public void search() {

        WindowData windowData = WindowData.getInstance();
        width = windowData.getWidth();
        height = windowData.getHeight();


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
            stocksTablePane.setBounds(adaptScreen(460 , 75 ,1300,750));
            if(stocksTablePane!=null) {
                stocksTablePanel.add(stocksTablePane);
                stocksTablePane.repaint();
            }
            list  = stockSituationService.getStockStituationData(stocksTablePanel.getChooseDate());
             num=new int[]{ list.get(0).num,list.get(1).num,list.get(2).num,list.get(3).num,list.get(4).num,list.get(5).num};
            thermometerPanel=new ThermometerPanel(num,list.get(6).num);
            thermometerPanel.setLocation(60 * width / 1920, 80 * height / 1030);
            stocksTablePanel.add(thermometerPanel);
            stocksTablePanel.message.setVisible(false);
            stocksTablePanel.notFound.setVisible(false);
            stocksTablePanel.label.setVisible(false);
            stocksTablePanel.title.setVisible(true);
            stocksTablePanel.repaint();
            thermometerPanel.repaint();

        }catch (Exception e) {
            stocksTablePanel.remove(stocksTablePane);
            stocksTablePane.repaint();
            stocksTablePanel.notFound.setVisible(true);
            stocksTablePanel.label.setVisible(true);
            stocksTablePanel.title.setVisible(false);
            stocksTablePanel.message.setVisible(true);
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
        StocksTablePanel.getInstance().popUp(stocksTablePane.getCode()+" 添加成功！");
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

}
