package utilities;

import po.StockPO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harvey on 2017/3/9.
 */
public class StockFilter {

    public StockFilter() {
    }

    //过滤器，过滤出停盘的数据
    public static List<StockPO> filter(List<StockPO> preList) {
        List<StockPO> handledList = new ArrayList<StockPO>();
        for (StockPO po : preList) {
            if (isSuspension(po)){
                handledList.add(po);
            }
        }
        return handledList;
    }

    //判断是否当天是否停盘
    public static boolean isSuspension(StockPO po){
        if (new Long(po.getVolume()) != 0){
            return true;
        }
        return false;
    }
}
