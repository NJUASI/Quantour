package presentation.view.panel.user;

import presentation.listener.userPanelListener.DeleteFavoriteListener;
import presentation.listener.userPanelListener.DetailOfCodeListener;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.PopUpFrame;
import presentation.view.tools.WindowData;
import presentation.view.tools.component.MyButton;
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
        fileImportPanel.setBounds(adaptScreen(500,100,310,400));
        add(fileImportPanel);

        messagePanel=new MessagePanel();
        messagePanel.setBounds(adaptScreen(100,100,310,400));
        add(messagePanel);

        JButton delete= new MyButton("删除");
        delete.setBounds(adaptScreen(1250,50,110,35));
        delete.addMouseListener(new DeleteFavoriteListener());
        add(delete);

        JButton search= new MyButton("查看");
        search.setBounds(adaptScreen(1050,50,110,35));
        search.addMouseListener(new DetailOfCodeListener());
        add(search);

        refreshFavorite();
    }

    public void refreshFavorite(){
        if (favoritePanel!=null){
            remove(favoritePanel);
            remove(label);
        }
        try {
            favoritePanel=new FavoritePanel();
            favoritePanel.setBounds(adaptScreen(900,100,600,600));
            label = new JLabel();
            label.setBounds(900 * width / 1920, (100+30*(favoritePanel.jTable.getRowCount()+1)) * height / 1030, 600 * width / 1920 , 600* height / 1030);
            label.setBorder(BorderFactory.createEmptyBorder());
            label.setBackground(ColorUtils.backgroundColor());
            label.setForeground(Color.WHITE);
            label.setOpaque(true);
            add(label);
            add(favoritePanel);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PrivateStockNotFoundException e) {
            e.printStackTrace();
            new PopUpFrame(e.getMessage());
            //TODO 高源后期添加
        }
//        label.repaint();
    }
    public static UserPanel getInstance(){
        if(userPanel==null){
            userPanel=new UserPanel();
        }
        return userPanel;
    }
}
