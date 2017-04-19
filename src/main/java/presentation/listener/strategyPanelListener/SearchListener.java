package presentation.listener.strategyPanelListener;

import presentation.controller.KStringPanelController;
import presentation.controller.StrategyPanelController;
import presentation.controller.StrategySwitchController;
import presentation.view.panel.iteration2.AnalysePanel;
import presentation.view.panel.iteration2.ChooseStrategyPanel;
import presentation.view.panel.iteration2.StrategyPanel;
import presentation.view.tools.component.PopupProgress;
import utilities.exceptions.*;
import vo.TraceBackVO;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by 61990 on 2017/4/9.
 */
public class SearchListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
       StrategyPanelController.getInstance().runThread();
    }
}