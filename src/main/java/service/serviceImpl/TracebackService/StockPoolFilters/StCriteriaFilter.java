package service.serviceImpl.TracebackService.StockPoolFilters;

import service.serviceImpl.TracebackService.StockPoolFilter;
import utilities.enums.StType;
import vo.StockPoolCriteriaVO;
import vo.StockPoolVO;

import java.util.List;

/**
 * Created by harvey on 17-4-2.
 */
public class StCriteriaFilter extends StockPoolFilter{

    /**
     * 筛选股票
     *
     * @param stocks
     * @param vo
     * @return 经过筛选后的目标股票池
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/4/2
     * @params List<StockVO> stocks 未经筛选的全部股票
     */
    @Override
    public List<StockPoolVO> meetCriteria(List<StockPoolVO> stocks, StockPoolCriteriaVO vo) {

        //TODO 因为现在还不需要筛选出st，故先直接返回，不做筛选
        return stocks;
//        if(vo.stType == StType.EXCLUDE){
//            for(int i = 0; i < stocks.size();){
//                if (stocks.get(i).isSt){
//                    stocks.remove(i);
//                }
//                i++;
//            }
//        }
//        else if(vo.stType == StType.ONLY){
//            for(int i = 0; i < stocks.size();){
//                if(!stocks.get(i).isSt){
//                    stocks.remove(i);
//                }
//                i++;
//            }
//        }
//        return getNextFilter().meetCriteria(stocks,vo);
    }
}
