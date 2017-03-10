package presentation.line.kLine;

import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import vo.ChartShowCriteriaVO;
import vo.StockVO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Byron Dong on 2017/3/8.
 */
public class LineData {

    private double high = -1;
    private double low = Double.MAX_VALUE;
    private double highVolume = -1;
    private double lowVolume = Double.MAX_VALUE;
    private List<StockVO> data;
    private ChartService service;

    public OHLCSeriesCollection seriesCollection;
    public TimeSeriesCollection timeSeriesCollection;

    public LineData(String code) {
        data = new ArrayList<StockVO>();
        this.service = new ChartServiceImpl();
        this.readData(code);
    }

    public LineData(ChartShowCriteriaVO chartShowCriteriaVO) {
        data = new ArrayList<StockVO>();
        this.service = new ChartServiceImpl();
        this.readData(chartShowCriteriaVO);
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getHighVolume() {
        return highVolume;
    }

    public double getLowVolume() {
        return lowVolume;
    }

    public void setData() {
        this.initCollection();
        this.seriesCollection.addSeries(this.getKData());
        this.timeSeriesCollection.addSeries(this.getVolData());
        this.setHighAndLow();
        this.setHighAndLowVolume();
    }

    private void initCollection() {
        this.seriesCollection = new OHLCSeriesCollection();
        this.timeSeriesCollection = new TimeSeriesCollection();
    }

    //获取K线数据的最高值和最低值(历史)
    private void setHighAndLow() {
        int seriesCount = seriesCollection.getSeriesCount();//一共有多少个序列，目前为一个
        for (int i = 0; i < seriesCount; i++) {
            int itemCount = seriesCollection.getItemCount(i);//每一个序列有多少个数据项
            for (int j = 0; j < itemCount; j++) {
                if (high < seriesCollection.getHighValue(i, j)) {//取第i个序列中的第j个数据项的最大值
                    high = seriesCollection.getHighValue(i, j);
                }
                if (low > seriesCollection.getLowValue(i, j)) {//取第i个序列中的第j个数据项的最小值
//                    System.out.println("ok");
                    low = seriesCollection.getLowValue(i, j);
                }
            }

        }
    }

    //获取最高值和最低值(成交量)
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

    private OHLCSeries getKData() {
        OHLCSeries series = new OHLCSeries("");//高开低收数据序列，股票K线图的四个数据，依次是开，高，低，收
        //series.add(new Day(28, 9, 2007), 9.2, 9.58, 9.16, 9.34);

        for (StockVO stockVO : this.data) {
//           System.out.println(stockVO.volume);
            series.add(new Day(stockVO.date.getDayOfMonth(), stockVO.date.getMonth().getValue(), stockVO.date.getYear())
                    , stockVO.open, stockVO.high, stockVO.low, stockVO.close);
        }

        return series;
    }


    private TimeSeries getVolData() {
        TimeSeries series = new TimeSeries("");//对应时间成交量数据
        //series.add(new Day(28, 9, 2007), 260659400/100);

        for (StockVO stockVO : this.data) {
            series.add(new Day(stockVO.date.getDayOfMonth(), stockVO.date.getMonth().getValue(), stockVO.date.getYear())
                    , Double.parseDouble(stockVO.volume) / 100);
        }
        return series;
    }

    private void readData(String code) {
        Iterator<StockVO> tempData = this.service.getSingleStockRecords(code);
        while (tempData.hasNext()) {
            this.data.add(tempData.next());
        }
    }

    private void readData(ChartShowCriteriaVO chartShowCriteriaVO) {
        Iterator<StockVO> tempData = this.service.getSingleStockRecords(chartShowCriteriaVO);
        while (tempData.hasNext()) {
            this.data.add(tempData.next());
        }
    }
}
