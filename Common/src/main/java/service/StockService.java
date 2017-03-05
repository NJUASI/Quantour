package service;

import vo.StockVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/3/5.
 */
public interface StockService extends Remote{
    /**
     * 显示所有股票信息的列表
     *
     * @params date 用户选择日期
     * @return the iterator
     * @throws RemoteException the remote exception
     */
    public Iterator<StockVO> showAllStocks(LocalDate date) throws RemoteException;

    /**
     * 显示用户的自选股信息列表
     *
     * @param userName the user name 用户名称
     * @param date  用户选择日期
     * @return the iterator
     * @throws RemoteException the remote exception
     */
    public Iterator<StockVO> showPrivateStocks(String userName,LocalDate date) throws RemoteException;

    /**
     * 用户添加自选股
     *
     * @param userName  the user name
     * @param stockCode the stock code
     * @return the boolean
     * @throws RemoteException the remote exception
     */
    public boolean addPrivateStock(String userName,String stockCode) throws RemoteException;

    /**
     * 用户删除自选股
     *
     * @param userName  the user name
     * @param stockCode the stock code
     * @return the boolean
     * @throws RemoteException the remote exception
     */
    public boolean deletePrivateStock(String userName,String stockCode) throws RemoteException;


}