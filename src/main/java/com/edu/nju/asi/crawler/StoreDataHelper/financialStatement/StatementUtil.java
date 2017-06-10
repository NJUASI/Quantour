package com.edu.nju.asi.crawler.StoreDataHelper.financialStatement;

import com.csvreader.CsvReader;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/6/8.
 */
public class StatementUtil {

    public static List<String> readInfo(CsvReader reader, int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            try {
                if (reader.get(i).equals("--")) {
                    result.add("0");
                } else {
                    result.add(reader.get(i));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String format(String date) {
        if (date.indexOf("/") != -1) {
            String temp[] = date.split("/");
            if (temp[1].length() == 1) {
                temp[1] = "0" + temp[1];
            }

            if (temp[2].length() == 1) {
                temp[2] = "0" + temp[1];
            }
            return temp[0] + "-" + temp[1] + "-" + temp[2];
        }
        return date;
    }

    public static boolean isTrueDate(LocalDate localDate) {
        if(localDate.isAfter(LocalDate.of(2010,1,1))) {

            if(localDate.getMonthValue()==12&&localDate.getDayOfMonth()==31){
                return true;
            }

            if(localDate.getMonthValue()==9&&localDate.getDayOfMonth()==30){
                return true;
            }

            if(localDate.getMonthValue()==6&&localDate.getDayOfMonth()==30){
                return true;
            }

            if(localDate.getMonthValue()==3&&localDate.getDayOfMonth()==31){
                return true;
            }

            return false;
        } else {
            return false;
        }
    }
}
