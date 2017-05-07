package com.edu.nju.asi.dataHelper.dataHelperImpl.DataSourceDataHelper;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.edu.nju.asi.utilities.IDReserve;

import java.io.File;
import java.io.IOException;

/**
 * Created by cuihua on 2017/4/17.
 */
public class BaseStockHelper {

    final String fileSeparator = System.getProperty("file.separator");
    final String userID = IDReserve.getInstance().getUserID();

    public boolean uploadBaseStocks() throws IOException, PinyinException {
        File baseStocksParent = new File(System.getProperty("user.dir") + fileSeparator + ".attachments" + fileSeparator + userID + fileSeparator + "base_stocks");

        // 用户电脑上已存在基准的数据，不再上传
        if (baseStocksParent.exists()) {
            return true;
        }

        CodeDirCreator creator = new CodeDirCreator("base_stocks.csv", true);
        creator.createDir();

        OriginalDataReader reader = new OriginalDataReader("base_stocks.csv", true);
        reader.handle();

        DuplicationAdder adder = new DuplicationAdder(true);
        adder.handle();

        DateFilesCreator creator2 = new DateFilesCreator(true);
        creator2.handle();

        StockNameToCodeCreator creator4 = new StockNameToCodeCreator(true);
        creator4.handle();

        return true;
    }

}
