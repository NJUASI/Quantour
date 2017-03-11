package service.serviceImpl;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import utilities.exceptions.DateShortException;
import po.StockPO;
import service.ChartService;
import vo.ChartShowCriteriaVO;
import vo.MovingAverageVO;
import vo.StockComparisionVO;
import vo.StockVO;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Byron Dong
 * Update time 2017/3/10
 *
 * K线、均线数据获取
 *
 * TODO getAveData参数days的含义
 */
public class ChartServiceImpl implements ChartService {

    StockDao stockDao;

    /**
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     */
    public ChartServiceImpl()  {
        stockDao = new StockDaoImpl();
    }

    /**
     * 获取单支股票的所有数据
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/9
     * @param code 股票代码
     * @return 特定股票的所有交易信息
     */
    @Override
    public Iterator<StockVO> getSingleStockRecords(String code) throws IOException {
        List<StockVO> stockVOList = new ArrayList<StockVO>();
        List<StockPO> tempList = stockDao.getStockData(code);
        for (StockPO po : tempList) {
            stockVOList.add(new StockVO(po));
        }
        return stockVOList.iterator();
    }

    /**
     * 获取单支股票的一段日期内的信息
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param chartShowCriteriaVO 股票的选择标准
     * @return 特定股票的所有交易信息
     */
    @Override
    public Iterator<StockVO> getSingleStockRecords(ChartShowCriteriaVO chartShowCriteriaVO) throws IOException {
        List<StockVO> stockVOList = new ArrayList<StockVO>();
        for (StockPO po : getStockPOs(chartShowCriteriaVO)) {
            stockVOList.add(new StockVO(po));
        }
        return stockVOList.iterator();
    }

    /**
     * 获取单支股票一段日期内，用户所选天数的均线图的平均值.
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param chartShowCriteriaVO the chart show criteria vo 用户所选股票的信息
     * @param days
     * @return 用户所选天数的均线图的平均值
     */
    @Override
    public Map<Integer, Iterator<MovingAverageVO>> getAveData(ChartShowCriteriaVO chartShowCriteriaVO, List<Integer> days)throws IOException {
        Map<Integer,Iterator<MovingAverageVO>> aveDataMap = new HashMap<Integer,Iterator<MovingAverageVO>>();

        for(int i=0;i<days.size();i++){
            //放入天数和其所对应的均值点的数据
            aveDataMap.put(days.get(i), calculate(chartShowCriteriaVO,days.get(i)));
        }
        return aveDataMap;
    }

    /**
     * 获取单支股票所有数据均线图的平均值.
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/10
     * @param code  用户所选股票的代号
     * @param days
     * @return 用户所选天数的均线图的平均值
     * @throws DateShortException 类型不匹配
     */
    @Override
    public Map<Integer, Iterator<MovingAverageVO>> getAveData(String code, List<Integer> days) throws DateShortException {
        return null;
        // TODO 龚尘淼 实现该方法
    }

    @Override
    public StockComparisionVO getComparision(String stockCode1, String stockCode2) {
        return null;
    }


    /**
     * 获取StockPO的列表
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param vo 选择条件
     * @return StockPO的列表
     */
    private List<StockPO> getStockPOs(ChartShowCriteriaVO vo) throws IOException {
        return stockDao.getStockData(vo.start,vo.end,vo.code);
    }


    /**
     * 计算以确定天数指标为标准的
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param chartShowCriteriaVO 选择条件
     * @param day 天数指标
     * @return 均线数据迭代器
     */
    private Iterator<MovingAverageVO> calculate(ChartShowCriteriaVO chartShowCriteriaVO, int day) throws IOException {

        List<MovingAverageVO> dayAveDataList = new ArrayList<MovingAverageVO>();

        LocalDate begin = chartShowCriteriaVO.start;
        LocalDate end = chartShowCriteriaVO.end;
        String code = chartShowCriteriaVO.code;

        List<StockPO> dataList = stockDao.getStockData(begin,end,code);
        if(dataList.get(0).getDate().isAfter(dataList.get(1).getDate())){
            dataList = reverse(dataList);
        }

        for (int i = 0;i < dataList.size()-day+1;i++){
            MovingAverageVO maVO = new MovingAverageVO();
            double sum = 0;
            maVO.date = dataList.get(i+day-1).getDate();
            for (int j = i;j <= i+day-1;j++){
                sum += dataList.get(j).getClose();
                System.out.print(sum+" ");
            }
            System.out.println();
            maVO.average = sum/day;
            dayAveDataList.add(maVO);
        }

        return dayAveDataList.iterator();
    }


    /**
     * Reverse list.    反转list
     *
     * @param dataList the data list 需要反转的list,日期倒序
     * @return the list 日期顺序的list
     */
    private List<StockPO> reverse(List<StockPO> dataList) {
        List<StockPO> reversedList = new ArrayList<StockPO>();
        for(int i = 0;i<dataList.size();i++){
            reversedList.add(0,dataList.get(i));
        }
        return  reversedList;
    }
}
