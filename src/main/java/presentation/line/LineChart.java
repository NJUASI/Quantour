package presentation.line;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import presentation.line.aveLine.AveChart;
import presentation.line.kLine.KChart;
import utilities.exceptions.ColorNotExistException;
import vo.ChartShowCriteriaVO;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Byron Dong on 2017/3/11.
 */
public class LineChart {

    //K线图表对象
    private KChart kChart;

    //均线图表对象
    private AveChart aveChart;

    //默认的X轴起始日期（可修改）
    private LocalDate start = LocalDate.of(2005,2,1);

    //默认的X轴的结束日期（可修改）
    private LocalDate end = LocalDate.of(2014,4,29);

    //title的字体
    private Font font;

    /**
     * 根据股票代号和均线类型进行初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    public LineChart(String code,List<Integer> tag,Font font) {
        kChart = new KChart(code);
        aveChart = new AveChart(code,tag);
        this.font = font;
    }

    /**
     * 根据股票代号，起始日期和均线类型进行初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    public LineChart(ChartShowCriteriaVO chartShowCriteriaVO, List<Integer> tag,Font font) {
        kChart = new KChart(chartShowCriteriaVO);
        aveChart = new AveChart(chartShowCriteriaVO,tag);
        this.start = chartShowCriteriaVO.start;
        this.end = chartShowCriteriaVO.end;
        this.font = font;
    }

    /**
     * 获取只有K线图的画板
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @return  XYPlot 画板对象
     */
    public JFreeChart getKLineChart(){
        this.kChart.setPlot(this.start,this.end,this.getGap());
        return new JFreeChart( this.kChart.getTitle(),font,this.kChart.getKLinePlot(),true);
    }

    /**
     * 获取K线图和成交量的联合画板
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @param  gap 成交量每个柱体之间的距离
     * @param  space K线图和柱状图之间的间隔
     * @param  k K线图在整个图中所占的比例（如：2）2/3
     * @param  v 成交量在整个图中所占的比例（如：1）为1/3
     * @return  CombinedDomainXYPlot 联合画板对象
     */
    public JFreeChart getKLineAndVolumeChart(double gap,int space,int k,int v){
        this.kChart.setPlot(this.start,this.end,this.getGap());
        XYPlot plot1 = this.kChart.getKLinePlot();
        XYPlot plot2 = this.kChart.getVolumePlot(gap);

        CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(kChart.getXAxis());//建立一个恰当的联合图形区域对象，以x轴为共享轴
        combineddomainxyplot.add(plot1,k);//添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域2/3
        combineddomainxyplot.add(plot2, v);//添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域1/3
        combineddomainxyplot.setGap(space);//设置两个图形区域对象之间的间隔空间

        return new JFreeChart( this.kChart.getTitle(),font,combineddomainxyplot,true);
    }

    /**
     * 获取K线图和均线图的联合画板
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @return  CombinedDomainXYPlot 联合画板对象
     * @throws  ColorNotExistException 传入的均线类型不存在
     */
    public JFreeChart getKLineAndAverageChart() throws ColorNotExistException {
        this.kChart.setPlot(this.start,this.end,this.getGap());
        XYPlot plot = this.kChart.getKLinePlot();
        plot = this.aveChart.set(plot);

        CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(kChart.getXAxis());//建立一个恰当的联合图形区域对象，以x轴为共享轴
        combineddomainxyplot.add(plot);//添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域2/3

        return new JFreeChart( this.kChart.getTitle(),font,combineddomainxyplot,true);
    }

    /**
     * 获取K线图和成交量的联合画板
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @param  gap 成交量每个柱体之间的距离
     * @param  space K线图和柱状图之间的间隔
     * @param  k K线图在整个图中所占的比例（如：2）2/3
     * @param  v 成交量在整个图中所占的比例（如：1）为1/3
     * @return  CombinedDomainXYPlot 联合画板对象
     * @throws  ColorNotExistException 传入的均线类型不存在
     */
    public JFreeChart getAll(double gap,int space,int k,int v) throws ColorNotExistException {
        this.kChart.setPlot(this.start,this.end,this.getGap());
        XYPlot plot1 = this.kChart.getKLinePlot();
        plot1 = this.aveChart.set(plot1);
        XYPlot plot2 = this.kChart.getVolumePlot(gap);

        CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(kChart.getXAxis());//建立一个恰当的联合图形区域对象，以x轴为共享轴
        combineddomainxyplot.add(plot1,k);//添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域2/3
        combineddomainxyplot.add(plot2, v);//添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域1/3
        combineddomainxyplot.setGap(space);//设置两个图形区域对象之间的间隔空间

        return new JFreeChart( this.kChart.getTitle(),font,combineddomainxyplot,true);
    }

    /**
     * 获取合适的日期间隔
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @return  int 间隔
     */
    private int getGap(){
        int years = end.getYear()-start.getYear();
        return (years+1)*7;//以一周的时间作为基础（7）
    }
}
