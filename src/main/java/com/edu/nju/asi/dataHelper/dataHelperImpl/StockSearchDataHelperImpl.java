package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockSearchDataHelper;
import com.edu.nju.asi.model.StockSearch;
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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Harvey on 2017/3/14.
 */
@Repository
public class StockSearchDataHelperImpl implements StockSearchDataHelper {

    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    /**
     * @return 所有股票名称的首字母缩写及其名称、代码
     */
    @Override
    public List<StockSearch> getAllStocksFirstLetters() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from StockSearch";
        List<StockSearch> list = session.createQuery(hql).list();
        transaction.commit();
        session.close();
        return list;
    }

    /**
     * @return 返回所有股票的代码及其名称，代码作为键值
     */
    @Override
    public Map<String, String> getAllStocksCode() {
        List list = getCodeAndName();
        Map<String,String> map = new TreeMap<String,String>();
        for(int i=0;i<list.size();i++){
            Object[] temp = (Object[]) list.get(i);
            map.put((String)temp[0],(String)temp[1]);
        }
        return map;
    }

    /**
     * @return 返回所有股票的汉语名称及其代码，名称作为键值
     */
    @Override
    public Map<String, String> getAllStocksName() {
        List list = getCodeAndName();
        Map<String,String> map = new TreeMap<String,String>();
        for(int i=0;i<list.size();i++){
            Object[] temp = (Object[])list.get(i);
            map.put((String)temp[1],(String)temp[0]);
        }
        return map;
    }

    /**
     * 添加StockSearch列表
     *
     * @param list
     */
    @Override
    public boolean addStockSearchAll(List<StockSearch> list) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO stocksearch(code, firstLetters, name, market) VALUES(?,?,?,?)";
        boolean result = true;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (StockSearch stockSearch : list){
                preparedStatement.setString(1,stockSearch.getSearchID().getCode());
                preparedStatement.setString(2,stockSearch.getFirstLetters());
                preparedStatement.setString(3,stockSearch.getSearchID().getName());
                preparedStatement.setInt(4,stockSearch.getSearchID().getMarket().getRepre());
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
     * 添加StockSearch列表
     */
    @Override
    public List<StockSearch> search(String info) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from StockSearch where searchID.code like :code or searchID.name like :name or " +
                "firstLetters like :firstLetters";
        Query query = session.createQuery(hql);
        query.setParameter("code","%"+info+"%");
        query.setParameter("name","%"+info+"%");
        query.setParameter("firstLetters","%"+info+"%");
        List<StockSearch> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    private List getCodeAndName(){
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select stocksearch.searchID.code, stocksearch.searchID.name from StockSearch stocksearch";
        List list = session.createQuery(hql).list();
        transaction.commit();
        session.close();
        return list;
    }
}
