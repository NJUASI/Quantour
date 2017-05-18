package com.edu.nju.asi.dataHelper;

import com.edu.nju.asi.model.OptionalStockID;
import com.edu.nju.asi.model.TraceBackStockPool;
import com.edu.nju.asi.utilities.exceptions.TraceBackStockExistedException;

import java.util.List;

/**
 * Created by Byron Dong on 2017/5/14.
 */
public interface TraceBackStockPoolDataHelper {

    /**
     * 获取指定用户名的回测股池
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    List<TraceBackStockPool> getTraceBackStockPool(String userName);

    /**
     * 获取指定用户名的回测股池股票代号
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
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
    boolean addTraceBackStock(TraceBackStockPool traceBackStockPool);

    /**
     * 添加回测股列表
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean addTraceBackStockAll(List<TraceBackStockPool> list) throws TraceBackStockExistedException;

    /**
     * 删除单只回测股
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean deleteTraceBackStock(OptionalStockID optionalStockID);

    /**
     * 删除回测股列表
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean deleteTraceBackStockAll(List<OptionalStockID> list);
}