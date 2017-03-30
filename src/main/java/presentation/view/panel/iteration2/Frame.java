package presentation.view.panel.iteration2;

import presentation.view.frame.MainFrame;
import presentation.view.tools.BeautyEyeUtil;
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
        BeautyEyeUtil.beautyEye();
        setTitle("Quantourist");
        setLayout(null);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        Rectangle bounds = new Rectangle(dim);

        //set the windows large
        bounds.x += insets.left;
        bounds.y += insets.top;
        bounds.width -= insets.left + insets.right;
        bounds.height -= insets.top + insets.bottom;

        WindowData.setWindowData(bounds.width, bounds.height);//save the window's data

        int width=WindowData.getInstance().getWidth();
        int height=WindowData.getInstance().getHeight();
        setSize(width,height);


//        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        setResizable(false);

        cardPanel = new JPanel();
        cardPanel.setBounds(90*width/1920,40*height/1030,1830*width/1920,990*height/1030);
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
