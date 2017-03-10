package presentation.line.aveLine;

import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import utilities.exceptions.DateShortException;
import vo.ChartShowCriteriaVO;
import vo.MovingAverageVO;

import java.util.*;

/**
 * Created by Byron Dong on 2017/3/9.
 */
public class LineData {

    private ChartService service;
    private int []tag;
    private List<TimeSeries> data;

    public LineData(String code,int []tag) {
        service = new ChartServiceImpl();
        data = new ArrayList<>();
        this.tag = tag;
        this.readData(code);
    }

    public LineData(ChartShowCriteriaVO chartShowCriteriaVO,int []tag) {
        service = new ChartServiceImpl();
        data = new ArrayList<>();
        this.tag = tag;
        this.readData(chartShowCriteriaVO);
    }

    public int[] getTag() {
        return tag;
    }

    public TimeSeriesCollection getTimeSeriesCollection(){
        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();

        for (TimeSeries series : data) {
            timeSeriesCollection.addSeries(series);
        }

        return timeSeriesCollection;
    }

    private void readData(String code){
        try {
            Map<Integer, Iterator<MovingAverageVO>> tempMap = this.service.getAveData(code,this.tag);

            for(int i:tag){
                TimeSeries series = new TimeSeries(String.valueOf(i)+"天均线");
                Iterator<MovingAverageVO> tempIterator = tempMap.get(i);
                while(tempIterator.hasNext()){
                    MovingAverageVO vo = tempIterator.next();
                    series.add(new Day(vo.date.getDayOfMonth(),vo.date.getMonth().getValue(),vo.date.getYear()),vo.average);
                }
                data.add(series);
            }

        } catch (DateShortException e) {
            e.printStackTrace();
        }
    }

    private void readData(ChartShowCriteriaVO chartShowCriteriaVO){
        try {
            Map<Integer, Iterator<MovingAverageVO>> tempMap = this.service.getAveData(chartShowCriteriaVO,this.tag);

            for(int i:tag){
                TimeSeries series = new TimeSeries(String.valueOf(i)+"天均线");
                Iterator<MovingAverageVO> tempIterator = tempMap.get(i);
                while(tempIterator.hasNext()){
                    MovingAverageVO vo = tempIterator.next();
                    series.add(new Day(vo.date.getDayOfMonth(),vo.date.getMonth().getValue(),vo.date.getYear()),vo.average);
                }
                data.add(i,series);
            }

        } catch (DateShortException e) {
            e.printStackTrace();
        }

    }

}
