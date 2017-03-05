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

    @Override
    public List<StockPO> getStock(String stockCode) throws IOException {
        br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(pathPre + stockCode + pathPost)));

        List<StockPO> result = new LinkedList<StockPO>();

        String line = null;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\t");
            int year = Integer.parseInt("20"+parts[1].split("/")[2]);
            int month = Integer.parseInt(parts[1].split("/")[0]);
            int day = Integer.parseInt(parts[1].split("/")[1]);
            LocalDate thisDate = LocalDate.of(year, month, day);

            result.add(new StockPO(Integer.parseInt(parts[0]), thisDate, Double.parseDouble(parts[2]),
                    Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), parts[6],
                    Double.parseDouble(parts[7]), Integer.parseInt(parts[8]), parts[9], Market.getEnum(parts[10])));
        }
        return result;
    }
}
