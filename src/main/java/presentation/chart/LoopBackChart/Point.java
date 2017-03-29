package presentation.chart.LoopBackChart;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Byron Dong on 2017/3/29.
 */
public class Point extends JPanel{




    @Override
    public void paintComponent(Graphics GPHCS){
        super.paintComponent(GPHCS);

        GPHCS.setColor(Color.BLUE);
        GPHCS.fillRect(25,25,100,30);

        GPHCS.setColor(Color.GRAY);
        GPHCS.fillRect(25,65,100,30);

        GPHCS.setColor(new Color(190,81,215));
        GPHCS.drawString("This is my text", 25, 120);
    }


}
