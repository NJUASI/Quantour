package presentation.view.panel.iteration2;

import presentation.view.panel.TemplatePanel;
import javax.swing.*;

/**
 * Created by day on 17/3/28.
 */
public class ChooseStrategyPanel extends TemplatePanel {
    private static ChooseStrategyPanel chooseStrategyPanel;
    private ChooseStrategyPanel(){
        setLayout(null);
        setBounds(adaptScreen(0,0,1830,990));

        JButton button= new JButton("12121");
        button.setBounds(adaptScreen(0,0,100,30));
        add(button);
    }

    public static ChooseStrategyPanel getInstance(){
        if(chooseStrategyPanel==null){
            chooseStrategyPanel=new ChooseStrategyPanel();
        }
        return chooseStrategyPanel;
    }
}
