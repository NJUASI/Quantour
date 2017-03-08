package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        setSize(250,200);

        list = new JList();

        list.setLocation(0,0);
        add(list);
    }
    void updateText(String s){
        //TODO 通过s筛选对应的联想
        String listName[] =new String[4];
        jListModel =  new DefaultComboBoxModel(new String[] { "张三", "李四" ,"王五","11","22"});
        list.setModel(jListModel);

    }
    public String getMessage(){
        int index=list.getSelectedIndex();
        return list.getModel().getElementAt(index).toString();
    }
}
