package presentation.view.panel.iteration2;

import presentation.view.panel.TemplatePanel;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by day on 17/3/28.
 */
public class ChooseStrategyPanel extends TemplatePanel {
    private static ChooseStrategyPanel chooseStrategyPanel;
    private ChooseStrategyPanel(){
        setLayout(null);
        setBounds(adaptScreen(0,0,1500,700));
//        setBackground(Color.blue);

        JButton button= new JButton("12121");
        button.setBounds(adaptScreen(30,30,100,30));
        add(button);
    }

    public static ChooseStrategyPanel getInstance(){
        if(chooseStrategyPanel==null){
            chooseStrategyPanel=new ChooseStrategyPanel();
        }
        return chooseStrategyPanel;
    }
}
