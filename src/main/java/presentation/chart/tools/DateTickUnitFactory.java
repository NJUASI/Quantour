package presentation.chart.tools;

import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;

import java.text.SimpleDateFormat;

/**
 * Created by Byron Dong on 2017/3/30.
 */
public class DateTickUnitFactory {

    /**
     * 根据数值num获取DateTickUnit,一旦传入没有以下值的tag，则返回null
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/30
     * @param  num 标志
     * @return  DateTickUnit 日期格式
     */
    public DateTickUnit createDateTickUnit(int num){

        DateTickUnit dateTickUnit = null;

        if (num < 10) {
            dateTickUnit = new DateTickUnit(DateTickUnitType.DAY, 1, new SimpleDateFormat("yyyy-MM-dd")); // 第二个参数是时间轴间距
        } else if (num < 20) {
            dateTickUnit = new DateTickUnit(DateTickUnitType.DAY, 3, new SimpleDateFormat("yyyy-MM-dd"));// 第二个参数是时间轴间距
        } else if (num < 40){
            dateTickUnit = new DateTickUnit(DateTickUnitType.DAY, 8, new SimpleDateFormat("yyyy-MM-dd")); // 第二个参数是时间轴间距
        }else if (num < 60){
            dateTickUnit = new DateTickUnit(DateTickUnitType.DAY, 17, new SimpleDateFormat("yyyy-MM-dd")); // 第二个参数是时间轴间距
        }else if (num < 200){
            dateTickUnit = new DateTickUnit(DateTickUnitType.DAY, 52, new SimpleDateFormat("yyyy-MM")); // 第二个参数是时间轴间距
        }else{
            dateTickUnit = new DateTickUnit(DateTickUnitType.MONTH, 6, new SimpleDateFormat("yyyy")); // 第二个参数是时间轴间距
        }

        return dateTickUnit;
    }
}
