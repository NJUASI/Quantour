package presentation.view.panel.user;

import presentation.controller.UserController;
import presentation.listener.userPanelListener.ChangeLocalPath;
import presentation.listener.userPanelListener.ChangeNetPath;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.component.FileChoose;
import presentation.view.tools.WindowData;
import presentation.view.tools.component.MyButton;
import presentation.view.tools.component.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/4/13.
 */
public class FileImportPanel extends TemplatePanel {
    int width;
    int height;
    MyLabel information;
    JRadioButton radioButton1,radioButton2;
    ButtonGroup group;
    JLabel label;
    Thread thread;
    public int count=0;
    public FileImportPanel() {
        width = WindowData.getInstance().getWidth();
        height = WindowData.getInstance().getHeight();

        Color color=new Color(27,29,33);

        setBackground(color);

        MyLabel name = new MyLabel("选择数据源",17);
        name.setBounds(adaptScreen(32+10, 50, 100, 35));
        add(name);

        radioButton1 = new JRadioButton("云端数据源");
        radioButton1.setBounds(adaptScreen(50+10,100,150,40));
        add(radioButton1);
        radioButton1.setBackground(color);
        radioButton1.setForeground(Color.WHITE);
        radioButton1.setSelected(false);
        radioButton1.addMouseListener(new ChangeNetPath());


        radioButton2 = new JRadioButton("本地数据源");
        radioButton2.setBounds(adaptScreen(50+10,145,150,40));
        radioButton2.setBackground(color);
        radioButton2.setForeground(Color.WHITE);
        radioButton2.addMouseListener(new ChangeLocalPath());
        add(radioButton2);



        group = new ButtonGroup();// 创建单选按钮组
        group.add(radioButton1);// 将radioButton1增加到单选按钮组中
        group.add(radioButton2);// 将radioButton2增加到单选按钮组中

        MyLabel name2 = new MyLabel("导入数据源",17);
        name2.setBounds(adaptScreen(30+10, 240, 100, 35));
        add(name2);

        JButton importData= new MyButton("导入数据");
        importData.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
        importData.setBounds(adaptScreen(150,310,120,25));


        importData.addMouseListener(new FileChoose());
        add(importData);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                UserController.getInstance().setUpdateMessage();
            }
        });


    }
    public void popLabel(){
        label= new MyLabel("");
        label.setBounds(adaptScreen(110, 400, 200, 200));
        add(label);
        thread=new Thread(() ->{
            int num =0;
            while (true){
                try{
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }

                ImageIcon bgPicture= new ImageIcon(thread.currentThread().getContextClassLoader().getResource("picture/loading/loading"+num+".png"));
                bgPicture.setImage(bgPicture.getImage().getScaledInstance(200*width/1920, 180*height/1030, Image.SCALE_DEFAULT ));
                label.setIcon(bgPicture);
                num=(num+1)%14;
            }
        });
        thread.start();
        label.repaint();
        repaint();
    }

    public void hideLabel(){
        thread.stop();
        remove(label);
        repaint();
    }

    public void setUploadInfo(String message){
        if(information!=null){
            remove(information);
        }
        information=new MyLabel(message);
        information.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
        information.setBounds(adaptScreen(70+10,175,400,40));
        add(information);
        information.setVisible(true);
        information.repaint();
        repaint();
    }

    public void notFoundDate(){
        radioButton1.setSelected(true);
        radioButton2.setSelected(false);
        radioButton2.setEnabled(false);
        count=0;
    }
    public void FoundDate(){
        radioButton1.setSelected(true);
        radioButton2.setSelected(false);
        radioButton2.setEnabled(true);
        count=1;
    }
    public void setFound(){
        radioButton1.setSelected(false);
        radioButton2.setSelected(true);
        radioButton2.setEnabled(true);
        count=1;
    }
}
