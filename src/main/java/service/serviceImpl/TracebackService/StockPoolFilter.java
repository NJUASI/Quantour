package service.serviceImpl.TracebackService;

import service.serviceImpl.TracebackService.StockPoolFilters.BlockCriteriaFilter;
import service.serviceImpl.TracebackService.StockPoolFilters.StCriteriaFilter;
import vo.StockPoolCriteriaVO;
import vo.StockPoolVO;

import java.util.List;

/**
 * Created by harvey on 17-4-2.
 *
 * 通过过滤器模式、和责任链模式筛选股票
 */
public class StockPoolFilter {

    StockPoolFilter nextFilter;

    public StockPoolFilter() {
        setFilterChains();
    }

    /**
     * 筛选股票
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/4/2
     * @params List<StockVO> stocks 未经筛选的全部股票
     * @return 经过筛选后的目标股票池
     */
    public List<StockPoolVO> meetCriteria(List<StockPoolVO> stocks, StockPoolCriteriaVO vo){
        return getNextFilter().meetCriteria(stocks, vo);
    }

    /**
     * 获取下一个过滤器
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/4/2
     * @return 下一个过滤器
     */
    public StockPoolFilter getNextFilter(){
        return this.nextFilter;
    }

    /**
     * 设置下一个过滤器
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/4/2
     * @params StockPoolFilter nextFilter 下一个过滤器
     */
    public void setNextFilter(StockPoolFilter nextFilter){
        this.nextFilter = nextFilter; 
    }

    /**
     *  将责任链在此处设置好，以便以后增加过滤器
     */
    private void setFilterChains() {
        //新建对象
        BlockCriteriaFilter blockCriteriaFilter = new BlockCriteriaFilter();
        StCriteriaFilter stCriteriaFilter = new StCriteriaFilter();

        //设置责任链
        blockCriteriaFilter.setNextFilter(stCriteriaFilter);
    }
}
