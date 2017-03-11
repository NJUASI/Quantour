package vo;

import po.StockPO;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cuihua on 2017/3/11.
 */
public class StockComparisionVO {

    // 最低值
    public double min1;

    // 最高值
    public double max1;

    // 涨幅（正）／跌幅（负）
    public double increaseMargin1;

    // 每天的收盘价
    public Iterator<Double> closes1;

    // 每天的对数收益率
    public Iterator<Double> logarithmicYield1;

    // 每天的对数收益率方差
    public Iterator<Double> logarithmicYieldVariance1;

    // 最低值
    public double min2;

    // 最高值
    public double max2;

    // 涨幅（正）／跌幅（负）
    public double increaseMargin2;

    // 每天的收盘价
    public Iterator<Double> closes2;

    // 每天的对数收益率
    public Iterator<Double> logarithmicYield2;

    // 每天的对数收益率方差
    public Iterator<Double> logarithmicYieldVariance2;

    public StockComparisionVO(List<StockPO> stock1, List<StockPO> stock2) {
        initDoubles(stock1, stock2);
        closes1 = initCloses(stock1);
        closes2 = initCloses(stock2);
        logarithmicYield1 = initLogarithmicYield(stock1);
        logarithmicYield2 = initLogarithmicYield(stock2);
        logarithmicYieldVariance1 = initLogarithmicYieldVariance(stock1);
        logarithmicYieldVariance2 = initLogarithmicYieldVariance(stock2);
    }


    // 初始化基本信息中的数值
    private void initDoubles(List<StockPO> stock1, List<StockPO> stock2) {
        min1 = getMin(stock1);
        min2 = getMin(stock2);
        max1 = getMax(stock1);
        max2 = getMax(stock2);
        increaseMargin1 = initMargins(stock1);
        increaseMargin2 = initMargins(stock2);

    }

    // 初始化收盘价
    private Iterator<Double> initCloses(List<StockPO> stock) {
        List<Double> closeList = new LinkedList<>();
        for (StockPO stockPO : stock) {
            closeList.add(stockPO.getClose());
        }
        return closeList.iterator();
    }

    // 初始化对数收益率
    private Iterator<Double> initLogarithmicYield(List<StockPO> stock) {
        List<Double> logarithmicYieldLsit = new LinkedList<>();
        for (StockPO stockPO : stock) {
            logarithmicYieldLsit.add(Math.log(stockPO.getAdjClose() / stockPO.getPreAdjClose()));
        }
        return logarithmicYieldLsit.iterator();
    }

    // 初始化对数收益率方差
    // TODO
    private Iterator<Double> initLogarithmicYieldVariance(List<StockPO> stock) {
        List<Double> logarithmicYieldVarianceList = new LinkedList<>();
        for (StockPO stockPO : stock) {
            logarithmicYieldVarianceList.add(Math.log(Math.log(stockPO.getAdjClose() / stockPO.getPreAdjClose())));
        }
        return logarithmicYieldVarianceList.iterator();

    }

    // 个股对数收益率的方差
    // TODO
    private Double getVariance() {
        return (double) 0;
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
        double thisMax = stockPOS.get(0).getLow();
        for (StockPO stock : stockPOS) {
            if (stock.getLow() > thisMax) {
                thisMax = stock.getLow();
            }
        }
        return thisMax;
    }

    // TODO 此处stock为顺序，即时间小的在前面
    private Double initMargins(List<StockPO> stockPOS) {
        int length = stockPOS.size();
        double differ = stockPOS.get(length - 1).getAdjClose() - stockPOS.get(0).getAdjClose();
        return differ / stockPOS.get(0).getAdjClose();
    }

}
