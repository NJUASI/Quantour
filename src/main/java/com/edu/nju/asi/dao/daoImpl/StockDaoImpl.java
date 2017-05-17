package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockSearchDataHelper;
import com.edu.nju.asi.dataHelper.StockDataHelper;
import com.edu.nju.asi.dataHelper.UserDataHelper;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.utilities.StockCodeHelper;
import com.edu.nju.asi.utilities.enums.BlockType;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.infoCarrier.traceBack.StockPool;
import org.springframework.stereotype.Component;

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
@Component("StockDao")
public class StockDaoImpl implements StockDao {

    //股票信息获取的helper对象
    private StockDataHelper stockDataHelper;
    private StockSearchDataHelper stockSearchDataHelper;
    private UserDataHelper userDataHelper;

    /**
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     */
    public StockDaoImpl() {
        stockDataHelper = HelperManager.stockDataHelper;
        stockSearchDataHelper = HelperManager.stockSearchDataHelper;
        userDataHelper = HelperManager.userDataHelper;
    }


    /*
    股票数据
     */
    /**
     * 获取特定日期指定股票的相关数据
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/5/11
     * @param stockCode 指定股票代码
     * @param date 指定日期
     * @return 特定日期指定股票的相关数据
     */
    @Override
    public Stock getStockData(String stockCode, LocalDate date) throws IOException {
        List<Stock> result = stockDataHelper.getStockData(date);
        for (Stock stock : result) {
            if (stock.getStockID().getCode().equals(stockCode)) {
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
    public List<Stock> getStockData(String stockCode, LocalDate start, LocalDate end) throws IOException, DateNotWithinException, NoDataWithinException {
        if (!isDateWithinSource(stockCode, start, end)) {
            // 用户要查找的时间区间超出数据源时间区间
            throw new DateNotWithinException();
        }

        List<Stock> result = stockDataHelper.getStockData(stockCode,start,end);

        if (result.size() == 0) {
            throw new NoDataWithinException(stockCode);
        }
        return result;
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
    public List<Stock> getStockData(String stockCode) throws IOException {
        List<Stock> result = stockDataHelper.getStockData(stockCode);
        return result;
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
    public List<Stock> getStockData(LocalDate date) throws IOException {
        return stockDataHelper.getStockData(date);
    }

    /**
     * 用户输入代码或者股票首字母或股票名称，查找符合条件的股票
     *
     * @param searchString 代码或股票首字母或股票名称
     * @return 符合条件的股票简要信息
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/14
     */
    @Override
    public List<StockSearch> searchStock(String searchString) {
        return stockSearchDataHelper.search(searchString);
    }



    /*
    交易时间相关
     */
    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/23
     * @param stockCode 股票代码
     * @return 此股票需要被剔除的所有日期
     */
    @Override
    public List<LocalDate> getDateWithoutData(String stockCode) throws IOException {
        List<Stock> result = getStockData(stockCode);

        List<LocalDate> dates = new LinkedList<>();

        LocalDate temp = result.get(0).getStockID().getDate();
        LocalDate end = result.get(result.size() - 1).getStockID().getDate();

        // 先加入所有目标可能的日期
        while (!temp.isEqual(end)) {
            dates.add(temp);
            temp = temp.plusDays(1);
        }

        // 再剔除有数据的日期
        for (Stock stock : result) {
            dates.remove(stock.getStockID().getDate());
        }

        return dates;
    }

    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/23
     * @param stockCode 股票代码
     * @return 在指定时间区段此股票需要被剔除的所有日期
     */
    @Override
    public List<LocalDate> getDateWithoutData(String stockCode, LocalDate start, LocalDate end) throws IOException {
        List<LocalDate> temp = getDateWithoutData(stockCode);

        for (int i = 0; i < temp.size(); ) {
            if (!isDateWithinWanted(start, end, temp.get(i))) {
                temp.remove(i);
            } else {
                i++;
            }
        }

        return temp;
    }

    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/23
     * @return 所有的数据日期
     */
    @Override
    public List<LocalDate> getDateWithData() throws IOException {
        return stockDataHelper.getDateWithData();
    }


   /*
    暂定
     */
    /**
     * 获取数据库中股票存在记录的第一天
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/23
     * @param stockCode 股票代码
     * @return 数据库中股票存在记录的第一天
     */
    @Override
    public List<LocalDate> getFirstAndLastDay(String stockCode) throws IOException {
        return stockDataHelper.getFirstAndLastDay(stockCode);
    }

    /**
     * 获取所有股票的代码
     *
     * @return the all stocks code
     */
    @Override
    public Map<String, String> getAllStocksCode() {
        return stockSearchDataHelper.getAllStocksCode();
    }

    /**
     * 获取所有股票的首字母
     *
     * @return the all stocks first letters
     */
    @Override
    public List<StockSearch> getAllStocksFirstLetters() throws IOException {
        return stockSearchDataHelper.getAllStocksFirstLetters();
    }

    /**
     * 获取所有股票的名称
     *
     * @return the all stocks first letters 返回所有股票的名称及其自然代码，名称作为键值
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/14
     */
    @Override
    public Map<String, String> getAllStocksName() {
        return stockSearchDataHelper.getAllStocksName();
    }

    /**
     * 获取所有股票的版块有关的信息
     *
     * @return 所有股票的版块有关的信息
     */
    @Override
    public List<StockPool> getAllStockPool() throws IOException, UnhandleBlockTypeException {
        List<StockPool> result = new LinkedList<>();

        Map<String, String> codeName = stockSearchDataHelper.getAllStocksCode();
        List<String> stockCodes = new ArrayList<>(codeName.keySet());
        List<String> stockNames = new ArrayList<>(codeName.values());

        // 手动去除基准股票（沪深300、中小板指、创业板指）
        stockCodes.remove("000300");
        stockCodes.remove("399005");
        stockCodes.remove("399006");

        for (int i = 0; i < stockCodes.size(); i++) {
            String tempCode = StockCodeHelper.format(stockCodes.get(i));

            BlockType thisBlockType;
            if (tempCode.startsWith("001") || tempCode.startsWith("000") | tempCode.startsWith("6")) {
                thisBlockType = BlockType.ZB;
            } else if (tempCode.startsWith("002")) {
                thisBlockType = BlockType.ZXB;
            } else if (tempCode.startsWith("300")) {
                thisBlockType = BlockType.CYB;
            } else {
                System.out.println("未处理股票板块：" + tempCode);
                throw new UnhandleBlockTypeException();
            }

            boolean isSt = stockNames.get(i).contains("ST");

            result.add(new StockPool(tempCode, thisBlockType, isSt));
        }

        return result;
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
    private boolean isDateWithinSource(String stockCode, LocalDate start, LocalDate end) throws IOException {
        List<LocalDate> firstAndLast = getFirstAndLastDay(stockCode);
        LocalDate sourceStart = firstAndLast.get(0);
        LocalDate sourceEnd = firstAndLast.get(1);

        if (start.isBefore(sourceStart) || end.isAfter(sourceEnd)) return false;
        else return true;
    }
}
