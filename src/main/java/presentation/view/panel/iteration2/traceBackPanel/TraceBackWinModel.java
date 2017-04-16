package presentation.view.panel.iteration2.traceBackPanel;

import service.TraceBackService;
import service.serviceImpl.TraceBackService.TraceBackServiceImpl;
import vo.ExcessAndWinRateDistVO;
import vo.TraceBackCriteriaVO;
import vo.TraceBackNumValVO;

import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.util.List;

/**
 * Created by 61990 on 2017/4/16.
 */
public class TraceBackWinModel extends AbstractTableModel {

    private final static int columns = 3;

    private String[] columnNames;
    private Object[][] data;

    TraceBackNumValVO traceBackNumValVO;
    TraceBackService traceBackService;

    public TraceBackWinModel(List<ExcessAndWinRateDistVO> vo) throws IOException {
        traceBackService= new TraceBackServiceImpl();
        init(vo);
    }

    //初始化列表名称和数据
    private void init(List<ExcessAndWinRateDistVO> vo) throws IOException {
        columnNames = new String[]{"周期", "超额收益", "1年内胜率"};


//        data = new Object[vo.size()][columns];
//        for (int i = 0 ; i<vo.size();i++){
//            data[i][0]=vo.get(i).relativeCycle;
//            data[i][1]=vo.get(i).excessRate;
//            data[i][2]=vo.get(i).winRate;
//        }

        data = new Object[13][columns];
        for (int i = 0 ; i<13;i++){
            for(int j =0 ;j<columns;j++){
                data[i][j]="-";
            }
        }

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
