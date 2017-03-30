package presentation.view.panel.iteration2;

import presentation.controller.StrategySwitchController;
import presentation.view.frame.MainFrame;
import presentation.view.panel.TemplatePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/25.
 */
public class StrategyPanel extends TemplatePanel{
    private static JPanel cardPanel;
    private static CardLayout card;
    static StrategyPanel strategyPanel;
    StrategyPanel(){


        setBounds(adaptScreen(0,0,1830,990));
        setVisible(true);

        //对象实例化

        cardPanel = new JPanel();
        cardPanel.setBounds(adaptScreen(0,0,1830,990));
        add(cardPanel);
        card = new CardLayout();
        cardPanel.setLayout(card);

        JLayeredPane mainPane = new JLayeredPane();
        cardPanel.add(mainPane, "mainPane");

//        ChooseStrategyPanel chooseStrategyPanel= ChooseStrategyPanel.getInstance();
        StrategySwitchController.getInstance().viewSwitch("chooseStrategyPanel");

    }
    public static StrategyPanel getInstance(){
        if(strategyPanel==null){
          strategyPanel=new StrategyPanel();
        }
        return strategyPanel;
    }
    /**
     * @param
     * @return cardPanel
     * @author 61990
     * @updateTime 2017/3/25
     */
    public static JPanel getCardPanel() {
        return cardPanel;
    }

    /**
     * @param
     * @return card
     * @author 61990
     * @updateTime 2017/3/25
     */
    public static CardLayout getCard() {
        return card;
    }
}
