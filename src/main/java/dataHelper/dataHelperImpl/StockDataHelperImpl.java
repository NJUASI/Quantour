package dataHelper.dataHelperImpl;

import dataHelper.StockDataHelper;
import po.StockPO;
import utilities.enums.Market;

import java.io.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/3/5.
 * Last updated by cuihua
 * Update time 2017/3/18
 *
 * 对getFirstDay接口理解错误，重新实现
 */
public class StockDataHelperImpl implements StockDataHelper {

    private final static String stockRecordByCodePathPre = "stock_records_by_code/";
    private final static String stockRecordByDatePathPre = "stock_records_by_date/";
    private final static String stockRecordPathPost = ".txt";

    private BufferedReader br;


    /**
     * 获取指定股票所有数据
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param stockCode  指定股票代码
     * @return 指定股票所有数据
     * @throws IOException IO
     */
    @Override
    public List<StockPO> getStockRecords(String stockCode) throws IOException {
        return getStockByPath(stockRecordByCodePathPre + backToSimplifiedStockCode(stockCode) + stockRecordPathPost);
    }

    /**
     * 获取指定日期的所有股票数据
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param date  指定日期
     * @return 指定日期的所有股票数据
     * @throws IOException IO
     */
    @Override
    public List<StockPO> getStockRecords(LocalDate date) throws IOException {
        return getStockByPath(stockRecordByDatePathPre + date.getYear() + "/" + date.toString() + stockRecordPathPost);
    }

    /**
     * 获取数据库中股票存在记录的第一天
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param stockCode 股票代码
     * @return 数据库中股票存在记录的第一天
     * @throws IOException IO
     */
    @Override
    public LocalDate getFirstDay(String stockCode) throws IOException {
        br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(stockRecordByCodePathPre + stockCode + stockRecordPathPost)));

        List<StockPO> allResult = getStockRecords(stockCode);
        return allResult.get(allResult.size()-1).getDate();
    }

    /**
     * 根据路径读取stock_records_by_code/date中的数据
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param path 要读取的数据源
     * @return 根据俄参数路径读取到的所有股票数据
     * @throws IOException IO
     */
    private List<StockPO> getStockByPath(String path) throws IOException {
        br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().
                getResourceAsStream(path)));

        List<StockPO> result = new LinkedList<StockPO>();

        String line = null;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\t");
            int year = Integer.parseInt(parts[1].split("-")[0]);
            int month = Integer.parseInt(parts[1].split("-")[1]);
            int day = Integer.parseInt(parts[1].split("-")[2]);
            LocalDate thisDate = LocalDate.of(year, month, day);

            result.add(new StockPO(Integer.parseInt(parts[0]), thisDate, Double.parseDouble(parts[2]),
                    Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), parts[6],
                    Double.parseDouble(parts[7]), parts[8], parts[9], Market.getEnum(parts[10]),
                    Double.parseDouble(parts[11]), Double.parseDouble(parts[12])));
        }
        return result;
    }

    private static String backToSimplifiedStockCode(String stockCode) {
        char[] parts = stockCode.toCharArray();
        int i = 0;
        for (; i < 6; i++) {
            if (parts[i] != '0') break;
        }
        return stockCode.substring(i);
    }
}
