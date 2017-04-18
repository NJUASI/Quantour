package utilities;

import service.serviceImpl.TraceBackService.TraceBackStrategy.StrategyStock;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Harvey on 2017/4/18.
 *
 * 为优化时间， 重写list的indexOf算法
 */
public class StrategyStockList extends ArrayList<StrategyStock>{

    /**
     * 寻找目标时间的StockStrategy
     * @param o 目标时间
     */
    @Override
    public int indexOf(Object o) {
        LocalDate date = (LocalDate)o;

        int start = 0;
        int end = this.size() - 1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (date.isBefore(get(middle).date)) {
                end = middle - 1;
            } else if (date.isAfter(get(middle).date)) {
                start = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
