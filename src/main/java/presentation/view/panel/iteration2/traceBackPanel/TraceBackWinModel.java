package presentation.view.panel.iteration2.traceBackPanel;

import presentation.view.tools.MyTabelModel;
import utilities.NumberFormat;
import vo.ExcessAndWinRateDistVO;

import java.io.IOException;
import java.util.List;

/**
 * Created by 61990 on 2017/4/16.
 */
public class TraceBackWinModel extends MyTabelModel {

    private final static int columns = 3;


    public TraceBackWinModel(List<ExcessAndWinRateDistVO> vo) throws IOException {
        init(vo);
    }

    //初始化列表名称和数据
    private void init(List<ExcessAndWinRateDistVO> vo) throws IOException {
        columnNames = new String[]{"周期", "超额收益", "1年内胜率"};

        data = new Object[vo.size()][columns];
        for (int i = 0 ; i<vo.size();i++){
            data[i][0]=vo.get(i).relativeCycle;
            data[i][1]= NumberFormat.percentFormat(vo.get(i).excessRate,2);
            data[i][2]=NumberFormat.percentFormat(vo.get(i).winRate,2);
        }
    }

}
