package presentation.view.tools;
import presentation.controller.StocksTableController;
import presentation.controller.ViewSwitchController;
import presentation.view.frame.MainFrame;
import presentation.view.panel.KStringPanel;
import presentation.view.panel.NavigationBar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyMouseListener extends MouseAdapter {
    private static boolean flag = false;
    // 用来判断是否已经执行双击事件
    private static int clickNum = 0;
    // 用来判断是否该执行双击事件
    @Override
    public void mouseClicked(MouseEvent e) {
        // final MouseEvent me = e;
        MyMouseListener.flag = false;
        System.out.println(clickNum);
        if (MyMouseListener.clickNum == 1) {// 1时执行双击事件
            KStringPanel.getInstance().count=1;
            ViewSwitchController.getInstance().viewSwitch("kStringPanel");
            StocksTableController.getInstance().checkDetail();
            KStringPanel.getInstance().count=0;
            NavigationBar.getInstance().whileClicked(2);

            MyMouseListener.clickNum = 0;
            MyMouseListener.flag = true;
            return;
        }
        // 定义定时器
        Timer timer = new Timer();
        // 定时器开始执行，延时0.2秒后确定是否执行单击事件
        timer.schedule(new TimerTask() {
            private int n = 0;
            // 记录定时器执行次数
            @Override
            public void run() {
                if (MyMouseListener.flag) {
                    MyMouseListener.clickNum = 0;
                    this.cancel();
                    return;
                }
                if (n == 1) {

                    MyMouseListener.flag = true;
                    MyMouseListener.clickNum = 0;
                    n = 0;
                    this.cancel();
                    return;
                }
                clickNum++;
                n++;
            }
        }, new Date(), 200);
//上边的意思是，单击第一次会运行一次run方法clickNum 会加1，然后0.2秒后再执行Run方法 //如果在这0.2秒中间用户又单击了事件，那就会运行开头的双击事件
    }
}