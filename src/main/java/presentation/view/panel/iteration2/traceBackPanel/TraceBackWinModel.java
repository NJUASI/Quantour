package presentation.view.panel.iteration2.traceBackPanel;

import service.TraceBackService;
import service.serviceImpl.TraceBackService.TraceBackServiceImpl;
import vo.TraceBackCriteriaVO;
import vo.TraceBackNumValVO;

import javax.swing.table.AbstractTableModel;
import java.io.IOException;

/**
 * Created by 61990 on 2017/4/16.
 */
public class TraceBackWinModel extends AbstractTableModel {

    private final static int columns = 3;

    private String[] columnNames;
    private Object[][] data;

    TraceBackNumValVO traceBackNumValVO;
    TraceBackService traceBackService;

    public TraceBackWinModel(TraceBackCriteriaVO vo) throws IOException {
        traceBackService= new TraceBackServiceImpl();
        init(vo);
    }

    //初始化列表名称和数据
    private void init(TraceBackCriteriaVO vo) throws IOException {
        columnNames = new String[]{"周期", "超额收益", "1年内胜率"};
        try {
            traceBackNumValVO=traceBackService.getNumericalVal(vo);
        }catch (Exception e){

        }

        data = new Object[13][columns];
        for (int i = 0 ; i<13;i++){
            for(int j =0 ;j<columns;j++){
                data[i][j]="-";
            }
        }
//        data[0][0]="本投资";
//        data[1][0]="";//TODO 基准收益的名字
//        data[2][0]="相对收益";
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
//        data[1][4]=traceBackNumValVO;TODO 获得基准收益率的最大回撤率
//        data[2][4]=traceBackNumValVO.sharpeRatio-traceBackNumValVO.maxRetracementRatio;
//
//        data[0][5]=traceBackNumValVO.returnVolatility;
//        data[1][5]=traceBackNumValVO.baseReturnVolatility;
//        data[2][5]=traceBackNumValVO.returnVolatility-traceBackNumValVO.baseReturnVolatility;
//
//        data[0][6]=traceBackNumValVO.beta;
////        data[1][6]=traceBackNumValVO.baseReturnVolatility;//todo  获得基准的beta
////        data[2][6]=traceBackNumValVO.beta-traceBackNumValVO.baseReturnVolatility;
//
//        data[0][7]=traceBackNumValVO.alpha;
////        data[1][7]=traceBackNumValVO.baseReturnVolatility;//todo  获得基准的alpha
////        data[2][7]=traceBackNumValVO.alpha-traceBackNumValVO.baseReturnVolatility;

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
