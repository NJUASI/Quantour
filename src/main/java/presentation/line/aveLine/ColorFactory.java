package presentation.line.aveLine;

import java.awt.*;

/**
 * Created by Byron Dong on 2017/3/9.
 */
public class ColorFactory {

    /**
     * 根据均线类型获取改均线的颜色,一旦传入没有以下值的tag，则返回null
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @param  tag 均线类型
     * @return  Color 颜色
     */
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
