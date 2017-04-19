package service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy;

import vo.FormativePeriodRateVO;

import java.util.List;

/**
 * Created by Harvey on 2017/4/19.
 */
public class RankMaxPercentPickStrategy extends AllPickStrategy {

    public RankMaxPercentPickStrategy(int rank) {
        super(rank);
    }

    /**
     * 根据FormativePeriodRateVO中的periodReturn对股票代码进行排序并选择
     *
     * @param formativePeriodRateVOS
     * @return List<String> 选择好的持有期的股票代码
     */
    @Override
    public List<String> pick(List<FormativePeriodRateVO> formativePeriodRateVOS) {
        List<String> sortedStockPool = ascSort(formativePeriodRateVOS);

        int size = sortedStockPool.size();
        int pickedNum =  (int)Math.ceil((double)size * rank / 100);

        return  sortedStockPool.subList(0,pickedNum);
    }
}
