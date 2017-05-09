package com.edu.nju.asi.dao;

import com.edu.nju.asi.model.DataSourceInfo;

/**
 * Created by cuihua on 2017/3/30.
 *
 * 更换数据源的接口
 */
public interface DataSourceInfoDao {

    /**
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @param dataSourceInfo 要上传的信息
     * @return 上传成功
     */
    boolean addDataSourceInfo(DataSourceInfo dataSourceInfo);

    /**
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @param dataSourceInfo 要修改的信息
     * @return 上传成功
     */
    boolean updateDataSourceInfo(DataSourceInfo dataSourceInfo);

    /**
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @param userName 用户名
     * @return 上传成功
     */
    boolean deleteDataSourceInfo(String userName);

    /**
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户自己上传的数据源格式信息，没有返回null
     */
    DataSourceInfo getDataSourceInfo(String userName);
}
