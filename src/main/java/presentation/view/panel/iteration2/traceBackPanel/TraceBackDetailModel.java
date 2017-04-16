package presentation.view.panel.iteration2.traceBackPanel;

import service.TraceBackService;
import service.serviceImpl.TraceBackService.TraceBackServiceImpl;
import vo.HoldingDetailVO;
import vo.TraceBackCriteriaVO;
import vo.TraceBackNumValVO;

import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.util.List;

/**
 * Created by 61990 on 2017/4/16.
 */
public class TraceBackDetailModel  extends AbstractTableModel {

    private final static int columns = 8;

    private String[] columnNames;
    private Object[][] data;


    public TraceBackDetailModel(List<HoldingDetailVO> holdingDetailVOS ) throws IOException {
        init(holdingDetailVOS);
    }

    //初始化列表名称和数据
    private void init(List<HoldingDetailVO> holdingDetailVOS) throws IOException {
        columnNames = new String[]{"周期详情", "开始日期", "结束日期", "股票持有只数", "策略收益",
                "基准收益", " 超额收益" , "模拟投资"};

        data = new Object[10][columns];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                data[i][j] = "-";
            }
        }

//        data = new Object[holdingDetailVOS.size()][columns];
//        for (int i = 0; i < holdingDetailVOS.size(); i++) {
//            HoldingDetailVO holdingDetailVO = holdingDetailVOS.get(i);
//            data[i][0] = holdingDetailVO.periodSerial;
//            data[i][1] = holdingDetailVO.startDate;
//            data[i][2] = holdingDetailVO.endDate;
//            data[i][3] = holdingDetailVO.holdingNum;
//            data[i][4] = holdingDetailVO.strategyReturn;
//            data[i][5] = holdingDetailVO.baseReturn;
//            data[i][6] = holdingDetailVO.excessReturn;
//            data[i][7] = holdingDetailVO.remainInvestment;
//        }

//        NumberFormat ddf= NumberFormat.getNumberInstance() ;
//            ddf.setMaximumFractionDigits(4);

    }

    /**
     * Returns the number of rows in the model. A
     * <code>JTable</code> uses this method to determine how many rows it
     * should display.  This method should be quick, as it
     * is called frequently during rendering.
     *
     * @return the number of rows in the model
     * @see #getColumnCount
     */
    @Override
    public int getRowCount() {
        return data.length;
    }

    /**
     * Returns the number of columns in the model. A
     * <code>JTable</code> uses this method to determine how many columns it
     * should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param rowIndex    the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     * @return the value Object at the specified cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    /**
     * Returns a default name for the column using spreadsheet conventions:
     * A, B, C, ... Z, AA, AB, etc.  If <code>column</code> cannot be found,
     * returns an empty string.
     *
     * @param column the column being queried
     * @return a string containing the default name of <code>column</code>
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Returns false.  This is the default implementation for all cells.
     *
     * @param rowIndex    the row being queried
     * @param columnIndex the column being queried
     * @return false
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return super.isCellEditable(rowIndex, columnIndex);
    }
}