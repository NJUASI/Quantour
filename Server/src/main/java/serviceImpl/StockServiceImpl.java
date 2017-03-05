package serviceImpl;

import service.StockService;
import vo.StockVO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/3/5.
 */
public class StockServiceImpl extends UnicastRemoteObject implements StockService {

    protected StockServiceImpl() throws RemoteException {
    }

    /**
     * 显示所有股票信息的列表
     *
     * @param date
     * @return the iterator
     * @throws RemoteException the remote exception
     * @params date 用户选择日期
     */
    @Override
    public Iterator<StockVO> showAllStocks(LocalDate date) throws RemoteException {
        return null;
    }

    /**
     * 显示用户的自选股信息列表
     *
     * @param userName the user name 用户名称
     * @param date     用户选择日期
     * @return the iterator
     * @throws RemoteException the remote exception
     */
    @Override
    public Iterator<StockVO> showPrivateStocks(String userName, LocalDate date) throws RemoteException {
        return null;
    }

    /**
     * 用户添加自选股
     *
     * @param userName  the user name
     * @param stockCode the stock code
     * @return the boolean
     * @throws RemoteException the remote exception
     */
    @Override
    public boolean addPrivateStock(String userName, String stockCode) throws RemoteException {
        return false;
    }

    /**
     * 用户删除自选股
     *
     * @param userName  the user name
     * @param stockCode the stock code
     * @return the boolean
     * @throws RemoteException the remote exception
     */
    @Override
    public boolean deletePrivateStock(String userName, String stockCode) throws RemoteException {
        return false;
    }
}
