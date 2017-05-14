package com.edu.nju.asi.dao;

import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.TraceBackStockID;
import com.edu.nju.asi.model.TraceBackStockPool;
import com.edu.nju.asi.utilities.exceptions.TraceBackStockExistedException;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Byron Dong on 2017/5/14.
 */
public interface TraceBackStockPoolDao {

    /**
     * 获取指定用户名的回测股池
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    List<Stock> getTraceBackStockPool(String userName, LocalDate localDate);

    /**
     * @param userName 用户账号
     * @return 此用户需要进行回测的股票池
     */
    List<String> getTraceBackStockPoolCodes(String userName);

    /**
     * 添加单只回测股
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean addTraceBackStock(TraceBackStockID traceBackStockID);

    /**
     * 添加回测股列表
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean addTraceBackStockAll(List<TraceBackStockID> list) throws TraceBackStockExistedException;

    /**
     * 删除单只回测股
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean deleteTraceBackStock(TraceBackStockID traceBackStockID);

    /**
     * 删除回测股列表
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean deleteTraceBackStockAll(List<TraceBackStockID> list);
}
