package presentation.controller;

import presentation.view.panel.ComparePanel;

import java.time.LocalDate;

/**
 * Created by 61990 on 2017/3/14.
 */
public class CompareController {
    /**
     * The constant CompareController.
     */
    private static CompareController compareController = new CompareController();
    /**
     * The Compare panel.
     */
    private ComparePanel comparePanel;
    /**
     * The Stock situation service.
     */
//    private StockSituationService stockSituationService;

    /**
     * Instantiates a new Thermometer controller.
     */
    private CompareController(){
        compareController = CompareController.getInstance();
//        stockSituationService = new StockSituationServiceImpl();
    }

    /**
     * Get instance Compare controller.
     *
     * @return the Compare controller
     */
    public static CompareController getInstance(){
        return compareController;
    }

    /**
     * 搜索操作,获取指定日期的市场温度计的数据
     */
    public void compare() {

    }
}
