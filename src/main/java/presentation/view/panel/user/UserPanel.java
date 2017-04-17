package presentation.view.panel.user;

import presentation.listener.userPanelListener.DeleteFavoriteListener;
import presentation.listener.userPanelListener.DetailOfCodeListener;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.PopUpFrame;
import presentation.view.tools.WindowData;
import presentation.view.tools.component.MyButton;
import presentation.view.tools.component.MyLabel;
import utilities.exceptions.PrivateStockNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by 61990 on 2017/4/13.
 */
public class UserPanel extends TemplatePanel {
    static UserPanel userPanel;
    int width;
    int height;
    public JButton search;
    public MessagePanel messagePanel;
    public FileImportPanel fileImportPanel;
    public FavoritePanel favoritePanel;
    public JLabel label;
    public UserPanel(){
        width= WindowData.getInstance().getWidth();
        height=WindowData.getInstance().getHeight();


        fileImportPanel=new FileImportPanel();
        fileImportPanel.setBounds(adaptScreen(1400,100,420,400));
        add(fileImportPanel);

        messagePanel=new MessagePanel();
        messagePanel.setBounds(adaptScreen(80,100,310,400));
        add(messagePanel);

        JButton delete= new MyButton("删除");
        delete.setBounds(adaptScreen(850,40,110,35));
        delete.addMouseListener(new DeleteFavoriteListener());
        add(delete);

        JButton search= new MyButton("查看");
        search.setBounds(adaptScreen(650,40,110,35));
        search.addMouseListener(new DetailOfCodeListener());
        add(search);

        refreshFavorite();

        MyLabel block1=new MyLabel("",16) ;
        block1.setBounds(adaptScreen(0,0,420,1000));
        block1.setBackground(new Color(32,36,39));
        block1.setOpaque(true);
        add(block1);

        MyLabel block2=new MyLabel("",16) ;
        block2.setBounds(adaptScreen(1300,0,620,1000));
        block2.setBackground(new Color(32,36,39));
        block2.setOpaque(true);
        add(block2);
    }

    public void refreshFavorite(){
        if (favoritePanel!=null){
            remove(favoritePanel);
            remove(label);
        }
        try {
            favoritePanel=new FavoritePanel();
            favoritePanel.setBounds(adaptScreen(420,100,1300-420,800));
            label = new JLabel();
            label.setBounds(420 * width / 1920, (30*(favoritePanel.jTable.getRowCount()+1)) +100* height / 1030, (1300-420) * width / 1920 , 900* height / 1030);
            label.setBorder(BorderFactory.createEmptyBorder());
            label.setBackground(ColorUtils.backgroundColor());
            label.setOpaque(true);
            add(label);
            add(favoritePanel);
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PrivateStockNotFoundException e) {
            e.printStackTrace();
            new PopUpFrame(e.getMessage());
            //TODO 高源后期添加
        }
    }
    public static UserPanel getInstance(){
        if(userPanel==null){
            userPanel=new UserPanel();
        }
        return userPanel;
    }
}
