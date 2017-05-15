package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.BaseStockDataHelper;
import com.edu.nju.asi.model.BaseStock;
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
import java.util.List;

/**
 * Created by Byron Dong on 2017/5/15.
 */
@Repository
public class BaseStockDataHelperImpl implements BaseStockDataHelper {

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
    public BaseStock getStockData(String stockCode, LocalDate date) {
        StockID stockID = new StockID(stockCode,date);
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        BaseStock stock = (BaseStock) session.get(BaseStock.class,stockID);
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
    public List<BaseStock> getStockData(String stockCode, LocalDate start, LocalDate end) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from BaseStock where stockID.date<=:endDate and stockID.date>=:startDate and stockID.code=:code order by stockID.date";

        Query query = session.createQuery(hql);
        query.setParameter("endDate",end);
        query.setParameter("startDate",start);
        query.setParameter("code",stockCode);
        List<BaseStock> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    /**
     * 添加股票列表
     *
     * @param baseStocks 需要添加的股票列表
     * @return boolean
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean addBaseStockAll(List<BaseStock> baseStocks) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO basestock(code,date,close,fluctuation, high, increaseMargin, low, market, name," +
                "open,preClose,transactionAmount,volume) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        boolean result = true;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (BaseStock stock : baseStocks){
                preparedStatement.setString(1,stock.getStockID().getCode());
                preparedStatement.setObject(2,stock.getStockID().getDate());
                preparedStatement.setDouble(3,stock.getClose());
                preparedStatement.setDouble(4,stock.getFluctuation());
                preparedStatement.setDouble(5,stock.getHigh());
                preparedStatement.setDouble(6,stock.getIncreaseMargin());
                preparedStatement.setDouble(7,stock.getLow());
                preparedStatement.setInt(8,stock.getMarket().getRepre());
                preparedStatement.setString(9,stock.getName());
                preparedStatement.setDouble(10,stock.getOpen());
                preparedStatement.setDouble(11,stock.getPreClose());
                preparedStatement.setString(12,stock.getTransactionAmount());
                preparedStatement.setString(13,stock.getVolume());
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
}
