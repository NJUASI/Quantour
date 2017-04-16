package presentation.view.panel.iteration2;

import org.jfree.chart.ChartPanel;
import presentation.chart.traceBack.HistogramChart;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.WindowData;
import presentation.view.tools.component.MyLabel;
import vo.ReturnPeriodVO;

import javax.swing.*;

/**
 * Created by 61990 on 2017/4/12.
 */
public class TraceBackAnalysePanel extends TemplatePanel {

    ChartPanel chartPanel1,chartPanel2;
    public TraceBackAnalysePanel(){

        setBackground(WindowData.getInstance().getColor());
        setLayout(null);
        chartPanel1=null;
        chartPanel2=null;


    }

    public void createChart(ReturnPeriodVO returnPeriodVO, ReturnPeriodVO absoluteReturnPeriodVO){
        if(chartPanel1!=null){
            remove(chartPanel1);
            remove(chartPanel2);
        }
        chartPanel1 = new HistogramChart(returnPeriodVO).createChart();
        chartPanel1.setBounds(adaptScreen(200, 180, 600, 400));
        chartPanel1.setVisible(true);
        add(chartPanel1);

        chartPanel2 = new HistogramChart(absoluteReturnPeriodVO).createChart();
        chartPanel2.setBounds(adaptScreen(900, 180, 600, 400));
        chartPanel2.setVisible(true);
        add(chartPanel2);

//        MyLabel label2=new MyLabel("相对收益分布图  正收益周期数："+returnPeriodVO.positivePeriodsNum+"，负收益周期数:"+returnPeriodVO.negativePeriodNum+"，赢率"+returnPeriodVO.winRate) ;
//        label2.setBounds(adaptScreen(250,100,600,35));
//        add(label2);
//
//        MyLabel label1=new MyLabel("绝对收益分布图  正收益周期数："+absoluteReturnPeriodVO.positivePeriodsNum+"，负收益周期数:"+absoluteReturnPeriodVO.negativePeriodNum+"，赢率"+absoluteReturnPeriodVO.winRate) ;
//        label1.setBounds(adaptScreen(950,100,600,35));
//        add(label1);


    }
}
