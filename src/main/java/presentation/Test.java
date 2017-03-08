package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/8.
 */
public class Test extends JFrame{
    Test(){
    setUndecorated(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ASI");
    setLayout(null);
    setVisible(true);
    setResizable(false);
    setBounds(0,0,1000,1000);
    WriteHere writeHere=new WriteHere();
    writeHere.setBounds(0,0,1000,1000);
    add(writeHere);
}
}
