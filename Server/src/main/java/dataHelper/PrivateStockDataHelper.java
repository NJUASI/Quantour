package dataHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2017/3/5.
 */
public interface PrivateStockDataHelper
{

    /**
     * 获取用户对应的所有自选股的代码
     *
     * @param userName the user name 用户名称
     * @return the private stock code 自选股代码列表
     * @author Harvey
     * @updateTime 2017/3/6
     */
    List<String> getPrivateStockCode(String userName);


    /**
     * 添加用户自选股
     *
     * @param userName  用户名称
     * @param stockCode 股票代码
     * @return boolean 添加是否成功
     * @author Harvey
     * @updateTime 2017/3/5
     */
    boolean addPrivateStock(String userName, String stockCode);

    /**
     * 删除用户自选股
     *
     * @param userName  用户名称
     * @param stockCode 股票代码
     * @return boolean 删除是否成功
     * @author Harvey
     * @updateTime 2017/3/5
     */
    boolean deletePrivateStock(String userName, String stockCode);

    /**
     * 用户注册时，给用户新建一个对应用户名的资源文件.
     *
     * @param userName the user name 用户名称
     * @return the boolean  是否创建成功
     * @author Harvey
     * @updateTime 2017/3/6
     */
    boolean createPrivateDir(String userName);
}
