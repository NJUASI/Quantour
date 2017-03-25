package presentation.view.panel.iteration2;

import presentation.view.frame.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/25.
 */
public class StrategyPanel extends JPanel{
    private static MainFrame mainPanel;
    private static JPanel cardPanel;
    public static JPanel barPanel,titlePanel;
    private static CardLayout card;
    static StrategyPanel strategyPanel;
    StrategyPanel(){
        card = new CardLayout();
        cardPanel.setLayout(card);
        cardPanel.setBounds(0,0,1920,1030);
        add(cardPanel);
        setBounds(0,0,1920,1030);
        setVisible(true);
//
//        cardPanel.add(ChooseStratage.getInstance(),"choosePanel");
//        cardPanel.add(AnalysePanel.getInstance(),"analysePanel");
        card.show(cardPanel,"choosePanel");
    }
    public static StrategyPanel getInstance(){
        if(strategyPanel==null){
          strategyPanel=new StrategyPanel();
        }
        return strategyPanel;
    }
}
