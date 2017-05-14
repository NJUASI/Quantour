package com.edu.nju.asi.dataHelper;

import com.edu.nju.asi.model.PrivateStock;
import com.edu.nju.asi.model.PrivateStockID;
import com.edu.nju.asi.utilities.exceptions.PrivateStockExistedException;

import java.util.List;

/**
 * Created by Byron Dong on 2017/5/11.
 */
public interface PrivateStockDataHelper {

    /**
     * 获取自选股
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    List<PrivateStock> getPrivateStock(String userName);

    /**
     * 添加自选股
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean addPrivateStock(PrivateStockID privateStockID);

    /**
     * 添加自选股列表
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean addPrivateStockAll(List<PrivateStockID> list) throws PrivateStockExistedException;

    /**
     * 删除自选股
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean deletePrivateStock(PrivateStockID privateStockID);

    /**
     * 删除自选股列表
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean deletePrivateStockAll(List<PrivateStockID> list);
}
