package presentation.view.tools;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Callback;

import javax.swing.*;
import java.time.LocalDate;

/**
 * Created by 61990 on 2017/3/7.
 */

/**
 * 日期选择器构造器
 *
 * @param
 * @return
 * @author 61990
 * @updateTime 2017/3/6
 */
public class SingleDatePickerPanel extends JFXPanel {
    DatePicker date;
    public SingleDatePickerPanel(){
        int width= WindowData.getInstance().getWidth();
        int height= WindowData.getInstance().getHeight();
        setBorder(BorderFactory.createEmptyBorder());

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Group root = new Group();
                Scene scene = new Scene(root, width*175/1920, 37*height/1030);
                setScene(scene);
                date = new DatePicker();
                date.getStylesheets().add("css/DatePicker.css");
                date.setMinSize(width*175/1920,37*height/1030);
                date.setPrefSize(width*175/1920,37*height/1030);
                date.setMaxSize(width*175/1920,37*height/1030);
                date.setValue(LocalDate.of(2014, 4, 29));
                date.setEditable(false);
                Callback<DatePicker, DateCell> dayCellFactory2 =
                        new Callback<DatePicker, DateCell>() {
                            @Override
                            public DateCell call(final DatePicker datePicker) {
                                return new DateCell() {
                                    @Override
                                    public void updateItem(LocalDate item, boolean empty) {
                                        super.updateItem(item, empty);
                                        if (item.isBefore(LocalDate.of(2005,1,31))||item.isAfter(LocalDate.of(2014,4,29))
                                                ) {
                                            setDisable(true);
                                            setStyle("-fx-background-color: #424B54;"+
                                                    "-fx-text-fill: #1C2023");
                                        }
                                    }
                                };
                            }
                        };

                date.setDayCellFactory(dayCellFactory2);
                root.getChildren().add(date);
            }
        });
    }
    public LocalDate getDate() {
       return date.getValue();
    }
}
