package presentation.view;

/**
 * Created by 61990 on 2017/3/6.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import com.qt.datapicker.DatePicker;


public class DatePickerPanel extends TempletPanel {
    ObservingTextField jtf;
    /**
     * 加载日期选择器组件
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    private static final long serialVersionUID = 1L;

    public void launch() {
        setLayout(null);
        this.setBounds(adaptScreen(0,0,180,35));
        ObservingTextField jtf = new ObservingTextField();
        jtf.setEditable(false);
        JButton btn = new JButton("日历");
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                DatePicker dp = new DatePicker(jtf, Locale.CHINESE);
                Date selectedDate = dp.parseDate(jtf.getText());
                dp.setSelectedDate(selectedDate);
                dp.start(jtf);
            }
        });
        jtf.setBounds(adaptScreen(0, 0, 110, 35));
        btn.setBounds(adaptScreen(120, 0, 60, 35));
        this.add(jtf);
        this.add(btn);
        this.setVisible(true);
    }

    /**
     * 日期选择器构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/6
     */
    public DatePickerPanel() {
        launch();
    }

    /**
     * 获取日期
     *
     * @param
     * @return 此时的field的date
     * * @author 61990
     * @updateTime 2017/3/6
     */
    public Date getDate() {
        String s2[] = jtf.getText().split("-");
        int year = Integer.parseInt(s2[0]);
        if (year > 50) {
            year += 1900;
        } else {
            year += 2000;
        }
        int month = Integer.parseInt(s2[1]);
        int day = Integer.parseInt(s2[2]);
        return new Date(year, month, day);
    }
}

class ObservingTextField extends JTextField implements Observer {

    /**
     * author 61990 date 2017/3/6
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void update(Observable o, java.lang.Object arg) {
        // TODO Auto-generated method stub
        Calendar calendar = (Calendar) arg;
        DatePicker dp = (DatePicker) o;
        String s2[] = dp.formatDate(calendar).split("-");
        int year = Integer.parseInt(s2[0]);
        if (year > 50) {
            year += 1900;
        } else {
            year += 2000;
        }
        int month = Integer.parseInt(s2[1]);
        int day = Integer.parseInt(s2[2]);
        setText(year + "-" + month + "-" + day);
    }

}