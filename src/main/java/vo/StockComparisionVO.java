package vo;

import po.StockPO;
import utilities.enums.Market;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by cuihua on 2017/3/11.
 * Last updated by cuihua
 * Update time 2017/3/15
 * 修改此数据结构
 */
public class StockComparisionVO {

    // 股票名称
    public String name;

    // 股票代码
    public String code;

    // 最低值
    public double min;

    // 最高值
    public double max;

    // 涨幅（正）／跌幅（负）
    public Map<LocalDate, Double> increaseMargin;

    // 每天的收盘价
    public Map<LocalDate, Double> closes;

    // 每天的对数收益率
    public Map<LocalDate, Double> logarithmicYield;

    // 对数收益率方差
    public double logarithmicYieldVariance;


    public StockComparisionVO(List<StockPO> stock) {
        initBasics(stock);

        increaseMargin = getIncreaseMargin(stock);
        closes = initCloses(stock);

        initLogarithmicYieldInfo(stock);
    }


    // 初始化基本信息中的数值
    private void initBasics(List<StockPO> stock) {
        name = stock.get(0).getName();
        code = stock.get(0).getCode();
        min = getMin(stock);
        max = getMax(stock);
    }

    // 初始化涨幅／跌幅
    // 注意：此处stock为时间顺序，即时间小的在前面
    private Map<LocalDate, Double> getIncreaseMargin(List<StockPO> stocks) {
        Map<LocalDate, Double> result = new TreeMap<>();
        for (StockPO stockPO : stocks) {
            if (stockPO.getPreAdjClose() == -1) {
                // 此条数据为数据库中最早的一条，故不能得其涨幅／跌幅
                continue;
            }
            result.put(stockPO.getDate(), stockPO.getAdjClose() / stockPO.getPreAdjClose() - 1);
        }
        return result;
    }

    // 初始化收盘价
    private Map<LocalDate, Double> initCloses(List<StockPO> stock) {
        Map<LocalDate, Double> result = new TreeMap<>();
        for (StockPO stockPO : stock) {
            result.put(stockPO.getDate(), stockPO.getClose());
        }
        return result;
    }

    // 初始化对数收益率和对数收益率方差
    private void initLogarithmicYieldInfo(List<StockPO> stock) {
        logarithmicYield = new TreeMap<>();

        List<Double> logarithmicYieldList = new LinkedList<>();
        for (StockPO stockPO : stock) {
            if (stockPO.getPreAdjClose() == -1) {
                // 此条数据为数据库中最早的一条，故不能得其对数收益率
                continue;
            }
            double temp = Math.log(stockPO.getAdjClose() / stockPO.getPreAdjClose());
            logarithmicYieldList.add(temp);
            logarithmicYield.put(stockPO.getDate(), temp);
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


    private Double getMin(List<StockPO> stockPOS) {
        double thisMin = stockPOS.get(0).getLow();
        for (StockPO stock : stockPOS) {
            if (stock.getLow() < thisMin) {
                thisMin = stock.getLow();
            }
        }
        return thisMin;
    }

    private Double getMax(List<StockPO> stockPOS) {
        double thisMax = stockPOS.get(0).getHigh();
        for (StockPO stock : stockPOS) {
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
