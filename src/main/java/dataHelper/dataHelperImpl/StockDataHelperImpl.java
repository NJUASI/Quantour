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
 */
public class StockDataHelperImpl implements StockDataHelper {

    private final String pathPre = "stock_records/";
    private final String pathPost = ".txt";

    private BufferedReader br;


    /**
     * 获取指定股票所有数据
     *
     * @author cuihua
     * @updateTime 2017/3/5
     * @param stockCode  指定股票代码
     * @return List<StockPO> 指定股票所有数据
     */
    @Override
    public List<StockPO> getStockRecords(String stockCode) throws IOException {
        br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(pathPre + stockCode + pathPost)));

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
                    Double.parseDouble(parts[7]), Integer.parseInt(parts[8]), parts[9], Market.getEnum(parts[10])));
        }
        return result;
    }

    /**
     * 获取指定日期的所有股票数据
     *
     * @author cuihua
     * @updateTime 2017/3/6
     * @param date  指定日期
     * @return List<StockPO> 指定日期的所有股票数据
     *
     * TODO cuihua 最好将日期与市场温度计结合
     */
    @Override
    public List<StockPO> getStockRecords(LocalDate date) throws IOException {
        return null;
    }

    @Override
    public List<StockPO> getStock(String code) {
        return null;
    }


}
