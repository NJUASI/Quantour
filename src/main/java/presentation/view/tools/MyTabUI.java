package presentation.view.tools;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class MyTabUI extends BasicTabbedPaneUI {

    private static final Insets NO_INSETS = new Insets(0, 0, 0, 0);
    private ColorSet selectedColorSet;
    private ColorSet defaultColorSet;
    private ColorSet hoverColorSet;
    private boolean contentTopBorderDrawn = true;
    private Color lineColor = new Color(28, 32, 35);
    private Color dividerColor = new Color(28, 32, 35);
    private Insets contentInsets = new Insets(1, 1, 1, 1);
    private int lastRollOverTab = -1;

    protected LayoutManager createLayoutManager() {
        if (tabPane.getTabLayoutPolicy() == JTabbedPane.SCROLL_TAB_LAYOUT) {
            return super.createLayoutManager();
        } else {
            return new TabbedPaneLayout();
        }
    }

    public MyTabUI() {
        selectedColorSet = new ColorSet();
        selectedColorSet.topGradColor1 =  new Color(37, 41, 44);//选中的最上层
        selectedColorSet.topGradColor2 =  new Color(37, 41, 44);//选中的第二层
        selectedColorSet.bottomGradColor1 = new Color(37, 41, 44);//选中的第三层
        selectedColorSet.bottomGradColor2 = new Color(37, 41, 44);//选中的最下层
        defaultColorSet = new ColorSet();
        defaultColorSet.topGradColor1 = new Color(19, 22, 24);//未选的最上层
        defaultColorSet.topGradColor2 = new Color(19, 22, 24);
        defaultColorSet.bottomGradColor1 = new Color(19, 22, 24);
        defaultColorSet.bottomGradColor2 = new Color(19, 22, 24);
        hoverColorSet = new ColorSet();
        hoverColorSet.topGradColor1 = new Color(37, 41, 44);//鼠标在的时候最上层
        hoverColorSet.topGradColor2 =  new Color(37, 41, 44);
        hoverColorSet.bottomGradColor1 = new Color(37, 41, 44);
        hoverColorSet.bottomGradColor2 = new Color(37, 41, 44);
        maxTabHeight = 21;
        setContentInsets(1);
    }

    public void setContentInsets(int i) {
        contentInsets = new Insets(i, i, i, i);
    }

    // 返回当前运行的用于显示选项卡的选项卡数
    public int getTabRunCount(JTabbedPane pane) {
        return 1;
    }
    protected void installDefaults() {
        super.installDefaults();
        RollOverListener l = new RollOverListener();
        tabPane.addMouseListener(l);
        tabPane.addMouseMotionListener(l);
        tabAreaInsets = NO_INSETS; // tab色insets边框
        tabInsets = new Insets(0, 0, 50, 50);
    }

    protected Insets getContentBorderInsets(int tabPlacement) {
        return contentInsets;
    }

    protected int calculateTabHeight(int tabPlacement, int tabIndex,
                                     int fontHeight) {
        return 21;
    }

    protected int calculateTabWidth(int tabPlacement, int tabIndex,
                                    FontMetrics metrics) {
        int w = super.calculateTabWidth(tabPlacement, tabIndex, metrics);
        int wid = metrics.charWidth('M');
        w += wid * 2;
        return w;
    }

    protected int calculateMaxTabHeight(int tabPlacement) {
        return 20;
    }

    protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new Color(32, 36, 39));
        super.paintTabArea(g, tabPlacement, selectedIndex);
        if (contentTopBorderDrawn) {
            g2d.setColor(lineColor);
            g2d.drawLine(0, 20, tabPane.getWidth() - 1, 20);
        }
    }

    protected void paintTabBackground(Graphics g, int tabPlacement,int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Graphics2D g2d = (Graphics2D) g;
        ColorSet colorSet;

        Rectangle rect = rects[tabIndex];
        if (isSelected) {
            colorSet = selectedColorSet;
        } else if (getRolloverTab() == tabIndex) {
            colorSet = hoverColorSet;
        } else {
            colorSet = defaultColorSet;
        }
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int width = rect.width;
        int xpos = rect.x;
        int yPos = rect.y;
        if (tabIndex > -1) {
            width--;
            xpos++;
            yPos += 2;
        }
        g2d.setPaint(new GradientPaint(xpos, 0, colorSet.topGradColor1, xpos,
                h / 2, colorSet.topGradColor2));
        g2d.fill(this.getUpArea(xpos, yPos, width, h - 2));
        g2d.setPaint(new GradientPaint(0, h / 2, colorSet.bottomGradColor1, 0,
                h, colorSet.bottomGradColor2));
        g2d.fill(this.getDownArea(xpos, yPos, width, h - 2));
        if (contentTopBorderDrawn) {
            g2d.setColor(lineColor);
            g2d.drawLine(rect.x, 20, rect.x + rect.width - 1, 20);
        }
    }

    private Shape getUpArea(int x, int y, int w, int h) {
        Rectangle2D rec = new Rectangle2D.Float(x, y, w, h / 2 + 1);
        Area a = new Area(rec);
        RoundRectangle2D rect = new RoundRectangle2D.Float(x, y, w, h, 15, 15);
        Area b = new Area(rect);
        a.intersect(b);
        return a;
    }

    private Shape getDownArea(int x, int y, int w, int h) {
        Rectangle2D rec = new Rectangle2D.Float(x, y + h / 2, w, h / 2 + 1);
        Area a = new Area(rec);
        RoundRectangle2D rect = new RoundRectangle2D.Float(x, y, w, h, 15, 15);
        return a;
    }
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
                                  int x, int y, int w, int h, boolean isSelected) {
        Rectangle rect = getTabBounds(tabIndex, new Rectangle(x, y, w, h));
         g.setColor(dividerColor);
        Graphics2D g2 = (Graphics2D) g;
        Composite old = g2.getComposite();
        AlphaComposite comp = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, 0.0f);
        g2.setComposite(comp);
        g2.setColor(dividerColor);
        g2.drawLine(rect.x + rect.width, 0, rect.x + rect.width, 20);
        g2.setComposite(old);
    }
    protected void paintContentBorderTopEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h) {}

    protected int getTabLabelShiftY(int tabPlacement, int tabIndex, boolean isSelected) {
        return 0;
    }

    private class ColorSet {
        Color topGradColor1;
        Color topGradColor2;
        Color bottomGradColor1;
        Color bottomGradColor2;
    }

    private class RollOverListener implements MouseMotionListener,MouseListener {
        public void mouseDragged(MouseEvent e) {
        }
        public void mouseMoved(MouseEvent e) {
            checkRollOver();
        }
        public void mouseClicked(MouseEvent e) {
        }
        public void mousePressed(MouseEvent e) {
        }
        public void mouseReleased(MouseEvent e) {
        }
        public void mouseEntered(MouseEvent e) {
            checkRollOver();
        }
        public void mouseExited(MouseEvent e) {
            tabPane.repaint();
        }
        private void checkRollOver() {
            int currentRollOver = getRolloverTab();
            if (currentRollOver != lastRollOverTab) {
                lastRollOverTab = currentRollOver;
                Rectangle tabsRect = new Rectangle(0, 0, tabPane.getWidth(), 20);
                tabPane.repaint(tabsRect);
            }
        }
    }

    public class TabbedPaneLayout extends BasicTabbedPaneUI.TabbedPaneLayout {
        public TabbedPaneLayout() {
            MyTabUI.this.super();
        }
        protected void calculateTabRects(int tabPlacement, int tabCount) {
            super.calculateTabRects(tabPlacement, tabCount);
            for (int i = 0; i < rects.length; i++) {
                rects[i].x = rects[i].x + (5 * i);
            }
        }
        protected void padSelectedTab(int tabPlacement, int selectedIndex) {}
    }
}
