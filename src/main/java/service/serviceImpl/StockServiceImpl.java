package service.serviceImpl;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import po.StockPO;
import service.StockService;
import vo.StockVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 股票信息查看、自选股操作
 *
 * Created by Harvey on 2017/3/5.
 * Last updated by cuihua
 * Update time 2017/3/12
 * 因修改下层接口而修改
 */
public class StockServiceImpl implements StockService {

    StockDao stockDao;

    public StockServiceImpl() {
        stockDao = new StockDaoImpl();
    }

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
    @Override
    public Iterator<StockVO> getAllStocks(LocalDate date) throws IOException {

        List<StockVO> stockVOList = new ArrayList<StockVO>();
        try {
            for (StockPO po:stockDao.getStockData(date)) {
                stockVOList.add(new StockVO(po));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockVOList.iterator();
    }

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
    @Override
    public Iterator<StockVO> getPrivateStocks(String userName, LocalDate date) throws IOException {
        List<StockVO> stockVOList = new ArrayList<StockVO>();
        for (StockPO po:stockDao.getPrivateStockData(userName,date)) {
            stockVOList.add(new StockVO(po));
        }
        return stockVOList.iterator();
    }

    /**
     * 用户添加自选股
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @param stockCode 股票代码
     * @return 是否添加成功
     */
    @Override
    public boolean addPrivateStock(String userName, String stockCode) {
        return stockDao.addPrivateStock(userName, Integer.parseInt(stockCode));
    }

    /**
     * 用户删除自选股
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @param stockCode 股票代码
     * @return 是否删除成功
     */
    @Override
    public boolean deletePrivateStock(String userName, String stockCode) {
        return stockDao.deletePrivateStock(userName, Integer.parseInt(stockCode));
    }
}
