package com.edu.nju.asi.service.serviceImpl;

import com.edu.nju.asi.dao.TraceBackStockPoolDao;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.OptionalStockID;
import com.edu.nju.asi.model.TraceBackStockPool;
import com.edu.nju.asi.service.TraceBackStockPoolService;
import com.edu.nju.asi.utilities.exceptions.TraceBackStockExistedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Byron Dong on 2017/5/14.
 */
@Service("TraceBackStockPoolService")
public class TraceBackStockPoolServiceImpl implements TraceBackStockPoolService {

    @Autowired
    private TraceBackStockPoolDao traceBackStockPoolDao;

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
        return traceBackStockPoolDao.getTraceBackStockPool(userName);
    }

    @Override
    public List<String> getTraceBackStockPoolCodes(String userName) {
        return traceBackStockPoolDao.getTraceBackStockPoolCodes(userName);
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
        return traceBackStockPoolDao.addTraceBackStock(traceBackStockPool);
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
        return traceBackStockPoolDao.addTraceBackStockAll(list);
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
        return traceBackStockPoolDao.deleteTraceBackStock(traceBackStockID);
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
        return traceBackStockPoolDao.deleteTraceBackStockAll(list);
    }
}
