package presentation.view;



import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Created by 61990 on 2017/3/5.
 */
class Main {

    /**
     * main入口
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static void main(String[] args) {
        new MainPanel();

//        JFrame frame=new JFrame("date display");
//
//        JDatePickerImpl datePicker;
//
//        UtilDateModel model = new UtilDateModel();
//        model.setDate(1990, 8, 24);
//        model.setSelected(true);
//        JDatePanelImpl datePanel = new JDatePanelImpl(model);
//        datePicker = new JDatePickerImpl(datePanel, null);
//        frame.setLayout(new FlowLayout());
//        JLabel label=new JLabel("Date");
//        JButton submit=new JButton("SUBMIT");
//        frame.add(label);
//        frame.add(datePicker);
//        frame.add(submit);
//        frame.setSize(400,400);
//        frame.setVisible(true);
    }
}
