package dao.daoImpl;

import dao.StockDao;
import dataHelper.PrivateStockDataHelper;
import dataHelper.StockDataHelper;
import dataHelper.dataHelperImpl.PrivateStockDataHelperImpl;
import dataHelper.dataHelperImpl.StockDataHelperImpl;
import po.StockPO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihua on 2017/3/4.
 */
public class StockDaoImpl implements StockDao {

    //股票信息获取的helper对象
    private StockDataHelper stockHelper;
    private PrivateStockDataHelper privateStockDataHelper;

    /**
     * @author Byron Dong
     * @updateTime 2017/3/5 构造函数，初始化成员变量stockHelper
     */
    public StockDaoImpl() {
        stockHelper = new StockDataHelperImpl();
        privateStockDataHelper = new PrivateStockDataHelperImpl();
    }

    /**
     * 获取所有股票所有数据
     *
     * @return List<StockPO> 保存的所有股票
     * @author Harvey
     * @updateTime 2017/3/5
     */
    @Override
    public List<StockPO> getAllStock(LocalDate date) {
        //TODO
        return null;
    }

    /**
     * 获取用户自选股票的数据
     *
     * @param userName 用户名称
     * @param date     股票代码
     * @return List<StockPO> 指定用户的自选股票
     * @author Harvey
     * @updateTime 2017/3/5
     */
    @Override
    public List<StockPO> getPrivateStocks(String userName, LocalDate date) {
        return null;
    }

    /**
     * 添加用户自选股
     *
     * @param userName  用户名称
     * @param stockCode 股票代码
     * @return boolean 添加是否成功
     * @author Harvey
     * @updateTime 2017/3/5
     */
    @Override
    public boolean addPrivateStock(String userName, String stockCode) {
        return privateStockDataHelper.addPrivateStock(userName,stockCode);
    }

    /**
     * 删除用户自选股
     *
     * @param userName  用户名称
     * @param stockCode 股票代码
     * @return boolean 删除是否成功
     * @author Harvey
     * @updateTime 2017/3/5
     */
    @Override
    public boolean deletePrivateStock(String userName, String stockCode) {
        return privateStockDataHelper.deletePrivateStock(userName,stockCode);
    }

    /**
     * 用户注册时，给用户新建一个对应用户名的资源文件.
     *
     * @param userName the user name 用户名称
     * @return the boolean  是否创建成功
     * @author Harvey
     * @updateTime 2017/3/6
     */
    @Override
    public boolean createPrivateDir(String userName) {
        return privateStockDataHelper.createPrivateDir(userName);
    }

    @Override
    public List<StockPO> getStockData(String s) {
        return null;
    }

    /**
     * 获取特定时间段内的指定股票的所有数据，没有返回null
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
        List<StockPO> tempStockList = null;
        tempStockList = this.stockHelper.getStock(code);

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
