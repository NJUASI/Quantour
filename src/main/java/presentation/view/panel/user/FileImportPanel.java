package presentation.view.panel.user;

import presentation.view.panel.TemplatePanel;
import presentation.view.tools.FileChoose;
import presentation.view.tools.WindowData;
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
    public FileImportPanel() {
        width = WindowData.getInstance().getWidth();
        height = WindowData.getInstance().getHeight();

        Color color=new Color(30,0,30);

        setBackground(color);

        MyLabel name = new MyLabel("选择数据源",17);
        name.setBounds(adaptScreen(30, 50, 100, 35));
        add(name);

        radioButton1 = new JRadioButton("云端数据源");
        radioButton1.setBounds(adaptScreen(50,100,150,40));
        add(radioButton1);
        radioButton1.setBackground(color);
        radioButton1.setForeground(Color.WHITE);
        radioButton1.setSelected(true);
        radioButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //TODO 改变文件读取路径
            }
        });

        radioButton2 = new JRadioButton("本地数据源");
        radioButton2.setBounds(adaptScreen(50,145,150,40));
        radioButton2.setBackground(color);
        radioButton2.setForeground(Color.WHITE);
        radioButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //TODO 改变文件读取路径
            }
        });
        add(radioButton2);

        information=new MyLabel("由fff上传于2003-1-12");
        information.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
        information.setBounds(adaptScreen(90,175,250,40));
        add(information);

        group = new ButtonGroup();// 创建单选按钮组
        group.add(radioButton1);// 将radioButton1增加到单选按钮组中
        group.add(radioButton2);// 将radioButton2增加到单选按钮组中

        MyLabel name2 = new MyLabel("导入数据源",17);
        name2.setBounds(adaptScreen(30, 240, 100, 35));
        add(name2);

        JButton importData= new JButton("导入数据");
        importData.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
        importData.setBounds(adaptScreen(80,290,120,25));
        importData.addMouseListener(new FileChoose());
        add(importData);
    }

    public void setUploadInfo(String message){
        information.setText(message);
    }
}
