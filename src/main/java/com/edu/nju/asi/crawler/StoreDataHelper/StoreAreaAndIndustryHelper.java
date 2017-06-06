package com.edu.nju.asi.crawler.StoreDataHelper;

import com.csvreader.CsvReader;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.model.SearchID;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.utilities.enums.Market;
import com.edu.nju.asi.utilities.util.JDBCUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Byron Dong on 2017/6/5.
 */
public class StoreAreaAndIndustryHelper {

    private String areaPath = "F:\\Quant\\area.csv";

    private String industryPath = "F:\\Quant\\industry.csv";

    public void handle() {
        Map<String,String> area = crawing(areaPath);
        Map<String,String> industry = crawing(industryPath);
        List<StockSearch> stockSearches = getBasicInfo();
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
        String path = "F:\\Quant\\stockData.txt";
        File file = new File(path);
        List<StockSearch> stockSearches = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String info[] = line.split(";");
                StockSearch stockSearch = new StockSearch();
                SearchID searchID = new SearchID(info[0], info[2], StoreAreaAndIndustryHelper.get(info[1]));
                stockSearch.setSearchID(searchID);
                stockSearch.setFirstLetters(info[3]);
                stockSearch.setArea("none");
                stockSearch.setIndustry("none");
                stockSearch.setBase(false);
                stockSearches.add(stockSearch);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return stockSearches;
    }

    private static Market get(String num) {
        if (num.equals("0")) {
            return Market.SZ;
        } else {
            return Market.SH;
        }
    }
}
