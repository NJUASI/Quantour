package com.edu.nju.asi.dataHelper;

import com.edu.nju.asi.model.PrivateStock;
import com.edu.nju.asi.model.OptionalStockID;
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
    boolean addPrivateStock(OptionalStockID opionalStockID);

    /**
     * 添加自选股列表
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean addPrivateStockAll(List<OptionalStockID> list) throws PrivateStockExistedException;

    /**
     * 删除自选股
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean deletePrivateStock(OptionalStockID opionalStockID);

    /**
     * 删除自选股列表
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean deletePrivateStockAll(List<OptionalStockID> list);
}
