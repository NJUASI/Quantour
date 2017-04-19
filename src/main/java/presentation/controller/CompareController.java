package presentation.controller;


import presentation.view.panel.ComparePanel;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import utilities.exceptions.DataSourceFirstDayException;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.NoDataWithinException;
import vo.StockComparisionVO;
import vo.StockComparsionCriteriaVO;
import vo.StockSearchVO;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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
     * Instantiates a new Thermometer controller.
     */
    private CompareController(){
        comparePanel = ComparePanel.getInstance();
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

        try {
            ChartService chartService=new ChartServiceImpl();

            String code1 = comparePanel.getNum1();
            String code2 = comparePanel.getNum2();
            LocalDate startDate = comparePanel.getStartDate();
            LocalDate endDate = comparePanel.getEndDate();

            List<StockComparisionVO> vo=chartService.getComparision(new StockComparsionCriteriaVO(code1, code2, startDate, endDate));
            if(comparePanel.getCompareChartPanel()!=null){
                comparePanel.removesCompareChartPanel();
            }

            comparePanel.setCompareCharetPanel(vo);
            comparePanel.logo.setVisible(false);
        } catch (DateNotWithinException e) {
            e.printStackTrace();
            comparePanel.setExceptionMessage("请重新选择时间范围");
        } catch (DataSourceFirstDayException e) {
            comparePanel.setExceptionMessage("所选日期数据缺失，无法计算涨幅，请重新选择");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoDataWithinException e) {
            comparePanel.setExceptionMessage("请重新选择时间范围");
        }

        comparePanel.refreshAssociate();
    }

    /**
     * Update text field. 更新第一组的name和num框
     */
    public void updateTextField() {
        StockSearchVO temp = comparePanel.getMessageByAssociatePanel1();
        comparePanel.addMessageToGroup1(temp.name,temp.code);
    }

    /**
     * Update text field 2. 更新第二组的name和num框
     */
    public void updateTextField2() {
        StockSearchVO temp = comparePanel.getMessageByAssociatePanel2();
        comparePanel.addMessageToGroup2(temp.name,temp.code);
    }

    /**
     * Num 1 change. Group1的股票代码改变，更新associatePanel1
     */
    public void num1Change() throws IOException {
        comparePanel.updateJList1(comparePanel.getNum1());
        comparePanel.setAssociatePanel();
    }

    /**
     * Num 2 change. Group2的股票代码改变，更新associatePanel2
     */
    public void num2Change() throws IOException {
        comparePanel.updateJList2(comparePanel.getNum2());
        comparePanel.setAssociatePanel2();
    }

    /**
     * Name 1 change. Group1的股票名称改变，更新associatePanel1
     */
//    public void name1Change() {
//        comparePanel.updateJList1(comparePanel.getStockName1());
//        comparePanel.setAssociatePanel();
//    }

    /**
     * Name 2 change. Group2的股票名称改变，更新associatePanel2
     */
//    public void name2Change() {
//        comparePanel.updateJList2(comparePanel.getStockName2());
//        comparePanel.setAssociatePanel2();
//    }

}
