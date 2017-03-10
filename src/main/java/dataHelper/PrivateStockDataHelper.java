package dataHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2017/3/5.
 * Last updated by Harvey
 * Update time 2017/3/6
 */
public interface PrivateStockDataHelper {

    /**
     * 获取用户对应的所有自选股的代码
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @param userName 用户名称
     * @return 自选股代码列表
     */
    List<String> getPrivateStockCode(String userName);


    /**
     * 添加用户自选股
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName  用户名称
     * @param stockCode 股票代码
     * @return 添加是否成功
     */
    boolean addPrivateStock(String userName, String stockCode);

    /**
     * 删除用户自选股
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName  用户名称
     * @param stockCode 股票代码
     * @return 删除是否成功
     */
    boolean deletePrivateStock(String userName, String stockCode);

    /**
     * 用户注册时，给用户新建一个对应用户名的资源文件.
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @param userName 用户名称
     * @return 是否创建成功
     */
    boolean createPrivateDir(String userName);
}
