package service;

import utilities.exceptions.MatchNothingException;
import vo.StockSearchVO;
import vo.StockVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Harvey on 2017/3/5.
 * Last updated by Harvey
 * Update time 2017/3/5
 *
 * 股票信息查看、自选股操作
 */
public interface StockService{

    /**
     * 显示所有股票信息的列表
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @params date 用户选择日期
     * @return 股票信息列表
     * @throws IOException IO
     */
    Iterator<StockVO> getAllStocks(LocalDate date) throws IOException;

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
    Iterator<StockVO> getPrivateStocks(String userName, LocalDate date) throws IOException;

    /**
     * 用户添加自选股
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @param stockCode 股票代码
     * @return 是否添加成功
     */
    public boolean addPrivateStock(String userName, String stockCode);

    /**
     * 用户删除自选股
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @param stockCode 股票代码
     * @return 是否删除成功
     */
    public boolean deletePrivateStock(String userName, String stockCode);

    /**
     * 用户输入代码或者股票首字母，查找符合条件的股票
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/14
     * @param searchString 代码或股票首字母
     * @return List<StockSearchVO> 符合条件的股票简要信息
     */
    public List<StockSearchVO> searchStock(String searchString) throws MatchNothingException;


}
