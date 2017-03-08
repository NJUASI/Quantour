package presentation.view;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.time.LocalDate;

/**
 * Created by 61990 on 2017/3/7.
 */
public class DoubleDatePickerPanel  extends JFXPanel {
    DatePicker startDate;
    DatePicker endDate;
    int width;
    int height;
    public DoubleDatePickerPanel(){

        setLayout(null);
        width= WindowData.getInstance().getWidth();
        height= WindowData.getInstance().getHeight();
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

    void initDatePicker(){

        startDate = new DatePicker();
        startDate.setValue(LocalDate.now());
        startDate.setLayoutX(0);
        startDate.setLayoutY(0);
        startDate.setMinSize(width*150/1920,35*height/1030);
        startDate.setPrefSize(width*150/1920,35*height/1030);
        startDate.setMaxSize(width*150/1920,35*height/1030);

        endDate = new DatePicker();
        endDate.setValue(LocalDate.now());
        endDate.setLayoutX(width*220/1920);
        endDate.setLayoutY(0);
        endDate.setMinSize(width*150/1920,35*height/1030);
        endDate.setPrefSize(width*150/1920,35*height/1030);
        endDate.setMaxSize(width*150/1920,35*height/1030);

        endDate.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!startDate.getValue().isBefore(endDate.getValue())){
                    endDate.setValue(startDate.getValue());
                }
            }
        });

        Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item.isBefore(startDate.getValue())
                                        ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                        };
                    }
                };
        endDate.setDayCellFactory(dayCellFactory);
    }
    public LocalDate getStartDate() {
        return startDate.getValue();
    }
    public LocalDate getEndDate() {
        return endDate.getValue();
    }
}
