package service.serviceImpl;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import utilities.StockFilter;
import utilities.exceptions.DateShortException;
import po.StockPO;
import service.ChartService;
import vo.ChartShowCriteriaVO;
import vo.MovingAverageVO;
import vo.StockVO;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by cuihua on 2017/3/4.
 *
 * K线、均线数据获取
 */
public class ChartServiceImpl implements ChartService {

    StockDao stockDao;

    /**
     * Instantiates a new Chart service.
     * @auther Harvey
     * @updateTime 2017/3/5
     * @ the remote exception
     */
    public ChartServiceImpl()  {
        stockDao = new StockDaoImpl();
    }

    /**
     * 获取单支股票的一段日期内的信息
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param vo 股票的选择标准
     * @return 特定股票的所有交易信息
     * @ RMI
     */
    @Override
    public Iterator<StockVO> getSingleStockRecords(ChartShowCriteriaVO vo)  {
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
     * @return aveDataMap 用户天数的及其对应日期内均线图的平均值的map
     * @ the remote exception
     */
    @Override
    public Map<Integer, Iterator<MovingAverageVO>> getAveData(ChartShowCriteriaVO chartShowCriteriaVO, int[] days) throws DateShortException {
        Map<Integer,Iterator<MovingAverageVO>> aveDataMap = new HashMap<Integer,Iterator<MovingAverageVO>>();

        LocalDate firstDay = stockDao.getFirstDay(chartShowCriteriaVO.code);

        for(int i=0;i<days.length;i++){
            if(isDateTooShort(chartShowCriteriaVO,days[i])){
                throw new DateShortException();
            }

            aveDataMap.put(days[i], calculate(chartShowCriteriaVO,days[i],firstDay));
        }
        return aveDataMap;
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
     * @param firstDay            数据库中存放的数据的第一天
     * @return the iterator
     */
    private Iterator<MovingAverageVO> calculate(ChartShowCriteriaVO chartShowCriteriaVO, int day, LocalDate firstDay) {

        List<MovingAverageVO> dayAveDataList = new ArrayList<MovingAverageVO>();

        LocalDate begin = chartShowCriteriaVO.start;
        LocalDate end = chartShowCriteriaVO.end;
        String code = chartShowCriteriaVO.code;

        //调用筛选器，筛选出上市第一天到开盘天的数据
        List<StockPO> preList = stockDao.getStockData(firstDay,begin,code);
        List<StockPO> allList = new ArrayList<StockPO>();

        //数据长度小于day的长度，将数据加入到选择开始的结束日期数据之前，然后计算
        if(preList.size() < day){
            allList.addAll(preList);
        }
        //否则，即以靠近选择开始日期的day长度的数据计算选择开始日期的均线
        else{
            allList.addAll(preList.subList(preList.size()-day,preList.size()));
        }
        allList.addAll(stockDao.getStockData(begin.plusDays(1),end,code));

        for (int i = day-1;i <= allList.size();i++){
            MovingAverageVO maVO = new MovingAverageVO();
            int sum = 0;
            maVO.date = allList.get(i).getDate();
            for (int j = i-day-1;j <= i;j++){
                sum += allList.get(j).getClose();
            }
            maVO.average = sum/day;
            dayAveDataList.add(maVO);
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
