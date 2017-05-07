package com.edu.nju.asi.vo;

import com.edu.nju.asi.po.DataSourceInfoPO;

/**
 * Created by cuihua on 2017/4/13.
 *
 * 用户上传的数据源相关信息
 */
public class DataSourceInfoVO {

    // 上传文件路径
    public String fileSize;

    // 上传时间
    public String uploadTime;

    public DataSourceInfoVO(DataSourceInfoPO po) {
        fileSize = po.getFileSize();
        uploadTime = po.getUploadTime();
    }
}
