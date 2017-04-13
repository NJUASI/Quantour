package vo;

import po.DataSourceInfoPO;

import java.sql.Timestamp;

/**
 * Created by cuihua on 2017/4/13.
 *
 * 用户上传的数据源相关信息
 */
public class DataSourceInfoVO {

    // 上传文件路径
    public String filePath;

    // 上传时间
    public Timestamp uploadTime;

    // 上传用户
    public String userName;

    public DataSourceInfoVO(DataSourceInfoPO po) {
        filePath = po.getFilePath();
        uploadTime = po.getUploadTime();
        userName = po.getUserName();
    }
}
