package com.edu.nju.asi.task.storer;

import com.csvreader.CsvReader;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockDataHelper;
import com.edu.nju.asi.dataHelper.StockSearchDataHelper;
import com.edu.nju.asi.model.SearchID;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.utilities.LocalDateList;
import com.edu.nju.asi.utilities.enums.Market;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Byron Dong on 2017/6/5.
 */
public class StoreStockSearchHelper {

    //区域信息文件的路径
    private String areaPath;

    //行业信息文件的路径
    private String industryPath;

    //存放stock文件的路径
    private String stockPath;

    //stockData文件的路径
    private String stockDataPath;

    //stockSearch文件的路径
    private String stockSearchPath;

    //stock数据对象
    private StockDataHelper stockDataHelper;

    //stockSearch数据对象
    private StockSearchDataHelper stockSearchDataHelper;

    //爬取数据的开始日期
    private LocalDate start;

    //爬取数据的结束日期
    private LocalDate end;

    //初始化成员信息
    public StoreStockSearchHelper(String root, LocalDate start, LocalDate end) {
        if (!root.endsWith(File.separator)) {
            root = root + File.separator;
        }
        areaPath = root + "area.csv";
        industryPath = root + "industry.csv";
        stockPath = root + "stocks";
        stockDataPath = root + "stockData.txt";
        stockSearchPath = root + "stockSearch.txt";
        stockDataHelper = HelperManager.stockDataHelper;
        stockSearchDataHelper = HelperManager.stockSearchDataHelper;
        this.start = start;
        this.end = end;
    }

    /**
     * 存储新股的信息，生成爬取复权数据的文件
     *
     * @author ByronDong
     * @updateTime 2017/6/12
     */
    public void handle() {

        //读取行业，地域
        Map<String, String> area = crawing(areaPath);
        Map<String, String> industry = crawing(industryPath);
        //读取stockSearch的基本内容
        List<StockSearch> newStockSearch = getNewStockInfo();

        //组合新股数据并填入数据库
        System.out.println("-------开始存储新股------------");
        stockSearchDataHelper.addStockSearchAll(loadingData(area, industry, newStockSearch));
        System.out.println("-------存储新股结束------------");

        //为爬取复权数据进行初始化文件
        createStockData(start, end);
    }

    /**
     * 生成爬取复权数据的文件
     *
     * @param start 起始日期
     * @param end   结束日期
     * @author ByronDong
     * @updateTime 2017/6/12
     */
    private void createStockData(LocalDate start, LocalDate end) {
        List<StockSearch> stockSearches = stockSearchDataHelper.getAllStockSearchWithoutBase();
        List<String> info = new ArrayList<>();
        for (StockSearch stockSearch : stockSearches) {
            List<LocalDate> date = stockDataHelper.getFirstAndLastDay(stockSearch.getSearchID().getCode());
            System.out.println("检测："+stockSearch.getSearchID().getCode()+" "+date);
            if (!date.get(1).isBefore(start)) {
                String line = stockSearch.getSearchID().getCode() + ";" + String.valueOf(stockSearch.getSearchID().getMarket().getRepre()) +
                        ";" + stockSearch.getSearchID().getName() + ";" + stockSearch.getFirstLetters() + ";" + start.toString() + ";" +
                        end.toString();
                info.add(line);
            }
        }
        writeInfo(info);
    }

    /**
     * key值和info
     *
     * @param path 文件路径
     * @return Map<String,String> 得到的信息
     * @author ByronDong
     * @updateTime 2017/6/12
     */
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

    /**
     * 加载区域和行业信息
     *
     * @param area          区域信息
     * @param industry      行业信息
     * @param stockSearches stockSearch列表
     * @return List<StockSearch> 组合数据完成后的新股
     * @author ByronDong
     * @updateTime 2017/6/12
     */
    private List<StockSearch> loadingData(Map<String, String> area, Map<String, String> industry, List<StockSearch> stockSearches) {
        System.out.println("-------开始装填数据------------");
        for (StockSearch stockSearch : stockSearches) {
            if (area.get(stockSearch.getSearchID().getCode()) == null) {
                stockSearch.setArea("none");
            } else {
                stockSearch.setArea(area.get(stockSearch.getSearchID().getCode()));
            }

            if (industry.get(stockSearch.getSearchID().getCode()) == null) {
                stockSearch.setIndustry("none");
            } else {
                stockSearch.setIndustry(industry.get(stockSearch.getSearchID().getCode()));
            }
            System.out.println("装填： " + stockSearch.getSearchID().getCode() + "--" + stockSearch.getArea() + "--" + stockSearch.getIndustry());
        }
        System.out.println("-------装填数据完成------------");
        return stockSearches;
    }

    /**
     * 获取新股
     *
     * @return 新上市的新股
     * @author ByronDong
     * @updateTime 2017/6/12
     */
    private List<StockSearch> getNewStockInfo() {
        List<StockSearch> newCodeInfos = clearUnuseCode();
        List<StockSearch> oldCodeInfos = stockSearchDataHelper.getAllStockSearch();
        List<StockSearch> stockSearches = new ArrayList<>();
        for (StockSearch stockSearch : newCodeInfos) {
            if (oldCodeInfos.indexOf(stockSearch) == -1) {
                stockSearches.add(stockSearch);
            }
        }
        return stockSearches;
    }

    /**
     * 清洗无用股票
     *
     * @return List<StockSearch> 清洗后的股票
     * @author ByronDong
     * @updateTime 2017/6/12
     */
    private List<StockSearch> clearUnuseCode() {
        File file = new File(stockPath);
        File files[] = file.listFiles();
        List<String> codes = new ArrayList<>();
        for (File file1 : files) {
            codes.add(file1.getName().substring(0, 6));
        }
        System.out.println("----------获取code结束--------------");

        List<StockSearch> aftCodeList = new ArrayList<>();
        List<String> preCodeList = readeInfo();
        List<String> unuseCode = new ArrayList<>();
        for (String code : preCodeList) {
            System.out.println("--------------判断" + code + "---------------------");
            String[] info = code.split(";");
            if (codes.indexOf(info[0]) != -1) {
                StockSearch stockSearch = new StockSearch();
                SearchID searchID = new SearchID(info[0], info[2], StoreStockSearchHelper.get(info[1]));
                stockSearch.setSearchID(searchID);
                stockSearch.setFirstLetters(info[3]);
                stockSearch.setArea("none");
                stockSearch.setIndustry("none");
                stockSearch.setBase(false);
                stockSearch.setClickAmount(0);
                aftCodeList.add(stockSearch);
                System.out.println("--------------添加：" + code + "---------------------");
            } else {
                unuseCode.add(code);
            }
            System.out.println("--------------判断结束" + code + "---------------------");
        }
        System.out.println("无用code" + unuseCode);
        return aftCodeList;
    }

    /**
     * 从stockSearch.txt中读出指定信息
     *
     * @return List<String> 需要读出的信息
     * @author ByronDong
     * @updateTime 2017/6/12
     */
    private List<String> readeInfo() {
        List<String> info = new ArrayList<>();
        File file = new File(stockSearchPath);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
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

    /**
     * 向stockData.txt中写入指定信息
     *
     * @param info 需要写入的信息
     * @author ByronDong
     * @updateTime 2017/6/12
     */
    private void writeInfo(List<String> info) {
        File file = new File(stockDataPath);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for (String line : info) {
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

    /**
     * 获取Market
     *
     * @param num 市场标记
     * @return Market enum类型
     * @author ByronDong
     * @updateTime 2017/6/12
     */
    private static Market get(String num) {
        if (num.equals("0")) {
            return Market.SZ;
        } else {
            return Market.SH;
        }
    }
}
