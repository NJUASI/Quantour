package presentation.view.panel.iteration2.traceBackPanel;

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
        chartPanel1.setBounds(adaptScreen(200, 200, 600, 400));
        chartPanel1.setVisible(true);
        add(chartPanel1);

        chartPanel2 = new HistogramChart(absoluteReturnPeriodVO).createChart();
        chartPanel2.setBounds(adaptScreen(900, 200, 600, 400));
        chartPanel2.setVisible(true);
        add(chartPanel2);


        MyLabel label23=new MyLabel("绝对收益直方图",17) ;
        label23.setBounds(adaptScreen(250,100,150,35));
        add(label23);

        MyLabel label2=new MyLabel("正收益周期数："+returnPeriodVO.positivePeriodsNum,17) ;
        label2.setBounds(adaptScreen(450,100-40,500,35));
        add(label2);

        MyLabel label22=new MyLabel("负收益周期数:"+returnPeriodVO.negativePeriodNum,17) ;
        label22.setBounds(adaptScreen(450,100,500,35));
        add(label22);

        MyLabel label24=new MyLabel("赢率"+returnPeriodVO.winRate,17) ;
        label24.setBounds(adaptScreen(450,100+40,500,35));
        add(label24);

        MyLabel label13=new MyLabel("相对收益直方图",17) ;
        label13.setBounds(adaptScreen(950,100,150,35));
        add(label13);
        MyLabel label12=new MyLabel("正收益周期数："+absoluteReturnPeriodVO.positivePeriodsNum,17) ;
        label12.setBounds(adaptScreen(1150,100-40,600,35));
        add(label12);
        MyLabel label1=new MyLabel("负收益周期数:"+absoluteReturnPeriodVO.negativePeriodNum,17) ;
        label1.setBounds(adaptScreen(1150,100,600,35));
        add(label1);
        MyLabel label14=new MyLabel("赢率"+absoluteReturnPeriodVO.winRate,17) ;
        label14.setBounds(adaptScreen(1150,100+40,600,35));
        add(label14);



    }
}
