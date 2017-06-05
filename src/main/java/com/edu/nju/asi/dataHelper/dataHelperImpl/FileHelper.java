package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockDataHelper;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/6/3.
 */
public class FileHelper {

    public static void main(String[] args) {
        StockDataHelper stockDataHelper = HelperManager.stockDataHelper;
        List<String> tempInfo = null;
        List<String> info = new ArrayList<>();
        FileHelper main = new FileHelper();
        tempInfo = main.readeInfo();
        for(String line: tempInfo){
            List<LocalDate> date = stockDataHelper.getFirstAndLastDay(line.substring(0,6));
            if(date==null){
                continue;
            }
            System.out.println(date);
            System.out.println("日期数： "+date.size());
            line = line+";"+date.get(0).toString()+";"+date.get(1).toString();
            info.add(line);
            System.out.println(line);
        }

        main.writeInfo(info);
    }

    public List<String> readeInfo(){
        List<String> info =  new ArrayList<>();
        File file = new File("F:\\QuantNew\\stockSearch.txt");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while((line = bufferedReader.readLine())!=null){
                info.add(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }

    public void writeInfo(List<String> info){
        File file = new File("F:\\stockData.txt");

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for(String line: info){
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
