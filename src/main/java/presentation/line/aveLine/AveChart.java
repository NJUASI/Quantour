package presentation.line.aveLine;

import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import vo.ChartShowCriteriaVO;

import java.util.List;

/**
 * Created by Byron Dong on 2017/3/9.
 */
public class AveChart {

    private LineData data;
    private ColorFactory factory;

    public AveChart(String code, List<Integer> tag) {
        data = new LineData(code,tag);
        factory = new ColorFactory();
    }

    public AveChart(ChartShowCriteriaVO chartShowCriteriaVO, List<Integer> tag) {
        data = new LineData(chartShowCriteriaVO,tag);
        factory = new ColorFactory();
    }

    public XYPlot set(XYPlot plot){
        plot.setDataset(1,data.getTimeSeriesCollection());
        plot.setRenderer(1,getRender());

        return plot;
    }

    private XYLineAndShapeRenderer getRender(){
        XYLineAndShapeRenderer lineAndShapeRenderer = new XYLineAndShapeRenderer();
        lineAndShapeRenderer.setBaseItemLabelsVisible(true);
        List<Integer> temp = data.getDays();

        for(int i = 0; i< temp.size(); i++){
            lineAndShapeRenderer.setSeriesShapesVisible(i,false);
            lineAndShapeRenderer.setSeriesPaint(i,factory.getColor(temp.get(i)));
        }

        return lineAndShapeRenderer;
    }

}
