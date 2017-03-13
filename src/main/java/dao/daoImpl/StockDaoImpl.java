package dao.daoImpl;

import dao.StockDao;
import dataHelper.PrivateStockDataHelper;
import dataHelper.StockDataHelper;
import dataHelper.dataHelperImpl.PrivateStockDataHelperImpl;
import dataHelper.dataHelperImpl.StockDataHelperImpl;
import po.StockPO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by cuihua
 * Update time 2017/3/10
 *
 * 将异常全部抛出，留至界面处理
 */
public class StockDaoImpl implements StockDao {

    //股票信息获取的helper对象
    private StockDataHelper stockHelper;
    private PrivateStockDataHelper privateStockDataHelper;

    /**
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     */
    public StockDaoImpl() {
        stockHelper = new StockDataHelperImpl();
        privateStockDataHelper = new PrivateStockDataHelperImpl();
    }


    /*
    股票数据
     */
    /**
     * 获取特定时间段内的指定股票所有数据
     *
     * @author Byron Dong
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/10
     * @param start 时间区域的小值
     * @param end 时间区域的大值
     * @param stockCode 指定股票代码
     * @return 特定时间段内的指定股票所有数据
     */
    @Override
    public List<StockPO> getStockData(LocalDate start, LocalDate end, String stockCode) throws IOException {
        List<StockPO> resultStockList = new ArrayList<StockPO>();
        List<StockPO> tempStockList = stockHelper.getStockRecords(stockCode);

        for (StockPO stockPO : tempStockList) {
            if (this.isTrueDate(start, end, stockPO.getDate())) {
                resultStockList.add(stockPO);
            }
        }

        return resultStockList;
    }

    /**
     * 取指定股票的所有数据，没有返回null
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @param stockCode 指定的股票代码
     * @return 此股票的所有数据
     */
    @Override
    public List<StockPO> getStockData(String stockCode) throws IOException {
            return stockHelper.getStockRecords(stockCode);

    }

    /**
     * 获取特定日期的所有股票所有数据
     *
     * @author Harvey
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param date 选定的日期
     * @return 特定日期的保存的所有股票
     * @throws IOException IO
     */
    @Override
    public List<StockPO> getStockData(LocalDate date) throws IOException {
        return stockHelper.getStockRecords(date);
    }




    /*
    自选股
     */
    /**
     * 获取用户自选股票的数据
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @param date 股票代码
     * @return 指定用户的自选股票
     */
    @Override
    public List<StockPO> getPrivateStocks(String userName, LocalDate date) {
        List<String> privateStockCodes = privateStockDataHelper.getPrivateStockCode(userName);
        List<StockPO> stockPOList;
        List<StockPO> privateStockPoList = new ArrayList<StockPO>();
        try {
            stockPOList = stockHelper.getStockRecords(date);
            for (StockPO po:stockPOList) {
                if(privateStockCodes.contains(String.valueOf(po.getCode()))){
                    privateStockPoList.add(po);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return privateStockPoList;
    }

    /**
     * 添加用户自选股
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @param stockCode 股票代码
     * @return 添加是否成功
     */
    @Override
    public boolean addPrivateStock(String userName, String stockCode) {
        return privateStockDataHelper.addPrivateStock(userName,stockCode);
    }

    /**
     * 删除用户自选股
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @param stockCode 股票代码
     * @return 删除是否成功
     */
    @Override
    public boolean deletePrivateStock(String userName, String stockCode) {
        return privateStockDataHelper.deletePrivateStock(userName,stockCode);
    }




    /*
    提供给UserService
     */
    /**
     * 用户注册时，给用户新建一个对应用户名的资源文件
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @param userName 用户名称
     * @return 是否创建成功
     */
    @Override
    public boolean createPrivateDir(String userName) {
        return privateStockDataHelper.createPrivateDir(userName);
    }




   /*
    暂定
     */
    /**
     * 获取数据库中股票存在记录的第一天
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param code 股票代码
     * @return 数据库中股票存在记录的第一天
     */
    @Override
    public LocalDate getFirstDay(String code) {
        return stockHelper.getFirstDay(code);
    }





    /**
     * 判断时间是否在指定区域内
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param start 时间区域的小值
     * @param end 时间区域的大值
     * @param now 指定时间
     * @return 特定时间段内的所有指定股票所有数据
     */
    private boolean isTrueDate(LocalDate start, LocalDate end,LocalDate now){
        if(now.isEqual(start)||now.isEqual(end)){
            return true;
        }
        if(now.isAfter(start)&&now.isBefore(end)){
            return true;
        }
        return false;
    }
}
