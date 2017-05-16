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
import java.util.LinkedList;
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
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<TraceBackStockPool> getTraceBackStockPool(String userName) {
        return traceBackStockPoolDataHelper.getTraceBackStockPool(userName);
    }

    @Override
    public List<String> getTraceBackStockPoolCodes(String userName) {
        return traceBackStockPoolDataHelper.getTraceBackStockPoolCodes(userName);
    }

    /**
     * 添加单只回测股
     *
     * @param traceBackStockPool
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean addTraceBackStock(TraceBackStockPool traceBackStockPool) {
       return traceBackStockPoolDataHelper.addTraceBackStock(traceBackStockPool);
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
    public boolean addTraceBackStockAll(List<TraceBackStockPool> list) throws TraceBackStockExistedException {
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
    public boolean deleteTraceBackStock(OptionalStockID traceBackStockID) {
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
    public boolean deleteTraceBackStockAll(List<OptionalStockID> list) {
        return traceBackStockPoolDataHelper.deleteTraceBackStockAll(list);
    }
}
