package service.serviceImpl;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import utilities.exceptions.DataSourceFirstDayException;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.DateShortException;
import po.StockPO;
import service.ChartService;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by cuihua
 * Update time 2017/3/12
 * 新增接口getComparision实现
 *
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
    public Iterator<StockVO> getSingleStockRecords(ChartShowCriteriaVO chartShowCriteriaVO) throws IOException, DateNotWithinException {
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
     * @param days  用户指定需要查看的几日均线图：如5、10日均线图，则传入包含5、10的list
     * @return 用户所选天数的均线图的平均值
     */
    @Override
    public Map<Integer, List<MovingAverageVO>> getAveData(ChartShowCriteriaVO chartShowCriteriaVO, List<Integer> days) throws IOException, DateNotWithinException {
        Map<Integer, List<MovingAverageVO>> aveDataMap = new HashMap<Integer, List<MovingAverageVO>>();

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
     * @param days  用户指定需要查看的几日均线图：如5、10日均线图，则传入包含5、10的list
     * @return 用户所选天数的均线图的平均值
     * @throws DateShortException 类型不匹配
     */
    @Override
    public Map<Integer, List<MovingAverageVO>> getAveData(String code, List<Integer> days) throws DateShortException, DateNotWithinException {
        List<StockPO> poList = null;
        try {
            poList = stockDao.getStockData(code);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LocalDate firstDay = poList.get(0).getDate();
        LocalDate lastDay = poList.get(poList.size()-1).getDate();
        ChartShowCriteriaVO vo = new ChartShowCriteriaVO(code,firstDay,lastDay);

        try {
            return getAveData(vo,days);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("获取单支股票的均线图逻辑出错了");
        return null;
    }

    /**
     * 获取两只股票的比较信息
     *
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/11
     * @param stockComparsionCriteriaVO 要比较的两只股票标准，包括分别的代码，要比较的起讫时间
     * @return 界面上需要的两只股票的比较信息
     */
    @Override
    public List<StockComparisionVO> getComparision(StockComparsionCriteriaVO stockComparsionCriteriaVO) throws IOException, DataSourceFirstDayException, DateNotWithinException {
        List<StockPO> stockPOList1 = stockDao.getStockData(stockComparsionCriteriaVO.stockCode1, stockComparsionCriteriaVO.start, stockComparsionCriteriaVO.end);
        List<StockPO> stockPOList2 = stockDao.getStockData(stockComparsionCriteriaVO.stockCode2, stockComparsionCriteriaVO.start, stockComparsionCriteriaVO.end);
        List<StockComparisionVO> result = new LinkedList<>();
        result.add(new StockComparisionVO(stockPOList1));
        result.add(new StockComparisionVO(stockPOList2));
        return result;
    }

    /**
     * 获取StockPO的列表
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param vo 选择条件
     * @return StockPO的列表
     */
    private List<StockPO> getStockPOs(ChartShowCriteriaVO vo) throws IOException, DateNotWithinException {
        return stockDao.getStockData(vo.stockCode, vo.start,vo.end);
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
    private List<MovingAverageVO> calculate(ChartShowCriteriaVO chartShowCriteriaVO, int day) throws IOException, DateNotWithinException {

        String code = chartShowCriteriaVO.stockCode;
        LocalDate begin = chartShowCriteriaVO.start;
        LocalDate end = chartShowCriteriaVO.end;

        List<StockPO> dataList = stockDao.getStockData(code, begin,end);

        List<MovingAverageVO> dayAveDataList = new ArrayList<MovingAverageVO>();
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

        return dayAveDataList;
    }
}
