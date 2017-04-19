package presentation.view.tools;

import java.awt.*;

/**
 * Created by Byron Dong on 2017/4/6.
 */
public class ColorUtils {

    //标题背景

    public static Color titleBgColor(){return new Color(44,50,55);}
    // 字体颜色
    public static Color fontColor(){return new Color(186,193,206);}

    // 背景颜色
    public static Color backgroundColor(){
        return new Color(27,29,33);
    }

    // 页面标题颜色
    public static Color titleColor(){return new Color(56,62,71);}

    // 图表的背景线格颜色
    public static Color lineColor(){
        return new Color(44, 50, 54);
    }

    // 表格的分割线
    public static Color divideColor(){
        return  new Color(16,17,18);
    }

    // 表格中的选中颜色
    public static  Color tableSelectedColor(){return new Color(37,58,84);}

    // 涨红
    public static Color upColor(){
        return new Color(252, 47, 7);
    }

    // 跌绿
    public static Color downColor(){
        return new Color(57, 199, 125);
    }

    //
    public static Color linkColor(){
        return new Color(8, 55, 114);
    }

    // 图表的十字线颜色
    public static Color markLineColor(){
        return new Color(82,98,113);
    }

    // 图表的十字线交叉口日期北京颜色
    public static Color markLabelColor(){
        return new Color(87,107,131);
    }

    // 文字框未选中
    public static  Color fieldUnselectedColor(){return new Color(56,63,69);}

    // 文字框进入
    public static  Color fieldEnterColor(){return new Color(82,89,101);}

    // 文字框选中
    public static  Color fieldSelectedColor(){return new Color(107,115,129);}

}
