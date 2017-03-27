package dataHelper.dataHelperImpl;

import dataHelper.StockSituationDataHelper;
import po.StockSituationPO;
import utilities.exceptions.NoDataWithinException;
import utilities.exceptions.NoSituationDataException;

import java.io.*;
import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/3/5.
 * Last updated by cuihua
 * Update time 2017/3/9
 *
 * 具体实现其功能
 */
public class StockSituationDataHelperImpl implements StockSituationDataHelper {

    private final String pathPre = "stock_situation/";
    private final String pathPost = ".txt";

    private BufferedReader br;

    /**
     * 获取指定日期所有数据
     *
     * @author Byron Dong
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param date  指定日期
     * @return 指定日期的市场温度计数据
     */
    @Override
    public StockSituationPO getStockSituation(LocalDate date) throws NoSituationDataException {

        String line = null;
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().
                    getResourceAsStream(pathPre + date.getYear() + "/" + date.toString() + pathPost);
            if(inputStream == null){
                throw new NoSituationDataException();
            }
            else{
                br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                line = br.readLine();
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StockSituationPO result = new StockSituationPO(line.split("\t"));

        return result;
    }

}
