package com.edu.nju.asi.service.serviceImpl;

import com.edu.nju.asi.dao.TraceBackStockPoolDao;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.TraceBackStockID;
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
     * @param localDate
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public List<Stock> getTraceBackStockPool(String userName, LocalDate localDate) {
        return traceBackStockPoolDao.getTraceBackStockPool(userName, localDate);
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
        return traceBackStockPoolDao.addTraceBackStock(traceBackStockID);
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
    public boolean deleteTraceBackStock(TraceBackStockID traceBackStockID) {
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
    public boolean deleteTraceBackStockAll(List<TraceBackStockID> list) {
        return traceBackStockPoolDao.deleteTraceBackStockAll(list);
    }
}
