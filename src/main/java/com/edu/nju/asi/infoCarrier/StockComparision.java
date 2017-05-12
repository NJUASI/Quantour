package com.edu.nju.asi.infoCarrier;

import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by cuihua on 2017/3/11.
 * Last updated by cuihua
 * Update time 2017/3/15
 * 修改此数据结构
 */
public class StockComparision {

    // 股票名称
    public String name;

    // 股票代码
    public String code;

    // 最低值
    public double min;

    // 最高值
    public double max;

    // 涨幅（正）／跌幅（负），若为数据源中第一日则不显示
    public double increaseMargin;

    // 每天的收盘价
    public Map<LocalDate, Double> closes;

    // 每天的对数收益率
    public Map<LocalDate, Double> logarithmicYield;

    // 对数收益率方差
    public double logarithmicYieldVariance;


    public StockComparision(List<Stock> stocks) throws DataSourceFirstDayException {
        initBasics(stocks);

        closes = initCloses(stocks);

        initLogarithmicYieldInfo(stocks);
    }


    // 初始化基本信息中的数值
    private void initBasics(List<Stock> stocks) throws DataSourceFirstDayException {
        name = stocks.get(0).getName();
        code = stocks.get(0).getStockID().getCode();
        min = getMin(stocks);
        max = getMax(stocks);
        increaseMargin = getIncreaseMargin(stocks);
    }

    // 初始化涨幅／跌幅
    // 注意：此处stock为时间顺序，即时间小的在前面
    private double getIncreaseMargin(List<Stock> stocks) throws DataSourceFirstDayException {
//        Map<LocalDate, Double> result = new TreeMap<>();
//        for (StockPO stockPO : stocks) {
//            if (stockPO.getPreAdjClose() == -1) {
//                // 此条数据为数据库中最早的一条，故不能得其涨幅／跌幅
//                continue;
//            }
//            result.put(stockPO.getDate(), stockPO.getAdjClose() / stockPO.getPreAdjClose() - 1);
//        }
//        return result;


        double denominator = stocks.get(0).getPreAdjClose();
        if (denominator == -1) {
            throw new DataSourceFirstDayException();
        }
        double temp = stocks.get(stocks.size() - 1).getAdjClose() / denominator - 1;
        return temp;
    }

    // 初始化收盘价
    private Map<LocalDate, Double> initCloses(List<Stock> stocks) {
        Map<LocalDate, Double> result = new TreeMap<>();
        for (Stock tempStock : stocks) {
            result.put(tempStock.getStockID().getDate(), tempStock.getClose());
        }
        return result;
    }

    // 初始化对数收益率和对数收益率方差
    private void initLogarithmicYieldInfo(List<Stock> stocks) {
        logarithmicYield = new TreeMap<>();

        List<Double> logarithmicYieldList = new LinkedList<>();
        for (Stock stock : stocks) {
            if (stock.getPreAdjClose() == -1) {
                // 此条数据为数据库中最早的一条，故不能得其对数收益率
                continue;
            }
            double temp = Math.log(stock.getAdjClose() / stock.getPreAdjClose());
            logarithmicYieldList.add(temp);
            logarithmicYield.put(stock.getStockID().getDate(), temp);
        }

        logarithmicYieldVariance = initLogarithmicYieldVariance(logarithmicYieldList);

    }

    // 初始化对数收益率方差
    private Double initLogarithmicYieldVariance(List<Double> logarithmicYields) {
        if (logarithmicYields.size() == 1) {
            return (double) 0;
        } else {
            double ave = getAve(logarithmicYields);
            double temp = 0;
            for (double data : logarithmicYields) {
                temp += (data - ave) * (data - ave);
            }
            return temp / (logarithmicYields.size() - 1);
        }
    }


    private Double getMin(List<Stock> stocks) {
        double thisMin = stocks.get(0).getLow();
        for (Stock stock : stocks) {
            if (stock.getLow() < thisMin) {
                thisMin = stock.getLow();
            }
        }
        return thisMin;
    }

    private Double getMax(List<Stock> stocks) {
        double thisMax = stocks.get(0).getHigh();
        for (Stock stock : stocks) {
            if (stock.getHigh() > thisMax) {
                thisMax = stock.getHigh();
            }
        }
        return thisMax;
    }

    private Double getAve(List<Double> dataList) {
        double sum = 0;
        for (double data : dataList) {
            sum += data;
        }
        return sum / dataList.size();
    }

}
