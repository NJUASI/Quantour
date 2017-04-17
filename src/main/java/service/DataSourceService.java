package service;

import utilities.enums.DataSourceState;
import utilities.exceptions.NotCSVException;
import vo.DataSourceInfoVO;

import java.io.IOException;

/**
 * Created by cuihua on 2017/3/30.
 *
 * 更换数据源的接口
 */
public interface DataSourceService {

    /**
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/30
     * @param filePath 要上传的文件路径
     * @return 上传成功
     */
    boolean upload(String filePath) throws IOException, NotCSVException;

    /**
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/4/13
     * @return 用户自己上传的数据源格式信息，没有返回null
     */
    DataSourceInfoVO getMyDataSource() throws IOException;

    /**
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/4/15
     * @return 用户选择修改数据源来源
     */
    boolean setDataSourceState(DataSourceState newState);

    /**
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/4/15
     * @return 界面查看当前数据源类型
     */
    DataSourceState getDataSourceState();
}
