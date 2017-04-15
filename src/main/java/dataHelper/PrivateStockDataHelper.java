package dataHelper;

import utilities.exceptions.PrivateStockExistedException;
import utilities.exceptions.PrivateStockNotExistException;
import utilities.exceptions.PrivateStockNotFoundException;

import java.util.List;

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
    List<String> getPrivateStockCode(String userName) throws PrivateStockNotFoundException;


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
    boolean addPrivateStock(String userName, String stockCode) throws PrivateStockExistedException;

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
    boolean deletePrivateStock(String userName, String stockCode) throws PrivateStockNotExistException;
}
