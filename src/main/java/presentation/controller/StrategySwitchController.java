package presentation.controller;

import presentation.view.panel.iteration2.AnalysePanel;
import presentation.view.panel.iteration2.ChooseStrategyPanel;
import presentation.view.panel.iteration2.StrategyPanel;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by day on 17/3/28.
 */
public class StrategySwitchController {
    private JPanel cardPanel = StrategyPanel.getCardPanel();
    private CardLayout cardLayout = StrategyPanel.getCard();

    java.util.List<String> panelNames;

    /**
     * The View switch controller.
     */
    private static StrategySwitchController strategySwitchController = new StrategySwitchController();

    /**
     * Instantiates a new View switch controller.
     */
    private StrategySwitchController() {
        panelNames = new ArrayList<String>();
    }

    /**
     * Get instance view switch controller.
     *
     * @return the view switch controller
     */
    public static StrategySwitchController getInstance(){
        return strategySwitchController;
    }

    /**
     * View switch.
     *
     * @param panelName 面板的名称
     */
    public void viewSwitch(String panelName){
        if (!panelNames.contains(panelName)){
            switch (panelName){

                case "chooseStrategyPanel":
                    cardPanel.add(ChooseStrategyPanel.getInstance(),"chooseStrategyPanel");
                    break;
                case "analysePanel":
                    cardPanel.add(AnalysePanel.getInstance(),"analysePanel");
                    break;
            }
            panelNames.add(panelName);
        }
        cardLayout.show(cardPanel,panelName);
    }
}
