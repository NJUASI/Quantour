package com.edu.nju.asi.dao.daoImpl;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.edu.nju.asi.dao.DataSourceDao;
import com.edu.nju.asi.dataHelper.DataSourceDataHelper;
import com.edu.nju.asi.dataHelper.dataHelperImpl.DataSourceDataHelperImpl;
import com.edu.nju.asi.po.DataSourceInfoPO;

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