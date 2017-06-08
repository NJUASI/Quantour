package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.StockDataHelper;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockID;
import com.edu.nju.asi.utilities.StockList;
import com.edu.nju.asi.utilities.util.JDBCUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Byron Dong on 2017/3/5.
 * Last updated by cuihua
 * Update time 2017/3/18
 */
@Repository
public class StockDataHelperImpl implements StockDataHelper {

    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    /**
     * 获取特定日期指定股票的相关数据
     *
     * @param stockCode 指定股票代码
     * @param date      指定日期
     * @return 特定日期指定股票的相关数据
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public Stock getStockData(String stockCode, LocalDate date) {
        StockID stockID = new StockID(stockCode,date);
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Stock stock = (Stock)session.get(Stock.class,stockID);
        transaction.commit();
        session.close();
        return stock;
    }

    /**
     * 获取特定日期指定股票的相关数据
     *
     * @param stockCode 指定股票代码
     * @param start     指定开始日期（如果存在，包含start）
     * @param end       指定结束日期（如果存在，包含end）
     * @return 特定日期指定股票的相关数据
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<Stock> getStockData(String stockCode, LocalDate start, LocalDate end) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Stock where stockID.date<=:endDate and stockID.date>=:startDate and stockID.code=:code order by stockID.date";

        Query query = session.createQuery(hql);
        query.setParameter("endDate",end);
        query.setParameter("startDate",start);
        query.setParameter("code",stockCode);
        List<Stock> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    /**
     * 取指定股票的所有数据，没有返回null
     * 注意：取出来的所有股票数据中，年份小的在链表前端，年份大的在链表后端
     *
     * @param stockCode 指定的股票代码
     * @return （股票代码相同）此股票的所有数据
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/6
     */
    @Override
    public List<Stock> getStockData(String stockCode) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql  = "from Stock stock where stock.id.code =:code order by stock.stockID.date";
        Query query = session.createQuery(hql);
        query.setParameter("code",stockCode);
        List<Stock> stocks = query.list();
        transaction.commit();
        session.close();
        return stocks;
    }

    /**
     * 取所有股票的所有数据，没有返回null
     * 注意：取出来的单只股票数据中，年份小的在链表前端，年份大的在链表后端
     *
     * @param codes 指定的股票代码
     * @return （股票代码相同）此股票的所有数据
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/8
     */
    @Override
    public Map<String, StockList> getAllStockData(Set<String> codes) {
        Connection connection = JDBCUtil.getConnection();

        Map<String, StockList> map = new HashMap<>();

        long start = System.currentTimeMillis();
        for (String code : codes) {
            map.put(code, this.getOneStockData(code, connection));
        }
        System.out.println("总时间： "+(System.currentTimeMillis()-start));
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取特定日期的所有股票所有数据
     *
     * @param date 选定的日期
     * @return （时间相同）特定日期的保存的所有股票
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<Stock> getStockData(LocalDate date) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql  = "from Stock stock where stock.stockID.date =:date";
        long start = System.currentTimeMillis();
        Query query = session.createQuery(hql);
        query.setParameter("date",date);
        List<Stock> stocks = query.list();
        System.out.println(System.currentTimeMillis()-start);
        transaction.commit();
        session.close();
        return stocks;
    }

    /**
     * 添加股票列表
     *
     * @param stocks 需要添加的股票列表
     * @return boolean
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean addStockAll(List<Stock> stocks){
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO stock(code, date, close, high, low, name, open, preClose, volume, " +
                "circulationMarketValue, fluctuation, increaseMargin, totalValue, transactionAmount, turnoverRate," +
                "afterAdjClose, afterAdjHigh, afterAdjLow, afterAdjOpen, frontAdjClose, frontAdjHigh, frontAdjLow, " +
                "frontAdjOpen, preAfterAdjClose, preFrontAdjClose)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        boolean result = true;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (Stock stock : stocks){
                preparedStatement.setString(1,stock.getStockID().getCode());
                preparedStatement.setObject(2,stock.getStockID().getDate());
                preparedStatement.setDouble(3,stock.getClose());
                preparedStatement.setDouble(4,stock.getHigh());
                preparedStatement.setDouble(5,stock.getLow());
                preparedStatement.setString(6,stock.getName());
                preparedStatement.setDouble(7,stock.getOpen());
                preparedStatement.setDouble(8,stock.getPreClose());
                preparedStatement.setString(9,stock.getVolume());
                preparedStatement.setString(10,stock.getCirculationMarketValue());
                preparedStatement.setDouble(11,stock.getFluctuation());
                preparedStatement.setDouble(12,stock.getIncreaseMargin());
                preparedStatement.setString(13,stock.getTotalValue());
                preparedStatement.setString(14,stock.getTransactionAmount());
                preparedStatement.setDouble(15,stock.getTurnoverRate());
                preparedStatement.setDouble(16,stock.getAfterAdjClose());
                preparedStatement.setDouble(17,stock.getAfterAdjHigh());
                preparedStatement.setDouble(18,stock.getAfterAdjLow());
                preparedStatement.setDouble(19,stock.getAfterAdjOpen());
                preparedStatement.setDouble(20,stock.getFrontAdjClose());
                preparedStatement.setDouble(21,stock.getFrontAdjHigh());
                preparedStatement.setDouble(22,stock.getFrontAdjLow());
                preparedStatement.setDouble(23,stock.getFrontAdjOpen());
                preparedStatement.setDouble(24,stock.getPreAfterAdjClose());
                preparedStatement.setDouble(25,stock.getPreFrontAdjClose());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            result = false;
        }finally {
            JDBCUtil.close(preparedStatement,connection);
        }

        return result;
    }

    /**
     * @return 所有的交易日期
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<LocalDate> getDateWithData() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql  = "select distinct stock.id.date from Stock stock order by stock.id.date";
        List<LocalDate> dates = session.createQuery(hql).list();
        transaction.commit();
        session.close();
        return dates;
    }

    /**
     * @param stockCode 股票代码
     * @return 数据库中股票存在记录的起讫时间，List.get(0)为第一天，List.get(1)为最后一天
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<LocalDate> getFirstAndLastDay(String stockCode) {

        List<LocalDate> dates = getAllDateByCode(stockCode);

        if(dates==null||dates.isEmpty()){
            return null;
        }

        List<LocalDate> result = new ArrayList<>();
        result.add(dates.get(0));
        result.add(dates.get(dates.size()-1));
        return result;
    }

    private List<LocalDate> getAllDateByCode(String code){
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql  = "select distinct stock.id.date from Stock stock where stock.id.code =:code order by stock.id.date";
        Query query = session.createQuery(hql);
        query.setParameter("code",code);
        List<LocalDate> dates = query.list();
        transaction.commit();
        session.close();
        return dates;
    }

    private StockList getOneStockData(String code,Connection connection){
        StockList stocks = new StockList();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM stock WHERE stock.code=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                Stock stock = new Stock();
                stock.setStockID(new StockID(set.getString(1),LocalDate.parse(set.getDate(2).toString())));
                stock.setAfterAdjClose(set.getDouble(3));
                stock.setAfterAdjHigh(set.getDouble(4));
                stock.setAfterAdjLow(set.getDouble(5));
                stock.setAfterAdjOpen(set.getDouble(6));
                stock.setCirculationMarketValue(set.getString(7));
                stock.setClose(set.getDouble(8));
                stock.setFluctuation(set.getDouble(9));
                stock.setFrontAdjClose(set.getDouble(10));
                stock.setFrontAdjHigh(set.getDouble(11));
                stock.setFrontAdjLow(set.getDouble(12));
                stock.setFrontAdjOpen(set.getDouble(13));
                stock.setHigh(set.getDouble(14));
                stock.setIncreaseMargin(set.getDouble(15));
                stock.setLow(set.getDouble(16));
                stock.setName(set.getString(17));
                stock.setOpen(set.getDouble(18));
                stock.setPreAfterAdjClose(set.getDouble(19));
                stock.setPreClose(set.getDouble(20));
                stock.setPreFrontAdjClose(set.getDouble(21));
                stock.setTotalValue(set.getString(22));
                stock.setTransactionAmount(set.getString(23));
                stock.setTurnoverRate(set.getDouble(24));
                stock.setVolume(set.getString(25));
                stocks.add(stock);
            }
            set.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stocks;
    }
}
