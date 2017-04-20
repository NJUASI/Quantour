package presentation.controller;

import presentation.view.panel.iteration2.AnalysePanel;
import presentation.view.panel.iteration2.ChooseStrategyPanel;
import presentation.view.panel.iteration2.MultiComboBox;
import presentation.view.panel.iteration2.StrategyPanel;
import presentation.view.panel.iteration2.stockPool.PrivateStockPool;
import presentation.view.tools.PopUpFrame;
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
    TraceBackVO traceBackVO ;
    Thread thread;
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

    public TraceBackVO search() throws IOException, DateNotWithinException, DataSourceFirstDayException, NoDataWithinException, UnhandleBlockTypeException, PrivatePoolNotEnoughException {

        traceBackService = new TraceBackServiceImpl();
        stockService = new StockServiceImpl();

        TraceBackCriteriaVO vo = chooseStrategyPanel.getInfo();

        List<String> stockPool = PrivateStockPool.getInstance().getPrivatePoolCodes();

        if(stockPool.size()<100&&vo.isCustomized){throw new PrivatePoolNotEnoughException();}

        return traceBackService.traceBack(vo, stockPool);

    }

    public void runThread() {
       thread = new Thread(() -> {
            chooseStrategyPanel.popup();
            try {
                traceBackVO = search();
                AnalysePanel.getInstance().createChart(traceBackVO);
                StrategySwitchController.getInstance().viewSwitch("analysePanel");
            } catch (IOException e) {
                chooseStrategyPanel.handleExceptions(e.getMessage());
            } catch (UnhandleBlockTypeException e) {
                chooseStrategyPanel.handleExceptions(e.getMessage());
            } catch (DataSourceFirstDayException e) {
                chooseStrategyPanel.handleExceptions(e.getMessage());
            } catch (PrivatePoolNotEnoughException e) {
                chooseStrategyPanel.handleExceptions(e.getMessage());
            } catch (NoDataWithinException e) {
                chooseStrategyPanel.handleExceptions(e.getMessage());
            } catch (DateNotWithinException e) {
                chooseStrategyPanel.handleExceptions(e.getMessage());
            } catch (IllegalArgumentException e){
                chooseStrategyPanel.handleExceptions("起始日期需要比截止日期小");
            }
           chooseStrategyPanel.popdown();
            thread.stop();
        });
       thread.start();
    }

    public void deletePool() throws PrivateStockNotExistException, PrivateStockNotFoundException, IOException {
//        PrivateStockPool.getInstance().remove(TODO 传入需要删除的列表);
        chooseStrategyPanel.refreshTabel();
        //Todo remove gcm 之后还有更新stockTablePanel里面的自选股池
    }
}
