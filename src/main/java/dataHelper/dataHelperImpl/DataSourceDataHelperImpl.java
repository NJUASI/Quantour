package dataHelper.dataHelperImpl;

import dataHelper.DataSourceDataHelper;
import dataHelper.dataHelperImpl.DataSourceDataHelper.*;
import po.DataSourceInfoPO;

import java.io.*;
import java.sql.Timestamp;

/**
 * Created by cuihua on 2017/3/30.
 */
public class DataSourceDataHelperImpl implements DataSourceDataHelper {

    @Override
    public boolean upload(String filePath) throws IOException {
        OldDirRemover remover = new OldDirRemover();
        remover.myDelete();

        CodeDirCreator creator = new CodeDirCreator(filePath, false);
        creator.createDir();

        OriginalDataReader reader = new OriginalDataReader(filePath, false);
        reader.handle();

        DuplicationAdder adder = new DuplicationAdder(false);
        adder.handle();

        DateFilesCreator creator2 = new DateFilesCreator(false);
        creator2.handle();

        SituationCreator creator3 = new SituationCreator();
        creator3.handle();

        StockNameToCodeCreator creator4 = new StockNameToCodeCreator(false);
        creator4.handle();

        BaseStockHelper helper = new BaseStockHelper();
        helper.uploadBaseStocks();

        return true;
    }

    @Override
    public DataSourceInfoPO getMyDataSource() throws IOException {
        final String separator = System.getProperty("file.separator");
        final String filePath = System.getProperty("user.dir") + separator + ".attachments" + separator + "stocks" + separator + "info.txt";
        File thisFile = new File(filePath);
        if (!thisFile.exists()) return null;

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
        String[] result = br.readLine().split("\t");
        return new DataSourceInfoPO(result[0], new Timestamp(Long.parseLong(result[1])), result[2]);
    }
}
