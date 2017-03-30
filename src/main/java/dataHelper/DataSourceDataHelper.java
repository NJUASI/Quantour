package dataHelper;

import java.io.IOException;

/**
 * Created by cuihua on 2017/3/30.
 */
public interface DataSourceDataHelper {

    /**
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/30
     * @param filePath 要上传的文件路径
     * @return 上传成功
     */
    boolean upload(String filePath) throws IOException;
}
