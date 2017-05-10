package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.DataSourceInfoDataHelper;
import com.edu.nju.asi.model.DataSourceInfo;

/**
 * Created by cuihua on 2017/3/30.
 */
public class DataSourceInfoDataHelperImpl implements DataSourceInfoDataHelper {


    /**
     * @param dataSourceInfo 要上传的信息
     * @return 上传成功
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean addDataSourceInfo(DataSourceInfo dataSourceInfo) {
        return false;
    }

    /**
     * @param dataSourceInfo 要修改的信息
     * @return 上传成功
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean updateDataSourceInfo(DataSourceInfo dataSourceInfo) {
        return false;
    }

    /**
     * @param userName 用户名
     * @return 上传成功
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean deleteDataSourceInfo(String userName) {
        return false;
    }

    /**
     * @param userName
     * @return 用户自己上传的数据源格式信息，没有返回null
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public DataSourceInfo getDataSourceInfo(String userName) {
        return null;
    }
}
