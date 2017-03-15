package presentation.listener.doubleDatePickerPanelListener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import presentation.controller.DoubleDatePickerController;

import java.time.LocalDate;

/**
 * Created by Harvey on 2017/3/15.
 */
public class StartDateChangeListener implements ChangeListener<LocalDate> {

    @Override
    public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
        DoubleDatePickerController.getInstance().startDateChanged(newValue);
    }
}
