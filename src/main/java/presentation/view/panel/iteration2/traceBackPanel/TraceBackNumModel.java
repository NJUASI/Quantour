package presentation.view.panel.iteration2.traceBackPanel;


import presentation.view.panel.iteration2.ChooseStrategyPanel;
import presentation.view.tools.MyTabelModel;
import utilities.NumberFormat;
import vo.TraceBackNumValVO;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.io.IOException;

/**
 * Created by 61990 on 2017/4/15.
 */
public class TraceBackNumModel extends MyTabelModel {

    private final static int columns = 8;

    public TraceBackNumModel(TraceBackNumValVO vo) throws IOException {
        init(vo);
    }

    //初始化列表名称和数据
    private void init(TraceBackNumValVO traceBackNumValVO) throws IOException {
        columnNames = new String[]{"投资组合", "总收益", "年化收益","夏普比率","最大回撤率", "收益波动率",
                "贝塔率", " 阿尔法比率"};

        data = new Object[3][columns];
//        for (int i = 0 ; i<3;i++){
//            for(int j =0 ;j<columns;j++){
//                data[i][j]="-";
//            }
//        }
        data[0][0]="本策略";
        data[1][0]= ChooseStrategyPanel.getInstance().getBasicStock();//TODO 基准收益的名字
        data[2][0]="相对收益";
//
        //总收益
        data[0][1]= NumberFormat.percentFormat(traceBackNumValVO.sumRate,2);
        data[1][1]= NumberFormat.percentFormat(traceBackNumValVO.baseSumRate,2);
        data[2][1]=NumberFormat.percentFormat(traceBackNumValVO.sumRate-traceBackNumValVO.baseSumRate,2);

        //年化收益
        data[0][2]=NumberFormat.percentFormat(traceBackNumValVO.annualizedRateOfReturn,2);
        data[1][2]=NumberFormat.percentFormat(traceBackNumValVO.baseAnnualizedRateOfReturn,2);
        data[2][2]=NumberFormat.percentFormat(traceBackNumValVO.annualizedRateOfReturn-traceBackNumValVO.baseAnnualizedRateOfReturn,2);

        //夏普比率
        data[0][3]=NumberFormat.decimaFormat(traceBackNumValVO.sharpeRatio,4);
        data[1][3]=NumberFormat.decimaFormat(traceBackNumValVO.baseSharpeRatio,4);
        data[2][3]=NumberFormat.decimaFormat(traceBackNumValVO.sharpeRatio-traceBackNumValVO.baseSharpeRatio,4);

//        最大回测（好像还没有）
        data[0][4]=traceBackNumValVO.maxRetracementRatio;
//        data[1][4]=traceBackNumValVO.;//TODO 获得基准收益率的最大回撤率
//        data[2][4]=traceBackNumValVO.sharpeRatio-traceBackNumValVO.maxRetracementRatio;

//        data[0][4]="-";
        data[1][4]="-";//TODO 获得基准收益率的最大回撤率
        data[2][4]="-";

        //年化波动率
        data[0][5]=NumberFormat.percentFormat(traceBackNumValVO.returnVolatility,2);
        data[1][5]=NumberFormat.percentFormat(traceBackNumValVO.baseReturnVolatility,2);
        data[2][5]=NumberFormat.percentFormat(traceBackNumValVO.returnVolatility-traceBackNumValVO.baseReturnVolatility,2);

        //贝塔
        data[0][6]=NumberFormat.decimaFormat(traceBackNumValVO.beta,4);
        data[1][6]="-";//todo  获得基准的beta 基准没有beta
        data[2][6]=NumberFormat.decimaFormat(traceBackNumValVO.beta-traceBackNumValVO.baseReturnVolatility,4);

        //阿尔法
        data[0][7]=NumberFormat.decimaFormat(traceBackNumValVO.alpha,4);
        data[1][7]="-";//todo  获得基准的alpha 基准没有阿尔法
        data[2][7]=NumberFormat.decimaFormat(traceBackNumValVO.alpha-traceBackNumValVO.baseReturnVolatility,4);

    }


}