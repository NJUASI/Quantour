package presentation.view.panel;

import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/8.
 */
public class AssociatePanel extends ScrollPane {

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
        //TODO 通过str筛选对应的联想 得到可能的Vector str可能是 股票号或者名称
        String listName[] =new String[4];
        jListModel =  new DefaultComboBoxModel(new String[] { "张三", "李四" ,"王五","11","22"});
        list.setModel(jListModel);

    }
    public String getMessage(){
        int index=list.getSelectedIndex();
        return list.getModel().getElementAt(index).toString();
    }
}
