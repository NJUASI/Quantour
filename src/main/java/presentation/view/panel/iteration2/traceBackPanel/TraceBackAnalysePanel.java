package presentation.view.panel.iteration2.traceBackPanel;

import org.jfree.chart.ChartPanel;
import presentation.chart.traceBack.HistogramChart;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.WindowData;
import presentation.view.tools.component.MyLabel;
import utilities.NumberFormat;
import vo.ReturnPeriodVO;

import javax.swing.*;

/**
 * Created by 61990 on 2017/4/12.
 */
public class TraceBackAnalysePanel extends TemplatePanel {

    ChartPanel chartPanel1,chartPanel2;
    MyLabel label2,label22,label24 ,label12, label1 ,label14;
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
            remove(label2);
            remove(label22);
            remove(label24);
            remove(label12);
            remove(label1);
            remove(label14);
        }
        chartPanel1 = new HistogramChart(returnPeriodVO).createChart();
        chartPanel1.setBounds(adaptScreen(200, 200, 620, 400));
        chartPanel1.setVisible(true);
        add(chartPanel1);

        chartPanel2 = new HistogramChart(absoluteReturnPeriodVO).createChart();
        chartPanel2.setBounds(adaptScreen(900, 200, 620, 400));
        chartPanel2.setVisible(true);
        add(chartPanel2);

//        MyLabel label2label22label24 label12 label1 label14
        MyLabel label23 = new MyLabel("绝对收益直方图", 22);
        label23.setBounds(adaptScreen(250, 100, 2000, 35));
        add(label23);

        label2 = new MyLabel("正收益周期数：" + returnPeriodVO.positivePeriodsNum, 17);
        label2.setBounds(adaptScreen(500, 100 - 40, 500, 35));
        add(label2);

        label22 = new MyLabel("负收益周期数：" + returnPeriodVO.negativePeriodNum, 17);
        label22.setBounds(adaptScreen(500, 100, 500, 35));
        add(label22);

        label24 = new MyLabel("赢率：" + NumberFormat.decimaFormat(returnPeriodVO.winRate, 4), 17);
        label24.setBounds(adaptScreen(500, 100 + 40, 500, 35));
        add(label24);

        MyLabel label13 = new MyLabel("相对收益直方图", 22);
        label13.setBounds(adaptScreen(950, 100, 200, 35));
        add(label13);
        label12 = new MyLabel("正收益周期数：" + absoluteReturnPeriodVO.positivePeriodsNum, 17);
        label12.setBounds(adaptScreen(1200,100-40,600,35));
        add(label12);
         label1=new MyLabel("负收益周期数："+absoluteReturnPeriodVO.negativePeriodNum,17) ;
        label1.setBounds(adaptScreen(1200,100,600,35));
        add(label1);
         label14=new MyLabel("赢率："+NumberFormat.decimaFormat(absoluteReturnPeriodVO.winRate,4),17) ;
        label14.setBounds(adaptScreen(1200,100+40,600,35));
        add(label14);
    }
}
