//package presentation.view.tools;
//
///**
// * Created by 61990 on 2017/4/16.
// */
//        import java.awt.Color;
//        import java.awt.FlowLayout;
//        import java.awt.Graphics;
//        import java.awt.event.ActionEvent;
//        import java.awt.event.ActionListener;
//        import java.awt.event.MouseAdapter;
//        import java.awt.event.MouseEvent;
//        import javax.swing.BorderFactory;
//        import javax.swing.JButton;
//        import javax.swing.JFrame;
//        import javax.swing.JPanel;
//        import javax.swing.Timer;
//
//
///**
// *  演示如何使用Timer 这个类来完成Swing的动画效果。
// * @author zcl
// */
//public class TestWindow extends JPanel {
//
//
//    private final int panelWidth = 300, panelHeight = 200;
//    private final int DELAY = 10, IMAGE_SIZE = 35;
//    private Timer timer;
//    private JButton topButton;
//    private int x, y, moveX, moveY;
//
//
//    /**
//     * 面板构造函数，初始化面板。包括Timer 的场景。
//     */
//    public TestWindow() {
//        timer = new Timer(DELAY, new ReboundListener());
//        this.setLayout(new FlowLayout(FlowLayout.LEFT));
//        topButton = new JButton("top");
////        topButton.setPreferredSize(new Dimension(20, 20));
//
//
//        x = 0;
//        y = 40;
//        moveX = moveY = 3;
//        setBackground(Color.WHITE);
//        this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
//        JButton button  = new JButton("click");
//        button.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//            }
//        });
//        this.add(button);
//    }
//
//    }
//    /**
//     *  绘出图像在面板中的位置。
//     * @param page
//     */
//    @Override
//    public void paintComponent(Graphics page) {
//        super.paintComponent(page);
//        topButton.paint(page);
//    }
//
//
//    //Swing 动画，不断的更新图像的位置，已达到动画的效果。
//    private class ReboundListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            x += moveX;
//            y += moveY;
//            topButton.setBounds(0, 0, 100, y);
//            if (x <= 0 || x >= panelWidth - IMAGE_SIZE) {
//                moveX = moveX * (-1);
//            }
//            if (y <= 0 || y >= panelHeight - IMAGE_SIZE) {
//                moveY = moveY * (-1);
////              moveY =0;
//            }
//            repaint();
//        }
//    }
//
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("JUST SOSO");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(new TestWindow());
//        frame.setSize(300, 300);
//        frame.setLocation(400, 400);
//        frame.setVisible(true);
//    }
//}