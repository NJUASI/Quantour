package com.edu.nju.asi.po;

/**
 * Created by cuihua on 2017/4/13.
 *
 * 用户上传的数据源相关信息
 */
public class DataSourceInfoPO {

    // 上传文件大小
    private String fileSize;

    // 上传时间
    private String uploadTime;

    public DataSourceInfoPO(String fileSize, String uploadTime) {
        this.fileSize = fileSize;
        this.uploadTime = uploadTime;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getUploadTime() {
        return uploadTime;
    }
}
