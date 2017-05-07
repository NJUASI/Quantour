package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.StockSituationDataHelper;
import com.edu.nju.asi.po.StockSituationPO;
import com.edu.nju.asi.utilities.DataSourceStateKeeper;
import com.edu.nju.asi.utilities.FilePathStandardizer;
import com.edu.nju.asi.utilities.IDReserve;
import com.edu.nju.asi.utilities.enums.DataSourceState;
import com.edu.nju.asi.utilities.exceptions.NoSituationDataException;

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

    private static final String separator = System.getProperty("file.separator");

    private static final String pathPre = "stocks" + separator + "stock_situation" + separator;
    private static final String pathPost = ".txt";

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
        String path = pathPre + date.getYear() + separator + date.toString() + pathPost;

        String line = null;
        try {
            if (DataSourceStateKeeper.getInstance().getState() == DataSourceState.ORIGINAL) {
                path = FilePathStandardizer.standardize(path);
                InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
                if (inputStream == null) {
                    throw new NoSituationDataException();
                } else {
                    br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                }
            } else if (DataSourceStateKeeper.getInstance().getState() == DataSourceState.USER){
                br = new BufferedReader(new InputStreamReader(new FileInputStream(
                        System.getProperty("user.dir") + separator + ".attachments" + separator +
                                IDReserve.getInstance().getUserID() + separator + path), "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new NoSituationDataException();
        }
        try {
            line = br.readLine();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StockSituationPO result = new StockSituationPO(line.split("\t"));

        return result;
    }

}
