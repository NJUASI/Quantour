package com.edu.nju.asi.crawler.StoreDataHelper;

import com.csvreader.CsvReader;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.model.SearchID;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.utilities.enums.Market;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Byron Dong on 2017/6/5.
 */
public class StoreStockSearchHelper {

    private String areaPath = "F:\\Quant\\area.csv";

    private String industryPath = "F:\\Quant\\industry.csv";

    private String stockPath = "F:\\Quant\\stocks";

    private String filePath = "F:\\Quant\\stockData.txt";

    public void handle() {
        //清洗code,会产生stockData.txt
        deleteUnuseCode();

        //读取行业，地域
        Map<String,String> area = crawing(areaPath);
        Map<String,String> industry = crawing(industryPath);
        //读取stockSearch的基本内容
        List<StockSearch> stockSearches = getBasicInfo();

        //组合数据并填入数据库
        System.out.println("-------开始存储area和industry------------");
        HelperManager.stockSearchDataHelper.addStockSearchAll(loadingData(area,industry,stockSearches));
        System.out.println("-------存储area和industry结束------------");
    }

    private Map<String, String> crawing(String path) {
        Map<String, String> info = new HashMap<>();
        System.out.println("-------开始读取" + path + "------------");
        try {
            CsvReader reader = new CsvReader(path, ',', Charset.forName("GBK"));
            reader.readHeaders();

            while (reader.readRecord()) {
                String code = reader.get(1);
                String needInfo = reader.get(3);
                info.put(code, needInfo);
                System.out.println("读取： " + code + " " + needInfo);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-------读取" + path + "结束------------");
       return info;
    }

    private List<StockSearch> loadingData(Map<String,String> area,Map<String,String> industry,List<StockSearch> stockSearches){
        System.out.println("-------开始装填数据------------");
        for(StockSearch stockSearch:stockSearches){
            if(area.get(stockSearch.getSearchID().getCode())==null){
                stockSearch.setArea("none");
            } else{
                stockSearch.setArea(area.get(stockSearch.getSearchID().getCode()));
            }

            if(industry.get(stockSearch.getSearchID().getCode())==null){
                stockSearch.setIndustry("none");
            } else{
                stockSearch.setIndustry(industry.get(stockSearch.getSearchID().getCode()));
            }
            System.out.println("装填： "+stockSearch.getSearchID().getCode()+"--"+stockSearch.getArea()+"--"+stockSearch.getIndustry());
        }
        System.out.println("-------装填数据完成------------");
        return stockSearches;
    }

    public List<StockSearch> getBasicInfo() {
        String path = filePath;
        File file = new File(path);
        List<StockSearch> stockSearches = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String info[] = line.split(";");
                StockSearch stockSearch = new StockSearch();
                SearchID searchID = new SearchID(info[0], info[2], StoreStockSearchHelper.get(info[1]));
                stockSearch.setSearchID(searchID);
                stockSearch.setFirstLetters(info[3]);
                stockSearch.setArea("none");
                stockSearch.setIndustry("none");
                stockSearch.setBase(false);
                stockSearch.setClickAmount(0);
                stockSearches.add(stockSearch);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return stockSearches;
    }

    public List<String> readeInfo(){
        List<String> info =  new ArrayList<>();
        File file = new File("F:\\Quant\\stockSearch.txt");
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
        File file = new File(filePath);

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

    public void deleteUnuseCode(){
        File file = new File(stockPath);
        File files[] = file.listFiles();
        List<String> codes = new ArrayList<>();
        for(File file1:files){
            codes.add(file1.getName().substring(0,6));
        }
        System.out.println("----------获取code结束--------------");

        List<String> aftCodeList = new ArrayList<>();
        List<String> preCodeList = readeInfo();
        List<String> unuseCode = new ArrayList<>();
        for(String code: preCodeList){
            System.out.println("--------------判断"+code+"---------------------");
            String[] info = code.split(";");
            if(codes.indexOf(info[0])!=-1){
                aftCodeList.add(code);
                System.out.println("--------------添加："+code+"---------------------");
            } else{
                unuseCode.add(code);
            }
            System.out.println("--------------判断结束"+code+"---------------------");
        }
        writeInfo(aftCodeList);
        System.out.println("无用code"+unuseCode);
    }

    private static Market get(String num) {
        if (num.equals("0")) {
            return Market.SZ;
        } else {
            return Market.SH;
        }
    }
}
