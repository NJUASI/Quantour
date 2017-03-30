package presentation.controller;

import presentation.view.panel.associatePanel.AssociatePanel;
import service.StockService;
import service.serviceImpl.StockServiceImpl;

import java.awt.event.KeyEvent;

/**
 * Created by Harvey on 2017/3/15.
 */
public class AssociateController {

    /**
     * The Associate panel.
     */
    AssociatePanel associatePanel;

    /**
     * The Stock service.
     */
    StockService stockService;

    /**
     * The constant associateController.
     */
    private static AssociateController associateController = new AssociateController();

    /**
     * Instantiates a new Associate controller.
     */
    private AssociateController(){
        associatePanel = AssociatePanel.getInstance();
        stockService = new StockServiceImpl();
    }

    /**
     * Get instance associate controller.
     *
     * @return the associate controller
     */
    public static AssociateController getInstance(){
        return associateController;
    }

    /**
     * Move by key. 根据上下方向键移动
     * @param e
     */
    public void MoveByKey(KeyEvent e) {
        //为上方向键
        if(e.getKeyCode() == KeyEvent.VK_UP){
            associatePanel.moveUp();
        }
        //为下方向键
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            associatePanel.moveDown();
        }
    }
}
