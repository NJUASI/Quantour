package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.StockDataHelper;
import com.edu.nju.asi.infoCarrier.FirstAndLastDay;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockID;
import com.edu.nju.asi.utilities.util.JDBCUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        String hql  = "from Stock stock where stock.id.date =:date";
        Query query = session.createQuery(hql);
        query.setParameter("date",date);
        List<Stock> stocks = query.list();
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
        String sql = "INSERT INTO stock(code, date, close, high, low, market, name, open, preClose, volume," +
                "circulationMarketValue, fluctuation, increaseMargin, totalValue, transactionAmount, turnoverRate)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
                preparedStatement.setInt(6,stock.getMarket().getRepre());
                preparedStatement.setString(7,stock.getName());
                preparedStatement.setDouble(8,stock.getOpen());
                preparedStatement.setDouble(9,stock.getPreClose());
                preparedStatement.setString(10,stock.getVolume());
                preparedStatement.setString(11,stock.getCirculationMarketValue());
                preparedStatement.setDouble(12,stock.getFluctuation());
                preparedStatement.setDouble(13,stock.getIncreaseMargin());
                preparedStatement.setString(14,stock.getTotalValue());
                preparedStatement.setString(15,stock.getTransactionAmount());
                preparedStatement.setDouble(16,stock.getTurnoverRate());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
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

}
