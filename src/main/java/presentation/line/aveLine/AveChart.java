package presentation.line.aveLine;

import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import utilities.exceptions.ColorNotExistException;
import vo.ChartShowCriteriaVO;

import java.util.List;

/**
 * Created by Byron Dong on 2017/3/9.
 */
public class AveChart {

    //均线数据对象
    private LineData data;

    //均线颜色工厂
    private ColorFactory factory;

    /**
     * 根据股票代号和均线类型进行初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    public AveChart(String code, List<Integer> tag) {
        data = new LineData(code,tag);
        factory = new ColorFactory();
    }

    /**
     * 根据股票代号，起始日期和均线类型进行初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    public AveChart(ChartShowCriteriaVO chartShowCriteriaVO, List<Integer> tag) {
        data = new LineData(chartShowCriteriaVO,tag);
        factory = new ColorFactory();
    }

    /**
     * 将均线数据和均线画笔添加到K线的画板中
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @param  plot K线的画版
     * @return  XYPlot 包含均线数据collection的画板
     * @throws ColorNotExistException 均线类型不存在
     */
    public XYPlot set(XYPlot plot) throws ColorNotExistException {
        plot.setDataset(1,data.getTimeSeriesCollection());
        plot.setRenderer(1,getRender());

        return plot;
    }

    /**
     * 设置均线的画笔
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @return  XYLineAndShapeRenderer 均线画笔
     * @throws ColorNotExistException 均线类型不存在
     */
    private XYLineAndShapeRenderer getRender() throws ColorNotExistException {
        XYLineAndShapeRenderer lineAndShapeRenderer = new XYLineAndShapeRenderer();
        lineAndShapeRenderer.setBaseItemLabelsVisible(true);
        List<Integer> temp = data.getDays();

        for(int i = 0; i< temp.size(); i++){
            lineAndShapeRenderer.setSeriesShapesVisible(i,false);
            if(factory.getColor(temp.get(i))==null){throw  new ColorNotExistException();}
            lineAndShapeRenderer.setSeriesPaint(i,factory.getColor(temp.get(i)));
        }

        return lineAndShapeRenderer;
    }

}
