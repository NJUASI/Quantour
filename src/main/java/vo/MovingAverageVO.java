package vo;

import java.time.LocalDate;

/**
 * Created by Harvey on 2017/3/9.
 */
public class MovingAverageVO {
    //对应日期
    public LocalDate date;

    //当天平均指数
    public double average;

    public MovingAverageVO(LocalDate date, double average) {
        this.date = date;
        this.average = average;
    }

    public MovingAverageVO() {

    }
}
