package presentation.controller;

import presentation.view.panel.iteration2.AnalysePanel;
import presentation.view.panel.iteration2.ChooseStrategyPanel;
import presentation.view.panel.iteration2.StrategyPanel;
import vo.TraceBackCriteriaVO;

/**
 * Created by 61990 on 2017/4/9.
 */
public class StrategyPanelController {
    /**
     * The strategy panel.
     */
    StrategyPanel strategyPanel;
    ChooseStrategyPanel chooseStrategyPanel;
    AnalysePanel analysePanel;
    /**
     * The constant ourInstance.
     */
    private static StrategyPanelController ourInstance = new StrategyPanelController();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static StrategyPanelController getInstance() {
        return ourInstance;
    }

    /**
     * Instantiates a new K string panel controller.
     */
    private StrategyPanelController() {
        strategyPanel = StrategyPanel.getInstance();
        chooseStrategyPanel=ChooseStrategyPanel.getInstance();
        analysePanel=AnalysePanel.getInstance();
    }

    public void search() {
        TraceBackCriteriaVO vo=chooseStrategyPanel.getInfo();
    }

}
