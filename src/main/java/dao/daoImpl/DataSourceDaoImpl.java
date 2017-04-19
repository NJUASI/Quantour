package dao.daoImpl;

import com.github.stuxuhai.jpinyin.PinyinException;
import dao.DataSourceDao;
import dataHelper.DataSourceDataHelper;
import dataHelper.dataHelperImpl.DataSourceDataHelperImpl;
import po.DataSourceInfoPO;

import java.io.*;

/**
 * Created by cuihua on 2017/3/30.
 */
public class DataSourceDaoImpl implements DataSourceDao {

    private DataSourceDataHelper helper;

    public DataSourceDaoImpl() {
        helper = new DataSourceDataHelperImpl();
    }

    @Override
    public boolean upload(String filePath) throws IOException, PinyinException {
        return helper.upload(filePath);
    }

    @Override
    public DataSourceInfoPO getMyDataSource() throws IOException {
        return helper.getMyDataSource();
    }
}