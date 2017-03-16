package presentation.line.kLine;

import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import utilities.exceptions.DateNotWithinException;
import vo.ChartShowCriteriaVO;
import vo.StockVO;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Byron Dong on 2017/3/8.
 */
public class LineData {

    //K线的数据最高价
    private double high = -1;

    //K线的数据最低价
    private double low = Double.MAX_VALUE;

    //数据的最高交易量
    private double highVolume = -1;

    //数据的最低交易量
    private double lowVolume = Double.MAX_VALUE;

    //根据要求取出的数据
    private List<StockVO> data;

    //逻辑层对象
    private ChartService service;

    //K线数据集合
    public OHLCSeriesCollection seriesCollection;

    //交易量数据集合
    public TimeSeriesCollection timeSeriesCollection;

    /**
     * 根据股票代号进行初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    public LineData(String code) {
        data = new ArrayList<StockVO>();
        this.service = new ChartServiceImpl();
        this.readData(code);
    }

    /**
     * 根据股票代号和起始日期进行初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    public LineData(ChartShowCriteriaVO chartShowCriteriaVO) {
        data = new ArrayList<StockVO>();
        this.service = new ChartServiceImpl();
        this.readData(chartShowCriteriaVO);
    }

    /**
     * 从数据中筛选出K线最低价和最高价，并完成collection的初始化，添加数据
     *
     * @return K线最高价
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    public void setData() {
        this.initCollection();
        this.seriesCollection.addSeries(this.getKData());
        this.timeSeriesCollection.addSeries(this.getVolData());
        this.setHighAndLow();
        this.setHighAndLowVolume();
    }

    /**
     * 获取K线最高价
     *
     * @return K线最高价
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    public double getHigh() {
        return high;
    }

    /**
     * 获取K线最低价
     *
     * @return K线最低价
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    public double getLow() {
        return low;
    }

    /**
     * 获取最高交易量
     *
     * @return 最高交易量
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    public double getHighVolume() {
        return highVolume;
    }

    /**
     * 获取最低交易量
     *
     * @return 最低交易量
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    public double getLowVolume() {
        return lowVolume;
    }

    public String getStockName(){
        return this.data.get(0).name;
    }


    /**
     * 完成collection初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    private void initCollection() {
        this.seriesCollection = new OHLCSeriesCollection();
        this.timeSeriesCollection = new TimeSeriesCollection();
    }

    /**
     * 获取K线数据的最高值和最低值(历史)
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    private void setHighAndLow() {
        int seriesCount = seriesCollection.getSeriesCount();//一共有多少个序列，目前为一个
        for (int i = 0; i < seriesCount; i++) {
            int itemCount = seriesCollection.getItemCount(i);//每一个序列有多少个数据项
            for (int j = 0; j < itemCount; j++) {
                if (high < seriesCollection.getHighValue(i, j)) {//取第i个序列中的第j个数据项的最大值
                    high = seriesCollection.getHighValue(i, j);
                }
                if (low > seriesCollection.getLowValue(i, j)&&seriesCollection.getLowValue(i, j)>0) {//取第i个序列中的第j个数据项的最小值
//                    System.out.println("ok");
                    low = seriesCollection.getLowValue(i, j);
                }
            }

        }
    }

    /**
     * 获取最高值和最低值(成交量)
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    private void setHighAndLowVolume() {
        int seriesCount2 = timeSeriesCollection.getSeriesCount();//一共有多少个序列，目前为一个
        for (int i = 0; i < seriesCount2; i++) {
            int itemCount = timeSeriesCollection.getItemCount(i);//每一个序列有多少个数据项
            for (int j = 0; j < itemCount; j++) {
                if (highVolume < timeSeriesCollection.getYValue(i, j)) {//取第i个序列中的第j个数据项的值
                    highVolume = timeSeriesCollection.getYValue(i, j);
                }
                if (lowVolume > timeSeriesCollection.getYValue(i, j)) {//取第i个序列中的第j个数据项的值
                    lowVolume = timeSeriesCollection.getYValue(i, j);
                }
            }

        }
    }

    /**
     * 获取K线数据集合
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @Return OHLCSeries 数据集合
     */
    private OHLCSeries getKData() {
        OHLCSeries series = new OHLCSeries("");//高开低收数据序列，股票K线图的四个数据，依次是开，高，低，收
        //series.add(new Day(28, 9, 2007), 9.2, 9.58, 9.16, 9.34);

        for (StockVO stockVO : this.data) {
            series.add(new Day(stockVO.date.getDayOfMonth(), stockVO.date.getMonth().getValue(), stockVO.date.getYear())
                    , stockVO.open, stockVO.high, stockVO.low, stockVO.close);
        }

        return series;
    }

    /**
     * 获取成交量数据集合
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @Return TimeSeries 成交量数据集合
     */
    private TimeSeries getVolData() {
        TimeSeries series = new TimeSeries("");//对应时间成交量数据
        //series.add(new Day(28, 9, 2007), 260659400/100);

        for (StockVO stockVO : this.data) {
            series.add(new Day(stockVO.date.getDayOfMonth(), stockVO.date.getMonth().getValue(), stockVO.date.getYear())
                    , Double.parseDouble(stockVO.volume) / 100);
        }
        return series;
    }

    /**
     * 根据股票代号获取股票数据
     *
     * @param code 股票代号
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    private void readData(String code) {
        Iterator<StockVO> tempData = null;
        try {
            tempData = this.service.getSingleStockRecords(code);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (tempData.hasNext()) {
            this.data.add(tempData.next());
        }

    }

    /**
     * 根据股票代号和日期获取股票数据
     *
     * @param chartShowCriteriaVO 股票信息载体
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    private void readData(ChartShowCriteriaVO chartShowCriteriaVO) {
        Iterator<StockVO> tempData = null;
        try {
            tempData = this.service.getSingleStockRecords(chartShowCriteriaVO);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DateNotWithinException e) {
            // TODO 高源：超出数据库内时间区间范围
            e.printStackTrace();
        }
        while (tempData.hasNext()) {
            this.data.add(tempData.next());
        }
    }
}
