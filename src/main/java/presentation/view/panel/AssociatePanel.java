package presentation.view.panel;

import presentation.view.panel.associatePanel.SearchComboBoxModel;
import presentation.view.tools.WindowData;
import vo.StockSearchVO;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/8.
 */
public class AssociatePanel extends ScrollPane {

    private static AssociatePanel associatePanel;

    ListModel jListModel;

    JList list;

    public AssociatePanel(){

        WindowData windowData = WindowData.getInstance();
        int width = windowData.getWidth();
        int height =windowData.getHeight();
        setSize(250*width/1920,200*height/1030);

        list = new JList();

        list.setLocation(0,0);
        add(list);
    }

    public void updateText(String str){
        jListModel =  new SearchComboBoxModel(str);
        list.setModel(jListModel);
    }

    public StockSearchVO getMessage(){
        int index=list.getSelectedIndex();
        return (StockSearchVO) jListModel.getElementAt(index);
    }

    public static AssociatePanel getInstance(){
        if(associatePanel == null){
            associatePanel = new AssociatePanel();
        }

        return associatePanel;
    }

    //获取选择的股票的代码
    public String getCode() {
        int index=list.getSelectedIndex();
        return ((StockSearchVO)jListModel.getElementAt(index)).code;
    }
}
