package presentation.view.panel.associatePanel;

import presentation.listener.associatePanelListener.ComparePanelChooseListener1;
import presentation.listener.associatePanelListener.ComparePanelChooseListener2;
import presentation.listener.associatePanelListener.KStringChooseListener;
import presentation.listener.associatePanelListener.KeyControlListener;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.UI.MyScrollBarUI;
import presentation.view.tools.WindowData;
import vo.StockSearchVO;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/8.
 */
public class AssociatePanel extends JScrollPane {

    private static AssociatePanel associatePanel;

    ListModel<StockSearchVO> jListModel;

    JList list;

    public AssociatePanel(){


        WindowData windowData = WindowData.getInstance();
        int width = windowData.getWidth();
        int height =windowData.getHeight();
        setSize(250*width/1920,200*height/1030);
        list = new JList();

        list.addKeyListener(new KeyControlListener());
        list.setLocation(0,0);
        setAssociate();
        setViewportView(list);

    }

    public void updateJList(String str){
        jListModel =  new SearchComboBoxModel(str);
        list.setModel(jListModel);
    }

    public StockSearchVO getMessage(){
        int index=list.getSelectedIndex();
        return jListModel.getElementAt(index);
    }

    public static AssociatePanel getInstance(){
        if(associatePanel == null){
            associatePanel = new AssociatePanel();
        }
        return associatePanel;
    }

    //为KStringPanel的associatePanel增加监听
    public void kStringPanelChoose() {
        list.addMouseListener(new KStringChooseListener());
    }

    //jlist焦点上移
    public void moveUp() {
        if(list.getSelectedIndex()!=0){
            list.setSelectedIndex(list.getSelectedIndex()-1);
        }
    }

    //jlist焦点上移
    public void moveDown() {
        if(list.getSelectedIndex()!=list.getMaxSelectionIndex()){
            list.setSelectedIndex(list.getSelectedIndex()+1);
        }
    }

    //为ComparePanel的associatePanel1增加监听
    public void comparePanelChoose1() {
        list.addMouseListener(new ComparePanelChooseListener1());
    }

    //为ComparePanel的associatePanel2增加监听
    public void comparePanelChooose2() {
        list.addMouseListener(new ComparePanelChooseListener2());
    }

    private void setAssociate(){
        getVerticalScrollBar().setUI(new MyScrollBarUI());
        setBackground(ColorUtils.titleColor());
        setForeground(ColorUtils.fontColor());
        setBorder(BorderFactory.createEmptyBorder());

        list.setBackground(ColorUtils.titleColor());
        list.setForeground(ColorUtils.fontColor());
        list.setBorder(BorderFactory.createEmptyBorder());
    }
}
