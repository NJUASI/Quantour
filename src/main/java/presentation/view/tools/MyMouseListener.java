package presentation.view.tools;
import presentation.controller.StocksTableController;
import presentation.controller.ViewSwitchController;
import presentation.view.panel.KStringPanel;
import utilities.CodeReserve;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class MyMouseListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println("enter Clicked");
        //如果是双击
        if (e.getClickCount() == 2) {
            KStringPanel.getInstance().count = 1;
            CodeReserve.getInstance().setCode(StocksTableController.getInstance().stocksTablePane.getCode(), StocksTableController.getInstance().stocksTablePane.getName());
            ViewSwitchController.getInstance().viewSwitch("kStringPanel");
            StocksTableController.getInstance().checkDetail();
            KStringPanel.getInstance().count = 0;
        }
    }
}