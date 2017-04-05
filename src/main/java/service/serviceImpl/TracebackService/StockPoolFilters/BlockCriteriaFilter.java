package service.serviceImpl.TracebackService.StockPoolFilters;

import service.serviceImpl.TracebackService.StockPoolFilter;
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

        if(vo.blockType !=  BlockType.ALL){
            for(int i = 0; i < stocks.size();){
                if(stocks.get(i).blockType != vo.blockType){
                    stocks.remove(i);
                    continue;
                }
                i++;
            }
        }
        return getNextFilter().meetCriteria(stocks,vo);
    }
}
