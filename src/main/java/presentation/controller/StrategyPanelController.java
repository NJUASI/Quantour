package presentation.controller;

import presentation.view.panel.iteration2.AnalysePanel;
import presentation.view.panel.iteration2.ChooseStrategyPanel;
import presentation.view.panel.iteration2.MultiComboBox;
import presentation.view.panel.iteration2.StrategyPanel;
import service.StockService;
import service.TraceBackService;
import service.serviceImpl.StockService.StockServiceImpl;
import service.serviceImpl.TraceBackService.TraceBackServiceImpl;
import utilities.IDReserve;
import utilities.exceptions.*;
import vo.TraceBackCriteriaVO;
import vo.TraceBackVO;

import javax.swing.*;
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
    StockService stockService;
    JLabel progressBar, message;
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
        chooseStrategyPanel = ChooseStrategyPanel.getInstance();
        analysePanel = AnalysePanel.getInstance();
    }

    public void search() throws DateNotWithinException, NoMatchEnumException, IOException,
            NoDataWithinException, CodeNotFoundException, DateShortException, UnhandleBlockTypeException,
            InvalidInputException, DataSourceFirstDayException, PrivateStockNotFoundException {

        traceBackService = new TraceBackServiceImpl();
        analysePanel.setTitle(chooseStrategyPanel.getStrategyType());

        TraceBackCriteriaVO vo = chooseStrategyPanel.getInfo();

        List<String> stockPool = stockService.getPrivateStockCodes(IDReserve.getInstance().getUserID());

        analysePanel.createChart(traceBackService.traceBack(vo, stockPool));

        //        vo.isCustomized=false; //TODO 后期需要删去
//         //TODO 后期需要改成其他对象，不是null

//        stockPool.add("000001");
//
//        TraceBackVO traceBackVO = new TraceBackVO();
//        traceBackVO.holdingDetailVOS=null;
//        traceBackVO.certainHoldings=null;
//        traceBackVO.certainFormates=null;
//        traceBackVO.strategyCumulativeReturn=null;
//        traceBackVO.baseCumulativeReturn=null;
//        traceBackVO.traceBackNumValVO=null;
//        traceBackVO.relativeReturnPeriodVO=null;
//        traceBackVO.absoluteReturnPeriodVO=null;
//        analysePanel.createChart(traceBackVO);
    }

    public void deletePool() throws PrivateStockNotExistException, PrivateStockNotFoundException {
        StockService stockService = new StockServiceImpl();

        stockService.deletePrivateStock(IDReserve.getInstance().getUserID(), chooseStrategyPanel.strategyPoolPanel.stockPoolTable.getCode());
        chooseStrategyPanel.refreshTabel();
    }


}
