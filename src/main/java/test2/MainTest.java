package test2;

import java.util.Date;

/**
 * Created by cuihua on 2017/3/28.
 */
public class MainTest {

    public static void main(String[] args) {

        Date before = new Date();

        CsvFileUtil util = new CsvFileUtil("/Users/cuihua/Documents/大学学习/大二/软件工程与计算三/数据/股票历史数据ALL.csv");
        util.handle();

        Date after = new Date();
        System.out.println("TOTAL:");
        MainTest.printDifference(before, after);

    }

    public static void printDifference(Date startDate, Date endDate){

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
        System.out.println();

    }

}
