package com.edu.nju.asi.dataHelperVersion2;

import com.edu.nju.asi.model.DataSourceInfo;

import java.time.LocalDateTime;

/**
 * Created by cuihua on 2017/3/30.
 */
public interface DataSourceDataHelper {

    /**
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/30
     * @return 上传成功
     */
    boolean upload(String userName, String fileSize, LocalDateTime localDateTime);

    /**
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/4/13
     * @return 用户自己上传的数据源格式信息，没有返回null
     */
    DataSourceInfo getMyDataSource(String userName);
}
