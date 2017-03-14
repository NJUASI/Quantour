package presentation.view.tools;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.DatePicker;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;

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

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Group root = new Group();
                Scene scene = new Scene(root, width*150/1920, 35*height/1030);
                setScene(scene);
                date = new DatePicker();
                date.setMinSize(width*150/1920,35*height/1030);
                date.setPrefSize(width*150/1920,35*height/1030);
                date.setMaxSize(width*150/1920,35*height/1030);
                date.setValue(LocalDate.now());
                date.setEditable(false);
                root.getChildren().add(date);
            }
        });
    }
    public LocalDate getDate() {
       return date.getValue();
    }
}
