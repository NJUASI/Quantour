package presentation.controller;

import presentation.chart.candlestick.CandlestickChart;
import presentation.view.panel.KStringPanel;
import utilities.enums.MovingAverageType;
import utilities.exceptions.*;
import vo.ChartShowCriteriaVO;
import vo.StockSearchVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Harvey on 2017/3/25.
 */
public class KStringPanelController {

    /**
     * The K string panel.
     */
    KStringPanel kStringPanel;

    /**
     * The constant ourInstance.
     */
    private static KStringPanelController ourInstance = new KStringPanelController();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static KStringPanelController getInstance() {
        return ourInstance;
    }

    /**
     * Instantiates a new K string panel controller.
     */
    private KStringPanelController() {
        kStringPanel = KStringPanel.getInstance();
    }

    /**
     * Search all. 查找总体信息
     */
    public void searchAll() {
        searchFunction(true);
    }

    /**
     * Search. 查找局部信息
     */
    public void search() {
        if(!KStringPanel.getInstance().getStockCode().equals("")){
            searchFunction(false);
        }
    }

    /**
     * Update text field. 选择股票后，更新股票名称框和代码框的内容
     */
    public void updateTextField() {

        StockSearchVO temp = kStringPanel.getMessage();
        kStringPanel.addMessage(temp.name,temp.code);
    }

    /**
     * Stock name change. 实时根据股票名称框的信息联想股票
     */
    public void stockNameChange() {
        kStringPanel.associatePanelSetting();
        kStringPanel.updateAssociateJList(kStringPanel.getStockName());
    }

    /**
     * Stock code change. 实时根据股票代码框的信息联想股票
     */
    public void stockCodeChange() {
        kStringPanel.associatePanelSetting();
        kStringPanel.updateAssociateJList(kStringPanel.getStockCode());
    }

    /**
     * Search function. 减少search和searchAll的重复代码
     */
    private void searchFunction(boolean isAll){

        kStringPanel.setAssociatePanelUnvisible();

        String stockCode = kStringPanel.getStockCode();

        if(stockCode.equals("")){
            kStringPanel.warnMessageOnKStringPanel("请输入股票代码");
        }
        else {
            if (kStringPanel.getChartPanel()!= null) {
                kStringPanel.removeChartPanel();
            }

            // 创建图形
            ArrayList<MovingAverageType> tag = new ArrayList<>();
            tag.add(MovingAverageType.MA5);
            tag.add(MovingAverageType.MA10);
            tag.add(MovingAverageType.MA20);
            tag.add(MovingAverageType.MA30);
            tag.add(MovingAverageType.MA60);

            try {
                CandlestickChart candlestickChart = null;
                if(isAll){
                    candlestickChart = new CandlestickChart(stockCode,tag);
                }
                else{
                    ChartShowCriteriaVO chartShowCriteriaVO=new ChartShowCriteriaVO(stockCode,kStringPanel.getStartDate(),kStringPanel.getEndDate());
                    try{
                        candlestickChart = new CandlestickChart(chartShowCriteriaVO,tag);
                    } catch (NoDataWithinException e) {
                        e.printStackTrace();
                    } catch (DateNotWithinException e) {
                        kStringPanel.warnMessageOnKStringPanel("请重新选择时间范围");
                        e.printStackTrace();
                    } catch (DateShortException e) {
                        e.printStackTrace();
                    } catch (NoMatchEnumException e) {
                        e.printStackTrace();
                    }
                }

                kStringPanel.setChartPanel(candlestickChart);

            } catch (CodeNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
