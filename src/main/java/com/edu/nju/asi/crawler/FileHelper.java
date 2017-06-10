package com.edu.nju.asi.crawler;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockDataHelper;
import com.edu.nju.asi.model.SearchID;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.utilities.enums.Market;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/6/3.
 */
public class FileHelper {

    public static void main(String[] args) {
//        StockDataHelper stockDataHelper = HelperManager.stockDataHelper;
//        List<String> tempInfo = null;
//        List<String> info = new ArrayList<>();
//        FileHelper main = new FileHelper();
//        tempInfo = main.readeInfo();
//        for(String line: tempInfo){
//            String code = line.split(";")[0];
//            List<LocalDate> date = stockDataHelper.getFirstAndLastDay(code);
//            System.out.println(code);
//            if(date==null){
//                continue;
//            }
//            System.out.println(date);
//            System.out.println("日期数： "+date.size());
//            line = line+";"+date.get(0).toString()+";"+date.get(1).toString();
//            info.add(line);
//            System.out.println(line);
//        }
//
//        main.writeInfo(info);
        FileHelper fileHelper = new FileHelper();
        List<String> allInfo  = fileHelper.readeInfo("F:\\Quant\\stockData.txt");
        List<String> hadInfo = fileHelper.readeInfo("F:\\Quant\\tempStockData.txt");
        List<String> needInfo = new ArrayList<>();
        for(String info:allInfo){
            String[] infos = info.split(";");
            if(hadInfo.indexOf(info)==-1){
                if(LocalDate.parse(infos[5]).isAfter(LocalDate.of(2017,6,2))){
                    if(!LocalDate.parse(infos[4]).isAfter(LocalDate.of(2017,6,3))){
                        infos[4]="2017-06-03";
                    }
                    needInfo.add(infos[0]+";"+infos[1]+";"+infos[2]+";"+infos[3]+";"+infos[4]+";"+infos[5]);
                }
            }
        }
        fileHelper.writeInfo(needInfo,"F:\\Quant\\needStockData.txt");
    }

    public List<String> readeInfo(String path){
        List<String> info =  new ArrayList<>();
        File file = new File(path);
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

    public void writeInfo(List<String> info,String path){
        File file = new File(path);

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

//    public void deleteUnuseCode(){
//        File file = new File("F:\\Quant\\stocks");
//        File files[] = file.listFiles();
//        List<String> codes = new ArrayList<>();
//        for(File file1:files){
//            codes.add(file1.getName().substring(0,6));
//        }
//        System.out.println("----------获取code结束--------------");
//
//        List<String> aftCodeList = new ArrayList<>();
//        List<String> preCodeList = readeInfo();
//        List<String> unuseCode = new ArrayList<>();
//        for(String code: preCodeList){
//            System.out.println("--------------判断"+code+"---------------------");
//            String[] info = code.split(";");
//            if(codes.indexOf(info[0])!=-1){
//                aftCodeList.add(code);
//                System.out.println("--------------添加："+code+"---------------------");
//            } else{
//                unuseCode.add(code);
//            }
//            System.out.println("--------------判断结束"+code+"---------------------");
//        }
//        writeInfo(aftCodeList);
//        System.out.println("无用code"+unuseCode);
//    }

    private Market getMarket(int num){
        if(num==0){
            return Market.SZ;
        } else{
            return Market.SH;
        }
    }

}
