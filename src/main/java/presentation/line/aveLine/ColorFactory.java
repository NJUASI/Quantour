package presentation.line.aveLine;

import java.awt.*;

/**
 * Created by Byron Dong on 2017/3/9.
 */
public class ColorFactory {

    //一旦传入没有以下值的tag，则返回null
    public Color getColor(int tag){
        if(tag==5){
            return Color.RED;
        }
        if(tag == 10){
            return Color.YELLOW;
        }
        if(tag == 20){
            return Color.MAGENTA;
        }
        if(tag == 30){
            return Color.GREEN;
        }
        if(tag == 60){
            return Color.BLUE;
        }
        return null;
    }
}
