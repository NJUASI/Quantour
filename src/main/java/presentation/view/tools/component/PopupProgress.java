package presentation.view.tools.component;

import presentation.view.tools.PopFrame;

import javax.swing.*;

/**
 * Created by 61990 on 2017/4/16.
 */
public class PopupProgress extends Thread {

    JLabel label,progressBar;

    public PopupProgress(JLabel progressBar, JLabel label) {
        this.progressBar = progressBar;
        this.label=label;
    }

    public void run() {
        for (int i = 0; i <= 12; i++) {
                i=i%12;

            switch (i/3){
                case 0:
                    label.setText("正在回测.");
                    break;
                case 1:
                    label.setText("正在回测..");
                    break;
                case 2:
                    label.setText("正在回测...");
                    break;
                case 3:
                    label.setText("正在回测....");
                    break;
            }

                ImageIcon bgPicture= new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("picture/prograss/prograss"+i+".png"));
                bgPicture.setImage(bgPicture.getImage());
                progressBar.setIcon(bgPicture);

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
