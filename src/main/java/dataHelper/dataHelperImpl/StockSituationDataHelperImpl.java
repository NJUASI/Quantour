package dataHelper.dataHelperImpl;

import dataHelper.StockSituationDataHelper;
import po.StockSituationPO;

import java.io.*;
import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/3/5.
 */
public class StockSituationDataHelperImpl implements StockSituationDataHelper {

    private final String pathPre = "stock_situation/";
    private final String pathPost = ".txt";

    private BufferedReader br;

    /**
     * 获取指定日期所有数据
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param date  指定日期
     * @return StockSituationPO 指定市场温度计数据
     */
    @Override
    public StockSituationPO getStockSituation(LocalDate date) throws IOException {
        br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().
                getResourceAsStream(pathPre + date.getYear() + "/" + date.toString() + pathPost)));
        String line = br.readLine();
        br.close();
        StockSituationPO result = new StockSituationPO(line.split("\t"));

        return result;
    }

}
