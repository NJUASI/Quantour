package dao.daoImpl;

import dao.StockDao;
import dataHelper.PrivateStockDataHelper;
import dataHelper.SearchDataHelper;
import dataHelper.StockDataHelper;
import dataHelper.dataHelperImpl.PrivateStockDataHelperImpl;
import dataHelper.dataHelperImpl.SearchDataHelperImpl;
import dataHelper.dataHelperImpl.StockDataHelperImpl;
import po.PrivateStockPO;
import po.StockPO;
import utilities.exceptions.DateNotWithinException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by cuihua
 * Update time 2017/3/15
 * 新增异常
 */
public class StockDaoImpl implements StockDao {

    //股票信息获取的helper对象
    private StockDataHelper stockHelper;
    private PrivateStockDataHelper privateStockDataHelper;
    private SearchDataHelper searchDataHelper;

    /**
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     */
    public StockDaoImpl() {
        stockHelper = new StockDataHelperImpl();
        privateStockDataHelper = new PrivateStockDataHelperImpl();
        searchDataHelper = new SearchDataHelperImpl();
    }


    /*
    股票数据
     */
    /**
     * 获取特定日期指定股票的相关数据
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/12
     * @param stockCode 指定股票代码
     * @param date 指定日期
     * @return 特定日期指定股票的相关数据
     */
    @Override
    public StockPO getStockData(String stockCode, LocalDate date) throws IOException {
        List<StockPO> result = stockHelper.getStockRecords(date);
        for (StockPO stock : result) {
            if (stock.getCode().equals(stockCode)) {
                return stock;
            }
        }
        return null;
    }

    /**
     * 获取特定时间段内的指定股票所有数据
     * 注意：取出来的所有股票数据中，年份小的在链表前端，年份大的在链表后端
     *
     * @author Byron Dong
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/10
     * @param stockCode 指定股票代码
     * @param start 时间区域的小值
     * @param end 时间区域的大值
     * @return 特定时间段内的指定股票所有数据
     */
    @Override
    public List<StockPO> getStockData(String stockCode, LocalDate start, LocalDate end) throws IOException, DateNotWithinException {
        if (!isDateWithinSource(start, end)) {
            // 用户要查找的时间区间超出数据源时间区间
            throw new DateNotWithinException();
        }
        List<StockPO> result = stockHelper.getStockRecords(stockCode);
        for (int i = 0; i < result.size(); ) {
            if (!isDateWithinWanted(start, end, result.get(i).getDate())) {
                result.remove(i);
            }else {
                i++;
            }
        }
        return reverse(result);
    }

    /**
     * 取指定股票的所有数据，没有返回null
     * 注意：取出来的所有股票数据中，年份小的在链表前端，年份大的在链表后端
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @param stockCode 指定的股票代码
     * @return 此股票的所有数据
     */
    @Override
    public List<StockPO> getStockData(String stockCode) throws IOException {
        return reverse(stockHelper.getStockRecords(stockCode));
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
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/12
     * @param userName 用户名称
     * @param date 股票代码
     * @return 指定用户的自选股票
     */
    @Override
    public List<StockPO> getPrivateStockData(String userName, LocalDate date) throws IOException {
        List<StockPO> result = new LinkedList<StockPO>();

        PrivateStockPO myPrivateStockPO = getPrivateStocks(userName);
        for (String stockCode : myPrivateStockPO.getPrivateStocks()) {
            result.add(getStockData(stockCode, date));
        }

        return result;
    }

    /**
     * 获取用户的自选股票
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/12
     * @param userName 用户名称
     * @return 指定用户的自选股
     */
    @Override
    public PrivateStockPO getPrivateStocks(String userName) {
        return new PrivateStockPO(userName, privateStockDataHelper.getPrivateStockCode(userName));
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
        return privateStockDataHelper.addPrivateStock(userName, stockCode);
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
        return privateStockDataHelper.deletePrivateStock(userName, stockCode);
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
     * Gets all stocks code. 获取所有股票的代码
     *
     * @return the all stocks code
     */
    @Override
    public Map<String, String> getAllStocksCode() {
        return searchDataHelper.getAllStocksCode();
    }

    /**
     * Gets all stocks first letters.获取所有股票的首字母
     *
     * @return the all stocks first letters
     */
    @Override
    public Map<String, String> getAllStocksFirstLetters() {
        return searchDataHelper.getAllStocksFirstLetters();
    }

    /**
     * 获取所有股票的名称
     *
     * @return the all stocks first letters 返回所有股票的名称及其，名称作为键值
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/14
     */
    @Override
    public Map<String, String> getAllStocksName() {
        return null;
    }

    /**
     * 判断目标时间是否在指定区域内
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     * @param start 时间区域的小值
     * @param end 时间区域的大值
     * @param now 指定时间
     * @return 目标时间在指定区域内
     */
    private boolean isDateWithinWanted(LocalDate start, LocalDate end, LocalDate now) {
        if (now.isEqual(start) || now.isEqual(end)) {
            return true;
        }
        if (now.isAfter(start) && now.isBefore(end)) {
            return true;
        }
        return false;
    }

    /**
     * 判断要查找的时间是否在数据源时间区间内
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/15
     * @param start 时间区域的小值
     * @param end 时间区域的大值
     * @return 要查找的时间区域在数据源内
     */
    private static boolean isDateWithinSource(LocalDate start, LocalDate end) {
        // 硬编码实现
        LocalDate sourceStart = LocalDate.of(2005, 2, 1);
        LocalDate sourceEnd = LocalDate.of(2014, 4, 29);

        if (start.isBefore(sourceStart) || end.isAfter(sourceEnd)) return false;
        else return true;
    }

    /**
     * 转置从dataHelper处来的数据库中的日期倒序数据
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param dataList 需被倒置的股票数据
     * @return 正常日期顺序的股票数据信息
     */
    private List<StockPO> reverse(List<StockPO> dataList) {
        List<StockPO> reversedList = new ArrayList<StockPO>();
        for (int i = 0; i < dataList.size(); i++) {
            reversedList.add(0, dataList.get(i));
        }
        return reversedList;
    }
}
