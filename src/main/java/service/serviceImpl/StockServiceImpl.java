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
 * Created by Harvey on 2017/3/5.
 *
 * 股票、自选股信息获取
 */
public class StockServiceImpl implements StockService {

    StockDao stockDao;

    public StockServiceImpl() {
        stockDao = new StockDaoImpl();
    }

    /**
     * 显示所有股票信息的列表
     * @auther Harvey
     * @updateTime 2017/3/5
     * @return the iterator 股票在有效日期内所有的数据
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
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param userName the user name 用户名称
     * @param date     用户选择日期
     * @return the iterator 用户自选股列表
     */
    @Override
    public Iterator<StockVO> getPrivateStocks(String userName, LocalDate date) {
        List<StockVO> stockVOList = new ArrayList<StockVO>();
        for (StockPO po:stockDao.getPrivateStocks(userName,date)) {
            stockVOList.add(new StockVO(po));
        }
        return stockVOList.iterator();
    }

    /**
     * 用户添加自选股
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param userName  the user name   用户名称
     * @param stockCode the stock code 股票代码
     * @return the boolean 是否添加成功
     */
    @Override
    public boolean addPrivateStock(String userName, String stockCode) {
        return stockDao.addPrivateStock(userName,stockCode);
    }

    /**
     * 用户删除自选股
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param userName  the user name   用户名称
     * @param stockCode the stock code  股票代码
     * @return the boolean  是否删除成功
     */
    @Override
    public boolean deletePrivateStock(String userName, String stockCode) {
        return stockDao.deletePrivateStock(userName,stockCode);
    }
}
