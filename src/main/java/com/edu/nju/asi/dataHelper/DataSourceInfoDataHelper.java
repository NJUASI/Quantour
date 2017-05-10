package com.edu.nju.asi.dataHelper;

import com.edu.nju.asi.model.DataSourceInfo;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.edu.nju.asi.po.DataSourceInfoPO;

import java.io.IOException;

/**
 * Created by cuihua on 2017/3/30.
 */
public interface DataSourceInfoDataHelper {

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
