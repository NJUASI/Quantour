package presentation.view.panel.iteration2;


import presentation.view.tools.MyTabelModel;
import vo.TraceBackNumValVO;


import javax.swing.table.TableModel;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;

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
        for (int i = 0 ; i<3;i++){
            for(int j =0 ;j<columns;j++){
                data[i][j]="-";
            }
        }
        data[0][0]="本投资";
//        data[1][0]="";//TODO 基准收益的名字
        data[2][0]="相对收益";
//
//        data[0][1]=traceBackNumValVO.sumRate;
//        data[1][1]=traceBackNumValVO.baseSumRate;
//        data[2][1]=traceBackNumValVO.sumRate-traceBackNumValVO.baseSumRate;
//
//        data[0][2]=traceBackNumValVO.annualizedRateOfReturn;
//        data[1][2]=traceBackNumValVO.baseAnnualizedRateOfReturn;
//        data[2][2]=traceBackNumValVO.annualizedRateOfReturn-traceBackNumValVO.baseAnnualizedRateOfReturn;
//
//        data[0][3]=traceBackNumValVO.sharpeRatio;
//        data[1][3]=traceBackNumValVO.baseSharpeRatio;
//        data[2][3]=traceBackNumValVO.sharpeRatio-traceBackNumValVO.baseSharpeRatio;
//
//        data[0][4]=traceBackNumValVO.maxRetracementRatio;
//        data[1][4]=traceBackNumValVO;//TODO 获得基准收益率的最大回撤率
//        data[2][4]=traceBackNumValVO.sharpeRatio-traceBackNumValVO.maxRetracementRatio;
//
//        data[0][5]=traceBackNumValVO.returnVolatility;
//        data[1][5]=traceBackNumValVO.baseReturnVolatility;
//        data[2][5]=traceBackNumValVO.returnVolatility-traceBackNumValVO.baseReturnVolatility;

//        data[0][6]=traceBackNumValVO.beta;
//        data[1][6]=traceBackNumValVO.baseReturnVolatility;//todo  获得基准的beta
//        data[2][6]=traceBackNumValVO.beta-traceBackNumValVO.baseReturnVolatility;
//
//        data[0][7]=traceBackNumValVO.alpha;
////        data[1][7]=traceBackNumValVO.baseReturnVolatility;//todo  获得基准的alpha
////        data[2][7]=traceBackNumValVO.alpha-traceBackNumValVO.baseReturnVolatility;

//        NumberFormat ddf= NumberFormat.getNumberInstance() ;
//            ddf.setMaximumFractionDigits(4);

    }


}