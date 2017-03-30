package presentation.controller;

import presentation.view.frame.MainFrame;
import presentation.view.panel.*;
import presentation.view.panel.iteration2.StrategyPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harvey on 2017/3/12.
 */
public class ViewSwitchController{

    private JPanel cardPanel = MainFrame.getCardPanel();
    private CardLayout cardLayout = MainFrame.getCard();

    List<String> panelNames;

    /**
     * The View switch controller.
     */
    private static ViewSwitchController viewSwitchController = new ViewSwitchController();

    /**
     * Instantiates a new View switch controller.
     */
    private ViewSwitchController() {
        panelNames = new ArrayList<String>();
    }

    /**
     * Get instance view switch controller.
     *
     * @return the view switch controller
     */
    public static ViewSwitchController getInstance(){
        return viewSwitchController;
    }

    /**
     * View switch.
     *
     * @param panelName 面板的名称
     */
    public void viewSwitch(String panelName){
        if (!panelNames.contains(panelName)){
            switch (panelName){

                case "kStringPanel":
                    cardPanel.add(KStringPanel.getInstance(),"kStringPanel");
                    break;
                case "comparePanel":
                    cardPanel.add(ComparePanel.getInstance(),"comparePanel");
                    break;
//                case "thermometerPanel":
//                    cardPanel.add(ThermometerPanel.getInstance(),"thermometerPanel");
//                    break;
                case "stocksTablePanel":
                    cardPanel.add(StocksTablePanel.getInstance(),"stocksTablePanel");
                    break;
                case "strategyPanel":
                    cardPanel.add(StrategyPanel.getInstance(),"strategyPanel");
                    break;
            }
            panelNames.add(panelName);
        }
        cardLayout.show(cardPanel,panelName);
    }
}
