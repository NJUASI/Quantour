package presentation.controller;

import presentation.view.panel.KStringPanel;
import presentation.view.panel.StocksTablePanel;
import presentation.view.tools.StocksTablePane;
import presentation.view.tools.WindowData;

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
        int height =windowData.getHeight();

        if(first == true){
            first = false;
            stocksTablePane = new StocksTablePane(stocksTablePanel.getChooseDate());
            stocksTablePane.setLocation(300*width/1920,130*height/1030);
            stocksTablePanel.add(stocksTablePane);
            stocksTablePane.repaint();
        }
        else {
            stocksTablePanel.remove(stocksTablePane);
            stocksTablePane = new StocksTablePane(stocksTablePanel.getChooseDate());
            stocksTablePane.setLocation(300*width/1920,130*height/1030);
            stocksTablePanel.add(stocksTablePane);
            stocksTablePane.repaint();
        }
    }

    /**
     * Check detail.选择单支股票，查看详情
     */
    public void checkDetail() {
        kStringPanel = KStringPanel.getInstance();
        kStringPanel.datePanel.setDate(stocksTablePanel.getChooseDate());
        kStringPanel.addMessage(stocksTablePane.getName(), stocksTablePane.getCode());
    }
}
