package presentation.view.tools;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/17.
 */
public class MyButton extends JLabel {
    ImageIcon bgPicture;
    int x=40;
    int y=35;
    String path;
    int width;
    int height;
    public MyButton(String str,int i){
        path=str;
        width=WindowData.getInstance().getWidth();
        height=WindowData.getInstance().getHeight();
        if(i==0){
            x=40;
            y=35;
           bgPicture =new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("picture/button/"+str+"1.png"));

        }else{
           bgPicture =new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("picture/button/"+str+"1.png"));

        }
        bgPicture.setImage(bgPicture.getImage().getScaledInstance(x*width/1920, y*height/1030, Image.SCALE_DEFAULT ));
        setIcon(bgPicture);
        setSize(x*width/1920, y*height/1030);
    }
    public void moveIn(){
        bgPicture =new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("picture/button/"+path+"2.png"));
        bgPicture.setImage(bgPicture.getImage().getScaledInstance(x*width/1920, y*height/1030, Image.SCALE_DEFAULT ));
        setIcon(bgPicture);
    }
    public void moveOut(){
        bgPicture =new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("picture/button/"+path+"1.png"));
        bgPicture.setImage(bgPicture.getImage().getScaledInstance(x*width/1920, y*height/1030, Image.SCALE_DEFAULT ));
        setIcon(bgPicture);
    }
}
