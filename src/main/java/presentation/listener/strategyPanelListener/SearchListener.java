package presentation.listener.strategyPanelListener;

import presentation.controller.KStringPanelController;
import presentation.controller.StrategyPanelController;
import presentation.view.panel.iteration2.ChooseStrategyPanel;
import presentation.view.panel.iteration2.StrategyPanel;
import presentation.view.tools.component.PopupProgress;
import utilities.exceptions.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by 61990 on 2017/4/9.
 */
public class SearchListener extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
        ChooseStrategyPanel.getInstance().popup();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            StrategyPanelController.getInstance().search();
        } catch (DateNotWithinException e1) {
            e1.printStackTrace();
        } catch (NoMatchEnumException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (NoDataWithinException e1) {
            e1.printStackTrace();
        } catch (CodeNotFoundException e1) {
            e1.printStackTrace();
        } catch (DateShortException e1) {
            e1.printStackTrace();
        } catch (UnhandleBlockTypeException e1) {
            e1.printStackTrace();
        } catch (InvalidInputException e1) {
            e1.printStackTrace();
        } catch (DataSourceFirstDayException e1) {
            e1.printStackTrace();
        } catch (PrivateStockNotFoundException e1) {
            e1.printStackTrace();
        }
        ChooseStrategyPanel.getInstance().popdown();
    }
}