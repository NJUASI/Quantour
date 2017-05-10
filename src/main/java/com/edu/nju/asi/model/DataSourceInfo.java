package com.edu.nju.asi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Byron Dong on 2017/5/7.
 *
 * 用户上传的数据源相关信息
 */
@Entity
@Table(name = "datasourceinfo")
public class DataSourceInfo implements Serializable {

    //用户名
    @Id
    @GenericGenerator(name="myGenerator",strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    @Column(name = "userName")
    private String userName;

    // 上传文件大小
    @Basic
    private String fileSize;

    // 上传时间
    @Basic
    private String uploadTime;

    public DataSourceInfo() {
    }

    public DataSourceInfo(String userName,String fileSize, String uploadTime) {
        this.userName = userName;
        this.fileSize = fileSize;
        this.uploadTime = uploadTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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
