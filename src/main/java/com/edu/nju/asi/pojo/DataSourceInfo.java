package com.edu.nju.asi.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Byron Dong on 2017/5/7.
 *
 * 用户上传的数据源相关信息
 */
@Entity
@Table
public class DataSourceInfo implements Serializable {

    // 上传文件大小
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String fileSize;

    // 上传时间
    private String uploadTime;

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }
}
