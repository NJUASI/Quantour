package presentation.view.tools;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Callback;


import javax.swing.*;
import java.awt.Color;
import java.time.LocalDate;

/**
 * Created by 61990 on 2017/3/7.
 */
public class DoubleDatePickerPanel  extends JFXPanel {

    //开始时间
    public DatePicker startDate;
    //结束时间
    public DatePicker endDate;
    ImageView image;
    Label label1,label2;
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
    public DoubleDatePickerPanel(){

        setLayout(null);
        width= WindowData.getInstance().getWidth();
        height= WindowData.getInstance().getHeight();
        setBackground(new Color(55,60,56));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Group root = new Group();
                Scene scene = new Scene(root, width*385/1920, 37*height/1030);
//                scene.setFill(null);
//                root.setOpacity(0.1);
//                setOpaque(false);

                setScene(scene);
                initDatePicker();
                root.getChildren().add(image);
                root.getChildren().add(label1);
                root.getChildren().add(label2);
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
        startDate.setValue(LocalDate.of(2014, 4, 29));
        startDate.setLayoutX(80*width/1920);
        startDate.setLayoutY(0);
        startDate.setMinSize(width*170/1920,37*height/1030);
        startDate.setPrefSize(width*170/1920,37*height/1030);
        startDate.setMaxSize(width*170/1920,37*height/1030);
        startDate.setEditable(false);
        startDate.getStylesheets().add("css/DatePicker.css");

        endDate = new DatePicker();
        endDate.setValue(LocalDate.of(2014, 4, 29));
        endDate.setLayoutX(width*350/1920);
        endDate.setLayoutY(0);
        endDate.setMinSize(width*170/1920,37*height/1030);
        endDate.setPrefSize(width*170/1920,37*height/1030);
        endDate.setMaxSize(width*170/1920,37*height/1030);
        endDate.setEditable(false);
        endDate.getStylesheets().add("css/DatePicker.css");

        label1 =new Label("开始日期");
        label1.setFont(new Font("微软雅黑",16*width/1920));
        label1.setTextFill(javafx.scene.paint.Color.rgb(201,208,214));
        label1.setPrefSize(80*width/1920,35*height/1030);
        label2 =new Label("结束日期");
        label2.setFont(new Font("微软雅黑",16*width/1920));
        label2.setTextFill(javafx.scene.paint.Color.rgb(201,208,214));
        label2.setPrefSize(80*width/1920,35*height/1030);
        label1.setLayoutX(0);
        label1.setLayoutY(0);
        label2.setLayoutX(270*width/1920);
        label2.setLayoutY(0);


        image=new ImageView();


        image.setImage(new Image(getClass().getClassLoader().getResourceAsStream("picture/bgColor.png")));

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

        startDate.setDayCellFactory(dayCellFactory2);

        Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item.isBefore(startDate.getValue().plusDays(1))||item.isAfter(LocalDate.of(2014,4,29))
                                        ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #424B54;"+
                                            "-fx-text-fill: #1C2023");
                                }
                            }
                        };
                    }
                };

        endDate.setDayCellFactory(dayCellFactory);

        setBorder(BorderFactory.createEmptyBorder());

//        startDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
//            @Override
//            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
//                setEndDate(newValue.plusDays(1));
//            }
//        });
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

    public void setDate(LocalDate date,LocalDate date2) {
        startDate.setValue(date);
        endDate.setValue(date2);
    }
    public void setDate(LocalDate date) {
        startDate.setValue(date);
        endDate.setValue(date);
    }
}
