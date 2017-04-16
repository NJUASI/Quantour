package presentation.controller;

import presentation.view.panel.iteration2.AnalysePanel;
import presentation.view.panel.iteration2.ChooseStrategyPanel;
import presentation.view.panel.iteration2.StrategyPanel;
import service.TraceBackService;
import service.serviceImpl.TraceBackService.TraceBackServiceImpl;
import utilities.exceptions.*;
import vo.TraceBackCriteriaVO;
import vo.TraceBackVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    TraceBackService traceBackService;
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

    public void search() throws DateNotWithinException, NoMatchEnumException, IOException, NoDataWithinException, CodeNotFoundException, DateShortException, UnhandleBlockTypeException, InvalidInputException, DataSourceFirstDayException {
        traceBackService = new TraceBackServiceImpl();
        analysePanel.setTitle(chooseStrategyPanel.getStrategyType());


        TraceBackCriteriaVO vo=chooseStrategyPanel.getInfo();

        vo.isCustomized=false; //TODO 后期需要删去
         //TODO 后期需要改成其他对象，不是null
        List<String> stockPool = new ArrayList<>();
        stockPool.add("000001");

        TraceBackVO traceBackVO = new TraceBackVO();
        traceBackVO.holdingDetailVOS=null;
        traceBackVO.certainHoldings=null;
        traceBackVO.certainFormates=null;
        traceBackVO.strategyCumulativeReturn=null;
        traceBackVO.baseCumulativeReturn=null;
        traceBackVO.traceBackNumValVO=null;
        traceBackVO.relativeReturnPeriodVO=null;
        traceBackVO.absoluteReturnPeriodVO=null;
//        analysePanel.createChart(traceBackService.traceBack(vo,stockPool));
        analysePanel.createChart(traceBackVO);
    }

}
