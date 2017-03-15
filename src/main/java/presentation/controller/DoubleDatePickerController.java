package presentation.controller;

import presentation.view.tools.DoubleDatePickerPanel;

import java.time.LocalDate;

/**
 * Created by Harvey on 2017/3/15.
 */
public class DoubleDatePickerController {

    private static DoubleDatePickerController doubleDatePickerController = new DoubleDatePickerController();

    private DoubleDatePickerPanel doubleDatePickerPanel;

    private DoubleDatePickerController(){
        doubleDatePickerPanel = DoubleDatePickerPanel.getInstance();
    }

    public static DoubleDatePickerController getInstance(){
        return doubleDatePickerController;
    }

    public void startDateChanged(LocalDate newValue) {
        //将结束日期加1
        doubleDatePickerPanel.setEndDate(newValue.plusDays(1));
    }

}
