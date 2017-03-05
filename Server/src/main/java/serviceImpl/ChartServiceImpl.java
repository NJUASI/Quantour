package serviceImpl;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import exceptions.DateShortException;
import po.StockPO;
import service.ChartService;
import vo.ChartShowCriteriaVO;
import vo.StockVO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cuihua on 2017/3/4.
 *
 * K线、均线数据获取
 */
public class ChartServiceImpl extends UnicastRemoteObject implements ChartService {

    StockDao stockDao;

    /**
     * Instantiates a new Chart service.
     * @auther Harvey
     * @updateTime 2017/3/5
     * @throws RemoteException the remote exception
     */
    protected ChartServiceImpl() throws RemoteException {
        stockDao = new StockDaoImpl();
    }

    /**
     * 获取单支股票的一段日期内的信息
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param vo 股票的选择标准
     * @return 特定股票的所有交易信息
     * @throws RemoteException RMI
     */
    @Override
    public Iterator<StockVO> getSingleStockRecords(ChartShowCriteriaVO vo) throws RemoteException {
        List<StockVO> stockVOList = new ArrayList<StockVO>();
        for (StockPO po : getStockPOs(vo)) {
            stockVOList.add(new StockVO(po));
        }
        return stockVOList.iterator();
    }

    /**
     * 获取单支股票一段日期内，用户所选天数的均线图的平均值.
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param chartShowCriteriaVO the chart show criteria vo 股票的选择标准
     * @param days 天数指标的数组
     * @return the ave data 用户所选天数的均线图的平均值
     * @throws RemoteException the remote exception
     */
    @Override
    public Iterator<Iterator<Double>> getAveData(ChartShowCriteriaVO chartShowCriteriaVO, int[] days) throws RemoteException, DateShortException {
        List<Iterator<Double>> aveDataList = new ArrayList<Iterator<Double>>();

        for(int i=0;i<days.length;i++){
            if(isDateTooShort(chartShowCriteriaVO,days[i])){
                throw new DateShortException();
            }

            aveDataList.add(calculate(chartShowCriteriaVO,days[i]));
        }
        return aveDataList.iterator();
    }

    /**
     * 获取StockPO的列表
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param vo the vo 选择条件
     * @return the list StockPO的列表
     */
    private List<StockPO> getStockPOs(ChartShowCriteriaVO vo){
        return stockDao.getStockData(vo.start,vo.end,vo.code);
    }


    /**
     * 计算以确定天数指标为标准的.
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param chartShowCriteriaVO the chart show criteria vo 选择条件
     * @param day                 the day 天数指标
     * @return the iterator
     */
    private Iterator<Double> calculate(ChartShowCriteriaVO chartShowCriteriaVO, int day) {
        List<Double> dayAveDataList = new ArrayList<Double>();
        LocalDate begin = chartShowCriteriaVO.start;
        LocalDate end = chartShowCriteriaVO.end;
        int interval = begin.until(end).getDays();
        while(!begin.plusDays(day-1).isAfter(end)){
            List<StockPO> stockPOList = new ArrayList<StockPO>();
            double sum = 0;
            for (StockPO po: stockPOList) {
                sum += po.getClose();
            }
            dayAveDataList.add(sum/interval);
        }
        return dayAveDataList.iterator();
    }

    /**
     * 判断所选日期是否小于等于均线图的时间.
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param chartShowCriteriaVO the chart show criteria vo 选择条件
     * @param day                 the day  天数指标
     * @return the boolean
     */
    private boolean isDateTooShort(ChartShowCriteriaVO chartShowCriteriaVO,int day) {
        LocalDate begin = chartShowCriteriaVO.start;
        LocalDate end = chartShowCriteriaVO.end;
        int interval = begin.until(end).getDays();
        if(interval<=day){
            return false;
        }
        return true;
    }
}
