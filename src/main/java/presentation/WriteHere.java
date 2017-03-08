package presentation;

import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/8.
 */
public class WriteHere extends JPanel{
    WriteHere(){
        setLayout(null);
        TextArea tt=new TextArea();
        setSize(1000,1000);
        tt.setBounds(0,0,220,220);
        add(tt);
        JFreeChart ss ;
        //
    }
}
