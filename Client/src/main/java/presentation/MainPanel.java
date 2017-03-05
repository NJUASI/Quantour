package presentation;


import java.awt.Dimension;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;

//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
/**
 * Created by 61990 on 2017/3/5.
 */
class MainPanel extends JFrame {
    private static MainPanel mainPanel;
    public JLayeredPane mainPane;
    private static JPanel cardPanel;
    private static CardLayout card;

    public MainPanel(){
        createWindow();
        
        cardPanel = new JPanel();
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        card = new CardLayout();
        cardPanel.setLayout(card);
        mainPane = new JLayeredPane();
        cardPanel.add(mainPane, "mainPane");

    }

    private void createWindow(){

        setTitle("ASI");
        getContentPane().setLayout(new BorderLayout(0, 0));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Insets   insets   =   Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        Rectangle   bounds   =   new   Rectangle(dim);

        //set the windows large
        bounds.x   +=   insets.left;
        bounds.y   +=   insets.top;
        bounds.width   -=   insets.left   +   insets.right;
        bounds.height   -=   insets.top   +   insets.bottom;

        new WindowData(bounds.width,bounds.height);//save the window's data

        setBounds(bounds);

        setUndecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        setResizable(false);
    }

    public static MainPanel getInstance() {
        if (mainPanel == null) {
            mainPanel = new MainPanel();
        }
        return mainPanel;
    }
}
