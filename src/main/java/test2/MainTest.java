package test2;

import java.util.Date;

/**
 * Created by cuihua on 2017/3/28.
 */
public class MainTest {

    public static void main(String[] args) {

        test.MainTest obj = new test.MainTest();
        Date before = new Date();

        CsvFileUtil util = new CsvFileUtil("E:\\软工3\\数据\\量化交易\\股票历史数据ALL.csv");
        util.handle();

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
