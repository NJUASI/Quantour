package dao.daoImpl;

import dao.StockDao;
import dataHelper.StockDataHelper;
import dataHelper.dataHelperImpl.StockDataHelperImpl;
import po.StockPO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihua on 2017/3/4.
 */
public class StockDaoImpl implements StockDao {

    //股票信息获取的helper对象
    private StockDataHelper stockHelper;

    /**
     * @author Byron Dong
     * @updateTime 2017/3/5 构造函数，初始化成员变量stockHelper
     */
    public StockDaoImpl() {
        this.stockHelper = new StockDataHelperImpl();
    }

    /**
     * 获取指定股票所有数据
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param code  指定股票代码
     * @return List<StockPO> 特定时间段内的所有指定股票所有数据
     */
    @Override
    public List<StockPO> getStockData(String code) {
        return this.stockHelper.getStockData(code);
    }

    /**
     * 获取特定时间段内的所有指定股票所有数据
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param start 时间区域的小值
     * @param end 时间区域的大值
     * @param code  指定股票代码
     * @return List<StockPO> 特定时间段内的所有指定股票所有数据
     */
    @Override
    public List<StockPO> getStockData(LocalDate start, LocalDate end, String code) {
        List<StockPO> resultStockList = new ArrayList<StockPO>();
        List<StockPO> tempStockList =  this.stockHelper.getStockData(code);

        for(StockPO stockPO : tempStockList){
            if(this.isTrueDate(start,end,stockPO.getDate())){
                resultStockList.add(stockPO);
            }
        }

        return resultStockList;
    }

    /**
     * 判断时间是否在指定区域内
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param start 时间区域的小值
     * @param end 时间区域的大值
     * @param now  指定时间
     * @return List<StockPO> 特定时间段内的所有指定股票所有数据
     */
    private boolean isTrueDate(LocalDate start, LocalDate end,LocalDate now){

        if(now.isEqual(start)||now.isEqual(end)){
            return true;
        }

        if(now.isAfter(start)&&now.isBefore(end)){
            return true;
        }

        return false;
    }
}
