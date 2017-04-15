package presentation.view.panel.user;

import presentation.view.panel.TemplatePanel;
import presentation.view.tools.FileChoose;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;

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


        favoritePanel=new FavoritePanel();
        favoritePanel.setBounds(adaptScreen(900,100,600,600));
        add(favoritePanel);
        try {
            favoritePanel=new FavoritePanel();
            favoritePanel.setBounds(adaptScreen(900,100,600,600));
            add(favoritePanel);
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
