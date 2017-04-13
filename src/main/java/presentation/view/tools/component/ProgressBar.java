package presentation.view.tools.component;

import javax.swing.*;

/**
 * Created by 61990 on 2017/4/12.
 */
public class ProgressBar extends Thread {
//    static ProgressBar progressBar;
    JProgressBar bar;

    public ProgressBar(JProgressBar progressBar) {
        this.bar = progressBar;

    }

    public void run() {
        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            bar.setValue(i);
        }
        bar.setIndeterminate(false);
        bar.setString("导入成功！");
    }
//    public static ProgressBar getInstance(JProgressBar bar, JComponent component){
//        if(progressBar==null){
//            progressBar=new ProgressBar(bar,component);
//        }
//        return progressBar;
//    }
}
