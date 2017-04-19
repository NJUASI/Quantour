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
    public FileImportPanel() {
        width = WindowData.getInstance().getWidth();
        height = WindowData.getInstance().getHeight();

        Color color=new Color(27+6,29+6,33+6);

        setBackground(color);

        MyLabel name = new MyLabel("选择数据源",17);
        name.setBounds(adaptScreen(32+10, 50, 100, 35));
        add(name);

        radioButton1 = new JRadioButton("云端数据源");
        radioButton1.setBounds(adaptScreen(50+10,100,150,40));
        add(radioButton1);
        radioButton1.setBackground(color);
        radioButton1.setForeground(Color.WHITE);
        radioButton1.setSelected(true);
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
        importData.setBounds(adaptScreen(80+10,290,120,25));


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
        label= new MyLabel("正在上传.");
        label.setBounds(adaptScreen(60+10, 340, 200, 35));
        add(label);
        thread=new Thread(() ->{
            int num =0;
            while (true){
                try{
                    Thread.sleep(400);
                }catch (Exception e){
                    e.printStackTrace();
                }
                switch (num){
                    case 0:
                        label.setText("正在上传..");
                        break;
                    case 1:
                        label.setText("正在上传....");
                        break;
                    case 2:
                        label.setText("正在上传....");
                        break;
                    case 3:
                        label.setText("正在上传..");
                        break;
                }
                num=(num+1)%4;
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
    }
    public void FoundDate(){
        radioButton1.setSelected(true);
        radioButton2.setSelected(false);
        radioButton2.setEnabled(true);
    }
}
