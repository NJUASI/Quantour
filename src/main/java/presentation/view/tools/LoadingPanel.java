package presentation.view.tools;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/4/16.
 */
    public class LoadingPanel extends JPanel {
       public JLabel jLabel,label2;
        public boolean out=false;
        Thread thread=new Thread();
        public LoadingPanel(){
            setLayout(null);
            setSize(400,200);
            setBackground(new Color(27,29,33));
            label2=new JLabel("正在回测");
            label2.setBounds(140, 40, 120, 42);
            label2.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,16));
            label2.setForeground(ColorUtils.fontColor());
            add(label2);

            jLabel=new JLabel();
            jLabel.setBounds(50, 90, 305, 42);
            add(jLabel);
    }
    public void start(){
        for(int i=0;i<=12;i++){
            i=i%12;
            switch (i/3){
                case 0:
                    label2.setText("正在回测.");
                    break;
                case 1:
                    label2.setText("正在回测..");
                    break;
                case 2:
                    label2.setText("正在回测...");
                    break;
                case 3:
                    label2.setText("正在回测....");
                    break;
            }
            ImageIcon bgPicture= new ImageIcon(thread.currentThread().getContextClassLoader().getResource("picture/prograss/prograss"+i+".png"));
            bgPicture.setImage(bgPicture.getImage());
            jLabel.setIcon(bgPicture);
            if(out){
                break;
            }
            try {
                thread.sleep(113);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}