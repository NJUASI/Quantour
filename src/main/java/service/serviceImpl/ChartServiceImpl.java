package service.serviceImpl;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import utilities.exceptions.*;
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
    Set<String> allCodes;

    /**
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     */
    public ChartServiceImpl()  {
        stockDao = new StockDaoImpl();
        allCodes = stockDao.getAllStocksCode().keySet();
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
    public List<StockVO> getSingleStockRecords(String code) throws IOException, CodeNotFoundException {
        List<StockVO> stockVOList = new ArrayList<StockVO>();
        List<StockPO> tempList = null;

        if(codeExist(code)){
            tempList = stockDao.getStockData(code);
        }

        for (StockPO po : tempList) {
            System.out.println(po.getDate());
            stockVOList.add(new StockVO(po));
        }
        return stockVOList;
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
    public List<StockVO> getSingleStockRecords(ChartShowCriteriaVO chartShowCriteriaVO) throws IOException, DateNotWithinException, CodeNotFoundException, NoDataWithinException {
        List<StockVO> stockVOList = new ArrayList<StockVO>();
        List<StockPO> stockPOList = null;

        if(codeExist(chartShowCriteriaVO.stockCode)){
            stockPOList = getStockPOs(chartShowCriteriaVO);
        }

        for (StockPO po : stockPOList) {
            stockVOList.add(new StockVO(po));
        }
        return stockVOList;
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
    public Map<Integer, List<MovingAverageVO>> getAveData(ChartShowCriteriaVO chartShowCriteriaVO, List<Integer> days) throws IOException, DateNotWithinException, CodeNotFoundException, NoDataWithinException {
        Map<Integer, List<MovingAverageVO>> aveDataMap = new TreeMap<>();

        String code = chartShowCriteriaVO.stockCode;
        LocalDate begin = chartShowCriteriaVO.start;
        LocalDate end = chartShowCriteriaVO.end;

        List<StockPO> poList = null;
        if (codeExist(code)) {
            poList = stockDao.getStockData(code, begin, end);
        }

        // 按5日／10日等分别计算
        for (int i = 0; i < days.size(); i++) {
            LocalDate firstDay = stockDao.getFirstAndLastDay(code).get(0);
            List<StockPO> preList = stockDao.getStockData(code, firstDay, begin);

            //之前的数据够用
            if (preList.size() >= days.get(i)) {
                // preList中包含与现有poList中重复的begin的数据，股subList中需要减一
                preList = preList.subList(preList.size() - days.get(i), preList.size() - 1);
            }

            List<StockPO> tempList = preList;
            tempList.addAll(poList);

            //放入天数和其所对应的均值点的数据
            aveDataMap.put(days.get(i), calculate(tempList, days.get(i)));
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
    public Map<Integer, List<MovingAverageVO>> getAveData(String code, List<Integer> days) throws DateShortException {

        Map<Integer, List<MovingAverageVO>> aveDataMap = new TreeMap<Integer, List<MovingAverageVO>>();

        //获取单支股票全部数据
        List<StockPO> poList = null;
        try {
            poList = stockDao.getStockData(code);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < days.size(); i++) {
            try {
                aveDataMap.put(days.get(i), calculate(poList, days.get(i)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return aveDataMap;
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
    public List<StockComparisionVO> getComparision(StockComparsionCriteriaVO stockComparsionCriteriaVO) throws IOException, NoDataWithinException, DateNotWithinException, DataSourceFirstDayException {
        List<StockPO> stockPOList1 = stockDao.getStockData(stockComparsionCriteriaVO.stockCode1, stockComparsionCriteriaVO.start, stockComparsionCriteriaVO.end);
        List<StockPO> stockPOList2 = stockDao.getStockData(stockComparsionCriteriaVO.stockCode2, stockComparsionCriteriaVO.start, stockComparsionCriteriaVO.end);

        List<StockComparisionVO> result = new LinkedList<>();
        result.add(new StockComparisionVO(stockPOList1));
        result.add(new StockComparisionVO(stockPOList2));
        return result;
    }

    /**
     * 获取单支股票被剔除的日期
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     * @param stockCode 股票代码
     * @return  List<LocalDate> 被剔除的日期
     */
    @Override
    public List<LocalDate> getDateWithoutData(String stockCode) throws IOException {
        return stockDao.getDateWithoutData(stockCode);
    }

    /**
     * 获取指定时间段单支股票被剔除的日期
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     * @param chartShowCriteriaVO 股票的选择标准
     * @return List<LocalDate> 被剔除的日期
     */
    @Override
    public List<LocalDate> getDateWithoutData(ChartShowCriteriaVO chartShowCriteriaVO) throws IOException {
        return stockDao.getDateWithoutData(chartShowCriteriaVO.stockCode, chartShowCriteriaVO.start, chartShowCriteriaVO.end);
    }

    /**
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/25
     * @param stockCode 股票代码
     * @return 股票在数据源中的起讫时间
     */
    @Override
    public FirstLastDayVO getFirstAndLastDay(String stockCode) throws IOException {
        List<LocalDate> result = stockDao.getFirstAndLastDay(stockCode);
        return new FirstLastDayVO(result.get(0), result.get(1));
    }

    /**
     * 获取StockPO的列表
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param vo 选择条件
     * @return StockPO的列表
     */
    private List<StockPO> getStockPOs(ChartShowCriteriaVO vo) throws IOException, DateNotWithinException, NoDataWithinException {
        return stockDao.getStockData(vo.stockCode, vo.start, vo.end);
    }


    /**
     * 计算以确定天数指标为标准的
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param dataList 需要计算的数据集合
     * @param day 天数指标
     * @return 均线数据迭代器
     */
    private List<MovingAverageVO> calculate(List<StockPO> dataList, int day) throws IOException {
        List<MovingAverageVO> dayAveDataList = new ArrayList<MovingAverageVO>();
        for (int i = 0;i < dataList.size()-day+1;i++){
            MovingAverageVO maVO = new MovingAverageVO();
            double sum = 0;
            maVO.date = dataList.get(i+day-1).getDate();
            for (int j = i;j <= i+day-1;j++){
                sum += dataList.get(j).getClose();
            }
            maVO.average = sum/day;
            dayAveDataList.add(maVO);
        }

        return dayAveDataList;
    }

    /**
     *
     * @param code 股票代码
     * @return 股票存在
     * @throws CodeNotFoundException 数据源中未找到此股票
     */
    private boolean codeExist(String code) throws CodeNotFoundException {
        if(allCodes.contains(code)) return true;
        else throw new CodeNotFoundException();
    }
}
