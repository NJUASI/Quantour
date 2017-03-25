package presentation.controller;

import presentation.view.panel.KStringPanel;
import utilities.exceptions.DateNotWithinException;
import vo.StockSearchVO;

import javax.swing.*;
import java.io.IOException;

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
     * Search all. 总体信息
     */
    public void searchAll() {
        kStringPanel.setAssociatePanelUnvisible();

        if(kStringPanel.getStockCode().equals("")){
            kStringPanel.warnMessage("请输入股票代码");
        }
        else {
            if (kStringPanel.getChartPanel()!= null) {
                kStringPanel.removeChartPanel();
            }

            kStringPanel.findOne(kStringPanel.getStockCode());
        }
    }

    /**
     * Search. 局部信息
     */
    public void search() {

        kStringPanel.setAssociatePanelUnvisible();

        if(kStringPanel.getStockCode().equals("")){
            JOptionPane.showMessageDialog(kStringPanel.getChartPanel(),"请输入股票代号");
        }
        else {

            if (kStringPanel.getChartPanel()!= null) {
                kStringPanel.removeChartPanel();
            }

            try {
                kStringPanel.findSpecial(kStringPanel.getStockCode(), kStringPanel.getStartDate(), kStringPanel.getEndDate());
            } catch (DateNotWithinException e1) {
                e1.printStackTrace();
                kStringPanel.warnMessage("请重新选择时间范围");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    /**
     * Update text field. 选择股票后，更新股票名称框和代码框的内容
     */
    public void updateTextField() {

        StockSearchVO temp = kStringPanel.getMessage();
        kStringPanel.addMessage(temp.name,temp.code);
    }
}
