package presentation.view.tools;

import java.awt.*;

/**
 * Created by 61990 on 2017/3/5.
 */
public class WindowData {
    //屏幕公共信息
    private static WindowData windowData;
    //屏幕宽度和高度
    private static int width;
    private static int height;
    private static Color bgColor;

    /**
     * 设置屏幕高和宽度
     *
     * @param width  宽
     * @param height 高
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static void setWindowData(int width, int height) {
        windowData.height = height;
        windowData.width = width;
        bgColor = ColorUtils.backgroundColor();
    }

    /**
     * 构造器
     *
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public WindowData() {

    }

    /**
     * 获得屏幕高和宽度
     *
     * @param
     * @return width 宽 height 高
     * @author 61990
     * @updateTime 2017/3/5
     */
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * 获得背景颜色
     *
     * @param
     * @return width 宽 height 高
     * @author 61990
     * @updateTime 2017/3/10
     */
    public Color getColor() {
        return bgColor;
    }

    /**
     * 单件模式
     *
     * @param
     * @return windowData 屏幕数据
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static WindowData getInstance() {
        if (windowData == null) {
            windowData = new WindowData();
        }
        return windowData;
    }

}
