package vo;

import po.StockPO;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by cuihua on 2017/3/11.
 * Last updated by cuihua
 * Update time 2017/3/13
 * 修改实现getMax
 */
public class StockComparisionVO {

    /**
     * 股票A
     */
    // 最低值
    public double min1;

    // 最高值
    public double max1;

    // 涨幅（正）／跌幅（负）
    public double increaseMargin1;

    // 每天的收盘价
    public Map<LocalDate, Double> closes1;

    // 每天的对数收益率
    public Map<LocalDate, Double> logarithmicYield1;

    // 对数收益率方差
    public Double logarithmicYieldVariance1;


    /**
     * 股票B
     */
    // 最低值
    public double min2;

    // 最高值
    public double max2;

    // 涨幅（正）／跌幅（负）
    public double increaseMargin2;

    // 每天的收盘价
    public Map<LocalDate, Double> closes2;

    // 每天的对数收益率
    public Map<LocalDate, Double> logarithmicYield2;

    // 对数收益率方差
    public Double logarithmicYieldVariance2;

    public StockComparisionVO(List<StockPO> stock1, List<StockPO> stock2) {
        initDoubles(stock1, stock2);

        closes1 = initCloses(stock1);
        closes2 = initCloses(stock2);

        List<Double> logarithmicYieldList1 = initLogarithmicYieldNum(stock1);
        List<Double> logarithmicYieldList2 = initLogarithmicYieldNum(stock2);

        logarithmicYield1 = initLogarithmicYieldMap(stock1, logarithmicYieldList1);
        logarithmicYield1 = initLogarithmicYieldMap(stock2, logarithmicYieldList2);
        logarithmicYieldVariance1 = initLogarithmicYieldVariance(logarithmicYieldList1);
        logarithmicYieldVariance2 = initLogarithmicYieldVariance(logarithmicYieldList2);
    }


    // 初始化基本信息中的数值
    private void initDoubles(List<StockPO> stock1, List<StockPO> stock2) {
        min1 = getMin(stock1);
        min2 = getMin(stock2);
        max1 = getMax(stock1);
        max2 = getMax(stock2);
        increaseMargin1 = getIncreaseMargin(stock1);
        increaseMargin2 = getIncreaseMargin(stock2);

    }

    // 初始化收盘价
    private Map<LocalDate, Double> initCloses(List<StockPO> stock) {
        Map<LocalDate, Double> result = new HashMap<>();
        for (StockPO stockPO : stock) {
            result.put(stockPO.getDate(), stockPO.getClose());
        }
        return result;
    }

    // 初始化对数收益率列表List
    private List<Double> initLogarithmicYieldNum(List<StockPO> stock) {
        List<Double> logarithmicYieldList = new LinkedList<>();
        for (StockPO stockPO : stock) {
            if (stockPO.getPreAdjClose() == -1) {
                // 此条数据为数据库中最早的一条，故不能得其对数收益率
                continue;
            }
            logarithmicYieldList.add(Math.log(stockPO.getAdjClose() / stockPO.getPreAdjClose()));
        }
        return logarithmicYieldList;
    }

    // 初始化对数收益率列表Map
    private Map<LocalDate, Double> initLogarithmicYieldMap(List<StockPO> stock, List<Double> logarithmicYieldNum) {
        Map<LocalDate, Double> result = new HashMap<>();
        for (int i = 0; i < logarithmicYieldNum.size(); i++) {
            result.put(stock.get(i).getDate(), logarithmicYieldNum.get(i));
        }
        return result;
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

    // 注意：此处stock为时间顺序，即时间小的在前面
    private Double getIncreaseMargin(List<StockPO> stockPOS) {
        int length = stockPOS.size();
        double differ = stockPOS.get(length - 1).getAdjClose() - stockPOS.get(0).getAdjClose();
        return differ / stockPOS.get(0).getAdjClose();
    }

    private Double getAve(List<Double> dataList) {
        double sum = 0;
        for (double data : dataList) {
            sum += data;
        }
        return sum / dataList.size();
    }
}
