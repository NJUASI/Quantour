package com.edu.nju.asi.dao;

import com.edu.nju.asi.model.PrivateStockID;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.PrivateStockExistedException;
import com.edu.nju.asi.utilities.exceptions.PrivateStockNotExistException;
import com.edu.nju.asi.utilities.exceptions.PrivateStockNotFoundException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/5/11.
 */
public interface PrivateStockDao {

    /**
     * 显示用户的自选股信息列表
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName the user name 用户名称
     * @param date 用户选择日期
     * @return the iterator 自选股信息列表
     */
    List<Stock> getPrivateStocks(String userName, LocalDate date) throws IOException, PrivateStockNotFoundException;

    /**
     * 获取用户的自选股的股票池
     *
     * @author  Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/17
     * @param userName 用户名称
     * @return 指定用户的自选股
     */
    List<String> getPrivateStockCodes(String userName) throws PrivateStockNotFoundException;

    /**
     * 用户添加自选股
     * @auther Harvey
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @param privateStockID 用户名称和股票代码
     * @return 是否添加成功
     */
    boolean addPrivateStock(PrivateStockID privateStockID) throws PrivateStockExistedException, PrivateStockNotFoundException;

    /**
     * 添加自选股列表
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean addPrivateStockAll(List<PrivateStockID> list) throws PrivateStockExistedException;

    /**
     * 用户删除自选股
     * @auther Harvey
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @param privateStockID 用户名称和股票代码
     * @return 是否删除成功
     */
    boolean deletePrivateStock(PrivateStockID privateStockID) throws PrivateStockNotExistException, PrivateStockNotFoundException;

    /**
     * 删除自选股列表
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean deletePrivateStockAll(List<PrivateStockID> list);

}
