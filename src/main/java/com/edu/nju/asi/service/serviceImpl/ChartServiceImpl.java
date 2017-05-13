package com.edu.nju.asi.service.serviceImpl;

import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.dao.daoImpl.StockDaoImpl;
import com.edu.nju.asi.infoCarrier.*;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.StockCodeHelper;
import com.edu.nju.asi.utilities.enums.MovingAverageType;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("ChartService")
public class ChartServiceImpl implements ChartService {

    @Autowired
    StockDao stockDao;

    Set<String> allCodes;

    public ChartServiceImpl() {
//        stockDao = new StockDaoImpl();
//        allCodes = stockDao.getAllStocksCode().keySet();
    }


    /**
     * 因为对springMVC未找到自动注入时初始化的方法，故在使用成员变量前先初始化
     */
    private void init(){
        if (allCodes == null) allCodes = stockDao.getAllStocksCode().keySet();
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
    public List<Stock> getSingleStockRecords(String code) throws IOException, CodeNotFoundException {
        List<Stock> stockList = null;
        if(codeExist(code)){
            stockList = stockDao.getStockData(code);
        }
        return stockList;
    }

    /**
     * 获取单支股票的一段日期内的信息
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param chartShowCriteria 股票的选择标准
     * @return 特定股票的所有交易信息
     */
    @Override
    public List<Stock> getSingleStockRecords(ChartShowCriteria chartShowCriteria) throws IOException, DateNotWithinException, CodeNotFoundException, NoDataWithinException {
        List<Stock> stockList = null;
        if(codeExist(chartShowCriteria.stockCode)){
            stockList = getStocks(chartShowCriteria);
        }
        return stockList;
    }

    /**
     * 获取单支股票一段日期内，用户所选天数的均线图的平均值.
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param chartShowCriteria 用户所选股票的信息
     * @param MATypes  用户指定需要查看的几日均线图：如5、10日均线图，则传入包含5、10的list
     * @return 用户所选天数的均线图的平均值
     */
    @Override
    public Map<MovingAverageType, List<MovingAverage>> getAveData(ChartShowCriteria chartShowCriteria, List<MovingAverageType> MATypes) throws IOException, DateNotWithinException, CodeNotFoundException, NoDataWithinException, NoMatchEnumException {
        Map<MovingAverageType, List<MovingAverage>> aveDataMap = new TreeMap<>();

        String code = chartShowCriteria.stockCode;
        LocalDate begin = chartShowCriteria.start;
        LocalDate end = chartShowCriteria.end;

        List<Stock> stockList = null;
        if (codeExist(code)) {
            stockList = stockDao.getStockData(code, begin, end);
        }

        // 按5日／10日等分别计算
        for (int i = 0; i < MATypes.size(); i++) {
            LocalDate firstDay = stockDao.getFirstAndLastDay(code).get(0);
            List<Stock> preList = stockDao.getStockData(code, firstDay, begin);

            //之前的数据够用
            int thisMARepre = MATypes.get(i).getRepre();
            if (preList.size() >= thisMARepre) {
                // preList中包含与现有stockList中重复的begin的数据，故subList中需要减一
                preList = preList.subList(preList.size() - thisMARepre, preList.size() - 1);
            }

            List<Stock> tempList = preList;
            tempList.addAll(stockList);

            //放入天数和其所对应的均值点的数据
            aveDataMap.put(MATypes.get(i), calculate(tempList, thisMARepre));
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
     * @param MATypes  用户指定需要查看的几日均线图：如5、10日均线图，则传入包含5、10的list
     * @return 用户所选天数的均线图的平均值
     * @throws DateShortException 类型不匹配
     */
    @Override
    public Map<MovingAverageType, List<MovingAverage>> getAveData(String code, List<MovingAverageType> MATypes) throws DateShortException {

        Map<MovingAverageType, List<MovingAverage>> aveDataMap = new TreeMap<>();

        //获取单支股票全部数据
        List<Stock> stockList = null;
        try {
            stockList = stockDao.getStockData(code);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < MATypes.size(); i++) {
            try {
                aveDataMap.put(MATypes.get(i), calculate(stockList, MATypes.get(i).getRepre()));
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
     * @param stockComparsionCriteria 要比较的两只股票标准，包括分别的代码，要比较的起讫时间
     * @return 界面上需要的两只股票的比较信息
     */
    @Override
    public List<StockComparision> getComparision(StockComparsionCriteria stockComparsionCriteria) throws IOException, NoDataWithinException, DateNotWithinException, DataSourceFirstDayException {
        List<Stock> stockList1 = stockDao.getStockData(stockComparsionCriteria.stockCode1, stockComparsionCriteria.start, stockComparsionCriteria.end);
        List<Stock> stockList2 = stockDao.getStockData(stockComparsionCriteria.stockCode2, stockComparsionCriteria.start, stockComparsionCriteria.end);

        List<StockComparision> result = new LinkedList<>();
        result.add(new StockComparision(stockList1));
        result.add(new StockComparision(stockList2));
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
     * @param chartShowCriteria 股票的选择标准
     * @return List<LocalDate> 被剔除的日期
     */
    @Override
    public List<LocalDate> getDateWithoutData(ChartShowCriteria chartShowCriteria) throws IOException {
        return stockDao.getDateWithoutData(chartShowCriteria.stockCode, chartShowCriteria.start, chartShowCriteria.end);
    }

    /**
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/25
     * @param stockCode 股票代码
     * @return 股票在数据源中的起讫时间
     */
    @Override
    public FirstAndLastDay getFirstAndLastDay(String stockCode) throws IOException {
        List<LocalDate> result = stockDao.getFirstAndLastDay(stockCode);
        return new FirstAndLastDay(result.get(0), result.get(1));
    }

    /**
     * 获取StockPO的列表
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param criteria 选择条件
     * @return StockPO的列表
     */
    private List<Stock> getStocks(ChartShowCriteria criteria) throws IOException, DateNotWithinException, NoDataWithinException {
        return stockDao.getStockData(criteria.stockCode, criteria.start, criteria.end);
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
    private List<MovingAverage> calculate(List<Stock> dataList, int day) throws IOException {
        List<MovingAverage> dayAveDataList = new ArrayList<MovingAverage>();
        for (int i = 0;i < dataList.size()-day+1;i++){
            MovingAverage maVO = new MovingAverage();
            double sum = 0;
            maVO.date = dataList.get(i+day-1).getStockID().getDate();
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
        init();
        code = StockCodeHelper.simplify(code);

        if(allCodes.contains(code)) return true;
        else throw new CodeNotFoundException();
    }
}
