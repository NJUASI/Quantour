package po;

import java.sql.Timestamp;

/**
 * Created by cuihua on 2017/4/13.
 *
 * 用户上传的数据源相关信息
 */
public class DataSourceInfoPO {

    // 上传文件路径
    private String filePath;

    // 上传时间
    private Timestamp uploadTime;

    // 上传用户
    private String userName;

    public DataSourceInfoPO(String filePath, Timestamp uploadTime, String userName) {
        this.filePath = filePath;
        this.uploadTime = uploadTime;
        this.userName = userName;
    }

    public String getFilePath() {
        return filePath;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public String getUserName() {
        return userName;
    }
}
