package presentation.chart;

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
            return new Color(255,96,96);
        }
        if(tag == 10){
            return new Color(255,141,30);
        }
        if(tag == 20){
            return new Color(233,112,220);
        }
        if(tag == 30){
            return new Color(0,255,96);
        }
        if(tag == 60){
            return new Color(0,128,255);
        }
        return null;
    }
}
