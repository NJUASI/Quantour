package dataHelper.dataHelperImpl.DataSourceDataHelper;

import java.io.File;
import java.io.IOException;

/**
 * Created by cuihua on 2017/4/17.
 */
public class BaseStockHelper {

    final String fileSeparator = System.getProperty("file.separator");
    final String parent = System.getProperty("user.dir") + fileSeparator + ".attachments"  + fileSeparator + "base_stocks"+ fileSeparator;

    public boolean uploadBaseStocks() throws IOException {
        File baseStocksParent = new File(System.getProperty("user.dir") + fileSeparator + ".attachments" + fileSeparator + "base_stocks");

        // 用户电脑上已存在基准的数据，不再上传
        if (baseStocksParent.exists()) return true;

        final String sourceBaseStockFilePath = Thread.currentThread().getContextClassLoader().getResource("base_stocks.csv").getFile();
        CodeDirCreator creator = new CodeDirCreator(sourceBaseStockFilePath, true);
        creator.createDir();

        OriginalDataReader reader = new OriginalDataReader(sourceBaseStockFilePath, true);
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
