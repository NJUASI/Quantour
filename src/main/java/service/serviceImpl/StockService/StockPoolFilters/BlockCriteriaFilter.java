package service.serviceImpl.StockService.StockPoolFilters;

import service.serviceImpl.StockService.StockPoolFilter;
import utilities.enums.BlockType;
import vo.StockPoolCriteriaVO;
import vo.StockPoolVO;

import java.util.List;

/**
 * Created by harvey on 17-4-2.
 */
public class BlockCriteriaFilter extends StockPoolFilter {

    /**
     * 筛选股票
     *
     * @param stocks
     * @return 经过筛选后的目标股票池
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/4/2
     * @params List<StockVO> stocks 未经筛选的全部股票
     */
    @Override
    public List<StockPoolVO> meetCriteria(List<StockPoolVO> stocks, StockPoolCriteriaVO vo) {

        // 选择ALL时放且只放在第一个位置
        if(vo.blockTypes.get(0) !=  BlockType.ALL){
            for(int i = 0; i < stocks.size();){
                if(vo.blockTypes.contains(stocks.get(i).blockType)){

                    i++;
                    continue;
                }

                // 此股票没有在期望的板块中
                stocks.remove(i);

            }
        }

        if(getNextFilter() == null){
            return stocks;
        }
        else {
            return getNextFilter().meetCriteria(stocks, vo);
        }
    }
}
