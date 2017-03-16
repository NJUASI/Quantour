package presentation.controller;

import presentation.view.chart.CompareChartPanel;
import presentation.view.panel.ComparePanel;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import utilities.exceptions.DataSourceFirstDayException;
import vo.StockComparisionVO;
import vo.StockComparsionCriteriaVO;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by 61990 on 2017/3/14.
 */
public class CompareController {
    /**
     * The constant CompareController.
     */
    private static CompareController compareController = new CompareController();
    /**
     * The Compare panel.
     */
    private ComparePanel comparePanel;


    /**
     * Instantiates a new Thermometer controller.
     */
    private CompareController(){
        comparePanel = ComparePanel.getInstance();
    }

    /**
     * Get instance Compare controller.
     *
     * @return the Compare controller
     */
    public static CompareController getInstance(){
        return compareController;
    }



    /**
     * 搜索操作,获取指定日期的市场温度计的数据
     */
    public void compare() {
        compareSpecial(comparePanel.getNum1(),comparePanel.getNum2(),comparePanel.getStartDate(),comparePanel.getEndDate());
    }


    /**
     * 通过code和前后日期寻找股票的特定时期 寻找股票的全部信息并绘图
     *
     * @param code1 股票号1
     * @param code2 股票号2
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     * @author 61990
     * @updateTime 2017/3/9
     */
    boolean first=true;
    private void compareSpecial(String code1,String code2, LocalDate startDate, LocalDate endDate){

        ChartService chartService = new ChartServiceImpl();
        //比较图表面板
        CompareChartPanel compareChartPanel = null;

        try {

            if(first){
                first=false;
                List<StockComparisionVO> vo=chartService.getComparision(new StockComparsionCriteriaVO(code1, code2, startDate, endDate));
                compareChartPanel=new CompareChartPanel(vo);
                compareChartPanel.setBounds(250,150,1500,800);
                comparePanel.add(compareChartPanel);
                compareChartPanel.repaint();
            }else {
                comparePanel.remove(compareChartPanel);
                List<StockComparisionVO> vo=chartService.getComparision(new StockComparsionCriteriaVO(code1, code2, startDate, endDate));
                compareChartPanel=new CompareChartPanel(vo);
                compareChartPanel.setBounds(250,150,1500,800);
                comparePanel.add(compareChartPanel);
                compareChartPanel.repaint();
            }
//            chartPanel.repaint();
        }catch (DataSourceFirstDayException e ) {
            // TODO 高源：首日无法计算涨跌幅
        } catch (Exception e){

        }
    }
}
