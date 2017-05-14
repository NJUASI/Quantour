package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.TraceBackStockPoolDao;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockDataHelper;
import com.edu.nju.asi.dataHelper.TraceBackStockPoolDataHelper;
import com.edu.nju.asi.model.*;
import com.edu.nju.asi.utilities.exceptions.TraceBackStockExistedException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/5/14.
 */
@Component("TraceBackStockPoolDao")
public class TraceBackStockPoolDaoImpl implements TraceBackStockPoolDao {

    private StockDataHelper stockDataHelper;
    private TraceBackStockPoolDataHelper traceBackStockPoolDataHelper;

    public TraceBackStockPoolDaoImpl() {
        stockDataHelper = HelperManager.stockDataHelper;
        traceBackStockPoolDataHelper = HelperManager.traceBackStockPoolDataHelper;
    }

    /**
     * 获取指定用户名的回测股池
     *
     * @param userName
     * @param localDate
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<Stock> getTraceBackStockPool(String userName, LocalDate localDate) {
        List<Stock> result = new ArrayList<>();

        List<TraceBackStockPool> traceBackStockPools = traceBackStockPoolDataHelper.getTraceBackStockPool(userName);
        for (TraceBackStockPool temp : traceBackStockPools) {
            TraceBackStockID id = temp.getTraceBackStockID();
            result.add(stockDataHelper.getStockData(id.getStockCode(), localDate));
        }

        return result;
    }

    /**
     * 添加单只回测股
     *
     * @param traceBackStockID
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean addTraceBackStock(TraceBackStockID traceBackStockID) {
       return traceBackStockPoolDataHelper.addTraceBackStock(traceBackStockID);
    }

    /**
     * 添加回测股列表
     *
     * @param list
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean addTraceBackStockAll(List<TraceBackStockID> list) throws TraceBackStockExistedException {
        return traceBackStockPoolDataHelper.addTraceBackStockAll(list);
    }

    /**
     * 删除单只回测股
     *
     * @param traceBackStockID
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean deleteTraceBackStock(TraceBackStockID traceBackStockID) {
        return traceBackStockPoolDataHelper.deleteTraceBackStock(traceBackStockID);
    }

    /**
     * 删除回测股列表
     *
     * @param list
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean deleteTraceBackStockAll(List<TraceBackStockID> list) {
        return traceBackStockPoolDataHelper.deleteTraceBackStockAll(list);
    }
}
