package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.StockSearchDataHelper;
import com.edu.nju.asi.model.StockSearch;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    private List getCodeAndName(){
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select stocksearch.code, stocksearch.name from StockSearch stocksearch";
        List list = session.createQuery(hql).list();
        transaction.commit();
        session.close();
        return list;
    }
}
