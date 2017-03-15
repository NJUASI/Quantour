package presentation.view.tools;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import presentation.listener.doubleDatePickerPanelListener.StartDateChangeListener;

import java.awt.*;
import java.time.LocalDate;

/**
 * Created by 61990 on 2017/3/7.
 */
public class DoubleDatePickerPanel  extends JFXPanel {

    //开始时间
    private DatePicker startDate;
    //结束时间
    private DatePicker endDate;

    //
    private static DoubleDatePickerPanel doubleDatePickerPanel;

    //
    int width;

    //
    int height;

    /**
     * 构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/8
     */
    private DoubleDatePickerPanel(){

        setLayout(null);
        width= WindowData.getInstance().getWidth();
        height= WindowData.getInstance().getHeight();
        setBackground(new Color(55,60,56));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Group root = new Group();
                Scene scene = new Scene(root, width*370/1920, 35*height/1030);
//                scene.setFill(null);
//                root.setOpacity(0.1);
//                setOpaque(false);
                setScene(scene);
                initDatePicker();
                root.getChildren().add(startDate);
                root.getChildren().add(endDate);
            }
        });
    }

    /**
     * 初始化相关部件
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */

    void initDatePicker(){

        startDate = new DatePicker();
        startDate.setValue(LocalDate.now());
        startDate.setLayoutX(0);
        startDate.setLayoutY(0);
        startDate.setMinSize(width*150/1920,35*height/1030);
        startDate.setPrefSize(width*150/1920,35*height/1030);
        startDate.setMaxSize(width*150/1920,35*height/1030);
        startDate.setEditable(false);
        endDate = new DatePicker();
        endDate.setValue(LocalDate.now());
        endDate.setLayoutX(width*220/1920);
        endDate.setLayoutY(0);
        endDate.setMinSize(width*150/1920,35*height/1030);
        endDate.setPrefSize(width*150/1920,35*height/1030);
        endDate.setMaxSize(width*150/1920,35*height/1030);
        endDate.setEditable(false);

        Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item.isBefore(startDate.getValue().plusDays(1))
                                        ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                        };
                    }
                };

        endDate.setDayCellFactory(dayCellFactory);

        startDate.valueProperty().addListener(new StartDateChangeListener());
    }

    public LocalDate getStartDate() {
        return startDate.getValue();
    }

    public LocalDate getEndDate() {
        return endDate.getValue();
    }

    public void setEndDate(LocalDate date) {
        endDate.setValue(date);
    }

    public static DoubleDatePickerPanel getInstance() {
        if(doubleDatePickerPanel == null){
            doubleDatePickerPanel = new DoubleDatePickerPanel();
        }

        return  doubleDatePickerPanel;
    }

    public void setDate(LocalDate date) {
        startDate.setValue(date);
        endDate.setValue(date);
    }
}
