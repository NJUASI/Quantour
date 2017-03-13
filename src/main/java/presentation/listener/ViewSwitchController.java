package presentation.listener;

import presentation.view.panel.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harvey on 2017/3/12.
 */
public class ViewSwitchController{

    private JPanel cardPanel = MainPanel.getCardPanel();
    private CardLayout cardLayout = MainPanel.getCard();

    List<String> panelNames;

    /**
     * The View switch controller.
     */
    private static ViewSwitchController viewSwitchController = new ViewSwitchController();

    /**
     * Instantiates a new View switch controller.
     */
    private ViewSwitchController() {
        panelNames = new ArrayList<String>();
    }

    /**
     * Get instance view switch controller.
     *
     * @return the view switch controller
     */
    public static ViewSwitchController getInstance(){
        return viewSwitchController;
    }

    /**
     * View switch.
     *
     * @param panelName 面板的名称
     */
    public void viewSwitch(String panelName){
        if (!panelNames.contains(panelName)){
            switch (panelName){
                case "loginPanel":
                    cardPanel.add(LoginPanel.getInstance(),"loginPanel");
                    break;
                case "registerPanel":
                    cardPanel.add(RegisterPanel.getInstance(),"registerPanel");
                    break;
                case "kStringPanel":
                    cardPanel.add(KStringPanel.getInstance(),"kStringPanel");
                    break;
                case "comparePanel":
                    cardPanel.add(ComparePanel.getInstance(),"comparePanel");
                    break;
                case "favoritesPanel":
                    cardPanel.add(FavoritesPanel.getInstance(),"favoritesPanel");
                    break;
                case "thermometerPanel":
                    System.out.println("enter");
                    cardPanel.add(ThermometerPanel.getInstance(),"thermometerPanel");
                    break;
            }
            panelNames.add(panelName);
        }
        cardLayout.show(cardPanel,panelName);
    }
}
