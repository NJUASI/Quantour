package presentation.view.panel.iteration2;

import presentation.view.frame.MainFrame;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/25.
 */
public class Frame extends JFrame {
    private static MainFrame mainPanel;
    private static JPanel cardPanel;
    public static JPanel barPanel,titlePanel;
    private static CardLayout card;
    Frame(){
        setTitle("Quantourist");
        setLayout(null);
        int width= 1920;
        int height=1030;
        WindowData.setWindowData(1920,1030);
        setSize(width,height);

//        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
//        setResizable(false);

        cardPanel = new JPanel();
        cardPanel.setBounds(90*width/1920,40*height/1030,1500*width/1920,800*height/1030);
        add(cardPanel);
        card = new CardLayout();
        cardPanel.setLayout(card);

        JLayeredPane mainPane = new JLayeredPane();
        cardPanel.add(mainPane, "mainPane");

        StrategyPanel strategyPanel= StrategyPanel.getInstance();
        cardPanel.add(strategyPanel,"strategyPanel");
        card.show(cardPanel,"strategyPanel");
    }
}
