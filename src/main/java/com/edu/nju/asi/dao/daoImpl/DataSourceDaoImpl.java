package com.edu.nju.asi.dao.daoImpl;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.edu.nju.asi.dao.DataSourceDao;
import com.edu.nju.asi.dataHelper.DataSourceInfoDataHelper;
import com.edu.nju.asi.po.DataSourceInfoPO;

import java.io.*;

/**
 * Created by cuihua on 2017/3/30.
 */
public class DataSourceDaoImpl implements DataSourceDao {

    private DataSourceInfoDataHelper helper;

    public DataSourceDaoImpl() {
//        helper = new DataSourceDataHelperImpl();
//    }

//    public boolean upload(String filePath){
////        return helper.upload(filePath);
//            return true;
//    }
//
//    public DataSourceInfoPO getMyDataSource() throws IOException {
////        return helper.getMyDataSource();
//            return null;
    }

    /**
     * @param filePath 要上传的文件路径
     * @return 上传成功
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/30
     */
    @Override
    public boolean upload(String filePath) throws IOException, PinyinException {
        return false;
    }

    /**
     * @return 用户自己上传的数据源格式信息，没有返回null
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/4/13
     */
    @Override
    public DataSourceInfoPO getMyDataSource() throws IOException {
        return null;
    }
}