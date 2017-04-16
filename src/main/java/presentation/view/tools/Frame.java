package presentation.view.tools;

import javax.swing.*;

/**
 * Created by 61990 on 2017/4/16.
 */
public class Frame extends JFrame {
    Frame(){
        setTitle("Quantourist");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LoadingPanel loadingPanel=new LoadingPanel();
        add(loadingPanel);
        setSize(600, 400);
        setLocation(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadingPanel.start();



    }
}
