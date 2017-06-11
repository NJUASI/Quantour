package com.edu.nju.asi.task.crawler;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockDataHelper;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.enums.Market;

import java.io.*;
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
        List<String> infos = fileHelper.readeInfo("F:\\Quant\\stockData.txt");
        StockDataHelper stockDataHelper = HelperManager.stockDataHelper;
        for(String info:infos){
            String[] tempInfo = info.split(";");
            List<Stock> stocks = fileHelper.modify(stockDataHelper.getStockData(tempInfo[0]));
            stockDataHelper.update(stocks);
        }

    }

    public List<Stock> modify(List<Stock> stocks){
        System.out.println("修改： "+stocks.get(0).getStockID().getCode());
        for(int i=stocks.size()-1;i>1;i--){
            stocks.get(i).setPreFrontAdjClose(stocks.get(i-1).getFrontAdjClose());
            stocks.get(i).setPreAfterAdjClose(stocks.get(i-1).getAfterAdjClose());
        }
        stocks.get(0).setPreFrontAdjClose(0);
        stocks.get(0).setPreAfterAdjClose(0);
        System.out.println("结束： "+stocks.get(0).getStockID().getCode());
        return stocks;
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
