package presentation.view.tools;

import com.sun.deploy.ui.ImageLoader;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * Created by Byron Dong on 2017/3/17.
 */
public class MyScrollBarUI extends BasicScrollBarUI {

    private Color frameColor = new Color(19, 22, 24);

    public Dimension getPreferredSize(JComponent c)
    {
        return new Dimension(16, 16);
    }

    // 重绘滚动条的滑块
    public void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds)
    {
        super.paintThumb(g, c, thumbBounds);
        int tw = thumbBounds.width;
        int th = thumbBounds.height;
        // 重定图形上下文的原点，这句一定要写，不然会出现拖动滑块时滑块不动的现象
        g.translate(thumbBounds.x, thumbBounds.y);

        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = null;
        if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL)
        {
            gp = new GradientPaint(0, 0, new Color(37, 41, 44), tw, 0, new Color(37, 41, 44));
        }
        if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL)
        {
            gp = new GradientPaint(0, 0, new Color(37, 41, 44), tw, 0, new Color(37, 41, 44));
        }
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, tw - 1, th - 1, 5, 5);
        g2.setColor(frameColor);
        g2.drawRoundRect(0, 0, tw - 1, th - 1, 5, 5);
    }

    // 重绘滑块的滑动区域背景
    public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds)
    {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = null;
        if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL)
        {
            gp = new GradientPaint(0, 0, new Color(19, 22, 24), trackBounds.width, 0, new Color(19, 22, 24));
        }
        if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL)
        {
            gp = new GradientPaint(0, 0, new Color(19, 22, 24), 0, trackBounds.height, new Color(19, 22, 24));
        }
        g2.setPaint(gp);
        g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        g2.setColor(new Color(19, 22, 24));
        g2.drawRect(trackBounds.x, trackBounds.y, trackBounds.width - 1, trackBounds.height - 1);
        if (trackHighlight == BasicScrollBarUI.DECREASE_HIGHLIGHT)
            this.paintDecreaseHighlight(g);
        if (trackHighlight == BasicScrollBarUI.INCREASE_HIGHLIGHT)
            this.paintIncreaseHighlight(g);
    }

    // 重绘当鼠标点击滑动到向上或向左按钮之间的区域
    protected void paintDecreaseHighlight(Graphics g)
    {
        g.setColor(new Color(19, 22, 24));
        int x = this.getTrackBounds().x;
        int y = this.getTrackBounds().y;
        int w = 0, h = 0;
        if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL)
        {
            w = this.getThumbBounds().width;
            h = this.getThumbBounds().y - y;

        }
        if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL)
        {
            w = this.getThumbBounds().x - x;
            h = this.getThumbBounds().height;
        }
        g.fillRect(x, y, w, h);
    }

    // 重绘当鼠标点击滑块至向下或向右按钮之间的区域
    protected void paintIncreaseHighlight(Graphics g)
    {
        Insets insets = scrollbar.getInsets();
        g.setColor(new Color(19, 22, 24));
        int x = this.getThumbBounds().x;
        int y = this.getThumbBounds().y;
        int w = this.getTrackBounds().width;
        int h = this.getTrackBounds().height;
        g.fillRect(x, y, w, h);
    }

    protected JButton createIncreaseButton(int orientation)
    {
        return new BasicArrowButton(orientation)
        {
            // 重绘按钮的三角标记
            public void paintTriangle(Graphics g, int x, int y, int size, int direction, boolean isEnabled)
            {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = null;
                Image arrowImg = null;
                switch (this.getDirection())
                {
                    case BasicArrowButton.SOUTH:
                        gp = new GradientPaint(0, 0, new Color(56, 63, 69), getWidth(), 0, new Color(56, 63, 69));
//                        arrowImg = ImageLoader.get("south.gif");
                        break;
                    case BasicArrowButton.EAST:
                        gp = new GradientPaint(0, 0, new Color(56, 63, 69), 0, getHeight(), new Color(56, 63, 69));
//                        arrowImg = ImageLoader.get("east.gif");
                        break;
                }
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(frameColor);
                g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
                g2.drawImage(arrowImg, (getWidth() - 2) / 2 - 5, (getHeight() - 2) / 2 - 5, 13, 13, null);
            }
        };
    }

    protected JButton createDecreaseButton(int orientation)
    {
        return new BasicArrowButton(orientation)
        {
            public void paintTriangle(Graphics g, int x, int y, int size, int direction, boolean isEnabled)
            {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = null;
                Image arrowImg = null;
                switch (this.getDirection())
                {
                    case BasicArrowButton.NORTH:
                        gp = new GradientPaint(0, 0, new Color(56, 63, 69), getWidth(), 0, new Color(56, 63, 69));
//                        arrowImg = ImageLoader.get("north.gif");
                        break;
                    case BasicArrowButton.WEST:
                        gp = new GradientPaint(0, 0, new Color(56, 63, 69), 0, getHeight(), new Color(56, 63, 69));
//                        arrowImg = ImageLoader.get("west.gif");
                        break;
                }
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(frameColor);
                g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
                g2.drawImage(arrowImg, (getWidth() - 2) / 2 - 5, (getHeight() - 2) / 2 - 5, 13, 13, null);
            }
        };
    }
}
