package presentation.view.panel.iteration2.traceBackPanel;

import presentation.view.tools.MyTabelModel;
import service.TraceBackService;
import service.serviceImpl.TraceBackService.TraceBackServiceImpl;
import utilities.NumberFormat;
import vo.HoldingDetailVO;
import vo.TraceBackCriteriaVO;
import vo.TraceBackNumValVO;

import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.util.List;

/**
 * Created by 61990 on 2017/4/16.
 */
public class TraceBackDetailModel extends MyTabelModel {

    private final static int columns = 8;


    public TraceBackDetailModel(List<HoldingDetailVO> holdingDetailVOS) {
        init(holdingDetailVOS);
    }

    //初始化列表名称和数据
    private void init(List<HoldingDetailVO> holdingDetailVOS) {
        columnNames = new String[]{"周期详情", "开始日期", "结束日期", "股票持有只数", "策略收益",
                "基准收益", " 超额收益", "模拟投资"};

        data = new Object[holdingDetailVOS.size()][columns];
        for (int i = 0; i < holdingDetailVOS.size(); i++) {
            HoldingDetailVO holdingDetailVO = holdingDetailVOS.get(i);
            data[i][0] = holdingDetailVO.periodSerial;
            data[i][1] = holdingDetailVO.startDate;
            data[i][2] = holdingDetailVO.endDate;
            data[i][3] = holdingDetailVO.holdingNum;
            data[i][4] = NumberFormat.percentFormat(holdingDetailVO.strategyReturn,2);
            data[i][5] = NumberFormat.percentFormat(holdingDetailVO.baseReturn,2);
            data[i][6] = NumberFormat.percentFormat(holdingDetailVO.excessReturn,2);
            data[i][7] = NumberFormat.decimaFormat(holdingDetailVO.remainInvestment,2);
        }
    }
}