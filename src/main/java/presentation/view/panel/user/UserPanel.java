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
    public JLabel label,logo;
    public UserPanel(){
        width= WindowData.getInstance().getWidth();
        height=WindowData.getInstance().getHeight();


        MyLabel user=new MyLabel("   用  户",20) ;
        user.setBounds(adaptScreen(60,40,330,40));
        user.setBackground(ColorUtils.titleBgColor());
        user.setOpaque(true);
        add(user);

        MyLabel table=new MyLabel("   自 选 股 票 池",20) ;
        table.setBounds(adaptScreen(450,40,1300-480,40));
        table.setBackground(ColorUtils.titleBgColor());
        table.setOpaque(true);
        add(table);

        MyLabel source=new MyLabel("   数  据  源",20) ;
        source.setBounds(adaptScreen(1330,40,420,40));
        source.setBackground(ColorUtils.titleBgColor());
        source.setOpaque(true);
        add(source);

        fileImportPanel=new FileImportPanel();
        fileImportPanel.setBounds(adaptScreen(1330,100,420,810));
        add(fileImportPanel);

        messagePanel=new MessagePanel();
        messagePanel.setBounds(adaptScreen(60,100,310,400));
        add(messagePanel);

        JButton delete= new MyButton("删除");
        delete.setBounds(adaptScreen(900,820,110,35));
        delete.addMouseListener(new DeleteFavoriteListener());
        add(delete);

        JButton search= new MyButton("查看");
        search.setBounds(adaptScreen(700,820,110,35));
        search.addMouseListener(new DetailOfCodeListener());
        add(search);



        MyLabel block1=new MyLabel("",16) ;
        block1.setBounds(adaptScreen(60,60,420-90,850));
        block1.setBackground(new Color(27,29,33));
        block1.setOpaque(true);
        add(block1);
        MyLabel middle1=new MyLabel("",16) ;
        middle1.setBounds(adaptScreen(390,60,60,850));
        middle1.setBackground(new Color(27-6,29-6,33-6));
        middle1.setOpaque(true);
        add(middle1);

        MyLabel block2=new MyLabel("",16) ;
        block2.setBounds(adaptScreen(1330,60,420,850));
        block2.setBackground(new Color(27,29,33));
        block2.setOpaque(true);
        add(block2);

        MyLabel block3=new MyLabel("",16) ;
        block3.setBounds(adaptScreen(0,0,1920,60));
        block3.setBackground(new Color(27-6,29-6,33-6));
        block3.setOpaque(true);
        add(block3);

        MyLabel block4=new MyLabel("",16) ;
        block4.setBounds(adaptScreen(1750,0,60,1030));
        block4.setBackground(new Color(27-6,29-6,33-6));
        block4.setOpaque(true);
        add(block4);

        MyLabel block5=new MyLabel("",16) ;
        block5.setBounds(adaptScreen(0,910,1920,60));
        block5.setBackground(new Color(27-6,29-6,33-6));
        block5.setOpaque(true);
        add(block5);

        MyLabel block6=new MyLabel("",16) ;
        block6.setBounds(adaptScreen(0,0,60,1030));
        block6.setBackground(new Color(27-6,29-6,33-6));
        block6.setOpaque(true);
        add(block6);

        MyLabel middle2=new MyLabel("",16) ;
        middle2.setBounds(adaptScreen(450+1300-480,0,60,1030));
        middle2.setBackground(new Color(27-6,29-6,33-6));
        middle2.setOpaque(true);
        add(middle2);

        ImageIcon bgPicture= new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("picture/logo4.png"));
        logo =new JLabel();
        bgPicture.setImage(bgPicture.getImage());
        logo.setIcon(bgPicture);
        logo.setBounds(850*width/1920-200,900*height/1920-200,400,400);
        add(logo);

        refreshFavorite();
    }

    public void refreshFavorite(){
        if (favoritePanel!=null){
            remove(favoritePanel);
            remove(label);
        }
        try {
            favoritePanel=new FavoritePanel();
            favoritePanel.setBounds(adaptScreen(450,80,1300-480,660));
            label = new JLabel();
            label.setBounds(450 * width / 1920, (30*(favoritePanel.jTable.getRowCount()+1)) +80* height / 1030, (1300-480) * width / 1920 , 660* height / 1030-(30*(favoritePanel.jTable.getRowCount()+1)) );
            label.setBorder(BorderFactory.createEmptyBorder());
            label.setBackground(ColorUtils.backgroundColor());
            label.setOpaque(true);
            add(label);
            if(favoritePanel.jTable.getRowCount()==0){
                logo.setVisible(true);
            }else{
                logo.setVisible(false);
            }
            add(favoritePanel);
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PrivateStockNotFoundException e) {
//            e.printStackTrace();
//            new PopUpFrame(e.getMessage());
            logo.setVisible(true);

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
