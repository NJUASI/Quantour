package service;

import vo.StockVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Iterator;

/**
 * Created by Harvey on 2017/3/5.
 *
 * 股票信息查看、自选股操作
 */
public interface StockService extends Remote{
    /**
     * 显示所有股票信息的列表
     * @auther Harvey
     * @updateTime 2017/3/5
     * @params date 用户选择日期
     * @return the iterator 股票信息列表
     * @throws RemoteException the remote exception
     */
    Iterator<StockVO> getAllStocks(LocalDate date) throws RemoteException;

    /**
     * 显示用户的自选股信息列表
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param userName the user name 用户名称
     * @param date  用户选择日期
     * @return the iterator 自选股信息列表
     * @throws RemoteException the remote exception
     */
    Iterator<StockVO> getPrivateStocks(String userName, LocalDate date) throws RemoteException;

    /**
     * 用户添加自选股
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param userName  the user name 用户名称
     * @param stockCode the stock code 股票代码
     * @return the boolean 是否添加成功
     * @throws RemoteException the remote exception
     */
    public boolean addPrivateStock(String userName,String stockCode) throws RemoteException;

    /**
     * 用户删除自选股
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param userName  the user name 用户名称
     * @param stockCode the stock code 股票代码
     * @return the boolean 是否删除成功
     * @throws RemoteException the remote exception
     */
    public boolean deletePrivateStock(String userName,String stockCode) throws RemoteException;


}
