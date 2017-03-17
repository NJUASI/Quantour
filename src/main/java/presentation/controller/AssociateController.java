package presentation.controller;

import presentation.view.panel.AssociatePanel;
import service.StockService;
import service.serviceImpl.StockServiceImpl;

/**
 * Created by Harvey on 2017/3/15.
 */
public class AssociateController {

    AssociatePanel associatePanel;

    StockService stockService;

    private static AssociateController associateController = new AssociateController();

    private AssociateController(){
        associatePanel = AssociatePanel.getInstance();
        stockService = new StockServiceImpl();
    }

    public static AssociateController getInstance(){
        return associateController;
    }

    public void search() {

    }
}
