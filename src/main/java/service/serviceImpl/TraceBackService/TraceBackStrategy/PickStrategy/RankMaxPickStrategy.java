package service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy;


import vo.FormativePeriodRateVO;

import java.util.List;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 形成期挑选股票的策略， 按排名最大的前n条来挑选
 */
public class RankMaxPickStrategy extends AllPickStrategy{

    public RankMaxPickStrategy(int rank) {
        super(rank);
    }

    /**
     * 根据FormativePeriodRateVO中的periodReturn对排序好的股票代码进行选择
     *
     * @param formativePeriodRateVOS
     * @return List<String> 选择好的持有期的股票代码
     */
    @Override
    public List<String> pick(List<FormativePeriodRateVO> formativePeriodRateVOS) {
        List<String> sortedStockPool = ascSort(formativePeriodRateVOS);

        return  sortedStockPool.subList(0,rank);
    }
}