package test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cuihua on 2017/3/26.
 */
public class MainTest {

    public static void main(String[] args) {
        MainTest obj = new MainTest();

        Date before = new Date();

        CsvFileUtil util = new CsvFileUtil();
        util.handle("C:\\Users\\61990\\Desktop\\股票历史数据ALL.csv");


        Date after = new Date();

        obj.printDifference(before, after);
    }


    public void printDifference(Date startDate, Date endDate){

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays,
                elapsedHours, elapsedMinutes, elapsedSeconds);

    }
}
